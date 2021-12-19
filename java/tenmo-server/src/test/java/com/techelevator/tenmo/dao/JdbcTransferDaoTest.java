package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcTransferDaoTest extends BaseDaoTests{

    private static final Transfer TRANSFER_1= new Transfer(3001, "bob", new BigDecimal("50.00"));
    private static final Transfer TRANSFER_2= new Transfer(3002, "sus", new BigDecimal("200.00"));
    private static final Transfer TRANSFER_3= new Transfer(3003, "mary", new BigDecimal("500.00"));


    private JdbcTransferDao sut;
    private Transfer testAccount;

    @Before
    public void setup() {
        sut = new JdbcTransferDao(dataSource);
            }


    @Test
    public void createTransfer() {
    }

    @Test
    public void getTransferHistory() {
        List<Transfer> transferList = sut.getTransferHistory(1001);
        Assert.assertEquals( 0,transferList.size() );
    }

    @Test
    public void getTransferDetail() {

    }

    private  void assertTransferMatch(Transfer expected, Transfer actual){
        Assert.assertEquals(expected.getTransferId(), actual.getTransferId());
        Assert.assertEquals(expected.getUsername(), actual.getUsername());
        Assert.assertEquals(expected.getAmount(), actual.getAmount());
    }
}