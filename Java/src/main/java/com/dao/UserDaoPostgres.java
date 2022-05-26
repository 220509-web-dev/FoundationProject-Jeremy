package com.dao;

import com.entities.User;
import com.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserDAO {

    @Override
    public User createUser(User user) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            String sql = "insert into forum_app.app_users (first_name, last_name, email, username, password, profile_pic) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getProfilePic());

            ps.execute();

            //getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedID = rs.getInt("id");

            user.setId(generatedID);// the book id changing for 0 to a non-zero values means that it is saved
            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from forum_app.app_users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            //Create a book and set the values of that book to the information in the result set
            User user = new User();
            user.setId(id);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setProfilePic(rs.getString("profile_pic"));
            user.setRoleId(rs.getInt("role_id"));
            return user;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUser(String username) {
        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from forum_app.app_users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            //Create a book and set the values of that book to the information in the result set
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setProfilePic(rs.getString("profile_pic"));
            user.setRoleId(rs.getInt("role_id"));
            return user;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from forum_app.app_users";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<User>();

            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setRoleId(rs.getInt("role_id"));
                users.add(user);
            }
            return users;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            String sql = "update forum_app.app_users set first_name = ?, last_name = ?, email = ? , username = ?, password = ?, profile_pic = ?, role_id = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getProfilePic());
            ps.setInt(7, user.getRoleId());
            ps.setInt(8, user.getId());

            ps.execute();

            return user;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserById(int id) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            String sql = "delete from forum_app.app_users where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
