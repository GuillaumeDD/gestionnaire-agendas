/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

import service.UtilisateurDAO;

/**
 * Exception lancee si un utilisateur portant le meme pseudonyme existe deja
 * @see Utilisateur
 * @see UtilisateurDAO
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class LoginDejaExistantException extends Exception {

    String login = null;
    /**
     * Creates a new instance of <code>UtilisateurDejaExistantException</code> without detail message.
     */
    public LoginDejaExistantException() {
    }

    public LoginDejaExistantException(String u) {
        this.login = u;
    }

}
