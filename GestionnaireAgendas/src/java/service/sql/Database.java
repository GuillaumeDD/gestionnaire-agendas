package service.sql;

import Authentification.Utilisateur;
import GestionAgenda.Agenda; 
import GestionAgenda.Evenement; 
import java.util.*;

public class Database {

    public Database () {
    }

    public HashMap<Long,Agenda> chargerAgendas (Utilisateur user) {
        AgendaSQL a = new AgendaSQL();
        return a.findByUser(user);
    }

    public HashMap<Long,Evenement> chargerEvenements (int agendaID) {
        EvenementSQL e = new EvenementSQL();
        return e.findByAgenda(agendaID);
    }

    public boolean utilisateurValide (String name, String mdp) {
        return true;
    }

    public int getUserID (String name, String mdp) {
        return 0;
    }

}

