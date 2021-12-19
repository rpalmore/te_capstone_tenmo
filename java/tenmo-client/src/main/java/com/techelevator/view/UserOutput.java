package com.techelevator.view;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.TenmoService;

import java.security.Principal;
import java.sql.SQLOutput;


public class UserOutput {

    public static void displayAccountBalance(Account account){

        System.out.println("Current balance: $" + account.getBalance());
    }


    public static void displayAllUsers(User[] user) {
        for (User eachUser : user) {
            System.out.println(eachUser.toString());
        }
            System.out.println();
            System.out.println("Enter ID of the user you wish to send money to. ");
        }


    public static void displayTransferHistory(Transfer[] transfers){
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("  *    Transfer History    *  ");
        System.out.println("------------------------------");
        for (Transfer eachTransfer : transfers) {
            System.out.println(eachTransfer.toString());
        }
        System.out.println();
        System.out.println("Please enter transfer ID to view details (0 to cancel):");
    }

    public static void displayTransferDetails(TransferDetail transferDetail){
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("  *    Transfer Details    *  ");
        System.out.println("------------------------------");
        System.out.println(transferDetail.toString());
    }
}


