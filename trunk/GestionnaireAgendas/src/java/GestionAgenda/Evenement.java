package GestionAgenda;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Evenement {

    private long eventID;

    private long agendaID;

    private String objet;

    private String lieu;

    private String description;

    private Date d;

    private long heureDebut;

    private long heureFin;

    boolean suppr;
    boolean modif;
    boolean nouveau;

    public Evenement () {
    }

    public Evenement (long _agendaID, String _objet, String _lieu, String _description, Date _d, long _heureDebut, long _heureFin) {
        setAgendaID(_agendaID);
        setObjet(_objet);
        setLieu(_lieu);
        setDescription(_description);
        setDate(_d);
        setHeureDebut(_heureDebut);
        setHeureFin(_heureFin);
        setSuppr(false);
        setModif(false);
        setNouveau(true);

    }

    public boolean estValideEvenement () {
        if(verifierChamp(getObjet(),getLieu(),getDescription(),getDate(),getHeureDebut(),getHeureFin())==true)
            return true;
        else return false;
    }

    public boolean verifierChamp (String objet, String lieu, String description, Date d, long heureDebut, long heureFin) {
    Date date_init= new Date(0);
        if(objet.equals("") || d==date_init || heureDebut==0 || heureFin==0)  return false;
           else return true;
    }

    public void supprimer () {
       setSuppr(true);
    }

    public long getEventID () {
        return eventID;
    }

    public void setEventID (long val) {
        this.eventID = val;
    }
    public long getAgendaID () {
        return agendaID;
    }

    public void setAgendaID (long val) {
        this.agendaID = val;
    }

    public Date getDate () {
        return d;
    }

    public void setDate (Date val) {
        this.d = val;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String val) {
        this.description = val;
    }

    public long getHeureDebut () {
        return heureDebut;
    }

    public void setHeureDebut (long val) {
        this.heureDebut = val;
    }

    public long getHeureFin () {
        return heureFin;
    }

    public void setHeureFin (long val) {
        this.heureFin = val;
    }

    public String getLieu () {
        return lieu;
    }

    public void setLieu (String val) {
        this.lieu = val;
    }

    public String getObjet () {
        return objet;
    }

    public void setObjet (String val) {
        this.objet = val;
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

