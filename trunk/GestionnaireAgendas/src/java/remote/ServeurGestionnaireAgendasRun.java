/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package remote;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author GDD
 */
public class ServeurGestionnaireAgendasRun {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
	      LocateRegistry.createRegistry(1099);
	    } catch(Exception e) { /* il y avait déjà un registre RMI lancé */ }

	    ServeurGestionnaireAgendasInterface od=null;

	    try {// Création de l'OD (l'objet distant)
            od = new ServeurGestionnaireAgendas();

            //	 Enregistrement de l'OD dans RMI
            Naming.rebind("remoteGestionnaireAgendas", od);
		}
		catch(Exception e) {
			System.out.println("Exception : probleme de creation et de l'enregistrement de l'OD. " + e);
		}
    }

}
