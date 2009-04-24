package GestionAgenda;

import Authentification.Utilisateur; 
import java.sql.Date;
import java.util.HashMap;
import service.sql.PortefeuilleAgendaSQL;

public class PortefeuilleAgenda {

    private Utilisateur utilisateur;

    private HashMap<Integer,Agenda> agendas;

    public PortefeuilleAgenda () {
    }

    public void creerAgenda (String name, String description, String lieu, String color) {
        Agenda a = new Agenda(name,description,lieu,color,utilisateur.getUserID());
        // On fixe un ID bidon qui va être mis à jour lors du reload de la BDD
        a.setAgendaID(2000);
        if(a.verifierValiditeNom(name))
            if(estUniqueNomAgenda(name))
                ajouterAgenda(a);
    }

    public boolean estUniqueNomAgenda (String name) {
       boolean result = true;
       for(Agenda boucle:agendas.values())
            if(boucle.getNom().equals(name)) result = false;
       return result;
    }

    public void ajouterAgenda (Agenda a) {
        agendas.put(a.getAgendaID(), a);
    }

    public void creerEvenement (int agendaID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    Agenda a = new Agenda();
    a = getAgenda(agendaID);
    Evenement evt = new Evenement(agendaID,objet,lieu,description,d,heureDebut,heureFin);
    // On fixe un ID bidon qui va être mis à jour lors du reload de la BDD
    evt.setEventID(3321);
    if(evt.estValideEvenement()==true)
        a.ajouterEvenement(evt);
    }

    public Agenda getAgenda (int agendaID) {
        return agendas.get(agendaID);
    }

    public void modifierAgenda (int agendaID, String name, String description, String lieu, String color) {
        Agenda ag = new Agenda();
        ag = getAgenda(agendaID);
        if(ag.verifierValiditeNom(name)==true)
            if(estUniqueNomAgenda(name))
                {
                ag.setNom(name);
                ag.setDescription(description);
                ag.setLieu(lieu);
                ag.setColor(color);
                ag.setModif(true);
                agendas.remove(agendaID);
                agendas.put(agendaID, ag);
                }
    }

    public void modifierEvenement (int agendaID, int eventID, String objet, String lieu, String description, Date d, int heureDebut, int heureFin) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.modifierEvenement(eventID, objet, lieu, description, d, heureDebut, heureFin);
    agendas.remove(agendaID);
    agendas.put(agendaID, ag);
    }

    public void supprimerAgenda (int agendaID) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.supprimer();
    agendas.remove(agendaID);
    }

    public void supprimerEvenement (int agendaID, int eventID) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.supprimerEvenement(eventID);
    }

    public void initialiser () {
        PortefeuilleAgendaSQL a = new PortefeuilleAgendaSQL();
        PortefeuilleAgenda pa = new PortefeuilleAgenda();
        pa = a.findByUser(getUtilisateur());
        setAgendas(pa.getAgendas());
        
    }

    public HashMap<Integer,Agenda> getAgendas () {
        return agendas;
    }

    public void setAgendas (HashMap<Integer,Agenda> val) {
        this.agendas = val;
    }

    public Utilisateur getUtilisateur () {
        return utilisateur;
    }

    public void setUtilisateur (Utilisateur val) {
        this.utilisateur = val;
    }

}

