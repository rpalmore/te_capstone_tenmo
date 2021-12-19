package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int transferId;
    private String username;
    private BigDecimal amount;

    public Transfer(int transferId, String username, BigDecimal amount) {
        this.transferId = transferId;
        this.username = username;
        this.amount = amount;
    }

    public Transfer() {
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    // Changes to this toString() method output do not display on client side.
    public String toString() {
        return "Transfer{" +
                "transferId!=" + transferId +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                '}';
    }
}
