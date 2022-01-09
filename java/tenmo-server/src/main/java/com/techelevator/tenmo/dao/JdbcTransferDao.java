package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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

    @Autowired
    private UserDao userDao;

    @Override
    public TransferDTO createTransfer(TransferDTO transferDTO) {

         String sql = "SELECT balance FROM accounts WHERE user_id = ?";

         SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferDTO.getUserFromId());
         results.next();
         BigDecimal balance = (results.getBigDecimal("balance"));

        int res = transferDTO.getAmount().compareTo(balance);

        boolean enoughFunds = true;

        // To make the transfer, we need to have 0 or -1;
        if (res == 0) {
            enoughFunds = true;
        } else if (res == 1) {
            enoughFunds = false;
            System.out.println("User does not have enough funds for this transfer.");

        } else if (res == -1) {
            enoughFunds = true;
        }

        if (enoughFunds) {
            // Withdraw funds from user account
            BigDecimal newBalanceWithdraw = balance.subtract(transferDTO.getAmount());
            String sql2 = "UPDATE accounts SET balance = ? " +
                    "WHERE user_id = ?";
            jdbcTemplate.update(sql2, newBalanceWithdraw, transferDTO.getUserFromId());

            // Add funds to recipient's account
            BigDecimal updatedBalanceDeposit = balance.add(transferDTO.getAmount());
            String sql3 = "UPDATE accounts SET balance = ? " +
                    "WHERE user_id = ?";
            jdbcTemplate.update(sql3, updatedBalanceDeposit, transferDTO.getUserToId());

            // Write transfer details to transfers table
            String sql4 = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                    "VALUES(2, 2, (SELECT account_id FROM accounts WHERE user_id = ?), " +
                                 "(SELECT account_id FROM accounts WHERE user_id = ?), ?)";
            jdbcTemplate.update(sql4, transferDTO.getUserFromId(), transferDTO.getUserToId(),
                    transferDTO.getAmount());
        }

        return transferDTO;
}

    public List<Transfer> getTransferHistory(long id){
        List<Transfer> transfers = new ArrayList<>();

        String sql =
                "SELECT transfer_id, us.username as userFrom, users.username as userTo, amount " +
                        "FROM transfers JOIN accounts ON transfers.account_from = accounts.account_id " +
                        "JOIN accounts ac ON transfers.account_to = ac.account_id " +
                        "JOIN users ON ac.user_id = users.user_id " +
                        "JOIN users us ON accounts.user_id = us.user_id " +
                        "WHERE users.user_id = ? OR us.user_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, id);

        while(results.next()){
            Transfer transfer = mapRowToTransferHistoryList(results);
            transfers.add(transfer);
        }
        return transfers;
    }
    
    public TransferDetail getTransferDetail(int id){

        String sql =
                "SELECT transfer_id, us.username as userFrom, users.username as userTo, transfer_type_id, transfer_status_id, amount " +
                "FROM transfers JOIN accounts ON transfers.account_from = accounts.account_id " +
                "JOIN accounts ac ON transfers.account_to = ac.account_id " +
                "JOIN users ON ac.user_id = users.user_id " +
                "JOIN users us ON accounts.user_id = us.user_id WHERE transfer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        TransferDetail transferDetail = new TransferDetail();
        while(results.next()){
            transferDetail = mapRowToTransferDetail(results);
        }
    return transferDetail;
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
        transfer.setUserFrom(rs.getString("userFrom"));
        transfer.setUserTo(rs.getString("userTo"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
        }

        private TransferDetail mapRowToTransferDetail(SqlRowSet rs){
        TransferDetail transferDetail = new TransferDetail();
        transferDetail.setTransferId(rs.getInt("transfer_id"));
        transferDetail.setUserFrom(rs.getString("userFrom"));
        transferDetail.setUserTo(rs.getString("userTo"));
        transferDetail.setTransferType(rs.getInt("transfer_type_id"));
        transferDetail.setTransferStatus(rs.getInt("transfer_status_id"));
        transferDetail.setAmount(rs.getBigDecimal("amount"));
        return transferDetail;
        }

}


