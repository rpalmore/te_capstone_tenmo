package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TransferDTO {

        private Long userFromId;
        private long userToId;
        private BigDecimal amount;


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
