import Authentification.Utilisateur;
import GestionAgenda.Agenda; 
import GestionAgenda.Evenement; 
import java.util.ArrayList; 

public class Database {

    public Database () {
    }

    public ArrayList<Agenda> chargerAgendas (Utilisateur user) {
        return null;
    }

    public ArrayList<Evenement> chargerEvenements (int agendaID) {
        return null;
    }

    public boolean utilisateurValide (String name, String mdp) {
        return true;
    }

    public int getUserID (String name, String mdp) {
        return 0;
    }

}

