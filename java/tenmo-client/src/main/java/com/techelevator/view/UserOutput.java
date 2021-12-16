package com.techelevator.view;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;

public class UserOutput {
    public static void displayAccountBalance(Account account){

        System.out.println("Current balance : $ " + account.getBalance());
    }

    public static void displayAllUsers(User[] user){
            for (User eachuser : user) {
                System.out.println(eachuser.toString());
            }
        System.out.println();
        System.out.println("Enter ID of the user you are sending money to: ");
        }
}

    /*public void printReviews(Review[] reviews) {
        for (Review review : reviews) {
            System.out.println(review.toString());
        }
    }*/

