package com.dao;

import com.entities.User;

import java.util.List;

public interface UserDAO {

    User createUser(User user);

    User getUserById(int id);

    User getUserByUser(String username);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUserById(int id);
}
