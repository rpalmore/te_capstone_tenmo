package com.techelevator.view;

import com.techelevator.tenmo.model.Account;

public class UserOutput {
    public static void displayAccountBalance(Account account){

        System.out.println("Current balance : $ " + account.getBalance());
    }
}
