package service;

import Authentification.Utilisateur; 
import GestionAgenda.Agenda; 
import java.util.ArrayList; 

public interface AgendaDAO {

    public void insert (Agenda a);

    public void update (Agenda a);

    public void delete (Agenda a);

    public ArrayList<Agenda> findAll ();

    public Agenda findByPrimaryKey (int agendaID);

    public ArrayList<Agenda> findByUser (Utilisateur u);

}

