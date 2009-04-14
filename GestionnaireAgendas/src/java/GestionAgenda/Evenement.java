package GestionAgenda;

import java.sql.Date;


public class Evenement {

    private int agendaID;

    private String objet;

    private String lieu;

    private String description;

    private Date d;

    private int heureDebut;

    private int heureFin;

    public Evenement () {
    }

    public Evenement (int agendaID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    }

    public boolean estValideEvenement () {
        return true;
    }

    public void verifierChamp (String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    }

    public void supprimer () {
    }

    public int getAgendaID () {
        return agendaID;
    }

    public void setAgendaID (int val) {
        this.agendaID = val;
    }

    public Date getD () {
        return d;
    }

    public void setD (Date val) {
        this.d = val;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String val) {
        this.description = val;
    }

    public int getHeureDebut () {
        return heureDebut;
    }

    public void setHeureDebut (int val) {
        this.heureDebut = val;
    }

    public int getHeureFin () {
        return heureFin;
    }

    public void setHeureFin (int val) {
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

}

