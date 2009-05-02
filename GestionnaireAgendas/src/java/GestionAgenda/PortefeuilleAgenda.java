package GestionAgenda;

import Authentification.Utilisateur;
import Exception.*;
import java.util.HashMap;
import service.sql.AgendaSQL;


public class PortefeuilleAgenda {

    private Utilisateur utilisateur;

    private HashMap<Long,Agenda> agendas = new HashMap();

    public PortefeuilleAgenda (Utilisateur u) {
        utilisateur = u;
    }

    public void creerAgenda (String name, String description, String lieu, String color) throws NomVideException,NomExistantException{
        Agenda a = new Agenda(name,description,lieu,color,utilisateur.getUserID());
        a.setAgendaID(200);
        if(a.verifierValiditeNom(name))
        {
            if(estUniqueNomAgenda(name))
                {
                ajouterAgenda(a);
                }
            else throw new NomExistantException();
        }
        else throw new NomVideException();
    }

    public boolean estUniqueNomAgenda (String name) {
       boolean result = true;
       for(Agenda boucle:agendas.values())
            if(boucle.getNom().equals(name)) result = false;
       return result;
    }

    public boolean estUniqueNomAgenda (String name,long agendaID) {
       boolean result = true;
       for(Long boucle:agendas.keySet())
            if(boucle != agendaID && agendas.get(boucle).getNom().equals(name)) result = false;
       return result;
    }

    public void ajouterAgenda (Agenda a) {
        agendas.put(a.getAgendaID(), a);
    }

    public void creerEvenement (long agendaID, String objet, String lieu, String description, String d, float heureDebut, float heureFin) throws EvenementSimultaneException,ChampsMalRenseignesException{
    Agenda a = new Agenda();
    a = getAgenda(agendaID);
    int num =0;
    Evenement evt = new Evenement(agendaID,objet,lieu,description,d,heureDebut,heureFin);
    // On fixe un ID bidon qui va être mis à jour lors du reload de la BDD
    evt.setEventID(3321);
    if(evt.estValideEvenement()==true)
        {
        try
        {
        a.ajouterEvenement(evt);
        }
        catch(EvenementSimultaneException e)
        {throw new EvenementSimultaneException();}
        
        }
    else throw new ChampsMalRenseignesException();
    }

    public Agenda getAgenda (long agendaID) {
        return agendas.get(agendaID);
    }

    
        public void modifierAgenda (long agendaID, String name, String description, String lieu, String color) throws NomVideException,NomExistantException {
        Agenda ag = new Agenda();
        ag = getAgenda(agendaID);
        if(ag.verifierValiditeNom(name)==true)
            if(estUniqueNomAgenda(name,agendaID))
                {
                ag.setNom(name);
                ag.setDescription(description);
                ag.setLieu(lieu);
                ag.setColor(color);
                ag.setModif(true);
                agendas.remove(agendaID);
                agendas.put(agendaID, ag);
                }
            else throw new NomExistantException();
        else throw new NomVideException();
    }

    public void modifierEvenement (long agendaID, long eventID, String objet, String lieu, String description, String d, float heureDebut, float heureFin) throws EvenementSimultaneException,ChampsMalRenseignesException{
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    //try
    //{
    ag.modifierEvenement(eventID, objet, lieu, description, d, heureDebut, heureFin);
    agendas.remove(agendaID);
    agendas.put(agendaID, ag);
    //}
    //catch(EvenementSimultaneException ex1)
     //   {throw new EvenementSimultaneException();}
    //catch(ChampsMalRenseignesException ex2)
     //   {throw new ChampsMalRenseignesException();}
    
    }

    public void supprimerAgenda (long agendaID) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.supprimer();
    }

    public void supprimerEvenement (long agendaID, long eventID) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.supprimerEvenement(eventID);
    }

    public void initialiser () {
        AgendaSQL a = new AgendaSQL();
        setAgendas(a.findByUser(getUtilisateur()));
        
    }

    public HashMap<Long,Agenda> getAgendas () {
        return agendas;
    }

    public void setAgendas (HashMap<Long,Agenda> val) {
        this.agendas = val;
    }

    public Utilisateur getUtilisateur () {
        return utilisateur;
    }

    public void setUtilisateur (Utilisateur val) {
        this.utilisateur = val;
    }

}

