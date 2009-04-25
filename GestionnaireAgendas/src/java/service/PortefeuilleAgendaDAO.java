package service;

import Authentification.Utilisateur; 
import GestionAgenda.*;

public interface PortefeuilleAgendaDAO {

    public void save (PortefeuilleAgenda pa);

    public void saveAgenda (Agenda a);

    public void deleteAgenda (Agenda a);

    public void updateAgenda (Agenda a);

    public void deleteAll (PortefeuilleAgenda pa);

    public void updateAll (PortefeuilleAgenda pa);

    public PortefeuilleAgenda findByUser (Utilisateur user);

}

