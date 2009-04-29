/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.sql;

import Authentification.Session;
import Authentification.Utilisateur;
import Exception.NoSessionException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.SessionDAO;

/**
 *
 * @author GDD
 */
public class SessionSQL implements SessionDAO{

    private BaseDeDonnees bd = new BaseDeDonnees();

    public void delete(Session s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Session findByUser(Utilisateur u, String IP) throws NoSessionException{
        String req = "SELECT *,COUNT(*) FROM Session WHERE IdUser = ? AND IP = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        Session result = null;
        try {
            prepStmt = bd.getCon().prepareStatement(req);
            prepStmt.setInt(1, u.getUserID());
            prepStmt.setString(2, IP);
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") != 1){
                throw new NoSessionException(u,IP);
            }else{
                result = new Session(rs.getInt("IdSession"), rs.getTimestamp("dateDebut"), rs.getTimestamp("dateDerniereActivite"), IP, u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void insert(Session s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Session s) throws NoSessionException{
        String req = "UPDATE Session set dateDerniereActivite = NOW() WHERE IdSession = ? AND IdUser = ? AND dateDebut = ? AND IP = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        String msgErreur = "Session inexistante, mise a jour impossible";
        int result = 0;
        try {
            prepStmt = bd.getCon().prepareStatement(req);
            prepStmt.setInt(1, s.getIdSession());
            prepStmt.setInt(2, s.getUserID());
            prepStmt.setTimestamp(3, s.getDebut());
            prepStmt.setString(4, s.getIP());
            result = prepStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        // On regarde le nombre d'enregistrement mis a jour
        if(result != 1){
            // Aucun enregistrement n'a ete mis a jour
            throw new NoSessionException(msgErreur);
        }else{
            try {
                // On met a jour la session
                req = "SELECT *,COUNT(*) from Session WHERE IdSession = ? AND IdUser = ? AND dateDebut = ? AND IP = ?";
                prepStmt = bd.getCon().prepareStatement(req);
                prepStmt.setInt(1, s.getIdSession());
                prepStmt.setInt(2, s.getUserID());
                prepStmt.setTimestamp(3, s.getDebut());
                prepStmt.setString(4, s.getIP());
                rs = prepStmt.executeQuery();
                rs.next();
                if(rs.getInt("COUNT(*)") != 1){
                    throw new NoSessionException(msgErreur);
                }else{
                    s.setDerniereActivite(rs.getTimestamp("dateDerniereActivite"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SessionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
