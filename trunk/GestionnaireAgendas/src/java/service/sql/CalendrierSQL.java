/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.sql;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Pauline
 */
public class CalendrierSQL {

    private BaseDeDonnees bd=BaseDeDonnees.getInstance();

    public int findWeekOfADay(String date)
    {
        int weekID=0;
        String req="";
        ResultSet rs = null;
        ArrayList<String> semaine = new ArrayList();
        req="SELECT IdSemaine FROM Calendrier WHERE Date='"+date+"' ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                weekID=(Integer)rs.getObject("IdSemaine");
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(CalendrierSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return weekID;
    }
    public ArrayList<String> findWeekByID(int weekID)
    {
        String req="";
        ResultSet rs = null;
        int i=0;
        ArrayList<String> semaine = new ArrayList();
        req="SELECT Date,DateFR FROM Calendrier WHERE IdSemaine="+weekID+" ORDER BY Date ";
        try {
            rs=bd.executerSELECT(req);
            while(rs.next())
                {
                semaine.add(i,(String)rs.getObject("Date").toString());
                semaine.add(i+1,(String)rs.getObject("DateFR").toString());
                i=i+2;
                }
            }
        catch (SQLException ex) {
            Logger.getLogger(CalendrierSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return semaine;
        
    }

}
