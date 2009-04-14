package service;

import Authentification.Utilisateur; 

public interface UtilisateurDAO {

    public Utilisateur findByUserMdp (String login, String mdp);

    public Utilisateur findByID (int userID);

    public void insert (Utilisateur u);

    public void update (Utilisateur u);

    public void delete (Utilisateur u);

}

