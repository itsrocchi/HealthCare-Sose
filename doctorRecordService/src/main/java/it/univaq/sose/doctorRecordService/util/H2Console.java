package it.univaq.sose.doctorRecordService.util;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Console {

    public static void startH2Console() {
        try {
            Server h2Server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8090");
            h2Server.start();
            System.out.println("H2 doctor console started at http://localhost:8089");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}