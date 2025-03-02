package sr.unasat.subscription.api;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import sr.unasat.subscription.api.config.JPAConfig;

public class SubscriptionConfig{
    public static void main(String[] args) throws Exception {
        // Disable JAXB optimization
        System.setProperty("org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.fastBoot", "true");
        // Create a Jetty server
        Server server = new Server(8080);

        // Create a ServletContextHandler  ( dit is voor je default patch)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        // Configure Jersey
        ResourceConfig config = new ResourceConfig();
        config.register(JacksonFeature.class); // Register Jackson for JSON support
        config.packages("sr.unasat.subscription.api.controllers"); // Replace with your package name (hij zal alleen kijken in de packages die jij hebt aangegeven waarin die resources zijn als zijn er andere resources/controllers die je niet hebt aangegeven hij gaat niet erin kijken )

        // Add the Jersey ServletContainer to the context
        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        context.addServlet(jerseyServlet, "/api/*"); // Map to /api/*
        context.addServlet(jerseyServlet, "/*");

        // Register CORSFilter
        FilterHolder corsFilter = new FilterHolder(new CORSFilter());
        context.addFilter(corsFilter, "/*", null);

        JPAConfig.getEntityMangerFactory();
        JPAConfig.getEntityManger();

        // Set the handler and start the server
        server.setHandler(context);
        server.start();
        server.join();
    }
}
