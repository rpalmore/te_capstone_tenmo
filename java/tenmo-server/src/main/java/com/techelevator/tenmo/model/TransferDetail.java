package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDetail {

    private int transferId;
    private int transferType;
    private int transferStatus;
    private String userFrom;
    //private int accountFrom;
    private int accountTo;
    //private String userTo;
    private BigDecimal amount;

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
    }

    public int getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }

    /*public int getAccountFrom() {
        return accountFrom;
    }*/

    /*public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }*/

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
