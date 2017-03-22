package com.genesis.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.dao.UserDao;
import com.genesis.domain.User;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @RequestMapping(
            path = "user",
            method = RequestMethod.PUT)
    public ResponseEntity<?> create(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        userDao.createUser(user);
        return ResponseEntity.created(URI.create(user.getId())).build();
    }
    
    @RequestMapping(
            path = "user",
            method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(userDao.findAll());
    }
    
    @RequestMapping(
            path = "user/average-age",
            method = RequestMethod.GET)
    public ResponseEntity<?> getAverageAge() {
        return ResponseEntity.ok(userDao.averageAge());
    }

}
