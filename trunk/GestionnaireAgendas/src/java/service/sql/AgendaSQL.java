

package service.sql;

import service.*;
import GestionAgenda.*;
import Authentification.*;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.*;

public class AgendaSQL implements AgendaDAO {

    private BaseDeDonnees bd = new BaseDeDonnees();

    public AgendaSQL(){
        bd.connexion();
    }

    public void insert (Agenda a){
    String req="";
    req="INSERT INTO Agenda(NomAgenda, LieuAgenda, Description, Couleur, IdUser) VALUES('"+a.getNom()+"', '"+a.getLieu()+"', '"+a.getDescription()+"', '"+a.getColor()+"', "+a.getUserID()+") ";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update (Agenda a){
    String req="";
    req="UPDATE Agenda SET NomAgenda='"+a.getNom()+"', LieuAgenda='"+a.getLieu()+"', Description='"+a.getDescription()+"', Couleur='"+a.getColor()+"', IdUser="+a.getUserID()+" WHERE IdAgenda="+a.getAgendaID()+" ";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete (Agenda a){
    String req="";
    req="DELETE FROM Agenda WHERE IdAgenda="+a.getAgendaID()+" ";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<Long,Agenda> findAll (){
        String req="";
        ResultSet rs = null;
        HashMap<Long,Agenda> agendas = new HashMap();
        req="SELECT * FROM Agenda ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Agenda a = new Agenda();
                a.setAgendaID((Long) rs.getObject("IdAgenda"));
                a.setNom((String) rs.getObject("NomAgenda"));
                a.setDescription((String) rs.getObject("Description"));
                a.setLieu((String) rs.getObject("LieuAgenda"));
                a.setColor((String) rs.getObject("Couleur"));
                a.setUserID((Long) rs.getObject("IdUser"));
                agendas.put(a.getAgendaID(), a);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return agendas;
    }

    public Agenda findByPrimaryKey (long agendaID){
        String req="";
        ResultSet rs = null;
        Agenda a = new Agenda();
        req="SELECT * FROM Agenda WHERE IdAgenda="+agendaID+"";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                a.setAgendaID((Long) rs.getObject("IdAgenda"));
                a.setNom((String) rs.getObject("NomAgenda"));
                a.setDescription((String) rs.getObject("Description"));
                a.setLieu((String) rs.getObject("LieuAgenda"));
                a.setColor((String) rs.getObject("Couleur"));
                a.setUserID((Long) rs.getObject("IdUser"));
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return a;}

    public HashMap<Long,Agenda> findByUser (Utilisateur u) {
        EvenementSQL e_sql = new EvenementSQL();
        String req="";
        ResultSet rs = null;
        HashMap<Long,Agenda> agendas = new HashMap();
        HashMap<Long,Evenement> evenements = new HashMap();
        req="SELECT * FROM Agenda WHERE IdUser='"+u.getUserID()+"' ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                Agenda a = new Agenda();
                a.setAgendaID((Long) rs.getObject("IdAgenda"));
                a.setNom((String) rs.getObject("NomAgenda"));
                a.setDescription((String) rs.getObject("Description"));
                a.setLieu((String) rs.getObject("LieuAgenda"));
                a.setColor((String) rs.getObject("Couleur"));
                a.setUserID((Long) rs.getObject("IdUser"));
                evenements=e_sql.findByAgenda(a.getAgendaID());
                a.setEvenements(evenements);
                agendas.put(a.getAgendaID(), a);
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(agendas.isEmpty()) return null;
        else return agendas;}

}
