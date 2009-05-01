/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import Authentification.Utilisateur;
import Exception.LoginDejaExistantException;
import Exception.UtilisateurInexistantException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import service.sql.BaseDeDonnees;
import service.sql.UtilisateurSQL;

/**
 *
 * @author GDD
 */
public class UtilisateurSQLTest extends TestCase {

    BaseDeDonnees bd = new BaseDeDonnees();
    
    public UtilisateurSQLTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        String req = "SELECT *,COUNT(*) FROM Utilisateur WHERE Login = ? AND Password = ?";
        PreparedStatement prepStmt;
        ResultSet rs;
        try {
            prepStmt = bd.getCon().prepareStatement(req);
            prepStmt.setString(1, "prequena");
            prepStmt.setString(2,"22222");
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") != 1){
                try {
                String req1 = "INSERT INTO Utilisateur set IdUser = ?, Login = ?, Password = ?";
                PreparedStatement prepStmt1;
                prepStmt1 = bd.getCon().prepareStatement(req1);
                prepStmt1.setInt(1, 1);
                prepStmt1.setString(2, "prequena");
                prepStmt1.setString(3, "22222");
                prepStmt1.executeUpdate();
                } catch (SQLException ex1) {
                    Logger.getLogger(UtilisateurSQLTest.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

            prepStmt = bd.getCon().prepareStatement(req);
            prepStmt.setString(1, "gdd");
            prepStmt.setString(2,"54321");
            rs = prepStmt.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(*)") != 1){
                try {
                String req1 = "INSERT INTO Utilisateur set Login = ?, Password = ?";
                PreparedStatement prepStmt1;
                prepStmt1 = bd.getCon().prepareStatement(req1);
                prepStmt1.setString(1, "gdd");
                prepStmt1.setString(2, "54321");
                prepStmt1.executeUpdate();
                } catch (SQLException ex1) {
                    Logger.getLogger(UtilisateurSQLTest.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurSQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testFindByID(){
        Utilisateur u = null;
        try {
            u = (new UtilisateurSQL()).findByID(1);
            assertTrue(u.getUserID() == 1);
            assertTrue(u.getLogin().compareTo("prequena") == 0);
            assertTrue(u.getMdp().compareTo("22222") == 0);
            
        } catch (UtilisateurInexistantException ex) {
            fail("L'utilisateur avec l'ID 1 n'a pas ete trouve.");
        }
        
        try {
            u = (new UtilisateurSQL()).findByID(-1);
            fail("Utilisateur obtenu de la base de donnee alors qu'il est inexistant.");
        } catch (UtilisateurInexistantException ex) {
        }
    }

    public void testFindByUserMdp(){
        Utilisateur u = null;
        try {
            u = (new UtilisateurSQL()).findByUserMdp("prequena", "22222");
            assertTrue(u.getUserID() == 1);
            assertTrue(u.getLogin().compareTo("prequena") == 0);
            assertTrue(u.getMdp().compareTo("22222") == 0);

        } catch (UtilisateurInexistantException ex) {
            fail("L'utilisateur n'a pas ete trouve.");
        }

        try {
            u = (new UtilisateurSQL()).findByUserMdp("bidule","");
            fail("Utilisateur obtenu de la base de donnee alors qu'il est inexistant.");
        } catch (UtilisateurInexistantException ex) {
        }
    }

    public void testLoginUtilise(){
        assertTrue(!(new UtilisateurSQL()).loginUtilise("dsqfjspd fjpidsijhgfoisdhgodfgldnfgl sdfg fdg fdg df"));
        assertTrue((new UtilisateurSQL()).loginUtilise("prequena"));
    }
    
    public void testInsert(){
        Utilisateur u1 = new Utilisateur("prequena","mouhahahha");
        try {
            (new UtilisateurSQL()).insert(u1);
            fail("Une exception UtilisateurDejaExistantException aurait du etre lancee.");
        } catch (LoginDejaExistantException ex) {
        }

        u1 = new Utilisateur("bidule","machin");
        try {
            String login, mdp;
            login = u1.getLogin();
            mdp = u1.getMdp();
            assertTrue(login.compareTo("bidule") == 0);
            assertTrue(mdp.compareTo("machin") == 0);
            assertTrue(u1.getUserID() == -1);

            (new UtilisateurSQL()).insert(u1);

            assertTrue(u1.getUserID() > 0);
            assertTrue(u1.getLogin().compareTo("bidule") == 0);
            assertTrue(u1.getMdp().compareTo("machin") == 0);

            String req = "DELETE FROM Utilisateur where Login = 'bidule'";
            PreparedStatement prepStmt;
            try {
                prepStmt = bd.getCon().prepareStatement(req);
                prepStmt.execute();
            } catch (SQLException ex) {
                fail("Impossible de supprimer l'enregistrement test 'bidule'.");
                Logger.getLogger(UtilisateurSQLTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (LoginDejaExistantException ex) {
            fail("L'exception UtilisateurDejaExistantException n'aurait pas du etre lancee.");
        }
    }

    public void testUpdate(){
        Utilisateur u1 = null;
        int id = -1;
        try {
            u1 = (new UtilisateurSQL()).findByUserMdp("prequena", "22222");
        } catch (UtilisateurInexistantException ex) {
            fail("Impossible de recuperer le compte test prequena");
        }
        id = u1.getUserID();
        u1.setMdp("tagada");
        try {
            (new UtilisateurSQL()).update(u1);
            u1 = (new UtilisateurSQL()).findByID(id);

            assertTrue(u1.getMdp().compareTo("tagada") == 0);
            assertTrue(u1.getLogin().compareTo("prequena") == 0);
            assertTrue(u1.getUserID() == id);

            u1.setLogin("yeah");
            (new UtilisateurSQL()).update(u1);
            u1 = (new UtilisateurSQL()).findByID(id);
            
            assertTrue(u1.getMdp().compareTo("tagada") == 0);
            assertTrue(u1.getLogin().compareTo("yeah") == 0);
            assertTrue(u1.getUserID() == id);

            u1.setLogin("prequena");
            u1.setMdp("22222");
            (new UtilisateurSQL()).update(u1);
            u1 = (new UtilisateurSQL()).findByID(id);

            assertTrue(u1.getMdp().compareTo("22222") == 0);
            assertTrue(u1.getLogin().compareTo("prequena") == 0);
            assertTrue(u1.getUserID() == id);

        } catch (LoginDejaExistantException ex) {
            fail("Impossible de mettre a jour l'enregistrement.");
        } catch (UtilisateurInexistantException ex) {
            fail("Impossible de mettre a jour l'enregistrement.");
        }
        
        u1.setLogin("gdd");
        try {
            (new UtilisateurSQL()).update(u1);
            fail("Une exception LoginDejaExistantException aurait du etre lancee.");
        } catch (UtilisateurInexistantException ex) {
            fail("Une exception LoginDejaExistantException aurait du etre lancee.");
        } catch (LoginDejaExistantException ex) {
        }

        u1.setLogin("loulilol");
        try {
            (new UtilisateurSQL()).update(u1);
            u1 = (new UtilisateurSQL()).findByID(id);

            assertTrue(u1.getMdp().compareTo("22222") == 0);
            assertTrue(u1.getLogin().compareTo("loulilol") == 0);
            assertTrue(u1.getUserID() == id);
        } catch (UtilisateurInexistantException ex) {
            fail("Impossible de mettre a jour l'enregistrement.");
        } catch (LoginDejaExistantException ex) {
            fail("Impossible de mettre a jour l'enregistrement.");
        }

        u1.setLogin("prequena");
        try {
            (new UtilisateurSQL()).update(u1);
            u1 = (new UtilisateurSQL()).findByID(id);

            assertTrue(u1.getMdp().compareTo("22222") == 0);
            assertTrue(u1.getLogin().compareTo("prequena") == 0);
            assertTrue(u1.getUserID() == id);
        } catch (UtilisateurInexistantException ex) {
            fail("Impossible de mettre a jour l'enregistrement.");
        } catch (LoginDejaExistantException ex) {
            fail("Impossible de mettre a jour l'enregistrement.");
        }

    }

    public void testDelete(){
        Utilisateur u1 = new Utilisateur("choubidouwawa", "sfjsdp");
        try {
            (new UtilisateurSQL()).insert(u1);
        } catch (LoginDejaExistantException ex) {
            fail("Impossible d'enregistrer l'utilisateur.");
        }

        (new UtilisateurSQL()).delete(u1);
        
        try {
            (new UtilisateurSQL()).findByID(u1.getUserID());
            fail("Utilisateur non efface !");
        } catch (UtilisateurInexistantException ex) {
        }

        try {
            (new UtilisateurSQL()).findByUserMdp(u1.getLogin(), u1.getMdp());
            fail("Utilisateur non efface !");
        } catch (UtilisateurInexistantException ex) {
        }
    }
}
