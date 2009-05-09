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

    public void insert (Evenement e);

    public void update (Evenement e);

    public void delete (Evenement e);

    public HashMap<Long,Evenement> findAll ();

    public Evenement findByPrimaryKey (long eventID);

    public HashMap<Long,Evenement> findAfter (Date d);

    public HashMap<Long,Evenement> findBefore (Date d);

    public HashMap<Long,Evenement> findBetween (Date d1, Date d2);

    public HashMap<Long,Evenement> findByAgenda (long agendaID);

}

