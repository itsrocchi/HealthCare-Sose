package it.univaq.sose.doctorRecordService.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Application;

import org.apache.cxf.jaxrs.servlet.CXFNonSpringJaxrsServlet;

import it.univaq.sose.doctorRecordService.DoctorRecordImpl;

public class SimpleCXFNonSpringJaxrsServlet extends CXFNonSpringJaxrsServlet {

    private static final long serialVersionUID = 1152463856276872604L;

    @SuppressWarnings({ "deprecation", "removal" })
    @Override
    public void loadBus(final ServletConfig servletConfig) {

        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    // Load the CXF bus
                    SimpleCXFNonSpringJaxrsServlet.super.loadBus(servletConfig);

                    H2Console.startH2Console();

                    // Initialize the database
                    DatabaseUtil.initializeDatabase();

                    // Create and configure JAX-RS application
                    Map<String, Object> appProperties = new HashMap<>();
                    appProperties.put("javax.ws.rs.Application", new Application() {
                        @Override
                        public Set<Class<?>> getClasses() {
                            Set<Class<?>> classes = new HashSet<>();
                            classes.add(DoctorRecordImpl.class);
                            return classes;
                        }
                    });
                    
                    // Set servlet properties
                    servletConfig.getServletContext().setAttribute("jaxrs.properties", appProperties);

                } catch (Exception e) {
                    // Handle exceptions here
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
