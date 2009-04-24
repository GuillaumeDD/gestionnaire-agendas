

package service.sql;
import service.*;
import GestionAgenda.*;
import Authentification.*;


public class PortefeuilleAgendaSQL implements PortefeuilleAgendaDAO {

    private BaseDeDonnees bd = new BaseDeDonnees();
    
    public void saveAll (){
    

    }

    public void saveAgenda (int aid){}

    public void deleteAgenda (int aid){}

    public void updateAgenda (int aid){}

    public void deleteAll (){}

    public void updateAll (){}

    public PortefeuilleAgenda findByUser (Utilisateur user){
        return null;}
}
