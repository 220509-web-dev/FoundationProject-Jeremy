package com.servlets;

import com.dao.PostDAO;
import com.dao.PostDaoPostgres;
import com.entities.Post;
import com.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.CustomLogger;
import com.utils.LogLevel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    private String logString;

    public PostServlet(ObjectMapper mapper, PostDAO postDAO) {
        this.mapper = mapper;
        this.postDAO = postDAO;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logString = "PostServlet received a get request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);

        // Pretend this value came from the database
        List<Post> postList = postDAO.getAllPost();

        String respPayload = mapper.writeValueAsString(postList);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logString = "PostServlet received a post request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);

        HttpSession session = req.getSession(false);
        System.out.println(session);
        if (session == null) {
            HashMap<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("code", 401);
            errorMessage.put("message", "No session found on request");
            errorMessage.put("timestamp", LocalDateTime.now().toString());

            logString = "Unauthorized PostServlet post request, user needs to login. - " + LocalDateTime.now();
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();

            resp.setStatus(401);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
        } else {
            Post post = mapper.readValue(req.getInputStream(), Post.class);
            postDAO.createPost(post);
            System.out.println(post);
        }


    }
}