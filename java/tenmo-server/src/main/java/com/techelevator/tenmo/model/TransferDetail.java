package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDetail {

    private int transferId;
    private int transferType;
    private int transferStatus;
    private String userFrom;
    private String userTo;
    private BigDecimal amount;

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

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return  "ID: " + transferId + "\n" +
                "Type: Send (" + transferType + ")\n" +
                "Status: Approved (" + transferStatus + ")\n" +
                "From: " + userFrom + "\n" +
                "To account: " + userTo + "\n" +
                "Amount: $" + amount;
    }
}
