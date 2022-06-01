package com.dao;

import com.entities.Post;
import com.entities.User;
import com.utils.ConnectionUtil;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoPostgres implements PostDAO {
    String logString;
    @Override
    public Post createPost(Post post) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to create post.";
            CustomLogger.log(logString, LogLevel.INFO);
            String g = "insert into forum_app.app_posts (title, description, thumbnail_url, video_url, owner_id, category_id) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(g, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getThumbnailUrl());
            ps.setString(4, post.getVideoUrl());
            ps.setInt(5, post.getOwnerId());
            ps.setInt(6, post.getCategoryId());

            ps.execute();

            //getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedID = rs.getInt("id");

            post.setId(generatedID);// the book id changing for 0 to a non-zero values means that it is saved
            logString = "Created post successfully!";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return post;

        } catch (SQLException exception) {
            logString = String.format("An error occurred while creating a post. More Information: %s", ExceptionUtils.getStackTrace(exception));
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getPostById(int id) {
        try (Connection connection = ConnectionUtil.getConnection()){
            logString = "Attempting to retrieve post by ID.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "select * from forum_app.app_posts where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            //Create a book and set the values of that book to the information in the result set
            Post post = new Post();
            post.setId(id);
            post.setTitle(rs.getString("title"));
            post.setDescription(rs.getString("description"));
            post.setThumbnailUrl(rs.getString("thumbnail_url"));
            post.setVideoUrl(rs.getString("video_url"));
            post.setLikes(rs.getInt("likes"));
            post.setDislikes(rs.getInt("dislikes"));
            post.setOwnerId(rs.getInt("owner_id"));
            post.setCategoryId(rs.getInt("category_id"));
            logString = "Retrieved post successfully!.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return post;

        } catch (SQLException exception){
            logString = String.format("Post was not found... More Information: Post ID: %d not found.", id);
            CustomLogger.log(logString,LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: Post ID: " + id + " not found.");
        }
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        try(Connection connection = ConnectionUtil.getConnection()){
            logString = "Attempting to retrieve all post.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "select * from forum_app.app_posts";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Post> posts = new ArrayList<Post>();

            while (rs.next()){
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setThumbnailUrl(rs.getString("thumbnail_url"));
                post.setVideoUrl(rs.getString("video_url"));
                post.setLikes(rs.getInt("likes"));
                post.setDislikes(rs.getInt("dislikes"));
                post.setOwnerId(rs.getInt("owner_id"));
                post.setCategoryId(rs.getInt("category_id"));
                posts.add(post);
            }
            logString = "Retrieved all post Successfully!";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return posts;
        } catch (SQLException exception) {
            logString = String.format("Error occurred while trying to find Posts.. More information: %s", ExceptionUtils.getMessage(exception));
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to update a post.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "update forum_app.app_posts set title = ?, description = ?, thumbnail_url = ?, video_url = ?, owner_id = ?, category_id = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getThumbnailUrl());
            ps.setString(4, post.getVideoUrl());
            ps.setInt(5, post.getOwnerId());
            ps.setInt(6, post.getCategoryId());
            ps.setInt(7, post.getId());

            ps.execute();

            logString = "Post updated successfully.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return post;
        } catch (SQLException exception) {
            logString = "Post not found.";
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: Post not found to update.");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePostById(int id) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            logString = "Attempting to delete a post.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "delete from forum_app.app_posts where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            logString = "Post deleted successfully.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
        } catch (SQLException exception) {
            logString = "Post not found.";
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: Post not found to delete.");
            exception.printStackTrace();
        }
    }
}
