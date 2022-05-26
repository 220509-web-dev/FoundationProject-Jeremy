package com.dao;

import com.entities.Post;
import com.entities.User;
import com.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDaoPostgres implements PostDAO {
    @Override
    public Post createPost(Post post) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            String sql = "insert into forum_app.app_posts (title, description, thumbnail_url, video_url, owner_id, category_id) values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            return post;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Post getPostById(int id) {
        try (Connection connection = ConnectionUtil.getConnection()){
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
            return post;

        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        try(Connection connection = ConnectionUtil.getConnection()){
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
            return posts;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        try(Connection connection = ConnectionUtil.getConnection()) {
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

            return post;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deletePostById(int id) {
        try(Connection connection = ConnectionUtil.getConnection()) {
            String sql = "delete from forum_app.app_posts where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
