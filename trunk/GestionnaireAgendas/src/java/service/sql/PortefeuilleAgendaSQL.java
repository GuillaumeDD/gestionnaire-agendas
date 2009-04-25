

package service.sql;
import service.*;
import GestionAgenda.*;
import Authentification.*;
import java.util.HashMap;


public class PortefeuilleAgendaSQL implements PortefeuilleAgendaDAO {

    private BaseDeDonnees bd = new BaseDeDonnees();
    
    public void save (PortefeuilleAgenda pa){
        for(Agenda boucle : pa.getAgendas().values())
        {
            if(boucle.aEteCree()) saveAgenda(boucle);
            else if (boucle.aEteModifie()) updateAgenda(boucle);
            else if(boucle.aEteSupprime()) deleteAgenda(boucle);
        }

    }

    public void saveAgenda (Agenda a){
    AgendaSQL asql = new AgendaSQL();
    asql.insert(a);
    }

    public void deleteAgenda (Agenda a){
    AgendaSQL asql = new AgendaSQL();
    asql.delete(a);
    }

    public void updateAgenda (Agenda a){
    AgendaSQL asql = new AgendaSQL();
    asql.update(a);
    }

    public void deleteAll (PortefeuilleAgenda pa){
    for(Agenda boucle : pa.getAgendas().values())
        {
         deleteAgenda(boucle);
        }
    }

    public void updateAll (PortefeuilleAgenda pa){}

    public PortefeuilleAgenda findByUser (Utilisateur user){

        HashMap<Integer,Agenda> agendas = new HashMap();
        AgendaSQL a = new AgendaSQL();
        PortefeuilleAgenda pa = new PortefeuilleAgenda();
        
        agendas=a.findByUser(user);
        pa.setUtilisateur(user);
        pa.setAgendas(agendas);
        return pa;
    }
}
