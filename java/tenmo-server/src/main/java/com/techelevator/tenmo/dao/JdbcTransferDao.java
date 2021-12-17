package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // When dealing with models, we need a default constructor.
    // Here, we don't need a default constructor.

/* @Autowired
    private AccountDao accountDao;*/

    @Autowired
    private UserDao userDao;

    /*@Autowired
    private TransferDao transferDao;*/

    @Override
    public TransferDTO createTransfer(TransferDTO transferDTO) {
      //public boolean createTransfer(TransferDTO transferDTO){

         String sql = "SELECT balance FROM accounts WHERE user_id = ?";

         SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferDTO.getUserFromId());
         results.next();
         BigDecimal balance = (results.getBigDecimal("balance"));

        // Setting up compareTo method for BigDecimal:
        int res = transferDTO.getAmount().compareTo(balance);

        boolean enoughFunds = true;

        // To make the transfer, we need to have 0 or -1;
        if (res == 0) {
            //System.out.println("Both values are equal.");
            enoughFunds = true;
        } else if (res == 1) {
            enoughFunds = false;
            // we will throw exception here
            //System.out.println("First value is greater.");
        } else if (res == -1) {
            enoughFunds = true;
            //System.out.println("Second value is greater.");
        }

        if (enoughFunds) {
            // withdraw amount from account of current user:
            BigDecimal newBalanceWithdraw = balance.subtract(transferDTO.getAmount());
            String sql2 = "UPDATE accounts SET balance = ? " +
                    "WHERE user_id = ?";
            jdbcTemplate.update(sql2, newBalanceWithdraw, transferDTO.getUserFromId());

            // add transfer amount to toUser account:
            BigDecimal updatedBalanceDeposit = balance.add(transferDTO.getAmount());
            String sql3 = "UPDATE accounts SET balance = ? " +
                    "WHERE user_id = ?";
            jdbcTemplate.update(sql3, updatedBalanceDeposit, transferDTO.getUserToId());

            // write the transfer details to the transfers table:
            String sql4 = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "VALUES(2, 2, (SELECT account_id FROM accounts WHERE user_id = ?), " +
                                 "(SELECT account_id FROM accounts WHERE user_id = ?), ?)";
            jdbcTemplate.update(sql4, transferDTO.getUserFromId(), transferDTO.getUserToId(),
                    transferDTO.getAmount());
        }

        return transferDTO;
}

        /*public List<Transfer> getTransferHistory(){
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, username AS From, amount FROM transfers " +
                "JOIN accounts ON transfers.account_from = accounts.account_id " +
                "JOIN users ON accounts.user_id = users.user_id " +
                "WHERE account_to = (SELECT account_id FROM accounts WHERE user_id = 1001)";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            Transfer transfer = mapRowToTransferHistoryList(results);
            transfers.add(transfer);
        }
                return transfers;
    }*/


    public List<Transfer> getTransferHistory(){
        List<Transfer> transfers = new ArrayList<>();
        //FROM SOMEONE TO BOB
        String sql = "SELECT transfer_id, username, amount FROM transfers " +
                "JOIN accounts ON transfers.account_from = accounts.account_id " +
                "JOIN users ON accounts.user_id = users.user_id " +
                "WHERE account_to = (SELECT account_id FROM accounts WHERE user_id = 1001)";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql);

        while(results.next()){
            Transfer transfer = mapRowToTransferHistoryList(results);
            transfers.add(transfer);
        }
        //FROM BOB to SOMEONE ELSE TO DO
        String sql2 = "SELECT transfer_id, username, amount FROM transfers " +
                "JOIN accounts ON transfers.account_from = accounts.account_id " +
                "JOIN users ON accounts.user_id = users.user_id " +
                "WHERE account_to = (SELECT account_id FROM accounts WHERE user_id = 1001)";
        SqlRowSet results2= jdbcTemplate.queryForRowSet(sql2);

        while(results2.next()){
            Transfer transfer = mapRowToTransferHistoryList(results2);
            transfers.add(transfer);
        }
        return transfers;
    }




       private TransferDTO mapRowToTransfer(SqlRowSet rs){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setUserFromId(rs.getLong("userFromId"));
        transferDTO.setUserToId(rs.getLong("userToId"));
        transferDTO.setAmount(rs.getBigDecimal("amount"));
        return transferDTO;
    }

        private Transfer mapRowToTransferHistoryList(SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setUsername(rs.getString("username"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
        }

}


/* JdbcTransferDao method
1. check to see if userFrom has enough money
2.if so withdraw from userFrom account
3.add to userTo amount
4.write to transfer table in the db --create
 */


