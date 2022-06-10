package com.dao;

import com.entities.User;
import com.utils.ConnectionUtil;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import com.utils.exceptions.UsernameNotAvailableException;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserDAO {
    String logString;

    @Override
    public User createUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to create user.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "insert into forum_app.app_users (first_name, last_name, username, password, profile_pic) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getProfilePic());

            ps.execute();

            //getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedID = rs.getInt("id");

            user.setId(generatedID);// the book id changing for 0 to a non-zero values means that it is saved
            System.out.println("User created successfully: " + user);
            logString = "Created user successfully!";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return user;

        } catch (SQLException exception) {
            logString = String.format("An error occurred while creating a User. More Information: %s", ExceptionUtils.getMessage(new UsernameNotAvailableException("Username already taken, please try again.")));
            CustomLogger.log(logString, LogLevel.ERROR);

//            exception.printStackTrace();
            throw new UsernameNotAvailableException("Username already taken, please try again.");
        }
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to retrieve post by User.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "select * from forum_app.app_users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            User user = new User();
            user.setId(id);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setProfilePic(rs.getString("profile_pic"));
            user.setRoleId(rs.getInt("role_id"));
            logString = "Retrieved User successfully!.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return user;

        } catch (SQLException exception) {
            logString = String.format("User was not found... More Information: User ID: %d not found.", id);
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: User ID: " + id + " not found.");
        }
        return null;
    }

    @Override
    public User getUserByUser(String username) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to retrieve User by Username.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "select * from forum_app.app_users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setProfilePic(rs.getString("profile_pic"));
            user.setRoleId(rs.getInt("role_id"));
            logString = "Retrieved User successfully!.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return user;

        } catch (SQLException exception) {
            logString = String.format("User was not found... More Information: Username: %d not found.", username);
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: Username: " + username + " not found.");
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to retrieve all Users.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "select * from forum_app.app_users";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<User>();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setRoleId(rs.getInt("role_id"));
                users.add(user);
            }
            logString = "Retrieved all Users Successfully!";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return users;
        } catch (SQLException exception) {
            logString = String.format("Error occurred while trying to find Users.. More information: %s", ExceptionUtils.getMessage(exception));
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to update a User.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "update forum_app.app_users set first_name = ?, last_name = ? , username = ?, password = ?, profile_pic = ?, role_id = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getProfilePic());
            ps.setInt(6, user.getRoleId());
            ps.setInt(7, user.getId());

            ps.execute();

            logString = "User updated successfully.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return user;
        } catch (SQLException exception) {
            logString = "User not found.";
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: User not found to update.");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to delete a User.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "delete from forum_app.app_users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            logString = "User deleted successfully.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
        } catch (SQLException exception) {
            logString = "User not found.";
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: User not found to delete.");
            exception.printStackTrace();
        }
    }
}
