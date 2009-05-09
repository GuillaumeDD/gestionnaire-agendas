package GestionAgenda;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import Exception.*;
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Classe de gestion d'agenda
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class Agenda implements Serializable{

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
/**
 * Constructeur par défaut
 * Initialise des champs vides.
 */
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
/**
 * Constructeur avec arguments
 * @param name : nom de l'agenda
 * @param description : description de l'agenda
 * @param lieu : lieu de l'agenda
 * @param color : couleur de l'agenda
 * @param userID : identifiant unique de l'utilisateur
 */
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
/**
 * Methode qui verifie la validite du nom i.e. si le nom est vide ou pas
 * @param name : nom a verifier
 * @return true si le nom est valide, false sinon
 */
    public boolean verifierValiditeNom (String name) {
        if(name.equals("")) return false;
        else return true;
    }

    /**
     * Methode qui fixe la couleur de l'agenda
     * @param couleur : couleur de l'agenda
     */
    public void setColor (String couleur) {
        this.color=couleur;
    }
/**
 * Methode qui fixe le lieu de l'agenda
 * @param _lieu : lieu de l'agenda
 */
    public void setLieu (String _lieu) {
        this.lieu=_lieu;
    }
/**
 * Methode qui permet d'obtenir le nom de l'agenda
 * @return le nom de l'agenda
 */
    public String getNom () {
        return nom;
    }
/**
 * Methode qui permet d'ajouter un événement à l'agenda
 * @param evt : événement à ajouter
 * @throws Exception.EvenementSimultaneException : lancee si un événement en simultanée est déjà dans l'agenda
 */
    public void ajouterEvenement (Evenement evt) throws EvenementSimultaneException {
        if(estUnique(evt)==true)
            {evenements.put(evt.getEventID(), evt);}
        else throw new EvenementSimultaneException();
    }
/**
 * Methode qui verifie si l'événement est unique i.e. qu'il n'y a pas d'autre événement en simultané
 * @param evt : événement à vérifier
 * @return true s'il est unique, false sinon
 */
    public boolean estUnique (Evenement evt) {
        boolean result=true;
     for(Long j:evenements.keySet())
         if( evenements.get(j).getDate().equals(evt.getDate()) && evenements.get(j).getHeureDebut()==evt.getHeureDebut() && evenements.get(j).getHeureFin()==evt.getHeureFin() )
                result=false;
     return result;
    }
/**
 * Méthode qui vérifie si l'événement est unique en ne testant pas l'événement avec l'identifiant eventID
 * @param evt : événément à vérifier
 * @param eventID : identifiant de l'événement qui ne doit pas etre testé
 * @return true s'il est unique, false sinon
 */
    public boolean estUnique (Evenement evt,Long eventID) {
        boolean result=true;
     for(Long j:evenements.keySet())
         if(j != eventID)
            if( evenements.get(j).getDate().equals(evt.getDate()) && evenements.get(j).getHeureDebut()==evt.getHeureDebut() && evenements.get(j).getHeureFin()==evt.getHeureFin() )
                result=false;
     return result;
    }
/**
 * Methode qui fixe la description de l'agenda
 * @param des : description de l'agenda
 */
    public void setDescription (String des) {
        this.description=des;
    }
/**
 * Méthode qui fixe le nom de l'agenda
 * @param name : nom de l'agenda
 */
    public void setNom (String name) {
        this.nom=name;
    }

    /**
     * Méthode qui modifie l'événement avec l'identifiant eventID
     * @param eventID : identifiant de l'événement
     * @param objet : nouvel objet de l'événement
     * @param lieu : nouveau lieu de l'événement
     * @param description : nouvelle description de l'événement
     * @param d : nouvelle date de l'événement
     * @param heureDebut : nouvelle heure de début de l'événement
     * @param heureFin : nouvelle heure de fin de l'événement
     * @throws Exception.EvenementSimultaneException : lancee si un événement simultané existe
     * @throws Exception.ChampsMalRenseignesException : lancee si les champs sont incorrects
     */
    public void modifierEvenement (long eventID, String objet, String lieu, String description, String d, float heureDebut, float heureFin) throws EvenementSimultaneException,ChampsMalRenseignesException {
    Evenement event = new Evenement();
    event = getEvenement(eventID);
    
    if(event.verifierChamp(objet, lieu, description, d, heureDebut, heureFin)==true)
    {
        if(estUnique(event,eventID)==true)
            {
            event.setObjet(objet);
            event.setLieu(lieu);
            event.setDescription(description);
            event.setDate(d);
            event.setHeureDebut(heureDebut);
            event.setHeureFin(heureFin);
            event.setModif(true);
            //evenements.remove(eventID);
            //evenements.put(eventID, event);
            }
        else 
            {throw new EvenementSimultaneException();}
    }
    else {throw new ChampsMalRenseignesException();}
    }

    /**
     * Méthode qui permet d'obtenir un événément à partir de son identifiant
     * @param eventID : l'identifiant de l'événement
     * @return l'événement avec l'identifiant eventID
     */
    public Evenement getEvenement (long eventID) {
    return evenements.get(eventID) ;
    }

    /**
     * Méthode qui supprime l'agenda et tous les événements de cet agenda
     */
    public void supprimer () {
      if(evenements != null)
      {
      for(Evenement boucle:evenements.values())
          boucle.supprimer();
      }
      setSuppr(true);
    }

    /**
     * Méthode qui permet de supprimer l'événement avec l'identifiant eventID
     * @param eventID : identifiant de l'événement à supprimer
     */
    public void supprimerEvenement (long eventID) {
        Evenement event = new Evenement();
        event = getEvenement(eventID);
        if(event != null) event.supprimer();
    }
/**
 * Méthode qui permet d'obtenir l'identifiant de l'agenda
 * @return l'identifiant de l'agenda
 */
    public long getAgendaID () {
        return agendaID;
    }
/**
 * Méthode qui permet de fixer l'identifiant de l'agenda
 * @param val : nouvel identifiant de l'agenda
 */
    public void setAgendaID (long val) {
        this.agendaID = val;
    }
/**
 * Méthode qui permet d'obtenir la couleur de l'agenda
 * @return la couleur de l'agenda
 */
    public String getColor () {
        return color;
    }
/**
 * Méthode qui permet d'obtenir tous les événements
 * @return l'ensemble des événements de l'agenda
 */
    public HashMap<Long,Evenement> getEvenements () {
        return evenements;
    }

    /**
     * Méthode qui permet d'obtenir l'ensemble des événements compris entre deux dates d1 et d2
     * @param d1 : la date plancher
     * @param d2 : la date plafond
     * @return l'ensemble des événements qui sont compris entre d1 et d2
     */
    public HashMap<Long,Evenement> getEvenementsBetween (Date d1, Date d2) {
        HashMap<Long,Evenement> events = new HashMap();
        Date d = new Date();
        for(Evenement e : evenements.values())
            {d = StringtoDateUS(e.getDate());
             if(d.compareTo(d1)>=0 && d.compareTo(d2)<=0) events.put(e.getEventID(), e);
            }
        return events;
    }

    /**
     * Méthode qui permet d'obtenir l'ensemble des événements de la date d1
     * @param d1 : date de l'événement
     * @return l'ensemble des événements dont la date est d1
     */
    public HashMap<Long,Evenement> getEvenementsByDate (Date d1) {
        HashMap<Long,Evenement> events = new HashMap();
        Date d = new Date();
        for(Evenement e : evenements.values())
            {d = StringtoDateUS(e.getDate());
             if(d.compareTo(d1)==0) events.put(e.getEventID(), e);
            }
        return events;
    }

    /**
     * Méthode qui permet de fixer un ensemble d'événement dans l'agenda.
     * Attention, cette méthode supprime tous les événements qui étaient initialement présents !
     * @param val : ensemble d'événements
     */
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

    /**
     * Méthode qui permet d'obtenir la description de l'agenda
     * @return la description de l'agenda
     */
    public String getDescription () {
        return description;
    }
/**
 * Méthode qui permet d'obtenir le lieu de l'agenda
 * @return lieu de l'agenda
 */
    public String getLieu () {
        return lieu;
    }

    /**
     * Méthode qui permet d'obtenir l'identifiant de l'utilisateur qui possède cet agenda
     * @return l'identifiant de l'utilisateur qui possède cet agenda
     */
    public long getUserID () {
        return userID;
    }
/**
 * Méthode qui fixe l'identifiant de l'utilisateur qui possède cet agenda
 * @param val : identifiant de l'utilisateur
 */
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

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append("Agenda : "+this.getNom()+"\n");
        for(Evenement boucle:evenements.values()){
            result.append("\t"+boucle.toString()+"\n");
        }
        return result.toString();
    }
}

