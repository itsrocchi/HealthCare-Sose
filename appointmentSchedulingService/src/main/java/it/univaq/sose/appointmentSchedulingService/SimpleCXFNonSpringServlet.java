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
                    SimpleCXFNonSpringServlet.super.loadBus(servletConfig);
                    // Create an instance of your service implementation class
                    
                    AppointmentServiceImpl service = new AppointmentServiceImpl();
                    
                    // Publish the endpoint using the instance of your service implementation class
                    Endpoint.publish("/ass", service);
                } catch (Exception e) {
                    // Gestisci eventuali eccezioni qui
                    e.printStackTrace();
                }
                return null;


            }
        });
    }
}
