package com.example.glproject.main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;


//TODO : 
//lancer database avant serveur
//ajouter logger a la place des printstackstrace
//hash mot de passe
public class LaunchServer {

	public static void main(String[] args) {
		// Initialize the server
		Server server = new Server();

		// Add a connector
		ServerConnector connector = new ServerConnector(server);
		connector.setHost("0.0.0.0");
		connector.setPort(8080);
		connector.setIdleTimeout(30000);
		server.addConnector(connector);

		// Configure Jersey
		ResourceConfig rc = new ResourceConfig();
		rc.packages(true, "com.example.glproject.ws");
		rc.register(LoggingFilter.class);
		rc.register(JacksonFeature.class);

		// Add a servlet handler for web services (/ws/*)
		ServletHolder servletHolder = new ServletHolder(new ServletContainer(rc));
		ServletContextHandler handlerWebServices = new ServletContextHandler(ServletContextHandler.SESSIONS);
		handlerWebServices.setContextPath("/ws");
		handlerWebServices.addServlet(servletHolder, "/*");

		// Add a handler for resources (/*)
		ResourceHandler handlerPortal = new ResourceHandler();
		handlerPortal.setResourceBase("src/main/webapp");
		handlerPortal.setDirectoriesListed(false);
		handlerPortal.setWelcomeFiles(new String[] { "login.html" });
		ContextHandler handlerPortalCtx = new ContextHandler();
		handlerPortalCtx.setContextPath("/");
		handlerPortalCtx.setHandler(handlerPortal);

		// Activate handlers
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		contexts.setHandlers(new Handler[] { handlerWebServices, handlerPortalCtx });
		server.setHandler(contexts);

		// ScriptEngineManager factory = new ScriptEngineManager();
		// ScriptEngine engine = factory.getEngineByName("JavaScript");

		// try {
		// engine.eval(new
		// FileReader("src/main/webapp/scripts/filldatabase.js"));
		// System.out.println("FILLDATABASE MON FRERE");
		// } catch (FileNotFoundException e1) {
		// e1.printStackTrace();
		// } catch (ScriptException e1) {
		// e1.printStackTrace();
		// }
		//

		// Start server
		
		try {
			server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}