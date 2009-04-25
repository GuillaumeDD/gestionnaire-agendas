<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="GestionAgenda.*" %>
<%@page import="service.*" %>
<%@page import="service.sql.*" %>
<%@page import="Authentification.*" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.logging.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design_new_agenda" href="CSS/new_agenda.css" />
        <title>Modifier Agenda</title>
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

<%
      String modif=request.getParameter("modifier");
      String suppr=request.getParameter("supprimer");
      String select=request.getParameter("select");
      
      if(select!=null)
      {
       Agenda a = new Agenda();
       String agendaID_string = request.getParameter("agenda");
       Long agendaID = Long.parseLong(agendaID_string);
       a = ((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda(agendaID);
       session.setAttribute("agenda_select",a);
       session.setAttribute("agendaID",agendaID);
       }

        if(modif!=null)
        {
        String nom_agenda =request.getParameter("nom_agenda");
        String lieu_agenda = request.getParameter("lieu_agenda");
        String description_agenda = request.getParameter("maDescription");
        String couleur = request.getParameter("choix_couleur");
        ((PortefeuilleAgenda)session.getAttribute("portefeuille")).modifierAgenda(((Agenda)session.getAttribute("agenda_select")).getAgendaID(), nom_agenda, description_agenda, lieu_agenda, couleur);

        //Enregistrement des modifications
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save((PortefeuilleAgenda)session.getAttribute("portefeuille"));

        //Rechargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();
        session.setAttribute("portefeuille", port);
        session.setAttribute("agenda_select",((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("agendaID")));
        
        }

      if(suppr!=null)
        {
        ((PortefeuilleAgenda)session.getAttribute("portefeuille")).supprimerAgenda((Long)session.getAttribute("agendaID"));
        session.setAttribute("agenda_select", null);

        //Enregistrement des modifications
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save((PortefeuilleAgenda)session.getAttribute("portefeuille"));

        //Rechargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();
        session.setAttribute("portefeuille", port);

        }

    %>


<!-- Formulaire de modification d'un agenda  -->
    <div id="cadre_creation"></div>

    <div id="cadre_creation_content">
        <br/>
        <form method="post" action="modif_agenda.jsp" >

        <div id="cadre_titre"><label class ="titre_creation_agenda"> Paramètres des agendas </label></div><br/>

        <!-- Sélection de lagenda à modifier -->
        <label class ="form_new_agenda">Agenda : </label>
        <select name="agenda" name="agenda">
        <%
        for(Agenda ag : ((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgendas().values())
            if(ag.getAgendaID()==((Agenda)session.getAttribute("agenda_select")).getAgendaID())
                out.println("<option value='"+ag.getAgendaID()+"' selected>"+ ag.getNom()+" </option><br/>");
            else
                out.println("<option value='"+ag.getAgendaID()+"'>"+ ag.getNom()+" </option><br/>");
        %>
        </select>
        &nbsp;<input type="submit" class="select_agenda" name="select" value="" ><br/><br/>

        
        <!-- Paramètres de cet agenda -->
        <% if(session.getAttribute("agenda_select") != null)
        {
        out.println("<label class ='form_new_agenda'> Nom : </label><input type='text' name='nom_agenda' value='"+((Agenda)session.getAttribute("agenda_select")).getNom()+"'><br/><br/>");
        out.println("<label class ='form_new_agenda'> Lieu : </label><input type='text' name='lieu_agenda' value='"+((Agenda)session.getAttribute("agenda_select")).getLieu()+"'><br/><br/>");
        out.println("<label class ='form_new_agenda'> Description : </label><textarea rows='5' cols='30' name='maDescription' >"+((Agenda)session.getAttribute("agenda_select")).getDescription()+"</textarea><br/><br/>");
        out.println("<label class ='form_new_agenda'> Couleur : </label>");
        out.println("<TABLE>");
        out.println("<TR><TD class='red'></TD><TD class='yellow'></TD><TD class='blue'></TD><TD class='green'></TD><TD class='cyan'></TD><TD class='pink'></TD><TD class='silver'></TD><TD class='purple'></TD><TD class='lime'></TD><TD class='orange'></TD></TR>");
        out.println("<TR><TD class='choix'><input type='radio' name='choix_couleur' value='red'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='yellow'></TD>");
        out.println("<TD class='choix'><input type='radio' name'choix_couleur' value='blue'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='green'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='aqua'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='fuchsia'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='lime'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='purple'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='silver'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='orange'></TD></TR>");
        out.println("</TABLE>");
        }
        else
        {
        out.println("<label class ='form_new_agenda'> Nom : </label><input type='text' name='nom_agenda' value=''><br/><br/>");
        out.println("<label class ='form_new_agenda'> Lieu : </label><input type='text' name='lieu_agenda' value=''><br/><br/>");
        out.println("<label class ='form_new_agenda'> Description : </label><textarea rows='5' cols='30' name='maDescription' ></textarea><br/><br/>");
        out.println("<label class ='form_new_agenda'> Couleur : </label>");
        out.println("<TABLE>");
        out.println("<TR><TD class='red'></TD><TD class='yellow'></TD><TD class='blue'></TD><TD class='green'></TD><TD class='cyan'></TD><TD class='pink'></TD><TD class='silver'></TD><TD class='purple'></TD><TD class='lime'></TD><TD class='orange'></TD></TR>");
        out.println("<TR><TD class='choix'><input type='radio' name='choix_couleur' value='red'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='yellow'></TD>");
        out.println("<TD class='choix'><input type='radio' name'choix_couleur' value='blue'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='green'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='aqua'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='fuchsia'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='lime'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='purple'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='silver'></TD>");
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='orange'></TD></TR>");
        out.println("</TABLE>");
        }
        %>
        

        <div id="bouton_supprimer">
        <input type="submit" class="supprimer_agenda" name="supprimer" value="" ><br/>
        <label class ="bouton_supprimer"> Supprimer </label>
        </div>

        <div id="bouton_valider">
        <input type="submit" class="valider_agenda" name="modifier" value="" ><br/>
        <label class ="bouton_valider"> Modifier </label>
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
