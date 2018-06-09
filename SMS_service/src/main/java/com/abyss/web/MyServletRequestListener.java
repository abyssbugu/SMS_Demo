package com.abyss.web; /**
 * Created by Abyss on 2018/5/3.
 * description:
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyServletRequestListener implements ServletContextListener,
        HttpSessionListener, ServletRequestListener {
    // Public constructor is required by servlet spec
    public MyServletRequestListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
//        ServletContext ctx = sce.getServletContext();
//        String prefix = ctx.getRealPath("/");
//        // Log4J
//        String log4jFile = ctx.getInitParameter("log4j");
//        String log4jConfigPath = prefix + log4jFile;
//        PropertyConfigurator.configure(log4jConfigPath);
//        System.out.println("log4j-init");
//        sce.getServletContext().setAttribute("onlineNum", 0);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void sessionCreated(HttpSessionEvent se) {
//        ServletContext servletContext = se.getSession().getServletContext();
//        Integer onlineNum = (Integer) servletContext.getAttribute("onlineNum");
//        servletContext.setAttribute("onlineNum", ++onlineNum);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
//        ServletContext servletContext = se.getSession().getServletContext();
//        Integer onlineNum = (Integer) servletContext.getAttribute("onlineNum");
//        servletContext.setAttribute("onlineNum", --onlineNum);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
