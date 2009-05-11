package service;

import GestionAgenda.Evenement; 
import java.sql.Date;
import java.util.HashMap;

/**
 * Interface representant les fonctions que doit implementer un systeme voulant assurer la persistance d'un objet Evenement
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 * @see Evenement
 */
public interface EvenementDAO {
/**
 * Méthode qui permet d'enregistrer un événement
 * @param e : l'événement à enregistrer
 */
    public void insert (Evenement e);
/**
 * Méthode qui permet de mettre à jour un événément
 * @param e : l'événement à mettre à jour
 */
    public void update (Evenement e);
/**
 * Méthode qui permet de supprimer un événement
 * @param e : événement à supprimer
 */
    public void delete (Evenement e);
/**
 * Méthode qui permet de récupérer l'ensemble des événéments
 * @return l'ensemble des événements
 */
    public HashMap<Long,Evenement> findAll ();
/**
 * Méthode qui permet de récupérer un événement à partir de son identifiant unique
 * @param eventID : identifiant unique de l'événement
 * @return l'événement dont l'identifiant est eventID
 */
    public Evenement findByPrimaryKey (long eventID);
/**
 * Méthode qui permet de récupérer l'ensemble des événements dont la date de réalisation est après d
 * @param d : date plancher
 * @return l'ensemble des événements dont la date de réalisation est après d
 */
    public HashMap<Long,Evenement> findAfter (Date d);
/**
 * Méthode qui permet de récupérer l'ensemble des événements dont la date de réalisation est avant d
 * @param d : date plancher
 * @return l'ensemble des événements dont la date de réalisation est avant d
 */
    public HashMap<Long,Evenement> findBefore (Date d);
/**
 * Méthode qui permet de récupérer l'ensemble des événements dont la date de réalisation est entre d1 et d2
 * @param d1 : date plancher
 * @param d2 : date plafond
 * @return l'ensemble des événements dont la date de réalisation est entre d1 et d2
 */
    public HashMap<Long,Evenement> findBetween (Date d1, Date d2);
/**
 * Méthode qui permet de récupérer l'ensemble des événements de l'agenda dont l'identifiant est agendaID
 * @param agendaID : identifiant de l'agenda
 * @return l'ensemble des événements de l'agenda dont l'identifiant est agendaID
 */
    public HashMap<Long,Evenement> findByAgenda (long agendaID);

}

