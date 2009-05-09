/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

import Authentification.Utilisateur;

/**
 * Exception lancee lorsqu'aucune session correspondant a l'identifiant idSession est stockee
 * @see Session
 * @see SessionDAO
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class SessionInexistanteException extends Exception {

    private Utilisateur u;

    private String IP;
    /**
     * Creates a new instance of <code>NoSessionException</code> without detail message.
     */
    public SessionInexistanteException() {
        u = null;
        IP = null;
    }

    public SessionInexistanteException(Utilisateur u, String IP) {
        this.u = u;
        this.IP = IP;
    }

    /**
     * Constructs an instance of <code>NoSessionException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SessionInexistanteException(String msg) {
        super(msg);
    }

    public String getIP() {
        return IP;
    }

    public Utilisateur getU() {
        return u;
    }

}
