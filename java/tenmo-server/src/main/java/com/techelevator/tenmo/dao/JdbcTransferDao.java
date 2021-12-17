package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // When dealing with models, we need a default constructor.
    // Here, we don't need a default constructor.

/* @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;*/

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


       private TransferDTO mapRowToTransfer(SqlRowSet rs){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setUserFromId(rs.getLong("userFromId"));
        transferDTO.setUserToId(rs.getLong("userToId"));
        transferDTO.setAmount(rs.getBigDecimal("amount"));
        return transferDTO;
    }

}


/* JdbcTransferDao method
1. check to see if userFrom has enough money
2.if so withdraw from userFrom account
3.add to userTo amount
4.write to transfer table in the db --create
 */


