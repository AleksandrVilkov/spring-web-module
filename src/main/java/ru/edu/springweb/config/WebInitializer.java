package ru.edu.springweb.config;

import jakarta.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

    private static final String SERVLET_NAME = "dispatcherServlet";
    private static final String SLASH = "/";
    private static final int LOAD_ON_STARTUP = 1;

    @Override
    public void onStartup(ServletContext servletContext) {
        var rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebApplicationConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        var dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebApplicationConfig.class);

        var dispatcher = servletContext.addServlet(
                SERVLET_NAME,
                new DispatcherServlet(dispatcherContext)
        );
        dispatcher.setLoadOnStartup(LOAD_ON_STARTUP);
        dispatcher.addMapping(SLASH);
    }
}
