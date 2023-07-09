package dev.jittakan.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements ServletContextListener,
        ServletRequestListener,
        HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Initializing the application");
        servletContext.setAttribute("message", "some global value of the app!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destroying the application");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Initializing the request");
        sre.getServletRequest().setAttribute("message", "Saving some value for the request!");
        sre.getServletRequest().setAttribute("title", "Servlet Catalog");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destroying the request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Initializing the HTTP session");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destroying the HTTP session");
    }
}
