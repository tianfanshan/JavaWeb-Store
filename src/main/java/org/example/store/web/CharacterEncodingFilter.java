package org.example.store.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (encoding != null){
            request.setCharacterEncoding(encoding);
            response.setContentType("text/html; Charset=" + encoding);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
