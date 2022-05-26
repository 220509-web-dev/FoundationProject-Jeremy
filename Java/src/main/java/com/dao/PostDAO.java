package com.dao;


import com.entities.Post;

import java.util.List;

public interface PostDAO {
    Post createPost(Post post);

    Post getPostById(int id);

    List<Post> getAllPost();

    Post updatePost(Post post);

    void deletePostById(int id);
}
