package service;

import Authentification.Session; 
import Authentification.Utilisateur; 
import Exception.SessionInexistanteException;
import Exception.SessionDejaExistanteException;

public interface SessionDAO {

    public void cleanUp();

    public void insert (Session s) throws SessionDejaExistanteException;

    public void update (Session s) throws SessionInexistanteException;

    public void delete (Session s);

    public Session findByUser (Utilisateur u, String IP) throws SessionInexistanteException;

}

