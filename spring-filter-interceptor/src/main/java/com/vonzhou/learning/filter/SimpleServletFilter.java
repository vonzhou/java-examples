package com.vonzhou.learning.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vonzhou on 16/7/23.
 */
public class SimpleServletFilter implements Filter {
    private static Logger logger = Logger.getLogger(SimpleServletFilter.class);

    /**
     * 可以看到 Filter 只在初次触发的时候才会调用 init 方法， 对于 destroy 方法的生命周期也是类似
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("SimpleServletFilter init....");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (meetFilterCondition(request)) {
            chain.doFilter(request, response);
        } else {
            // Reply directly here (1)
            replyDirectly(response);
        }

        // filter outside  (2)
        doFilterResponse(response);
    }

    private void doFilterResponse(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.getWriter().println("+++ From Filter, I will also filter the response, sorry");
    }

    private void replyDirectly(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.getWriter().println("+++ From Filter, Another Page...enjoy");
    }

    private boolean meetFilterCondition(ServletRequest request) {
        String id = request.getParameter("id");
        logger.info("Request Parameter id : " + id);
        if (id != null && !id.isEmpty() && id.equals("123")) {
            return true;
        }
        return false;
    }

    /**
     * 在 Tomcat 关闭的时候执行，是不是可以利用这一特性实现一些东西？？
     */
    public void destroy() {
        logger.info("SimpleServletFilter destroy....");
    }
}
