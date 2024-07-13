package it.univaq.sose.appointmentSchedulingService;

import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

public class SimpleCXFNonSpringServlet extends CXFNonSpringServlet {
    private static final long serialVersionUID = 1152463856246372604L;

    @Override
    public void loadBus(final ServletConfig servletConfig) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                try {
                    // Load the CXF bus
                    SimpleCXFNonSpringServlet.super.loadBus(servletConfig);
                    
                    // Initialize the database
                    DatabaseUtil.initializeDatabase();
                    
                    // Create an instance of your service implementation class
                    AppointmentServiceImpl service = new AppointmentServiceImpl();
                    
                    // Publish the endpoint
                    Endpoint.publish("/ass", service);
                } catch (Exception e) {
                    // Handle exceptions here
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
