package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class JdbcTransferDaoTest {

    private static final Transfer TRANSFER_1= new Transfer(3001, "bob", new BigDecimal("50.00"));
    private static final Transfer TRANSFER_2= new Transfer(3002, "sus", new BigDecimal("200.00"));
    private static final Transfer TRANSFER_3= new Transfer(3003, "mary", new BigDecimal("500.00"));




    @Test
    public void createTransfer() {
    }

    @Test
    public void getTransferHistory() {
    }

    @Test
    public void getTransferDetail() {

    }
}