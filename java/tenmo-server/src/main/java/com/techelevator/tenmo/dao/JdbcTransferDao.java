package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.security.Principal;

public class JdbcTransferDao implements TransferDao{
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Override
    public TransferDTO createTransfer(TransferDTO transferDTO) {

         String sql = "SELECT balance FROM accounts WHERE user_id = ?";

         SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferDTO.getUserFromId());

         BigDecimal balance = (results.getBigDecimal("balance"));

       // if(transferDTO.getAmount().compareTo(JdbcAccountDao.getAccount(transferDTO.getUserFromId()))){
        //if(transferDTO.getAmount().compareTo(results){

        //accountDao.getAccount(transferDTO.getUserFromId())= balance;

        /*if(transferDTO.getAmount().compareTo(balance)==0){
            System.out.println("We got 00");
        } else {
            System.out.println("not 0");
        }*/

        // Example of how we can use the compareTo method for BigDecimal
        int res = transferDTO.getAmount().compareTo(balance);

        if (res == 0) {
            System.out.println("Both values are equal.");
        } else if (res == 1) {
            System.out.println("First value is greater.");
        } else if (res == -1) {
            System.out.println("Second value is greater.");
        }

        return transferDTO;
}


/* JdbcTransferDao method
1. check to see if userFrom has enough money
2.if so withdraw from userFrom account
3.add to userTo amount
4.write to transfer table in the db --create
 */

       private TransferDTO mapRowToTransfer(SqlRowSet rs){
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setUserFromId(rs.getLong("userFromId"));
        transferDTO.setUserToId(rs.getLong("userToId"));
        transferDTO.setAmount(rs.getBigDecimal("amount"));
        return transferDTO;
    }

}





