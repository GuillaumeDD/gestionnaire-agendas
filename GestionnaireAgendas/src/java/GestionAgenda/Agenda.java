package GestionAgenda;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;


public class Agenda {

    private String nom;

    private String description;

    private String lieu;

    private String color;

    private long userID;

    private long agendaID;

    private HashMap<Long,Evenement> evenements = new HashMap();

    boolean suppr;

    boolean modif;

    boolean nouveau;

    public Agenda () {
        setNom("");
        setDescription("");
        setLieu("");
        setColor("");
        setUserID(0);
        setSuppr(false);
        setModif(false);
        setNouveau(false);
        evenements=new HashMap();
    }

    public Agenda (String name, String description, String lieu, String color, long userID) {
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

    public int ajouterEvenement (Evenement evt) {
        if(estUnique(evt)==true)
            {evenements.put(evt.getEventID(), evt);return 1;}
        else return 2;
    }

    public boolean estUnique (Evenement evt) {
        boolean result=true;
     for(Long j:evenements.keySet())
         if( evenements.get(j).getDate().equals(evt.getDate()) && evenements.get(j).getHeureDebut()==evt.getHeureDebut() && evenements.get(j).getHeureFin()==evt.getHeureFin() )
                result=false;
     return result;
    }

    public void setDescription (String des) {
        this.description=des;
    }

    public void setNom (String name) {
        this.nom=name;
    }

    public void modifierEvenement (long eventID, String objet, String lieu, String description, String d, float heureDebut, float heureFin) {
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

    public Evenement getEvenement (long eventID) {
    return evenements.get(eventID) ;
    }

    public void supprimer () {
      if(evenements != null)
      {
      for(Evenement boucle:evenements.values())
          boucle.supprimer();
      }
      setSuppr(true);
    }

    public void supprimerEvenement (long eventID) {
        Evenement event = new Evenement();
        event = getEvenement(eventID);
        if(event != null) event.supprimer();
    }

    public long getAgendaID () {
        return agendaID;
    }

    public void setAgendaID (long val) {
        this.agendaID = val;
    }

    public String getColor () {
        return color;
    }

    public HashMap<Long,Evenement> getEvenements () {
        return evenements;
    }

    public void setEvenements (HashMap<Long,Evenement> val) {
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

    public long getUserID () {
        return userID;
    }

    public void setUserID (long val) {
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

