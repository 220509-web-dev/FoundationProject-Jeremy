package com.services;

import com.dao.UserDAO;
import com.dto.ResourceCreationResponse;
import com.entities.User;
import com.utils.ConnectionUtil;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import com.utils.exceptions.InvalidRequestException;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    private String logString;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User getUserByUsername(String user) {
        return this.userDAO.getUserByUser(user);
    }

    public User getUserByID(int id) {
        return this.userDAO.getUserById(id);
    }

    public List<User> getAllUsers() {
        return this.userDAO.getAllUsers();
    }

    public User createUser(User newUser) {
        return this.userDAO.createUser(newUser);
    }


//    public ResourceCreationResponse createNewUser(User newUser) {
//
//        // Validate the data provided from the web layer
//        if (newUser == null ||
//                newUser.getUsername() == null || newUser.getUsername().equals("") ||
//                newUser.getFirstName() == null || newUser.getFirstName().equals("") ||
//                newUser.getLastName() == null || newUser.getLastName().equals("") ||
//                newUser.getPassword() == null || newUser.getPassword().equals("")) ;
//
//        {
//            // Logger.log(msg, LogLevel.ERROR);
//            logString = "Provided User data was invalid. First, Last, User, and Password can not be null! - " + LocalDateTime.now();
//            CustomLogger.log(logString, LogLevel.INFO);
//            throw new InvalidRequestException(logString);
//        }
//
//        // If valid, persist to DB and return its result
//        return new ResourceCreationResponse(userDAO.createUser(newUser).getId());


}

