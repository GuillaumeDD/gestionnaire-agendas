package service;

import Authentification.Utilisateur; 
import GestionAgenda.PortefeuilleAgenda; 

public interface PortefeuilleAgendaDAO {

    public void saveAll ();

    public void saveAgenda (int aid);

    public void deleteAgenda (int aid);

    public void updateAgenda (int aid);

    public void deleteAll ();

    public void updateAll ();

    public PortefeuilleAgenda findByUser (Utilisateur user);

}

