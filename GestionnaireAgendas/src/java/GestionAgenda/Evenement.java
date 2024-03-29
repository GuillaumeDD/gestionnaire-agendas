package GestionAgenda;

import java.io.Serializable;

public class Evenement implements Serializable{

    private long eventID;

    private long agendaID;

    private String objet;

    private String lieu;

    private String description;

    private String d;

    private float heureDebut;

    private float heureFin;

    boolean suppr;
    boolean modif;
    boolean nouveau;
/**
 * Constructeur par défaut
 */
    public Evenement () {
    }

    /**
     * Constructeur avec arguments
     * @param _agendaID : identifiant de l'agenda dans lequel se trouve l'événement
     * @param _objet : objet de l'événement
     * @param _lieu : lieu de l'événement
     * @param _description : description de l'événement
     * @param _d : date de l'événement
     * @param _heureDebut : heure de début de l'événement
     * @param _heureFin : heure de fin de l'événement
     */
    public Evenement (long _agendaID, String _objet, String _lieu, String _description, String _d, float _heureDebut, float _heureFin) {
        setAgendaID(_agendaID);
        setObjet(_objet);
        setLieu(_lieu);
        setDescription(_description);
        setDate(_d);
        setHeureDebut(_heureDebut);
        setHeureFin(_heureFin);
        setSuppr(false);
        setModif(false);
        setNouveau(true);

    }
/**
 * Méthode qui vérifie si un événement est valide i.e. que tout ses attributs sont correctement remplis
 * @return true s'il est valide, false sinon
 */
    public boolean estValideEvenement () {
        if(verifierChamp(getObjet(),getLieu(),getDescription(),getDate(),getHeureDebut(),getHeureFin())==true)
            return true;
        else return false;
    }

    /**
     * Méthode qui vérifie que les champs sont valides
     * @param objet : objet de l'événement
     * @param lieu : lieu de l'événement
     * @param description : description de l'événement
     * @param d : date de l'événement
     * @param heureDebut : heure de début de l'événement
     * @param heureFin : heure de fin de l'événement
     * @return true si les champs sont valides, false sinon
     */
    public boolean verifierChamp (String objet, String lieu, String description, String d, float heureDebut, float heureFin) {
    if(objet.equals("") || d.equals("") || heureDebut<0 || heureDebut>24 || heureFin<0 || heureFin>24 || heureDebut>=heureFin)  return false;
           else return true;
    }

    /**
     * Méthode qui supprime l'événement. Il sera supprimé lors de la prochaine synchronisation avec le support de persistance.
     */
    public void supprimer () {
       setSuppr(true);
    }

    public long getEventID () {
        return eventID;
    }

    public void setEventID (long val) {
        this.eventID = val;
    }
    public long getAgendaID () {
        return agendaID;
    }

    public void setAgendaID (long val) {
        this.agendaID = val;
    }

    public String getDate () {
        return d;
    }

    public void setDate (String val) {
        this.d = val;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String val) {
        this.description = val;
    }

    public float getHeureDebut () {
        return heureDebut;
    }

    public void setHeureDebut (float val) {
        this.heureDebut = val;
    }

    public float getHeureFin () {
        return heureFin;
    }

    public void setHeureFin (float val) {
        this.heureFin = val;
    }

    public String getLieu () {
        return lieu;
    }

    public void setLieu (String val) {
        this.lieu = val;
    }

    public String getObjet () {
        return objet;
    }

    public void setObjet (String val) {
        this.objet = val;
    }

    public boolean aEteSupprime () {
        return suppr;
    }

    public void setSuppr (boolean b) {
        this.suppr = b;
    }

    public boolean aEteModifie () {
        return modif;
    }

    public void setModif (boolean b) {
        this.modif = b;
    }

    public boolean aEteCree () {
        return nouveau;
    }

    public void setNouveau (boolean b) {
        this.nouveau = b;
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append("(objet)"+objet+" ; (lieu)"+lieu+ " ; (description)"+description);
        return result.toString();
    }
}

