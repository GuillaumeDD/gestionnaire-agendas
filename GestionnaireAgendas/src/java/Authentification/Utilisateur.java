package Authentification;

import java.io.Serializable;

/**
 * Classe de gestion d'utilisateur possedant un couple login/mot de passe
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class Utilisateur implements Serializable{

    private String login;

    private String mdp;

    private boolean mdpModifie;

    private boolean loginModifie;

    private int userID = -1;
/**
 * Constructeur par defaut
 */
    public Utilisateur () {
        this.mdpModifie = false;
        this.loginModifie = false;
    }
/**
 * Constructeur avec arguments
 * @param login : le pseudonyme de l'utilisateur
 * @param mdp : le mot de passe de l'utilisateur
 */
    public Utilisateur(String login, String mdp) {
        super();
        this.login = login;
        this.mdp = mdp;
    }
/**
 * Constructeur avec arguments
 * @param login : le pseudonyme de l'utilisateur
 * @param mdp : le mot de passe de l'utilisateur
 * @param userID : identifiant unique de l'utilisateur
 */
    public Utilisateur(String login, String mdp, int userID) {
        super();
        this.login = login;
        this.mdp = mdp;
        this.userID = userID;
    }

/**
 * Constructeur avec argument
 * @param userID : identifiant unique de l'utilisateur
 */
    public Utilisateur (int userID) {
        this.userID = userID;
    }
/**
 * Obtention du pseudonyme de l'utilisateur
 * @return Pseudonyme de l'utilisateur
 */
    public String getLogin () {
        return login;
    }

    /**
     * Fixe le pseudonyme de l'utilisateur
     * @param val : pseudonyme de l'utilisateur
     */
    public void setLogin (String val) {
        if(this.login.compareTo(val) != 0){ this.loginModifie = true;}
        this.login = val;
    }

    /**
     * Obtention du mot de passe de l'utilisateur
     * @return Mot de passe de l'utilisateur
     */
    public String getMdp () {
        return mdp;
    }
/**
 * Fixe le mot de passe de l'utilisateur
 * @param val : mot de passe de l'utilisateur
 */
    public void setMdp (String val) {
        if(this.mdp.compareTo(val) != 0){ this.mdpModifie = true;}
        this.mdp = val;
    }

    /**
     * Obtention de l'identifiant unique de l'utilisateur
     * @return identifiant unique
     */
    public int getUserID () {
        return userID;
    }
/**
 * Fixe l'identifiant unique de l'utilisateur
 * @param val : identifiant unique
 */
    public void setUserID (int val) {
        this.userID = val;
    }
/**
 * Methode qui determine si le pseudonyme de l'utilisateur a ete modifie
 * @return true s'il a ete modifie, false sinon
 */
    public boolean isLoginModifie() {
        return loginModifie;
    }

    /**
     * Methode qui determine si le mot de passe de l'utilisateur a ete modifie
     * @return true s'il a ete modifie, false sinon
     */
    public boolean isMdpModifie() {
        return mdpModifie;
    }

    /**
     * Methode qui remet a false loginModifie et mdpModifie
     */
    public void aEteMAJ(){
        this.mdpModifie = false;
        this.loginModifie = false;
    }
/**
 * Chaine de caracteres permettant un affichage minimal des informations de l'utilisateur
 * @return chaine de caracteres contenant les informations de l'utilisateur
 */
    public String toString(){
        StringBuffer r = new StringBuffer();
        r.append("Utilisateur : "+"(ID)"+userID+" ; "+"(login)"+login +" ; "+"(mdp)"+mdp+"\n");
        return r.toString();
    }
}

