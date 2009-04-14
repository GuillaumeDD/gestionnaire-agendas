package Authentification;

import java.sql.Date;


public class Session {

    private int idSession;

    private Date debut;

    private int derniereActivite;

    private String IP;

    private int idUser;

    public Session () {
    }

    public Session (Utilisateur u) {
    }

    public void enregistrer () {
    }

    public Utilisateur getUser () {
        return null;
    }

    public String getIP () {
        return IP;
    }

    public void setIP (String val) {
        this.IP = val;
    }

    public Date getDebut () {
        return debut;
    }

    public void setDebut (Date val) {
        this.debut = val;
    }

    public int getDerniereActivite () {
        return derniereActivite;
    }

    public void setDerniereActivite (int val) {
        this.derniereActivite = val;
    }

    public int getIdSession () {
        return idSession;
    }

    public void setIdSession (int val) {
        this.idSession = val;
    }

    public int getIdUser () {
        return idUser;
    }

    public void setIdUser (int val) {
        this.idUser = val;
    }

}

