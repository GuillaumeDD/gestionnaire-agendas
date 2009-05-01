package Authentification;


public class Utilisateur {

    private String login;

    private String mdp;

    private boolean mdpModifie;

    private boolean loginModifie;

    private int userID = -1;

    public Utilisateur () {
        this.mdpModifie = false;
        this.loginModifie = false;
    }

    public Utilisateur(String login, String mdp) {
        super();
        this.login = login;
        this.mdp = mdp;
    }

    public Utilisateur(String login, String mdp, int userID) {
        super();
        this.login = login;
        this.mdp = mdp;
        this.userID = userID;
    }

    //pour tester mes méthodes
    public Utilisateur (int userID) {
        this.userID = userID;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String val) {
        if(this.login.compareTo(val) != 0){ this.loginModifie = true;}
        this.login = val;
    }

    public String getMdp () {
        return mdp;
    }

    public void setMdp (String val) {
        if(this.mdp.compareTo(val) != 0){ this.mdpModifie = true;}
        this.mdp = val;
    }

    public int getUserID () {
        return userID;
    }

    public void setUserID (int val) {
        this.userID = val;
    }

    public boolean isLoginModifie() {
        return loginModifie;
    }

    public boolean isMdpModifie() {
        return mdpModifie;
    }

    public void aEteMAJ(){
        this.mdpModifie = false;
        this.loginModifie = false;
    }

    public String toString(){
        StringBuffer r = new StringBuffer();
        r.append("Utilisateur : "+"(ID)"+userID+" ; "+"(login)"+login +" ; "+"(mdp)"+mdp+"\n");
        return r.toString();
    }
}

