package GestionAgenda;

import java.util.ArrayList; 

public class Agenda {

    private String nom;

    private String description;

    private String lieu;

    private String color;

    private int userID;

    private int agendaID;

    private ArrayList<Evenement> evenements;

    public Agenda () {
    }

    public Agenda (String name, String description, String lieu, String color, int userID) {
    }

    public boolean verifierValiditeNom (String name) {
        return true;
    }

    public void setColor (String color) {
        return color;
    }

    public void setLieu (String lieu) {
        return lieu;
    }

    public String getNom () {
        return nom;
    }

    public void ajouterEvenement (Evenement evt) {
    }

    public boolean estUnique (Evenement evt) {
        return true;
    }

    public void setDescription (String description) {
        return description;
    }

    public void setNom (String name) {
        return nom;
    }

    public void modifierEvenement (int eventID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    }

    public void getEvenement (int eventID) {
    }

    public void supprimer () {
    }

    public void supprimerEvenement (int eventID) {
    }

    public int getAgendaID () {
        return agendaID;
    }

    public void setAgendaID (int val) {
        this.agendaID = val;
    }

    public String getColor () {
        return color;
    }

    public ArrayList<Evenement> getEvenements () {
        return evenements;
    }

    public void setEvenements (ArrayList<Evenement> val) {
        this.evenements = val;
    }

    public Iterator jourIterator (Date d) {
        return null;
    }

    public Iterator semaineIterator (Date debut, Date fin) {
        return null;
    }

    public Iterator moisIterator (int mois, int annee) {
        return null;
    }

    public String getDescription () {
        return description;
    }

    public String getLieu () {
        return lieu;
    }

    public int getUserID () {
        return userID;
    }

    public void setUserID (int val) {
        this.userID = val;
    }

}

