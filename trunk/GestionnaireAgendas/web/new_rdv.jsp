<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="GestionAgenda.*" %>
<%@page import="service.*" %>
<%@page import="service.sql.*" %>
<%@page import="Authentification.*" %>
<%@page import="Exception.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.logging.*" %>


<%@page import="java.text.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design_new_agenda" href="CSS/new_rdv.css" />
        <title>Nouvel Evènement</title>
    </head>
    <body>


    <div id="logo">
    </div>

<!-- Haut de page : message d'accueil -->
    <div id="haut_page">
        <div id="message_accueil"> Bonjour <%=session.getAttribute("user")%>, <br/> nous sommes le <%=session.getAttribute("dateDuJour")%> </div>
        <div id="deconnexion">
                <form method="post" action="identification.jsp" >
                <input type="submit" class="out" id="disconnect" value="" ><br/>
                <label class ="bouton_deco"> Déconnexion </label>
                </form>
        </div>
    </div>

<!-- Formulaire de modification des paramètres d'un agenda ou suppression de l'agenda -->
    <div id="cadre_creation"></div>

    <div id="cadre_creation_content">

     <%

     //Chargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();

     String creation=request.getParameter("creer");
     if(creation!=null)
        {
        String objet = request.getParameter("objet_rdv");
        String lieu = request.getParameter("lieu_rdv");
        String date = request.getParameter("date_rdv");
        String heure_debut = request.getParameter("heure_debut_rdv");
        String heure_fin = request.getParameter("heure_fin_rdv");
        String description = request.getParameter("maDescription");
        String agendaID_select = request.getParameter("agenda");
        long agendaID=0;
        float heureDebut=0,heureFin=0;
        if(agendaID_select!="") agendaID= Long.parseLong(agendaID_select);
        if(heure_debut!="") heureDebut= Float.parseFloat(heure_debut);
        if(heure_fin!="") heureFin= Float.parseFloat(heure_fin);

        try
          {port.creerEvenement(agendaID,objet,lieu,description,date,heureDebut,heureFin);
           out.println("<div id='message_ok'> L'évènement a été créé. </div>");}
        catch(EvenementSimultaneException e)
                {out.println("<div id='message_erreur'> ERREUR : Evènement simultané existant. </div>");}
        catch(ChampsMalRenseignesException e1)
                {out.println("<div id='message_erreur'> ERREUR : Champs mal renseignés. </div>");}

        
        //Enregistrement des modifications et rechargement du portefeuille d'agendas
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save(port);
        port.initialiser();
 

        }
    %>


        <br/>
        <form method="post" action="new_rdv.jsp" >

        <div id="cadre_titre"><label class ="titre_form_rdv"> Création d'un évènement </label></div><br/>
        <label class ="form_new_rdv"> Objet : </label><input type="text" name="objet_rdv"><br/><br/>
        <label class ="form_new_rdv"> Date : </label><input type="text" name="date_rdv">&nbsp;&nbsp;&nbsp;&nbsp;<label class="info">Format: AAAA-MM-JJ</label><br/><br/>
        <label class ="form_new_rdv"> Lieu : </label><input type="text" name="lieu_rdv"><br/><br/>
        <label class ="form_new_rdv"> Heure de début : </label><input type="text" name="heure_debut_rdv">&nbsp;&nbsp;&nbsp;&nbsp;<label class="info">Exemple: pour 10h30 écrire 10,5</label><br/><br/>
        <label class ="form_new_rdv"> Heure de fin : </label><input type="text" name="heure_fin_rdv"><br/><br/>
        <label class ="form_new_rdv"> Agenda : </label>
        <select name="agenda" >
           <%
        for(Agenda ag : port.getAgendas().values())
            if((Long)session.getAttribute("agendaID") != null)
                {
                if(ag.getAgendaID()==(Long)session.getAttribute("agendaID"))
                    out.println("<option value='"+ag.getAgendaID()+"' selected>"+ ag.getNom()+" </option><br/>");
                else
                    out.println("<option value='"+ag.getAgendaID()+"'>"+ ag.getNom()+" </option><br/>");
                }
            else out.println("<option value='"+ag.getAgendaID()+"'>"+ ag.getNom()+" </option><br/>");
        %>
        </select><br/><br/>
        <label class ="form_new_rdv"> Description : </label><textarea rows="5" cols="30" name="maDescription" id="description_agenda"></textarea><br/><br/>


        <div id="bouton_valider">
        <input type="submit" class="valider_rdv" name="creer" value="" ><br/>
        <label class ="bouton_valider"> Créer </label>
        </div>

        </form>


    </div>

<!-- Retour à la page d'accueil : Agenda hebdomadaire -->
        <form method="post" action="accueil.jsp" >
        <div id="retour_agendas">
        <input type="submit" class="retour_agenda" name="retour" value="" ><br/>
        <label class ="bouton_retour"> Retour à l'agenda </label>
        </div>
        </form>

    </body>
</html>