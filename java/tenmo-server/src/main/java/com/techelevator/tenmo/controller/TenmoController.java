package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.services.TenmoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
//@PreAuthorize("isAuthenticated()")
public class TenmoController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;



@RequestMapping(path= "/account/{id}", method= RequestMethod.GET)
    public Account getAccount(@PathVariable int id){

    return accountDao.getAccount(id);
}

}
