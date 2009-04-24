package GestionAgenda;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agenda {

    private String nom;

    private String description;

    private String lieu;

    private String color;

    private int userID;

    private int agendaID;

    private HashMap<Integer,Evenement> evenements;

    boolean suppr;

    boolean modif;

    boolean nouveau;

    public Agenda () {
    }

    public Agenda (String name, String description, String lieu, String color, int userID) {
        setNom(name);
        setDescription(description);
        setLieu(lieu);
        setColor(color);
        setUserID(userID);
        setSuppr(false);
        setModif(false);
        setNouveau(true);
    }

    public boolean verifierValiditeNom (String name) {
        if(name.equals("")) return false;
        else return true;
    }

    public void setColor (String couleur) {
        this.color=couleur;
    }

    public void setLieu (String _lieu) {
        this.lieu=_lieu;
    }

    public String getNom () {
        return nom;
    }

    public void ajouterEvenement (Evenement evt) {
        if(estUnique(evt)==true)
            evenements.put(evt.getEventID(), evt);
    }

    public boolean estUnique (Evenement evt) {
        if(evenements.containsValue(evt)==true)
            return false;
        else return true;
    }

    public void setDescription (String des) {
        this.description=des;
    }

    public void setNom (String name) {
        this.nom=name;
    }

    public void modifierEvenement (int eventID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    Evenement event = new Evenement();
    event = getEvenement(eventID);
    event.setObjet(objet);
    event.setLieu(lieu);
    event.setDescription(description);
    event.setDate(d);
    event.setHeureDebut(heureDebut);
    event.setHeureFin(heureFin);
    event.setModif(true);
    if(event.verifierChamp(objet, lieu, description, d, heureDebut, heureFin)==true)
        if(estUnique(event))
            {
            evenements.remove(eventID);
            evenements.put(eventID, event);
            }
    }

    public Evenement getEvenement (int eventID) {
    return evenements.get(eventID) ;
    }

    public void supprimer () {
      for(Evenement boucle:evenements.values())
          boucle.supprimer();
      setSuppr(true);
    }

    public void supprimerEvenement (int eventID) {
        Evenement event = new Evenement();
        event = getEvenement(eventID);
        event.supprimer();
        evenements.remove(eventID);
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

    public HashMap<Integer,Evenement> getEvenements () {
        return evenements;
    }

    public void setEvenements (HashMap<Integer,Evenement> val) {
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

    public boolean aEteSupprime () {
        return suppr;
    }

    public void setSuppr (boolean b) {
        this.suppr = b;
    }

    public boolean aEteModifie () {
        return modif;
    }

    public void setModif (boolean b) {
        this.modif = b;
    }

    public boolean aEteCree () {
        return nouveau;
    }

    public void setNouveau (boolean b) {
        this.nouveau = b;
    }

}

