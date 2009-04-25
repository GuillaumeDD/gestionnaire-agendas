package service;

import Authentification.Utilisateur; 
import GestionAgenda.Agenda; 
import java.util.HashMap;

public interface AgendaDAO {

    public void insert (Agenda a);

    public void update (Agenda a);

    public void delete (Agenda a);

    public HashMap<Long,Agenda> findAll ();

    public Agenda findByPrimaryKey (long agendaID);

    public HashMap<Long,Agenda> findByUser (Utilisateur u);

}

