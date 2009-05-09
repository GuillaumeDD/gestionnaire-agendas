

package service.sql;

import service.*;
import GestionAgenda.*;
import Authentification.*;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.*;
/**
 * Classe assurant la persistance d'un objet Agenda pour mySQL
 * @author Pauline REQUENA
 * @author Guillaume DUBUISSON DUPLESSIS
 * @see Agenda
 */
public class AgendaSQL implements AgendaDAO {

    private BaseDeDonnees bd = BaseDeDonnees.getInstance();

    public AgendaSQL(){
        //bd.connexion();
    }
/**
 * Méthode qui permet de sauvegarder l'ensemble des événements de l'agenda
 * @param a : l'agenda à sauvegarder
 */
    public void save(Agenda a)
    {
        for(Evenement boucle : a.getEvenements().values())
        {
            if(boucle.aEteCree()) saveEvenement(boucle);
            else if (boucle.aEteModifie()) updateEvenement(boucle);
            else if(boucle.aEteSupprime()) deleteEvenement(boucle);
        }
    }
/**
 * Cette méthode est un appel caché à la méthode insert de EvenementSQL
 * @see EvenementSQL
 * @param e : evenement à enregistrer
 */
    public void saveEvenement (Evenement e){
    EvenementSQL esql = new EvenementSQL();
    esql.insert(e);
    }
/**
 * Cette méthode est un appel caché à la méthode delete de EvenementSQL
 * @see EvenementSQL
 * @param e : evenement à supprimer
 */
    public void deleteEvenement (Evenement e){
    EvenementSQL esql = new EvenementSQL();
    esql.delete(e);
    }
/**
 * Cette méthode est un appel caché à la méthode update de EvenementSQL
 * @see EvenementSQL
 * @param e : evenement à mettre à jour
 */
    public void updateEvenement (Evenement e){
    EvenementSQL esql = new EvenementSQL();
    esql.update(e);
    }
/**
 * Méthode qui permet d'enregistrer un agenda
 * @param a : agenda à enregistrer
 */
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
/**
 * Méthode qui permet de mettre à jour un agenda
 * @param a : agenda à mettre à jour
 */
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
   /**
     * Méthode qui permet de supprimer un agenda
     * @param a : agenda à supprimer
     */
    public void delete (Agenda a){
    String req="";
    EvenementSQL e_sql=new EvenementSQL();
    req="DELETE FROM Agenda WHERE IdAgenda="+a.getAgendaID()+" ";
    try {
        bd.executerMAJ(req);
        }
    catch (SQLException ex) {
            Logger.getLogger(AgendaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    for(Evenement e:a.getEvenements().values())
        e_sql.delete(e);

    }
/**
 * Méthode qui permet d'obtenir tous les agendas
 * @return l'ensemble des agendas
 */
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
    /**
     * Méthode qui permet d'obtenir l'agenda dont l'identifiant est agendaID
     * @param agendaID : identifiant de l'agenda recherché
     * @return l'agenda dont l'identifiant est agendaID
     */
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
    /**
     * Méthode qui permet d'obtenir les agendas associés à l'utilisateur u
     * @param u : utilisateur dont on recherche les agendas
     * @return l'ensemble des agendas de l'utilisateur u
     */
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
