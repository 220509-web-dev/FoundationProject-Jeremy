package com.servlets;

import com.dao.PostDAO;
import com.dao.PostDaoPostgres;
import com.entities.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = "/flashcards",
        loadOnStartup = 2,
        initParams = {
                @WebInitParam(name = "flashcard-servlet-key", value = "flashcard-servlet-value"),
                @WebInitParam(name = "another-param", value = "another-value")
        }
) // annotation-based servlet registration
public class PostServlet extends HttpServlet {

    // This is bad practice, since we would need to create a new ObjectMapper for every servlet
    // instantiated in this manner
    private final ObjectMapper mapper;
    private final PostDAO postDAO;

    public PostServlet(ObjectMapper mapper, PostDAO postDAO) {
        this.mapper = mapper;
        this.postDAO = postDAO;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Pretend this value came from the database
        List<Post> postList = postDAO.getAllPost();

        String respPayload = mapper.writeValueAsString(postList);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);

    }

}