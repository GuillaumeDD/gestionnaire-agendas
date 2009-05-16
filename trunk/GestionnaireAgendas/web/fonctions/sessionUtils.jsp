<%-- 
    Document   : sessionUtils
    Created on : 2 mai 2009, 20:31:12
    Author     : GDD
--%>
    <%@page import="GestionAgenda.*" %>
    <%@page import="service.*" %>
    <%@page import="service.sql.*" %>
    <%@page import="Exception.*" %>
    <%@page import="Authentification.*" %>
    <%@page session="true" %>
<%!
boolean estConnecte(Integer userID, String IP){
    boolean result = false;
    if(userID != null){
        UtilisateurSQL uService = new UtilisateurSQL();
        SessionSQL sService = new SessionSQL();
        Utilisateur u = null;

        sService.cleanUp();

        try{
            u = uService.findByID(userID);
        }catch(UtilisateurInexistantException ex){
            result = false;
        }

        try{
            sService.findByUser(u, IP);
            result = true;
        }catch(SessionInexistanteException ex){
            result = false;
        }
    }
    return result;
}

Session getSession(int userID, String IP){
    Session s = null;
    Utilisateur u = null;
    SessionSQL sSession = new SessionSQL();
    sSession.cleanUp();
    try{
        u = (new UtilisateurSQL()).findByID(userID);
    }catch(UtilisateurInexistantException ex){
        
    }

    try{
        s = sSession.findByUser(u, IP);
        sSession.update(s);
    }catch(SessionInexistanteException ex){
        s = null;
        sSession.delete(s);
    }
    return s;
}
%>