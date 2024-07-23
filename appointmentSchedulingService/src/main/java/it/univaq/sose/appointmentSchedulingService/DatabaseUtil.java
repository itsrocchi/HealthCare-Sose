package it.univaq.sose.appointmentSchedulingService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtil {

    public static void initializeDatabase() {
        try {
            // Load H2 driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://mysql:3306/appointments_db", "root", "pswx");
                 Statement stmt = conn.createStatement()) {

                // Load schema.sql
                try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        DatabaseUtil.class.getResourceAsStream("/schema.sql")))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        stmt.execute(line);
                    }
                }

                // Load data.sql
               /* try (BufferedReader br = new BufferedReader(new InputStreamReader(
                        DatabaseUtil.class.getResourceAsStream("/data.sql")))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        stmt.execute(line);
                    }
                }*/

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
