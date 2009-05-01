package service;

import Authentification.Utilisateur; 
import Exception.LoginDejaExistantException;
import Exception.UtilisateurInexistantException;

public interface UtilisateurDAO {

    public Utilisateur findByUserMdp (String login, String mdp) throws UtilisateurInexistantException;

    public Utilisateur findByID (int userID) throws UtilisateurInexistantException;

    public boolean loginUtilise(String login);

    public void insert (Utilisateur u) throws LoginDejaExistantException;

    public void update (Utilisateur u) throws UtilisateurInexistantException, LoginDejaExistantException;

    public void delete (Utilisateur u);

}

