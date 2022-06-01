package com.app;

import com.dao.PostDAO;
import com.dao.PostDaoPostgres;
import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.entities.Post;
import com.entities.User;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDaoPostgres();
        User user = new User(0, "Rimiru", "Tempest", "Rimiruslime@gmail.com", "DemonLord", "Veldora", "https://res.cloudinary.com/drrkccbb4/image/upload/v1652896153/ForumApp/download_livdk3.jpg", 3);
        User a = userDAO.getUserById(11);
        User userb = userDAO.getUserByUser("Sosathegod");
        System.out.println(a);
//        a.setRoleId(3);
//        userDAO.updateUser(a);
//
//        userDAO.deleteUserById(9);

        PostDAO postDAO = new PostDaoPostgres();
        Post post = new Post(0,"RIMIRU IS WAY TOO OP!", "How can anyone even defend this man?", "https://res.cloudinary.com/drrkccbb4/image/upload/v1653597817/ForumApp/E5HrjcWUYAEjgDk_tt0i7x.jpg","https://res.cloudinary.com/drrkccbb4/video/upload/v1653598196/ForumApp/videoplayback_2_bk6n8g.mp4", 10, 10);
        postDAO.createPost(post);
        Post b = postDAO.getPostById(202);
        System.out.println(b);

//        List<Post> postList = postDAO.getAllPost();
//        System.out.println(postList);

        b.setCategoryId(8);
        postDAO.updatePost(b);

//        postDAO.deletePostById(1);



    }
}
