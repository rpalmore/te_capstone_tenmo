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
    // TO DO: limit results to users not currently logged in.
      // (See notes from 12/17 lecture)
      long id = userDao.findIdByUsername(principal.getName());
      return userDao.findIdAndName(id);
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
    public List<Transfer> getTransferHistory(Principal principal){
         long id = userDao.findIdByUsername(principal.getName());
         return transferDao.getTransferHistory(id);
    }

    //view Transfer Detail
    @RequestMapping(path= "/transfer/{id}", method= RequestMethod.GET)
    public TransferDetail getTransferDetailById(@PathVariable int id){
        return transferDao.getTransferDetail(id);
    }



    /*  @GetMapping("/pokemonDetail/{id}")
    public PokemonDetail getPokemonDetailById(@PathVariable int id){
        return service.getPokemonDetailById(id);
    }
    * */

}
