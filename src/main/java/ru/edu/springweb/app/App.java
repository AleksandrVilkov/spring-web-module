package ru.edu.springweb.app;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class App {

    private static final String TEMP_DIR = "temp";
    private static final int PORT = 8080;
    private static final String WEB_APP_FILE = "src/main/webapp";
    private static final String EMPTY_STRING = "";
    private static final String WEB_INF_CLASSES_PATH = "target/classes";
    private static final String WEB_APP_MOUNT = "/WEB-INF/classes";
    private static final String SLASH = "/";

    public static void main(String[] args) throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.setBaseDir(TEMP_DIR);
        tomcat.setPort(PORT);
        var context = (StandardContext) tomcat.addWebapp(EMPTY_STRING, new File(WEB_APP_FILE).getAbsolutePath());
        var resources = new StandardRoot(context);
        resources.addPreResources(
                new DirResourceSet(
                        resources,
                        WEB_APP_MOUNT,
                        new File(WEB_INF_CLASSES_PATH).getAbsolutePath(),
                        SLASH
                )
        );
        context.setResources(resources);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }
}
