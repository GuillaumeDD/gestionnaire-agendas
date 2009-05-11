

package service.sql;
import service.*;
import GestionAgenda.*;
import Authentification.*;
import java.util.HashMap;

/**
 * Classe assurant la persistance d'un objet PortefeuilleAgenda pour mySQL
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 * @see PortefeuilleAgenda
 */
public class PortefeuilleAgendaSQL implements PortefeuilleAgendaDAO {

    private BaseDeDonnees bd = BaseDeDonnees.getInstance();

    public PortefeuilleAgendaSQL(){ bd.connexion();}
/**
 * Méthode qui permet d'enregistrer un portefeuille d'agendas
 * @param pa : portefeuille d'agendas à sauvegarder
 */
    public void save (PortefeuilleAgenda pa){
        AgendaSQL asql=new AgendaSQL();
        for(Agenda boucle : pa.getAgendas().values())
        {
            if(boucle.aEteCree()) saveAgenda(boucle);
            else if (boucle.aEteModifie()) updateAgenda(boucle);
            else if(boucle.aEteSupprime()) deleteAgenda(boucle);
            asql.save(boucle);
        }

    }
/**
 * Cette méthode est un appel caché à la fonction insert de AgendaSQL
 * @param a : agenda à enregistrer
 * @see AgendaSQL
 */
    public void saveAgenda (Agenda a){
    AgendaSQL asql = new AgendaSQL();
    asql.insert(a);
    }
/**
 * Cette méthode est un appel caché à la fonction delete de AgendaSQL
 * @param a : agenda à supprimer
 * @see AgendaSQL
 */
    public void deleteAgenda (Agenda a){
    AgendaSQL asql = new AgendaSQL();
    asql.delete(a);
    }
/**
 * Cette méthode est un appel caché à la fonction update de AgendaSQL
 * @param a : agenda à mettre à jour
 * @see AgendaSQL
 */
    public void updateAgenda (Agenda a){
    AgendaSQL asql = new AgendaSQL();
    asql.update(a);
    }
 /**
  * Méthode qui permet de supprimer un portefeuille d'agendas
  * @param pa : portefeuille d'agendas à supprimer
  */
    public void deleteAll (PortefeuilleAgenda pa){
    for(Agenda boucle : pa.getAgendas().values())
        {
         deleteAgenda(boucle);
        }
    }
/**
 * Méthode qui permet de mettre à jour un portefeuille d'agendas
 * @param pa : portefeuille d'agendas à mettre à jour
 */
    public void updateAll (PortefeuilleAgenda pa){}

/**
 * Méthode qui permet de récupérer un portefeuille d'agendas appartenant à un utilisateur
 * @param user : utilisateur qui possède le portefeuille d'agendas
 * @return Portefeuille d'agendas de l'utilisateur
 */
    public PortefeuilleAgenda findByUser (Utilisateur user) {

        HashMap<Long,Agenda> agendas = new HashMap();
        AgendaSQL a = new AgendaSQL();
        PortefeuilleAgenda pa = new PortefeuilleAgenda(user);

        if(a.findByUser(user) != null)
            {
            agendas=a.findByUser(user);
            }
        pa.setAgendas(agendas);
        return pa;
    }
}
