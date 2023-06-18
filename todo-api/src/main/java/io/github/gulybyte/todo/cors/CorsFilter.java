package io.github.gulybyte.todo.cors;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

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

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        /* if (SPRING_PROFILE_PRODUCTION.equals(this.activeProfile)) {
            String[] allowDomains = {"http://project","https://project"};
            Set<String> allowOrigins = new HashSet(Arrays.asList(allowDomains));
            String originHeads = request.getHeader("Origin");
            if(allowOrigins.contains(originHeads)){
                response.setHeader("Access-Control-Allow-Origin", originHeads);
            }
        } */



        response.setHeader("Access-Control-Allow-Origin", "*");//TODO: development

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader(
                "Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "*");

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return;
        }

        filterChain.doFilter(request, response);
    }

}
