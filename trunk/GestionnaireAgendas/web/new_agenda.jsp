<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@page import="GestionAgenda.*" %>
<%@page import="service.*" %>
<%@page import="service.sql.*" %>
<%@page import="Authentification.*" %>
<%@page import="Exception.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.logging.*" %>
<%@include file="authentifier.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design_new_agenda" href="CSS/new_agenda.css" />
        <title>Nouvel Agenda</title>
    </head>
    <body>


    <div id="logo">
    </div>

<!-- Haut de page : message d'accueil -->
    <div id="haut_page">
        <div id="message_accueil"> Bonjour <%=session.getAttribute("login")%>, <br/> nous sommes le <%=session.getAttribute("dateDuJour")%> </div>
        <div id="deconnexion">
                <form method="post" action="identification.jsp" >
                <input type="submit" class="out" name="disconnect" value="" ><br/>
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
        
        String nom_agenda = request.getParameter("nom_agenda");
        String lieu_agenda = request.getParameter("lieu_agenda");
        String description = request.getParameter("maDescription");
        String couleur = request.getParameter("choix_couleur");
        try
            {
            port.creerAgenda(nom_agenda, description, lieu_agenda, couleur);
            out.println("<div id='message_ok'> L'agenda a été créé. </div>");
            }
        catch(NomVideException e)
                {out.println("<div id='message_erreur'> ERREUR : Le champ Nom n'a pas été renseigné. </div>");}
        catch(NomExistantException e1)
                {out.println("<div id='message_erreur'> ERREUR : Un agenda porte déjà ce nom. </div>");}

        //Enregistrement des modifications et rechargement du portefeuille d'agendas
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save(port);
        port.initialiser();
        
        }
    %>


        <br/>
        <form method="post" action="new_agenda.jsp" >

        <div id="cadre_titre"><label class ="titre_creation_agenda"> Création d'un nouvel agenda </label></div><br/><br/>
        <label class ="form_new_agenda"> Nom : </label><input type="text" name="nom_agenda"><br/><br/>
        <label class ="form_new_agenda"> Lieu : </label><input type="text" name="lieu_agenda"><br/><br/>
        <label class ="form_new_agenda"> Description : </label><textarea rows="5" cols="30" name="maDescription" id="description_agenda"></textarea><br/><br/>
        <label class ="form_new_agenda"> Couleur : </label>
        <TABLE>
        <TR><TD class="red"></TD><TD class="yellow"></TD><TD class="blue"></TD><TD class="green"></TD><TD class="cyan"></TD><TD class="pink"></TD><TD class="silver"></TD><TD class="purple"></TD><TD class="lime"></TD><TD class="orange"></TD></TR>
        <TR><TD class="choix"><input type="radio" name="choix_couleur" value="red"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="yellow"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="blue"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="green"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="aqua"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="fuchsia"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="lime"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="purple"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="silver"></TD>
        <TD class="choix"><input type="radio" name="choix_couleur" value="orange"></TD></TR>
        </TABLE>

        <br/>
        <div id="bouton_valider">
        <input type="submit" class="valider_agenda" name="creer" value="" ><br/>
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
