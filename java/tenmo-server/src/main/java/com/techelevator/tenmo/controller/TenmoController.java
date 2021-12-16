package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.services.TenmoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

@RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Account getAccount(Principal principal){
    int id = userDao.findIdByUsername(principal.getName());
    return accountDao.getAccount(id);
  }

  @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUserIdAndName(){
    // TO DO: limit results to users not currently logged in.
    return userDao.findIdAndName();
  }


}
