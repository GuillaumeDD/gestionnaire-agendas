/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

import Authentification.Session;

/**
 * Exception lancee lorsqu'une session avec le meme idSession est deja stockee.
 * @see Session
 * @see SessionDAO
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class SessionDejaExistanteException extends Exception {
    private Session s;
    /**
     * Creates a new instance of <code>SessionDejaExistanteException</code> without detail message.
     */
    public SessionDejaExistanteException() {
    }

    public SessionDejaExistanteException(Session s){
        this.s = s;
    }
    /**
     * Constructs an instance of <code>SessionDejaExistanteException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public SessionDejaExistanteException(String msg) {
        super(msg);
    }
}
