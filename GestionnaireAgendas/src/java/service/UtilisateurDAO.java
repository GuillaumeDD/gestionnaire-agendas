package service;

import Authentification.Utilisateur; 
import Exception.LoginDejaExistantException;
import Exception.UtilisateurInexistantException;
/**
 * Interface representant les fonctions que doit implementer un systeme voulant assurer la persistance d'un objet Utilisateur
 * @see Utilisateur
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public interface UtilisateurDAO {
/**
 * Recuperation d'un utilisateur a partir de son login et son mot de passe
 * @param login : login de l'utilisateur
 * @param mdp : mot de passe de l'utilisateur
 * @return l'utilisateur correspondant
 * @throws Exception.UtilisateurInexistantException : lancee si aucun utilisateur ne correspond au couple login/mot de passe
 */
    public Utilisateur findByUserMdp (String login, String mdp) throws UtilisateurInexistantException;

/**
 * Recuperation d'un utilisateur a partir de son identifiant unique
 * @param userID : identifiant unique de l'utilisateur
 * @return l'utilisateur correspondant
  * @throws Exception.UtilisateurInexistantException : lancee si aucun utilisateur ne correspond au couple login/mot de passe
 */
    public Utilisateur findByID (int userID) throws UtilisateurInexistantException;

/**
 * Determine si un pseudonyme est deja utilise par un utilisateur
 * @param login : pseudonyme dont on cherche a savoir s'il est deja utilise par un utilisateur
 * @return true si le pseudonyme est deja utilise, false sinon
 */
    public boolean loginUtilise(String login);

 /**
  * Enregistre un utilisateur
  * @param u : utilisateur a enregistrer
  * @throws Exception.LoginDejaExistantException : lancee si un utilisateur portant le meme pseudonyme existe deja
  */
    public void insert (Utilisateur u) throws LoginDejaExistantException;

 /**
  * Met a jour les donnees d'un utilisateur
  * @param u : utilisateur a mettre a jour
  * @throws Exception.UtilisateurInexistantException : lancee si aucun utilisateur ne correspond au userID de u
  * @throws Exception.LoginDejaExistantException : lancee si un utilisateur portant le meme pseudonyme existe deja
  */
    public void update (Utilisateur u) throws UtilisateurInexistantException, LoginDejaExistantException;

    /**
     * Suppression d'un utilisateur
     * @param u : utilisateur a supprimer
     */
    public void delete (Utilisateur u);

}

