package com.utils;

import com.dao.PostDAO;
import com.dao.UserDAO;
import com.dao.UserDaoPostgres;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filters.CustomFilter;
import com.servlets.PostServlet;
import com.servlets.UserServlet;
import com.dao.PostDaoPostgres;

import javax.servlet.*;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        PostDAO postDAO = new PostDaoPostgres();
        UserDAO userDAO = new UserDaoPostgres();

        UserServlet userServlet = new UserServlet(mapper, userDAO);
        PostServlet postServlet = new PostServlet(mapper, postDAO);

        ServletContext context = sce.getServletContext();

        CustomFilter customFilter = new CustomFilter();
        context.addFilter("CustomFilter", customFilter).addMappingForUrlPatterns(EnumSet.of(DispatcherType.INCLUDE), true, "/*");

        ServletRegistration.Dynamic userServlet1 = context.addServlet("UserServlet", userServlet);

        userServlet1.setLoadOnStartup(3);
        userServlet1.setInitParameter("user-servlet-key", "user-servlet-value");
        userServlet1.setInitParameter("another-param", "another-value");
        userServlet1.addMapping("/users/*");

        ServletRegistration.Dynamic postServlet1 = context.addServlet("PostServlet", postServlet);

        postServlet1.setLoadOnStartup(3);
        postServlet1.addMapping("/posts/*");


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }

}