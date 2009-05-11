package service;

import Authentification.Session; 
import Authentification.Utilisateur; 
import Exception.SessionInexistanteException;
import Exception.SessionDejaExistanteException;
/**
 * Interface representant les fonctions que doit implementer un systeme voulant assurer la persistance d'un objet Session
 * @see Session
 * @see SessionSQL
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public interface SessionDAO {
/**
 * Methode qui permet de supprimer les sessions perimees qui sont encore sauvegardees.
 * Une session qui a une duree de vie de plus de 5 heures est perimee.
 * Une session qui a un temps d'inactivite superieur a 20min est perimee.
 */
    public void cleanUp();

    /**
     * Enregistre la session
     * @param s : session a enregistrer
     * @throws Exception.SessionDejaExistanteException : lancee lorsqu'une session avec le meme idSession est deja stockee.
     */
    public void insert (Session s) throws SessionDejaExistanteException;
/**
 * Met a jour une session
 * @param s : session a mettre a jour
 * @throws Exception.SessionInexistanteException : lancee lorsqu'aucune session correspondant a l'identifiant idSession est stockee
 */
    public void update (Session s) throws SessionInexistanteException;
/**
 * Supprime la session
 * @param s : session a supprimer
 */
    public void delete (Session s);

    /**
     * Obtention de la session correspondant a un utilisateur et une adresse IP
     * @param u : Utilisateur de la session
     * @param IP : Adresse IP de l'utilisateur de la session
     * @return la session correspondant a u et IP
     * @throws Exception.SessionInexistanteException : lancee si l'utilisateur ne possede pas de session
     */
    public Session findByUser (Utilisateur u, String IP) throws SessionInexistanteException;

}

