package service.sql;
import service.*;
import GestionAgenda.*;
import java.util.HashMap;
import java.util.logging.*;
import java.sql.*;

public class EvenementSQL implements EvenementDAO {

    private BaseDeDonnees bd = BaseDeDonnees.getInstance();

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