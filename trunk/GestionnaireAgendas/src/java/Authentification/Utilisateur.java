package Authentification;


public class Utilisateur {

    private String login;

    private String mdp;

    private int userID;

    public Utilisateur () {

    }

    //pour tester mes méthodes
    public Utilisateur (int userID) {
        setUserID(userID);
    }

    public boolean verifierChamps (String login, String mdp) {
        return true;
    }

    public boolean utilisateurValide (String login, String mdp) {
        return true;
    }

    public String getLogin () {
        return login;
    }

    public void setLogin (String val) {
        this.login = val;
    }

    public String getMdp () {
        return mdp;
    }

    public void setMdp (String val) {
        this.mdp = val;
    }

    public int getUserID () {
        return userID;
    }

    public void setUserID (int val) {
        this.userID = val;
    }

}

