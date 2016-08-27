package com.vonzhou.learning.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vonzhou on 16/7/23.
 */
public class SimpleServletFilter implements Filter{
    private static Logger logger = Logger.getLogger(SimpleServletFilter.class);

    /**
     * 可以看到 Filter 只在初次触发的时候才会调用 init 方法， 对于 destroy 方法的生命周期也是类似
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("SimpleServletFilter init....");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String id = request.getParameter("id");
        logger.info("Request Parameter id : " + id);
        if(id != null && !id.isEmpty() && id.equals("123")){
            chain.doFilter(request,response);
        }

        // Reply directly here
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.getWriter().println("+++ From Filter, Another Page...enjoy");
    }

    /**
     * 在 Tomcat 关闭的时候执行，是不是可以利用这一特性实现一些东西？？
     */
    public void destroy() {
        logger.info("SimpleServletFilter destroy....");
    }
}
