package org.example.store.web;

import org.example.store.domain.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"/controller","*.jsp"})
public class LoginCheckFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Customer customer = (Customer) request.getSession().getAttribute("customer");

        String action = request.getParameter("action");

        if (customer == null && !"login".equals(action) && !"reg_init".equals(action)){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
