package org.tulm.login_service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.tulm.login_service.auth.AuthFilter;

public class App {

  /**
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    // Resource config
    ResourceConfig config = new ResourceConfig();
    config.packages("org.tulm.login_service.resource");
    config.register(AuthFilter.class);
    
    ServletHolder servletHolder;
    servletHolder = new ServletHolder(new ServletContainer(config));
   
    Server jettyServer = new Server(8080);
    
    ServletContextHandler sch;
    sch = new ServletContextHandler(jettyServer, "/");
    sch.addServlet(servletHolder, "/*");
            
    jettyServer.start();
    jettyServer.join();
  }
}
