package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.*;
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
    public List<User> getUserIdAndName(Principal principal){
      long id = userDao.findIdByUsername(principal.getName());
      return userDao.findIdAndName(id);
  }


  @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public TransferDTO transfer(@RequestBody TransferDTO transferDTO){
      System.out.println("In transfer " + transferDTO);
    return null;
  }


    @RequestMapping(path = "/transfer", method = RequestMethod.PUT)
    public TransferDTO processTransfer(@RequestBody TransferDTO transferDTO){
        System.out.println(transferDTO);
        return transferDao.createTransfer(transferDTO);
    }


    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List<Transfer> getTransferHistory(Principal principal){
         long id = userDao.findIdByUsername(principal.getName());
         return transferDao.getTransferHistory(id);
    }


    @RequestMapping(path= "/transfer/{id}", method= RequestMethod.GET)
    public TransferDetail getTransferDetailById(@PathVariable int id){
        return transferDao.getTransferDetail(id);
    }

}
