/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

import Authentification.Utilisateur;

/**
 *
 * @author GDD
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
