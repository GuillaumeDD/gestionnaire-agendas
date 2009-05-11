/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package remote;

import Authentification.Utilisateur;
import Exception.UtilisateurInexistantException;
import GestionAgenda.PortefeuilleAgenda;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import service.sql.PortefeuilleAgendaSQL;
import service.sql.UtilisateurSQL;

/**
 *
 * @author GDD
 */
public class ServeurGestionnaireAgendas extends UnicastRemoteObject implements ServeurGestionnaireAgendasInterface{

    public ServeurGestionnaireAgendas() throws RemoteException {
        super();
    }

    public PortefeuilleAgenda getPortefeuilleAgenda(String login, String mdp) throws RemoteException, ClassNotFoundException, FileNotFoundException, UtilisateurInexistantException {
        UtilisateurSQL utilisateurServ = new UtilisateurSQL();
        PortefeuilleAgendaSQL pfServ = new PortefeuilleAgendaSQL();
        Utilisateur u = null;
        PortefeuilleAgenda pf = null;
        u = utilisateurServ.findByUserMdp(login, mdp);
        pf = pfServ.findByUser(u);
        return pf;
    }

}
