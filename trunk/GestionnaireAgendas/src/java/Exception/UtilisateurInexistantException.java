/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

import Authentification.Utilisateur;

/**
 * Exception lancee si aucun utilisateur ne correspond au couple login/mot de passe
 * @see Utilisateur
 * @see UtilisateurDAO
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class UtilisateurInexistantException extends Exception {

    int userID = -1;
    String login = null;
    String mdp = null;

    /**
     * Creates a new instance of <code>UtilisateurInexistantException</code> without detail message.
     */
    public UtilisateurInexistantException() {
    }

    public UtilisateurInexistantException(int id) {
        this.userID = id;
    }

    public UtilisateurInexistantException(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    public UtilisateurInexistantException(int id, String login, String mdp){
        this.userID = id;
        this.login = login;
        this.mdp = mdp;
    }
    /**
     * Constructs an instance of <code>UtilisateurInexistantException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UtilisateurInexistantException(String msg) {
        super(msg);
    }

    public int getUserID() {
        return userID;
    }


    
}
