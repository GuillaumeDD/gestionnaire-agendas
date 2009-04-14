package GestionAgenda;

import Authentification.Utilisateur; 
import java.util.ArrayList; 

public class PortefeuilleAgenda {

    private Utilisateur utilisateur;

    private ArrayList<Agenda> agendas;

    public PortefeuilleAgenda () {
    }

    public void creerAgenda (String name, String description, String lieu, String color) {
    }

    public boolean estUniqueNomAgenda (String name) {
        return true;
    }

    public void ajouterAgenda (Agenda a) {
    }

    public void creerEvenement (int agendaID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    }

    public Agenda getAgenda (int agendaID) {
        return null;
    }

    public void modifierAgenda (int agendaID, String name, String description, String lieu, String color) {
    }

    public void modifierEvenement (int agendaID, int eventID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    }

    public void supprimerAgenda (int agendaID) {
    }

    public void supprimerEvenement (int agendaID, int eventID) {
    }

    public void initialiser () {
    }

    public ArrayList<Agenda> getAgendas () {
        return agendas;
    }

    public void setAgendas (ArrayList<Agenda> val) {
        this.agendas = val;
    }

    public Utilisateur getUtilisateur () {
        return utilisateur;
    }

    public void setUtilisateur (Utilisateur val) {
        this.utilisateur = val;
    }

}

