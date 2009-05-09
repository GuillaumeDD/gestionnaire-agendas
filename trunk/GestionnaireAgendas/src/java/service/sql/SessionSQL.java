/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.sql;

import Authentification.Session;
import Authentification.Utilisateur;
import Exception.SessionInexistanteException;
import Exception.SessionDejaExistanteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.SessionDAO;

/**
 * Classe permettant d'assurer la persistance d'un objet Session dans une base de donnees mySQL
 * @see BaseDeDonnees
 * @see Session
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class SessionSQL implements SessionDAO{

    private BaseDeDonnees bd;
    private Connection connexion;
/**
 * Constructeur qui recupere une connexion a une base de donnee mySQL
 */
    public SessionSQL(){
        bd = BaseDeDonnees.getInstance();
        connexion = bd.getCon();
    }
/**
 * Methode qui permet de supprimer les sessions perimees qui sont encore sauvegardees.
 * Une session qui a une duree de vie de plus de 5 heures est perimee.
 * Une session qui a un temps d'inactivite superieur a 20min est perimee.
 */
    public void cleanUp(){
        try {
            String req = "DELETE FROM Session where TIMESTAMPDIFF(SECOND, dateDerniereActivite, NOW()) > 1200";
            PreparedStatement prepStmt;
            prepStmt = connexion.prepareStatement(req);
            prepStmt.execute();

            req = "DELETE FROM Session where TIMESTAMPDIFF(SECOND, dateDebut, dateDerniereActivite) > 6000";
            prepStmt = connexion.prepareStatement(req);
            prepStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  /**
 * Supprime la session
 * @param s : session a supprimer
 */
    public void delete(Session s) {
        try {
            String req = "DELETE FROM Session where IdUser = ? AND IP = ?";
            PreparedStatement prepStmt;
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setInt(1, s.getUserID());
            prepStmt.setString(2, s.getIP());
            prepStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Obtention de la session correspondant a un utilisateur et une adresse IP
     * @param u : Utilisateur de la session
     * @param IP : Adresse IP de l'utilisateur de la session
     * @return la session correspondant a u et IP
     * @throws Exception.SessionInexistanteException : lancee si l'utilisateur ne possede pas de session
     */
    public Session findByUser(Utilisateur u, String IP) throws SessionInexistanteException{
        String req = "SELECT *,COUNT(*) FROM Session WHERE IdUser = ? AND IP = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        Session result = null;
        try {
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setInt(1, u.getUserID());
            prepStmt.setString(2, IP);
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") != 1){
                throw new SessionInexistanteException(u,IP);
            }else{
                result = new Session(rs.getInt("IdSession"), rs.getTimestamp("dateDebut"), rs.getTimestamp("dateDerniereActivite"), IP, u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    /**
     * Enregistre la session
     * @param s : session a enregistrer
     * @throws Exception.SessionDejaExistanteException : lancee lorsqu'une session avec le meme idSession est deja stockee.
     */
    public void insert(Session s) throws SessionDejaExistanteException{
        int result = 0;
        try{
            // on teste la presence d'une session
            findByUser(s.getUser(),s.getIP());

            // une session a ete trouvee
            throw new SessionDejaExistanteException(s);
        }catch(SessionInexistanteException e){
            try {
                // aucune session n'est deja enregistre
                String req = "INSERT INTO Session set IdUser = ?, dateDebut = NOW(), dateDerniereActivite = NOW(), IP = ?";
                PreparedStatement prepStmt;
                prepStmt = connexion.prepareStatement(req);
                prepStmt.setInt(1, s.getUserID());
                prepStmt.setString(2, s.getIP());
                prepStmt.executeUpdate();

                // on met a jour la session
                update(s);
            } catch (SessionInexistanteException ex) {
                Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
/**
 * Met a jour une session
 * @param s : session a mettre a jour
 * @throws Exception.SessionInexistanteException : lancee lorsqu'aucune session correspondant a l'identifiant idSession est stockee
 */
    public void update(Session s) throws SessionInexistanteException{
        String req = "UPDATE Session set dateDerniereActivite = NOW() WHERE IdUser = ? AND IP = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        String msgErreur = "Session inexistante, mise a jour impossible";
        int result = 0;
        try {
            prepStmt = connexion.prepareStatement(req);
            prepStmt.setInt(1, s.getUserID());
            prepStmt.setString(2, s.getIP());
            result = prepStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        // On regarde le nombre d'enregistrement mis a jour
        if(result != 1){
            // Aucun enregistrement n'a ete mis a jour
            throw new SessionInexistanteException(msgErreur);
        }else{
            try {
                // On met a jour la session
                req = "SELECT *,COUNT(*) from Session WHERE IdUser = ? AND IP = ?";
                prepStmt = connexion.prepareStatement(req);
                prepStmt.setInt(1, s.getUserID());
                prepStmt.setString(2, s.getIP());
                rs = prepStmt.executeQuery();
                rs.next();
                if(rs.getInt("COUNT(*)") != 1){
                    throw new SessionInexistanteException(msgErreur);
                }else{
                    s.setIdSession(rs.getInt("IdSession"));
                    s.setDebut(rs.getTimestamp("dateDebut"));
                    s.setDerniereActivite(rs.getTimestamp("dateDerniereActivite"));
                    s.setIP(rs.getString("IP"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
