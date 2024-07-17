package it.univaq.sose.appointmentSchedulingService;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Console {

    public static void startH2Console() {
        try {
            Server h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8088");
            h2Server.start();
            System.out.println("H2 appointment console started at http://localhost:8088");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
