/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.sql;

import Authentification.Utilisateur;
import Exception.LoginDejaExistantException;
import Exception.UtilisateurInexistantException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.UtilisateurDAO;

/**
 *
 * @author GDD
 */
public class UtilisateurSQL implements UtilisateurDAO{

    private BaseDeDonnees bd;
    private Connection connexion;

    public UtilisateurSQL(){
        bd = new BaseDeDonnees();
        connexion = bd.getCon();
    }


    public void delete(Utilisateur u) {
        try {
            String req = "DELETE FROM Utilisateur where IdUser = ?";
            PreparedStatement prepStmt;
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setInt(1, u.getUserID());
            prepStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Utilisateur findByID(int userID) throws UtilisateurInexistantException{
        String req = "SELECT *,COUNT(*) FROM Utilisateur WHERE IdUser = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        Utilisateur result = null;
        try {
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setInt(1, userID);
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") != 1){
                throw new UtilisateurInexistantException(userID);
            }else{
                result = new Utilisateur(rs.getString("Login"), rs.getString("Password"), rs.getInt("IdUser"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Utilisateur findByUserMdp(String login, String mdp)  throws UtilisateurInexistantException{
        String req = "SELECT *,COUNT(*) FROM Utilisateur WHERE Login = ? AND Password = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        Utilisateur result = null;
        try {
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setString(1, login);
            prepStmt.setString(2,mdp);
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") != 1){
                throw new UtilisateurInexistantException(login, mdp);
            }else{
                result = new Utilisateur(rs.getString("Login"), rs.getString("Password"), rs.getInt("IdUser"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void insert(Utilisateur u)  throws LoginDejaExistantException{
        Utilisateur user = null;
        if(loginUtilise(u.getLogin())){
            throw new LoginDejaExistantException(u.getLogin());
        }else{
            try {
                // aucun utilisateur portant le meme login n'est deja enregistre
                String req = "INSERT INTO Utilisateur set Login = ?, Password = ?";
                PreparedStatement prepStmt;
                prepStmt = connexion.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
                prepStmt.setString(1, u.getLogin());
                prepStmt.setString(2, u.getMdp());
                prepStmt.executeUpdate();
                
                // on met a jour l'utilisateur
                ResultSet rs = prepStmt.getGeneratedKeys();
                if(rs.next()){
                    u.setUserID(rs.getInt(1));
                }else{
                    System.out.println("Impossible de recuperer le dernier ID.");
                }
            } catch (SQLException ex1) {
                Logger.getLogger(UtilisateurSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void update(Utilisateur u) throws UtilisateurInexistantException, LoginDejaExistantException{
        // on recherche l'utilisateur pour voir s'il est existant
        findByID(u.getUserID());

        if(u.isLoginModifie() && loginUtilise(u.getLogin())){
            throw new LoginDejaExistantException(u.getLogin());
        }else{
            String req = "UPDATE Utilisateur set Login = ?, Password = ? WHERE IdUser = ?";
            PreparedStatement prepStmt;
            int result = 0;
            try {
                prepStmt = connexion.prepareStatement(req);
                prepStmt.setString(1, u.getLogin());
                prepStmt.setString(2, u.getMdp());
                prepStmt.setInt(3, u.getUserID());
                result = prepStmt.executeUpdate();
                u.aEteMAJ();
            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            // On regarde le nombre d'enregistrement mis a jour
            if(result != 1){
                // Aucun enregistrement n'a ete mis a jour
                throw new UtilisateurInexistantException(u.getUserID(), u.getLogin(), u.getMdp());
            }
        }
    }

    public boolean loginUtilise(String login) {
        String req = "SELECT *,COUNT(*) FROM Utilisateur WHERE Login = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        boolean result = false;
        try {
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setString(1, login);
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") > 0){
                result = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }



}
