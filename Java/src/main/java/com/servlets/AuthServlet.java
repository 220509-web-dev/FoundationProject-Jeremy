package com.servlets;

import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.AuthService;
import com.utils.CustomLogger;
import com.utils.LogLevel;
import com.utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class AuthServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final AuthService authService;
    private String logString;

    public AuthServlet(ObjectMapper mapper, AuthService authService) {
        this.mapper = mapper;
        this.authService = authService;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User reqUser = mapper.readValue(req.getInputStream(), User.class);

        UserDAO userDAO = new UserDaoPostgres();
        List<User> users = userDAO.getAllUsers();
        String path = req.getServletPath();
        String result;

        if (path.equals("/auth/login")) {
            User getUser = authService.login(reqUser.getUsername(), reqUser.getPassword());
            // set response
            resp.setContentType("application/json");
            if (getUser != null) {
                result = mapper.writeValueAsString(getUser);
            } else {
                ResponseObject ro = new ResponseObject("message:invalid username + password");
                result = mapper.writeValueAsString(ro);
                resp.setStatus(404);
            }
        } else if (path.equals("/auth/register")) {
            User newUser = authService.register(reqUser);
            resp.setContentType("application/json");
            if (newUser != null) {
                result = mapper.writeValueAsString(newUser);
            } else {
                ResponseObject ro = new ResponseObject("username already exists/invalid input");
                result = mapper.writeValueAsString(ro);
                resp.setStatus(404);
            }
        } else {
            // invalid endpoint
            ResponseObject ro = new ResponseObject("error");
            result = mapper.writeValueAsString(ro);
            resp.setStatus(404);
        }

        resp.getWriter().write(result);

//        HashMap<String, Object> credentials = mapper.readValue(req.getInputStream(), HashMap.class);
//        String providedUsername = (String) credentials.get("username");
//        String providedPassword = (String) credentials.get("password");
//
//        for (User user : users) {
//            if (providedUsername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
//                System.out.println("[LOG] - found user!");
//                logString = "Found user, Cookie Generated! - " + LocalDateTime.now();
//                CustomLogger.log(logString, LogLevel.INFO);
//                CustomLogger.parser();
//
//
//                HttpSession session = req.getSession();
//                session.setAttribute("auth-user", user);
//
//                resp.setStatus(204);
//                return;
//            }
//        }
//
//        resp.setStatus(400);
//        resp.setContentType("application/json");
//
//        HashMap<String, Object> errorMessage = new HashMap<>();
//        errorMessage.put("code", 400);
//        errorMessage.put("message", "No user found with provided credentials");
//        errorMessage.put("timestamp", LocalDateTime.now().toString());
//
//        logString = "No user found with provided credentials - " + LocalDateTime.now();
//        CustomLogger.log(logString, LogLevel.ERROR);
//        CustomLogger.parser();
//
//
//        resp.getWriter().write(mapper.writeValueAsString(errorMessage));


    }


}