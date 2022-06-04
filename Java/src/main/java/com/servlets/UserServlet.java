package com.servlets;

import com.dao.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.entities.User;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserDAO userDAO;
    private String logString;

    public UserServlet(ObjectMapper mapper, UserDAO userdao) {
        this.mapper = mapper;
        this.userDAO = userdao;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logString = "UserServlet received a get request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);
        // This value would actually come from some data source
        List<User> userList = userDAO.getAllUsers();

        String respPayload = mapper.writeValueAsString(userList);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logString = "UserServlet received a post request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);

        try {
            List<User> users = userDAO.getAllUsers();
            User newUser = mapper.readValue(req.getInputStream(), User.class);
            for (User user : users) {
                if (newUser.getEmail().equals(user.getEmail())) {
                    logString = "Email taken, please insert a different email - " + LocalDateTime.now();
                    CustomLogger.log(logString, LogLevel.ERROR);

                    System.err.println("[ERROR] - Email taken, please insert a different email.");
                } else if (newUser.getUsername().equals(user.getUsername())) {
                    logString = "Username taken, please insert a different username - " + LocalDateTime.now();
                    CustomLogger.log(logString, LogLevel.ERROR);

                    System.err.println("[ERROR] - Username taken, please insert a different username.");
                } else {
                    userDAO.createUser(newUser);
                }
            }


        } catch (Exception e) {
            logString = String.format("An error occurred while creating a User. More Information: %s", ExceptionUtils.getStackTrace(e));
            CustomLogger.log(logString, LogLevel.INFO);
            e.printStackTrace();
        }
        resp.setStatus(204);

    }


}