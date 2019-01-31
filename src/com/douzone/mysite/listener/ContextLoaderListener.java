package com.douzone.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextLoaderListener() {
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	String contextConfigLocation = 
    			servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
    	
    	System.out.println("Container Start..." + contextConfigLocation);
    }
	
}
