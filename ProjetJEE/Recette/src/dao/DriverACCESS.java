package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DriverACCESS {
	private static Connection snglConnection = null;

    private DriverACCESS() {
    	System.out.println(System.getProperties().get("user.dir"));
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url="jdbc:oracle:thin:@char-oracle11.condorcet.be:1521:xe";
        	String login = "ora31";
        	String mdp = "oracle";
        	
            snglConnection = DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion �tablie !");
            
        } catch (ClassNotFoundException e) {
           System.out.println("Impossible de trouver le driver pour la base de donn�e!");
        } catch (SQLException e) {
        	System.out.println("Impossible de se connecter �  la base de donn�e.");
        }

        if (snglConnection == null) {
        	System.out.println("La base de donn�e est innaccessible, fermeture du programme.");
        	System.exit(0);
        }
    }

    public static Connection getInstance() {
        if (snglConnection == null) {
            new DriverACCESS();
        }

        return snglConnection;
    }

}