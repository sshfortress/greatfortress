package com.sshfortress.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class CORSFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	
        response.setHeader("Access-Control-Allow-Origin", "*");        
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");       
        response
            .setHeader(
                "Access-Control-Allow-Headers",
                "Origin,X-Requested-With,Content-Type,Authorization,Accept, No-Cache, If-Modified-Since,Last-Modified, Cache-Control, Expires, X-E4M-With");
        
        response.setHeader("Access-Control-Allow-Credentials", "true");      
        response.setHeader("Access-Control-Max-Age", "2592000");
        filterChain.doFilter(request, response);
    }

}