/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sql;

import Authentification.Session;
import Authentification.Utilisateur;
import Exception.NoSessionException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
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
            System.out.println(prepStmt);
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
        } catch (NoSessionException ex) {
            Logger.getLogger(SessionSQLTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(s.getIP(), IP);
        assertEquals(s.getUserID(), u.getUserID());
        System.out.println(s);
    }

    public void testNoSessionException() {
        Utilisateur u = new Utilisateur(69);
        String IP = "127.10.10.1";
        Session s = null;
        try {
            s = (new SessionSQL()).findByUser(u, IP);
            fail("Une exception de type NoSessionException aurait du etre lancee.");
        } catch (NoSessionException ex) {
            
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
        } catch (NoSessionException ex) {
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
        } catch (NoSessionException ex) {

        }


    }
}
