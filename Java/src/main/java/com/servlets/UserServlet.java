package com.servlets;

import com.dao.UserDAO;
import com.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.entities.User;
import com.services.UserService;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserService userService;
    private String logString;
    private ErrorResponse error;

    public UserServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logString = "UserServlet received a get request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);
        List<User> userList = userService.getAllUsers();
        System.out.println("This is the request " + req);

        //Get user by Username
        String username = req.getParameter("username");

        try {
            int userId = Integer.parseInt(req.getParameter("id"));
            userList = userList.stream().filter(user -> user.getId() == userId).collect(Collectors.toList());

        } catch (NumberFormatException e) {
            logString = "Null or invalid ID input";
            CustomLogger.log(logString, LogLevel.ERROR);
        }

        // filter userList based on username
        if (username != null) {
            userList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        }

        // set response
        String result = mapper.writeValueAsString(userList);
        resp.setContentType("application/json");
        resp.getWriter().write(result);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logString = "UserServlet received a post request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);

        try {
            List<User> users = userService.getAllUsers();
            User newUser = mapper.readValue(req.getInputStream(), User.class);
            for (User user : users) {
                if (newUser.getUsername().equals(user.getUsername())) {
                    logString = "Username taken, please insert a different username - " + LocalDateTime.now();
                    CustomLogger.log(logString, LogLevel.ERROR);
                    error = new ErrorResponse(409, "This username is already in use.");
                    resp.setContentType("application/json");
                    resp.setStatus(409);
                    resp.getWriter().write(error.generateErrors(mapper));

                    System.err.println("[ERROR] - Username taken, please insert a different username.");
                } else {
                    userService.createUser(newUser);
                }
            }


        } catch (Exception e) {
//            logString = String.format("An error occurred while creating a User from the User Servlet. More Information: %s", ExceptionUtils.getStackTrace(e));
//            CustomLogger.log(logString, LogLevel.ERROR);
            e.printStackTrace();
        }
        resp.setStatus(204);

    }


}