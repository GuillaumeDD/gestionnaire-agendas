/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package remote;

import Exception.UtilisateurInexistantException;
import GestionAgenda.PortefeuilleAgenda;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GDD
 */
public class ClientGestionnaireAgendas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "rmi://" + "127.0.0.1" + "/remoteGestionnaireAgendas";
        ServeurGestionnaireAgendasInterface od=null;
		PortefeuilleAgenda data = null;
        
        try {
            od = (ServeurGestionnaireAgendasInterface)Naming.lookup(url);
            data = od.getPortefeuilleAgenda("test", "test2");
            System.out.println(data);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UtilisateurInexistantException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            od = (ServeurGestionnaireAgendasInterface)Naming.lookup(url);
            data = od.getPortefeuilleAgenda("prequena", "22222");
            System.out.println(data);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UtilisateurInexistantException ex) {
            Logger.getLogger(ClientGestionnaireAgendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
