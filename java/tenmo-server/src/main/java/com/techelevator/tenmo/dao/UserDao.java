package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    //List<User> findAllExceptCurrentUser();

    List<User> findIdAndName(long id);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password);
}
