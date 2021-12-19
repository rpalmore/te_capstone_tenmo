package com.techelevator.tenmo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransferDTO {

    private long userFromId;
    private long userToId;
    private BigDecimal amount;


    public TransferDTO(long userFromId, long userToId, BigDecimal amount) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.amount = amount;
    }

    public TransferDTO() {
    }

    public long getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(long userFromId) {
        this.userFromId = userFromId;
    }

    public long getUserToId() {
        return userToId;
    }

    public void setUserToId(long userToId) {
        this.userToId = userToId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferDTO{" +
                "userFromId=" + userFromId +
                ", userToId=" + userToId +
                ", amount=" + amount +
                '}';
    }
}
