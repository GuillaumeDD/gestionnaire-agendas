package service;

import Authentification.Utilisateur; 
import GestionAgenda.Agenda; 
import java.util.HashMap;
/**
 * Interface representant les fonctions que doit implementer un systeme voulant assurer la persistance d'un objet Agenda
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 * @see Agenda
 */
public interface AgendaDAO {
/**
 * Méthode qui permet d'enregistrer un agenda
 * @param a : agenda à enregistrer
 */
    public void insert (Agenda a);
/**
 * Méthode qui permet de mettre à jour un agenda
 * @param a : agenda à mettre à jour
 */
    public void update (Agenda a);

    /**
     * Méthode qui permet de supprimer un agenda
     * @param a : agenda à supprimer
     */
    public void delete (Agenda a);
/**
 * Méthode qui permet d'obtenir tous les agendas
 * @return l'ensemble des agendas
 */
    public HashMap<Long,Agenda> findAll ();

    /**
     * Méthode qui permet d'obtenir l'agenda dont l'identifiant est agendaID
     * @param agendaID : identifiant de l'agenda recherché
     * @return l'agenda dont l'identifiant est agendaID
     */
    public Agenda findByPrimaryKey (long agendaID);

    /**
     * Méthode qui permet d'obtenir les agendas associés à l'utilisateur u
     * @param u : utilisateur dont on recherche les agendas
     * @return l'ensemble des agendas de l'utilisateur u
     */
    public HashMap<Long,Agenda> findByUser (Utilisateur u);

}

