package service;

import Authentification.Utilisateur; 
import GestionAgenda.Agenda; 
import java.util.HashMap;

public interface AgendaDAO {

    public void insert (Agenda a);

    public void update (Agenda a);

    public void delete (Agenda a);

    public HashMap<Integer,Agenda> findAll ();

    public Agenda findByPrimaryKey (int agendaID);

    public HashMap<Integer,Agenda> findByUser (Utilisateur u);

}

