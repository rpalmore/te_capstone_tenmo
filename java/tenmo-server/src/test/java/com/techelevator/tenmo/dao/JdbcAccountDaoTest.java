package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.Assert.*;
public class JdbcAccountDaoTest extends BaseDaoTests {


    private static final Account ACCOUNT_1= new Account(2001, 1001, new BigDecimal("50.00"));
    private static final Account ACCOUNT_2= new Account(2002, 1002, new BigDecimal("100.00"));

    private JdbcAccountDao sut;
    private Account testAccount;


    @Before
    public void setup(){
        sut = new JdbcAccountDao(dataSource);
        testAccount = new Account(2003, 1003, new BigDecimal("200.00"));
    }

    @Test
    public void getAccount_returns_correct_Account_for_id() {
        Account actual = sut.getAccount(1001);//call the dao method getPark for the id of 1001
        assertAccountsMatch(ACCOUNT_1, actual);


      // actual = sut.getAccount(1001);
      // assertAccountsMatch(ACCOUNT_1, actual);

       //Account account = new Account(2001, 1001, new BigDecimal("50.00"));
       //Account expected = testAccount.getAccountId();
      // Account expected = AccountDao.getAccount(1001);
     //  Assert.assertEquals(expected, account);
    }

    @Test
    public void getAccount_returns_null_when_id_not_found() {
        Account actual = sut.getAccount(99);//call the dao method getPark for the id of 1001
        Assert.assertNull(actual);

    }

    private void assertAccountsMatch(Account expected, Account actual){
        Assert.assertEquals(expected.getAccountId(), actual.getAccountId());
        Assert.assertEquals(expected.getUserId(), actual.getUserId());
        Assert.assertEquals(expected.getBalance(), actual.getBalance());
    }
}
