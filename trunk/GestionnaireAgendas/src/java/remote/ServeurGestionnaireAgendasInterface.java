/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package remote;

import Exception.UtilisateurInexistantException;
import GestionAgenda.PortefeuilleAgenda;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author GDD
 */
public interface ServeurGestionnaireAgendasInterface extends Remote{
    PortefeuilleAgenda getPortefeuilleAgenda(String login, String mdp) throws RemoteException, ClassNotFoundException, FileNotFoundException, UtilisateurInexistantException ;
}
