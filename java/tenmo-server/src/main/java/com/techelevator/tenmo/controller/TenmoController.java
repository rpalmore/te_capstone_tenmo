package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransferDao transferDao;

@RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Account getAccount(Principal principal){
    long id = userDao.findIdByUsername(principal.getName());
    return accountDao.getAccount(id);
  }

  @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUserIdAndName(){
    // TO DO: limit results to users not currently logged in.
      // (See notes from 12/17 lecture)
    return userDao.findIdAndName();
  }


  // this gets the transfer amount and userTo from the client so we can handle on the server
  @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public TransferDTO transfer(@RequestBody TransferDTO transferDTO){
      System.out.println("In transfer " + transferDTO);
    return null;
  }

  // trying to add a put method to update all balances if conditions are OK to proceed
    @RequestMapping(path = "/transfer", method = RequestMethod.PUT)
    public TransferDTO processTransfer(@RequestBody TransferDTO transferDTO){
        System.out.println(transferDTO);
        return transferDao.createTransfer(transferDTO);
    }

    //view Transfer History
    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory(){
         return transferDao.getTransferHistory();
    }


}
