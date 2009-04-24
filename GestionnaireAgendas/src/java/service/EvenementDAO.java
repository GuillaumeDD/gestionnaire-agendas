package service;

import GestionAgenda.Evenement; 
import java.sql.Date;
import java.util.HashMap;

public interface EvenementDAO {

    public void insert (Evenement e);

    public void update (Evenement e);

    public void delete (Evenement e);

    public HashMap<Integer,Evenement> findAll ();

    public Evenement findByPrimaryKey (int eventID);

    public HashMap<Integer,Evenement> findAfter (Date d);

    public HashMap<Integer,Evenement> findBefore (Date d);

    public HashMap<Integer,Evenement> findBetween (Date d1, Date d2);

    public HashMap<Integer,Evenement> findByAgenda (int agendaID);

}

