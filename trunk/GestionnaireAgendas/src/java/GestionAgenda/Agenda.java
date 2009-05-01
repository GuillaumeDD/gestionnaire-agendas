package GestionAgenda;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import Exception.*;
import java.util.GregorianCalendar;


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

    public void ajouterEvenement (Evenement evt) throws EvenementSimultaneException {
        if(estUnique(evt)==true)
            {evenements.put(evt.getEventID(), evt);}
        else throw new EvenementSimultaneException();
    }

    public boolean estUnique (Evenement evt) {
        boolean result=true;
     for(Long j:evenements.keySet())
         if( evenements.get(j).getDate().equals(evt.getDate()) && evenements.get(j).getHeureDebut()==evt.getHeureDebut() && evenements.get(j).getHeureFin()==evt.getHeureFin() )
                result=false;
     return result;
    }

    public boolean estUnique (Evenement evt,Long eventID) {
        boolean result=true;
     for(Long j:evenements.keySet())
         if(j != eventID)
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

    public void modifierEvenement (long eventID, String objet, String lieu, String description, String d, float heureDebut, float heureFin) throws EvenementSimultaneException,ChampsMalRenseignesException {
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
    {
        if(estUnique(event,eventID))
            {
            evenements.remove(eventID);
            evenements.put(eventID, event);
            }
        else throw new EvenementSimultaneException();
    }
    else throw new ChampsMalRenseignesException();
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

    public HashMap<Long,Evenement> getEvenementsBetween (Date d1, Date d2) {
        HashMap<Long,Evenement> events = new HashMap();
        Date d = new Date();
        for(Evenement e : evenements.values())
            {d = StringtoDateUS(e.getDate());
             if(d.compareTo(d1)>=0 && d.compareTo(d2)<=0) events.put(e.getEventID(), e);
            }
        return events;
    }

    public HashMap<Long,Evenement> getEvenementsByDate (Date d1) {
        HashMap<Long,Evenement> events = new HashMap();
        Date d = new Date();
        for(Evenement e : evenements.values())
            {d = StringtoDateUS(e.getDate());
             if(d.compareTo(d1)==0) events.put(e.getEventID(), e);
            }
        return events;
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

    public Date StringtoDateFR(String s)
    {
        String s1 = s.substring(0,2);
        String s2 = s.substring(3,5);
        String s3 = s.substring(6,10);
        GregorianCalendar date = new GregorianCalendar(Integer.parseInt(s3),Integer.parseInt(s2)-1,Integer.parseInt(s1));
        return date.getTime();

    }

    public Date StringtoDateUS(String s)
    {
        String s1 = s.substring(8,10);
        String s2 = s.substring(5,7);
        String s3 = s.substring(0,4);
        GregorianCalendar date = new GregorianCalendar(Integer.parseInt(s3),Integer.parseInt(s2)-1,Integer.parseInt(s1));
        return date.getTime();
    }

}

