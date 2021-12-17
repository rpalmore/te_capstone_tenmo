package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

        private long userFromId;
        private long userToId;
        private BigDecimal amount;

        // adding constructor for test method -- but can remove if this breaks code
        public TransferDTO(long userFromId, long userToId, BigDecimal amount) {
                this.userFromId = userFromId;
                this.userToId = userToId;
                this.amount = amount;
        }

        // adding empty constructor for testing -- will remove if it breaks code
        public TransferDTO() {
        }

        public Long getUserFromId() {
                return userFromId;
        }

        public void setUserFromId(Long userFromId) {
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
