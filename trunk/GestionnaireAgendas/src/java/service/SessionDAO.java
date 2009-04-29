package service;

import Authentification.Session; 
import Authentification.Utilisateur; 
import Exception.NoSessionException;

public interface SessionDAO {

    public void insert (Session s);

    public void update (Session s) throws NoSessionException;

    public void delete (Session s);

    public Session findByUser (Utilisateur u, String IP) throws NoSessionException;

}

