package just.demo3.Action;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public Connection Connect_to_db(){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Pharmacie","postgres","123");
            if(conn != null){
                System.out.println("mety");
            } else {
                System.out.println("mety failed");
            }
        } catch (Exception e) {
            System.out.println("Tsy mety");
        }
        return conn;
    }

}