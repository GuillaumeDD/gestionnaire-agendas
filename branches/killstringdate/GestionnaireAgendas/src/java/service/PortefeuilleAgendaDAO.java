package service;

import Authentification.Utilisateur; 
import GestionAgenda.*;
/**
 * Interface representant les fonctions que doit implementer un systeme voulant assurer la persistance d'un objet PortefeuilleAgenda
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 * @see PortefeuilleAgenda
 */
public interface PortefeuilleAgendaDAO {
/**
 * Méthode qui permet d'enregistrer un portefeuille d'agendas
 * @param pa : portefeuille d'agendas à sauvegarder
 */
    public void save (PortefeuilleAgenda pa);
/**
 * Cette méthode est un appel caché à la fonction insert de AgendaSQL
 * @param a : agenda à enregistrer
 * @see AgendaSQL
 */
    public void saveAgenda (Agenda a);
/**
 * Cette méthode est un appel caché à la fonction delete de AgendaSQL
 * @param a : agenda à supprimer
 * @see AgendaSQL
 */
    public void deleteAgenda (Agenda a);
/**
 * Cette méthode est un appel caché à la fonction update de AgendaSQL
 * @param a : agenda à mettre à jour
 * @see AgendaSQL
 */
    public void updateAgenda (Agenda a);

 /**
  * Méthode qui permet de supprimer un portefeuille d'agendas
  * @param pa : portefeuille d'agendas à supprimer
  */
    public void deleteAll (PortefeuilleAgenda pa);
/**
 * Méthode qui permet de mettre à jour un portefeuille d'agendas
 * @param pa : portefeuille d'agendas à mettre à jour
 */
    public void updateAll (PortefeuilleAgenda pa);

/**
 * Méthode qui permet de récupérer un portefeuille d'agendas appartenant à un utilisateur
 * @param user : utilisateur qui possède le portefeuille d'agendas
 * @return Portefeuille d'agendas de l'utilisateur
 */
    public PortefeuilleAgenda findByUser (Utilisateur user);

}

