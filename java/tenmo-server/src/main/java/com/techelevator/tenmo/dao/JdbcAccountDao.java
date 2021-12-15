package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements  AccountDao{

    private JdbcTemplate jdbcTemplate;
    //JdbcUserDao jdbcUserDao = new JdbcUserDao();

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Account getAccount(int id) {

        String sql = "SELECT account_id, user_id, balance FROM accounts WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        Account account = new Account();
        while(results.next()){
            account = mapRowToAccount(results);
        }
                return account;
    }

    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setAccountId(rs.getInt("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }


}

