package service.sql;

import java.sql.*;


public class BaseDeDonnees {

    private Connection con = null;



    public BaseDeDonnees () {

        connexion();
    }


    public void connexion () {
        try{
            // Chargement des drivers SQL
            System.out.println("\n---------------------");
            System.out.println("Connexion au driver JDBC.");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver com.mysql.jdbc.Driver bien Chargé.");
            }
        catch(ClassNotFoundException a)
                {
		System.out.println("Driver non trouvé.");
		}
	catch(Exception b)
                {
		System.out.println("Problème sur chargement de driver JDBC.");
		}
        try
        {
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_agenda?user=root&password=mysql");
         System.out.println("Connexion à la base établie.");
        System.out.println("---------------------\n");
        }
        catch(SQLException c)
        {
            System.out.println("Connexion refuse ou base inconnue.");
        }
        catch(Exception d)
        {
            System.out.println("Probleme sur connexion.");
        }



            }


    public void fermeture () {
        try
        {
        con.close();
        System.out.println("\n-------------------------.");
        System.out.println("Fermeture de connexion.");
        System.out.println("--------------------------\n");
        }
        catch(Exception d)
        {
            System.out.println("Problème sur la fermeture de la connexion.");
        }
    }


    public ResultSet executerSELECT (String requete) throws SQLException{

        Statement stmt = con.createStatement();
        return stmt.executeQuery(requete);
    }

    public void executerMAJ (String requete) throws SQLException{

        Statement stmt = con.createStatement();
        stmt.executeUpdate(requete);
    }
}

