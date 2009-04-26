package GestionAgenda;

public class Evenement {

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

    public Evenement () {
    }

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

    public boolean estValideEvenement () {
        if(verifierChamp(getObjet(),getLieu(),getDescription(),getDate(),getHeureDebut(),getHeureFin())==true)
            return true;
        else return false;
    }

    public boolean verifierChamp (String objet, String lieu, String description, String d, float heureDebut, float heureFin) {
    if(objet.equals("") || d.equals("") || heureDebut==0 || heureFin==0)  return false;
           else return true;
    }

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

}

