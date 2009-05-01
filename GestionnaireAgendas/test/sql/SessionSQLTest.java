/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import Authentification.Session;
import Authentification.Utilisateur;
import Exception.SessionInexistanteException;
import Exception.SessionDejaExistanteException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import service.sql.BaseDeDonnees;
import service.sql.SessionSQL;

/**
 *
 * @author GDD
 */
public class SessionSQLTest extends TestCase {

    BaseDeDonnees bd = new BaseDeDonnees();
    
    public SessionSQLTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Suppression du contenu de la table Session
        String req = "DELETE FROM Session";
        PreparedStatement prepStmt;
        prepStmt = bd.getCon().prepareStatement(req);
        prepStmt.execute();

        // On ajoute des enregistrements fictifs pour faire les tests
        for(int i=0; i< 10; i++){
            req = "INSERT into Session set IdUser = ?, dateDebut = NOW(), IP=?";
            prepStmt = bd.getCon().prepareStatement(req);
            prepStmt.setInt(1, i);
            prepStmt.setString(2, "127.10.10."+i);
            prepStmt.execute();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    public void testFindByUser(){
        Utilisateur u = new Utilisateur(1);
        String IP = "127.10.10.1";
        Session s = null;
        try {
            s = (new SessionSQL()).findByUser(u, IP);
        } catch (SessionInexistanteException ex) {
            Logger.getLogger(SessionSQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(s.getIP(), IP);
        assertEquals(s.getUserID(), u.getUserID());
    }

    public void testFindByUserNoSessionException() {
        Utilisateur u = new Utilisateur(69);
        String IP = "127.10.10.1";
        Session s = null;
        try {
            s = (new SessionSQL()).findByUser(u, IP);
            fail("Une exception de type NoSessionException aurait du etre lancee.");
        } catch (SessionInexistanteException ex) {
            
        }
    }

    public void testUpdate(){
        Utilisateur u = new Utilisateur(1);
        String IP = "127.10.10.1";
        Session s = null, copie;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SessionSQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            s = (new SessionSQL()).findByUser(u, IP);
            copie = new Session(s);
            (new SessionSQL()).update(s);
            assertEquals(copie.getIP().compareTo(s.getIP()),0);
            assertEquals(copie.getDebut().compareTo(s.getDebut()),0);
            assertTrue(copie.getDerniereActivite().compareTo(s.getDerniereActivite()) < 0);
            assertTrue(copie.getIdSession() == s.getIdSession());
            assertTrue(copie.getUserID() == copie.getUserID());
        } catch (SessionInexistanteException ex) {
            fail("Une exception de type NoSessionException a ete lancee.");
        }

        try {
            s = (new SessionSQL()).findByUser(u, IP);
            copie = new Session(s);

            String req = "DELETE FROM Session";
            PreparedStatement prepStmt;
            prepStmt = bd.getCon().prepareStatement(req);
            prepStmt.execute();

            (new SessionSQL()).update(s);

            fail("Une exception de type NoSessionException aurait du etre lancee.");
        } catch (SQLException ex) {
            fail("Une exception de type NoSessionException aurait du etre lancee.");
            Logger.getLogger(SessionSQLTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SessionInexistanteException ex) {

        }


    }

    public void testInsert(){
        Utilisateur u = new Utilisateur(1);
        String IP = "127.10.10.1";
        Session s = new Session(u, IP), scopie = null;
        try {
            // test d'insertion d'une session deja enregistre
            (new SessionSQL()).insert(s);
            fail("Une exception SessionDejaExistanteException aurait du etre lancee.");
        } catch (SessionDejaExistanteException ex) {
            // Une session deja enregistree a ete detectee
            assertTrue(1 == 1);
        }
        u = new Utilisateur(69);
        IP = "192.168.0.1";
        s = new Session(u,IP);
        try {
            assertTrue(s.getDebut() == null);
            assertTrue(s.getDerniereActivite() == null);
            assertTrue(s.getIdSession() == -1);
            assertTrue(s.getUserID() == 69);
            assertTrue(s.getIP().compareTo(IP) == 0);

            (new SessionSQL()).insert(s);

            assertTrue(s.getDebut() != null);
            assertTrue(s.getDerniereActivite() != null);
            assertTrue(s.getIdSession() != -1);
            assertTrue(s.getUserID() == 69);
            assertTrue(s.getIP().compareTo(IP) == 0);

            try {
                scopie = (new SessionSQL()).findByUser(s.getUser(), s.getIP());
                
                assertTrue(scopie.getDebut().compareTo(s.getDebut()) == 0);
                assertTrue(scopie.getDerniereActivite().compareTo(s.getDerniereActivite()) == 0);
                assertTrue(scopie.getIdSession() == s.getIdSession());
                assertTrue(scopie.getUserID() == s.getUserID());
                assertTrue(scopie.getIP().compareTo(s.getIP()) == 0);

            } catch (SessionInexistanteException ex) {
                fail("Session non enregistree.");
            }
        } catch (SessionDejaExistanteException ex) {
            fail("Une exception SessionDejaExistanteException a ete lancee alors qu'elle n'aurait pas du l'etre.");
        }
    }

    public void testDelete(){
        Utilisateur u = new Utilisateur(1);
        String IP = "127.10.10.1";
        Session s = new Session(u, IP), scopie = null;
        try {
            (new SessionSQL()).findByUser(s.getUser(), s.getIP());
        } catch (SessionInexistanteException ex) {
            fail("Session inexistante. Impossible de la supprimer !");
        }

        (new SessionSQL()).delete(s);

        try {
            (new SessionSQL()).findByUser(s.getUser(), s.getIP());
            fail("La session n'a pas ete supprimee !");
        } catch (SessionInexistanteException ex) {

        }
    }
}
