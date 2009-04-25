<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design" href="CSS/design.css" />
        <title>Gestionnaire d'agendas - Accueil</title>
    </head>
    <body>
    

    <%@page import="GestionAgenda.*" %>
    <%@page import="service.*" %>
    <%@page import="service.sql.*" %>
    <%@page import="Authentification.*" %>
    <%@page session="true" %>

<%
    String connect=request.getParameter("connexion");
    if(connect!=null)
        {
        String login =request.getParameter("user");
        String mdp = request.getParameter("mdp");
        session.setAttribute("user",login);

        out.println("<div id='message'>");
        out.println("<form method='post' action='accueil.jsp'>");
        out.println("<label class='titre1'> Bonjour, "+ session.getAttribute("user")+" </label>  <br/><br/>");
        out.println("<label class='titre1'> Bienvenue dans le gestionnaire d'agendas en ligne </label>  <br/><br/>");
        out.println("<label class='titre1'>  Cliquez sur la flèche pour entrer dans l'application </label> <br/><br/>");
        out.println("<input type='submit' class='in1' id='connect' name='connect' value='' ><br/>");
        out.println("<label class ='bouton1'> Entrer </label>");
        out.println("</form></div>");
        }
    else
        {
        out.println("<div id='titre'> Gestionnaire d'agendas </div>");
        out.println("<div id='login'>");
        out.println("<form method='post' action='identification.jsp'>");
        out.println("<label class='titre'>  Connexion  </label><br/><br/>");
        out.println("<label class='form'>  Nom d'utilisateur :  </label>  <input type='text' name='user'><br/><br/>");
        out.println("<label class='form'>  Mot de passe :  </label>  <input type='text' name='mdp' ><br/><br/><br/>");
        out.println("<input type='submit' class='in' id='connect' name='connexion' value='' ><br/>");
        out.println("<label class ='bouton'> Se connecter </label>");
        out.println("</form></div>");
        }
    %>

    
    
    
    </body>
</html>
