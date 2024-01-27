package io.github.gulybyte.todo.config.cors;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.github.gulybyte.todo.config.property.TodoProperty;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Order(1)
@Component
public class CorsFilter implements Filter {

    @Autowired
	private TodoProperty todoProperty;

    @Override
    public void doFilter(final ServletRequest req,final ServletResponse res, final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "*");

        var origin = request.getHeader("Origin");

        var allowOrigins = new HashSet<>(Arrays.asList(
            todoProperty.getDomains().split(",")
        ));

        if(allowOrigins.contains("dev-mode")) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        } else if(allowOrigins.contains(origin)){
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access Denied!");
            return;
        }

        filterChain.doFilter(request, response);

    }

}
