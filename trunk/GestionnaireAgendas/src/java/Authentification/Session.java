package Authentification;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Classe de gestion de Session
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class Session {

    private int idSession = -1;

    private Timestamp debut = null;

    private Timestamp derniereActivite = null;

    private String IP;

    private Utilisateur user;

    /**
     * Constructeur de la classe
     * Il initialise les attributs de la classe
     */
    public Session () {
    }
/**
 * Constructeur par recopie
 * @param s : objet Session a copier
 */
    public Session (Session s){
        this.idSession = s.getIdSession();
        this.debut = s.getDebut();
        this.derniereActivite = s.getDerniereActivite();
        this.IP = s.getIP();
        this.user = s.getUser();
    }
/**
 * Constructeur avec initialisation d'un utilisateur et de son IP
 * @param u : Utilisateur de la session
 * @param IP : Adresse IP de l'utilisateur
 */
    public Session(Utilisateur u, String IP){
        this.user = u;
        this.IP = IP;
    }
/**
 *
 * @param idSession : identifiant de la session de l'utilisateur
 * @param debut : Date de la creation de la session
 * @param derniereActivite : Date de la derniere activite de la session
 * @param IP : adresse IP de l'utilisateur
 * @param u : utilisateur de la session
 */
    public Session(int idSession, Timestamp debut, Timestamp derniereActivite, String IP, Utilisateur u) {
        this.idSession = idSession;
        this.debut = debut;
        this.derniereActivite = derniereActivite;
        this.IP = IP;
        this.user = u;
    }
/**
 * Obtention de l'identifiant de l'utilisateur de la session
 * @see Utilisateur
 * @return identifiant de l'utilisateur de la session
 */
    public int getUserID(){
        return user.getUserID();
    }

    /**
     * Obtention de l'utilisateur
     * @return utilisateur de la session
     */
    public Utilisateur getUser() {
        return user;
    }

    /**
     * Modifie l'utilisateur de la session
     * @param u : utilisateur de la session
     */
    public void setUser(Utilisateur u) {
        this.user = u;
    }

    /**
     * Obtention de l'IP de l'utilisateur
     * @return l'adresse IP de l'utilisateur a partir de laquelle il a cree la session
     */
    public String getIP () {
        return IP;
    }
/**
 * Fixe l'adresse IP de l'utilisateur
 * @param val : adresse IP
 */
    public void setIP (String val) {
        this.IP = val;
    }

    /**
     * Date de la creation de la session
     * @return Date de la creation de la session
     */
    public Timestamp getDebut () {
        return debut;
    }

    /**
     * Fixe la date de creation de la session
     * @param val : date de la creation de la session
     */
    public void setDebut (Timestamp val) {
        this.debut = val;
    }

    /**
     * Date de la derniere activite de la session, i.e. la date de la derniere utilisation de la session par l'utilisateur
     * @return date de derniere activite de la session
     */
    public Timestamp getDerniereActivite () {
        return derniereActivite;
    }

    /**
     * Fixe la date de derniere activite de la session
     * @param val : date de derniere activite de la session
     */
    public void setDerniereActivite (Timestamp val) {
        this.derniereActivite = val;
    }

    /**
     * Obtention de l'identifiant unique de la session
     * @return Identifiant unique de la session
     */
    public int getIdSession () {
        return idSession;
    }
/**
 * Fixe l'identifiant unique de la session
 * @param val : identifiant unique de la session
 */
    public void setIdSession (int val) {
        this.idSession = val;
    }

    /**
     * Conversion de l'objet en String
     * @return une chaine de caractere qui permet un affichage minimal d'une session
     */
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

