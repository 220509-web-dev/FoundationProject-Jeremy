package com.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("CORS Filter leveraged");

        if (!(servletResponse instanceof HttpServletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Access-Control-Allow-Origin", "/*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type," +
                "Access-Control-Request-Method, Access-Control-Request-Headers");
        res.setHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
