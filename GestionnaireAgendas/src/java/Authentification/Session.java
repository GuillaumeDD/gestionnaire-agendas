package Authentification;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Session {

    private int idSession = -1;

    private Timestamp debut = null;

    private Timestamp derniereActivite = null;

    private String IP;

    private Utilisateur user;

    public Session () {
    }

    public Session (Session s){
        this.idSession = s.getIdSession();
        this.debut = s.getDebut();
        this.derniereActivite = s.getDerniereActivite();
        this.IP = s.getIP();
        this.user = s.getUser();
    }

    public Session(Utilisateur u, String IP){
        this.user = u;
        this.IP = IP;
    }

    public Session(int idSession, Timestamp debut, Timestamp derniereActivite, String IP, Utilisateur u) {
        this.idSession = idSession;
        this.debut = debut;
        this.derniereActivite = derniereActivite;
        this.IP = IP;
        this.user = u;
    }

    public int getUserID(){
        return user.getUserID();
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur u) {
        this.user = u;
    }

    public String getIP () {
        return IP;
    }

    public void setIP (String val) {
        this.IP = val;
    }

    public Timestamp getDebut () {
        return debut;
    }

    public void setDebut (Timestamp val) {
        this.debut = val;
    }

    public Timestamp getDerniereActivite () {
        return derniereActivite;
    }

    public void setDerniereActivite (Timestamp val) {
        this.derniereActivite = val;
    }

    public int getIdSession () {
        return idSession;
    }

    public void setIdSession (int val) {
        this.idSession = val;
    }

    public String toString(){
        StringBuffer r = new StringBuffer();
        r.append("-- SESSION --\n");
        r.append("idSession : "+idSession+"\n");

        DateFormat TimestampFormat = new SimpleDateFormat("HH'h'mm's'ss dd-MM-yy");
        r.append("debut de la session : "+TimestampFormat.format(debut)+"\n");
        r.append("Timestamp de derniere activite : "+TimestampFormat.format(derniereActivite)+"\n");
        r.append("IP : "+IP+"\n");
        r.append(user.toString());
        r.append("-------------\n");
        return r.toString();
    }
}

