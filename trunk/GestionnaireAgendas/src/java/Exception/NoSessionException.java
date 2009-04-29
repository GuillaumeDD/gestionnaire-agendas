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
public class NoSessionException extends Exception {

    private Utilisateur u;

    private String IP;
    /**
     * Creates a new instance of <code>NoSessionException</code> without detail message.
     */
    public NoSessionException() {
        u = null;
        IP = null;
    }

    public NoSessionException(Utilisateur u, String IP) {
        this.u = u;
        this.IP = IP;
    }

    /**
     * Constructs an instance of <code>NoSessionException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NoSessionException(String msg) {
        super(msg);
    }

    public String getIP() {
        return IP;
    }

    public Utilisateur getU() {
        return u;
    }

}
