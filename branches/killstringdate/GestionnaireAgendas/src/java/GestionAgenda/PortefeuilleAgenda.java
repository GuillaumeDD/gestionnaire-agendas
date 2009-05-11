package GestionAgenda;

import Authentification.Utilisateur;
import Exception.*;
import java.io.Serializable;
import java.util.HashMap;
import service.sql.AgendaSQL;

/**
 * Classe de gestion d'un portefeuille d'agendas
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 */
public class PortefeuilleAgenda implements Serializable{

    private Utilisateur utilisateur;

    private HashMap<Long,Agenda> agendas = new HashMap();

    /**
     * Constructeur avec argument
     * @param u : utilisateur qui possède ce portefeuille
     */
    public PortefeuilleAgenda (Utilisateur u) {
        utilisateur = u;
        agendas = new HashMap();
    }

    /**
     * Méthode qui permet de créer un nouvel agenda
     * @param name : nom de l'agenda
     * @param description : description de l'agenda
     * @param lieu : lieu de l'agenda
     * @param color : couleur de l'agenda
     * @throws Exception.NomVideException : lancee si le nom est une chaine de caractère vide
     * @throws Exception.NomExistantException : lancee si le nom de l'agenda est déjà utilisé
     * @see Agenda
     */
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

    /**
     * Méthode qui permet de vérifier si le nom de l'agenda est disponible
     * @param name : nom à tester
     * @return true si le nom n'est pas déjà utilisé, false sinon
     */
    public boolean estUniqueNomAgenda (String name) {
       boolean result = true;
       for(Agenda boucle:agendas.values())
            if(boucle.getNom().equals(name)) result = false;
       return result;
    }
/**
 * Méthode qui permet de vérifier si le nom de l'agenda est disponible en excluant l'agenda avec l'ID agendaID
 * @param name : nom à tester
 * @param agendaID : identifiant de l'agenda à exclure de la recherche
 * @return true si le nom n'est pas déjà utilisé, false sinon
 */
    public boolean estUniqueNomAgenda (String name,long agendaID) {
       boolean result = true;
       for(Long boucle:agendas.keySet())
            if(boucle != agendaID && agendas.get(boucle).getNom().equals(name)) result = false;
       return result;
    }

 /**
  * Méthode qui ajoute un agenda au portefeuille
  * @param a : agenda à ajouter
  */
    public void ajouterAgenda (Agenda a) {
        agendas.put(a.getAgendaID(), a);
    }

    /**
     * Méthode qui permet d'ajouter un événement dans l'agenda correspondant à l'identifiant agendaID
     * @param agendaID : identifiant de l'agenda dans lequel l'ajout doit avoir lieu
     * @param objet : objet de l'événement
     * @param lieu : lieu de l'événement
     * @param description : description de l'événement
     * @param d : date de l'événement
     * @param heureDebut : heure du début de l'événement
     * @param heureFin : heure de fin de l'événement
     * @throws Exception.EvenementSimultaneException : lancee si un événement en simultané existe déjà
     * @throws Exception.ChampsMalRenseignesException : lancee si les champs de l'événement sont incorrects
     */
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

    /**
     * Méthode qui permet de modifier l'agenda avec l'identifiant agendaID
     * @param agendaID : identifiant de l'agenda
     * @throws Exception.NomVideException : lancee si le nom est une chaine de caractère vide
     * @throws Exception.NomExistantException : lancee si le nom de l'agenda est déjà pris
     * @see Agenda
     */
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

        /**
         * Méthode qui permet de modifier l'événement avec l'identifiant eventID
         * @param agendaID : identifiant de l'agenda
         * @param eventID : identifiant de l'événement
         * @throws Exception.EvenementSimultaneException : lancee si un événement en simultané existe déjà
         * @throws Exception.ChampsMalRenseignesException : lancee si un champ est mal rempli
         * @see Evenement
         */
    public void modifierEvenement (long agendaID, long eventID, String objet, String lieu, String description, String d, float heureDebut, float heureFin) throws EvenementSimultaneException,ChampsMalRenseignesException{
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.modifierEvenement(eventID, objet, lieu, description, d, heureDebut, heureFin);
    agendas.remove(agendaID);
    agendas.put(agendaID, ag);
   }

    /**
     * Méthode qui supprime l'agenda avec l'identifiant agendaID
     * @param agendaID : identifiant de l'agenda à supprimer
     */
    public void supprimerAgenda (long agendaID) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.supprimer();
    }

    /**
     * Méthode qui supprime l'événement avec l'identifiant eventID dans l'agenda agendaID
     * @param agendaID : identifiant de l'agenda dans lequel se trouve l'événement
     * @param eventID : identifiant de l'événement qui doit etre supprimé
     */
    public void supprimerEvenement (long agendaID, long eventID) {
    Agenda ag = new Agenda();
    ag = getAgenda(agendaID);
    ag.supprimerEvenement(eventID);
    }

    /**
     * Méthode qui initialise un portefeuille d'agenda
     */
    public void initialiser () {
        AgendaSQL a = new AgendaSQL();
        if(a.findByUser(getUtilisateur())!=null)
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

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append("PORTEFEUILLE AGENDA :\n");
        result.append("Utilisateur : "+utilisateur.getLogin()+"\n");
        result.append("-----------------------\n");
        for(Agenda boucle:agendas.values()){
            result.append(boucle.toString()+"\n");
        }
        return result.toString();
    }
}

