package service.sql;
import service.*;
import GestionAgenda.*;
import java.util.HashMap;
import java.util.logging.*;
import java.sql.*;
/**
 * Classe assurant la persistance d'un objet Evenement pour mySQL
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 * @see Evenement
 */
public class EvenementSQL implements EvenementDAO {

    private BaseDeDonnees bd = BaseDeDonnees.getInstance();
/**
 * Méthode qui permet d'enregistrer un événement
 * @param e : l'événement à enregistrer
 */
    public void insert (Evenement e){
    String req="";
    req="INSERT INTO Evenement(Objet, Lieu, IdAgenda,Description, HeureDebut, HeureFin,Date) VALUES('"+e.getObjet()+"', '"+e.getLieu()+"', "+e.getAgendaID()+", '"+e.getDescription()+"', "+e.getHeureDebut()+", "+e.getHeureFin()+", '"+e.getDate()+"') ";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Méthode qui permet de mettre à jour un événément
 * @param e : l'événement à mettre à jour
 */
    public void update (Evenement e){
    String req="";
    req="UPDATE Evenement SET Objet='"+e.getObjet()+"', Lieu='"+e.getLieu()+"', Description='"+e.getDescription()+"', HeureDebut="+e.getHeureDebut()+", HeureFin="+e.getHeureFin()+", Date='"+e.getDate()+"' WHERE IdEvent="+e.getEventID()+" AND IdAgenda="+e.getAgendaID()+"";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Méthode qui permet de supprimer un événement
 * @param e : événement à supprimer
 */
    public void delete (Evenement e){
    String req="";
    req="DELETE FROM Evenement WHERE IdEvent="+e.getEventID()+" AND IdAgenda="+e.getAgendaID()+"";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/**
 * Méthode qui permet de récupérer l'ensemble des événéments
 * @return l'ensemble des événements
 */
    public HashMap<Long,Evenement> findAll (){
        String req="";
        ResultSet rs = null;

        HashMap<Long,Evenement> evenements = new HashMap();
        req="SELECT * FROM Evenement ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Evenement e = new Evenement();
                e.setEventID((Long) rs.getObject("IdEvent"));
                e.setAgendaID((Long) rs.getObject("IdAgenda"));
                e.setObjet((String) rs.getObject("Objet"));
                e.setDescription((String) rs.getObject("Description"));
                e.setLieu((String) rs.getObject("Lieu"));
                e.setHeureDebut((Float) rs.getObject("HeureDebut"));
                e.setHeureFin((Float) rs.getObject("HeureFin"));
                e.setDate((String) rs.getObject("Date"));
                evenements.put(e.getEventID(), e);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return evenements;
    }
/**
 * Méthode qui permet de récupérer un événement à partir de son identifiant unique
 * @param eventID : identifiant unique de l'événement
 * @return l'événement dont l'identifiant est eventID
 */
    public Evenement findByPrimaryKey (long eventID){
    String req="";
        ResultSet rs = null;
        Evenement e = new Evenement();
        req="SELECT * FROM Evenement WHERE IdEvent="+eventID+" ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                e.setEventID((Long) rs.getObject("IdEvent"));
                e.setAgendaID((Long) rs.getObject("IdAgenda"));
                e.setObjet((String) rs.getObject("Objet"));
                e.setDescription((String) rs.getObject("Description"));
                e.setLieu((String) rs.getObject("Lieu"));
                e.setHeureDebut((Float) rs.getObject("HeureDebut"));
                e.setHeureFin((Float) rs.getObject("HeureFin"));
                e.setDate((String) rs.getObject("Date"));
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return e;
    }
/**
 * Méthode qui permet de récupérer l'ensemble des événements dont la date de réalisation est après d
 * @param d : date plancher
 * @return l'ensemble des événements dont la date de réalisation est après d
 */
    public HashMap<Long,Evenement> findAfter (Date d){
    String req="";
        ResultSet rs = null;

        HashMap<Long,Evenement> evenements = new HashMap();
        req="SELECT * FROM Evenement WHERE Date >= "+d+" ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Evenement e = new Evenement();
                e.setEventID((Long) rs.getObject("IdEvent"));
                e.setAgendaID((Long) rs.getObject("IdAgenda"));
                e.setObjet((String) rs.getObject("Objet"));
                e.setDescription((String) rs.getObject("Description"));
                e.setLieu((String) rs.getObject("Lieu"));
                e.setHeureDebut((Float) rs.getObject("HeureDebut"));
                e.setHeureFin((Float) rs.getObject("HeureFin"));
                e.setDate((String) rs.getObject("Date"));
                evenements.put(e.getEventID(), e);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return evenements;
    }
/**
 * Méthode qui permet de récupérer l'ensemble des événements dont la date de réalisation est avant d
 * @param d : date plancher
 * @return l'ensemble des événements dont la date de réalisation est avant d
 */
    public HashMap<Long,Evenement> findBefore (Date d){
        String req="";
        ResultSet rs = null;

        HashMap<Long,Evenement> evenements = new HashMap();
        req="SELECT * FROM Evenement WHERE Date <= "+d+" ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Evenement e = new Evenement();
                e.setEventID((Long) rs.getObject("IdEvent"));
                e.setAgendaID((Long) rs.getObject("IdAgenda"));
                e.setObjet((String) rs.getObject("Objet"));
                e.setDescription((String) rs.getObject("Description"));
                e.setLieu((String) rs.getObject("Lieu"));
                e.setHeureDebut((Float) rs.getObject("HeureDebut"));
                e.setHeureFin((Float) rs.getObject("HeureFin"));
                e.setDate((String) rs.getObject("Date"));
                evenements.put(e.getEventID(), e);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return evenements;

    }
/**
 * Méthode qui permet de récupérer l'ensemble des événements dont la date de réalisation est entre d1 et d2
 * @param d1 : date plancher
 * @param d2 : date plafond
 * @return l'ensemble des événements dont la date de réalisation est entre d1 et d2
 */
    public HashMap<Long,Evenement> findBetween (Date d1, Date d2){
    String req="";
        ResultSet rs = null;

        HashMap<Long,Evenement> evenements = new HashMap();
        req="SELECT * FROM Evenement WHERE Date >= "+d1+" AND Date <= "+d2+" ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Evenement e = new Evenement();
                e.setEventID((Long) rs.getObject("IdEvent"));
                e.setAgendaID((Long) rs.getObject("IdAgenda"));
                e.setObjet((String) rs.getObject("Objet"));
                e.setDescription((String) rs.getObject("Description"));
                e.setLieu((String) rs.getObject("Lieu"));
                e.setHeureDebut((Float) rs.getObject("HeureDebut"));
                e.setHeureFin((Float) rs.getObject("HeureFin"));
                e.setDate((String) rs.getObject("Date"));
                evenements.put(e.getEventID(), e);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return evenements;
    }
/**
 * Méthode qui permet de récupérer l'ensemble des événements de l'agenda dont l'identifiant est agendaID
 * @param agendaID : identifiant de l'agenda
 * @return l'ensemble des événements de l'agenda dont l'identifiant est agendaID
 */
    public HashMap<Long,Evenement> findByAgenda (long agendaID){
    String req="";
        ResultSet rs = null;

        HashMap<Long,Evenement> evenements = new HashMap();
        req="SELECT * FROM Evenement WHERE IdAgenda="+agendaID+" ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Evenement e = new Evenement();
                e.setEventID((Long) rs.getObject("IdEvent"));
                e.setAgendaID((Long) rs.getObject("IdAgenda"));
                e.setObjet((String) rs.getObject("Objet"));
                e.setDescription((String) rs.getObject("Description"));
                e.setLieu((String) rs.getObject("Lieu"));
                e.setHeureDebut((Float) rs.getObject("HeureDebut"));
                e.setHeureFin((Float) rs.getObject("HeureFin"));
                e.setDate((String) rs.getObject("Date").toString());
                evenements.put(e.getEventID(), e);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return evenements;

    }



}