package service;

import GestionAgenda.Evenement; 
import java.util.ArrayList; 

public interface EvenementDAO {

    public void insert (Evenement e);

    public void update (Evenement e);

    public void delete (Evenement e);

    public ArrayList<Evenement> findAll ();

    public Evenement findByPrimaryKey (int eid);

    public ArrayList<Evenement> findAfter (Date d);

    public ArrayList<Evenement> findBefore (Date d);

    public ArrayList<Evenement> findBetween (Date d1, Date d2);

    public ArrayList<Evenement> findByAgenda (int agendaID);

}

