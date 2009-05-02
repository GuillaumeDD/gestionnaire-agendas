<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="GestionAgenda.*" %>
<%@page import="service.*" %>
<%@page import="service.sql.*" %>
<%@page import="Authentification.*" %>
<%@page import="Exception.*" %>
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

    <!-- Formulaire de modification d'un agenda  -->
    <div id="cadre_creation"></div>

    <div id="cadre_creation_content">

<%

        //Chargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();

      String modif=request.getParameter("modifier");
      String suppr=request.getParameter("supprimer");
      String select=request.getParameter("select");
      
      if(select!=null)
      {
       String agendaID_string = request.getParameter("agenda");
       Long agendaID = Long.parseLong(agendaID_string);
       session.setAttribute("agendaID",agendaID);
       }
       

        if(modif!=null)
        {
        if(session.getAttribute("agendaID") !=null)
        {
        String nom_agenda =request.getParameter("nom_agenda");
        String lieu_agenda = request.getParameter("lieu_agenda");
        String description_agenda = request.getParameter("maDescription");
        String couleur = request.getParameter("choix_couleur");
        try
        {
        port.modifierAgenda((Long)session.getAttribute("agendaID"), nom_agenda, description_agenda, lieu_agenda, couleur);
        out.println("<div id='message_ok'> L'agenda a été modifié. </div>");
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
        }

      if(suppr!=null)
        {
         if(session.getAttribute("agendaID") !=null)
        {
        port.supprimerAgenda((Long)session.getAttribute("agendaID"));
        session.setAttribute("agendaID", null);
        out.println("<div id='message_ok'> L'agenda a été supprimé. </div>");

        //Enregistrement des modifications et rechargement du portefeuille d'agendas
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save(port);
        port.initialiser();

        }
        }


    %>

        <br/>
        <form method="post" action="modif_agenda.jsp" >

        <div id="cadre_titre"><label class ="titre_creation_agenda"> Paramètres des agendas </label></div><br/>

        <!-- Sélection de lagenda à modifier -->
        <label class ="form_new_agenda">Agenda : </label>
        <select name="agenda" name="agenda">
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
        </select>
        &nbsp;<input type="submit" class="select_agenda" name="select" value="" ><br/><br/>

        
        <!-- Paramètres de cet agenda -->
        
        <%
        if(session.getAttribute("agendaID") != null)
        {
        out.println("<label class ='form_new_agenda'> Nom : </label><input type='text' name='nom_agenda' value='"+port.getAgenda((Long)session.getAttribute("agendaID")).getNom()+"'><br/><br/>");
        out.println("<label class ='form_new_agenda'> Lieu : </label><input type='text' name='lieu_agenda' value='"+port.getAgenda((Long)session.getAttribute("agendaID")).getLieu()+"'><br/><br/>");
        out.println("<label class ='form_new_agenda'> Description : </label><textarea rows='5' cols='30' name='maDescription' >"+port.getAgenda((Long)session.getAttribute("agendaID")).getDescription()+"</textarea><br/><br/>");
        out.println("<label class ='form_new_agenda'> Couleur : </label>");
        out.println("<TABLE>");
        out.println("<TR><TD class='red'></TD><TD class='yellow'></TD><TD class='blue'></TD><TD class='green'></TD><TD class='cyan'></TD><TD class='pink'></TD><TD class='lime'></TD><TD class='purple'></TD><TD class='silver'></TD><TD class='orange'></TD></TR>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("red"))
            out.println("<TR><TD class='choix'><input type='radio' name='choix_couleur' value='red' checked='checked' ></TD>");
        else out.println("<TR><TD class='choix'><input type='radio' name='choix_couleur' value='red'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("yellow"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='yellow' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='yellow'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("blue"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='blue' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='blue'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("green"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='green' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='green'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("aqua"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='aqua' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='aqua'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("fushia"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='fuchsia' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='fuchsia'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("lime"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='lime' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='lime'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("purple"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='purple' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='purple'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("silver"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='silver' checked='checked' ></TD>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='silver'></TD>");
        if(port.getAgenda((Long)session.getAttribute("agendaID")).getColor().equals("orange"))
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='orange' checked='checked' ></TD></TR>");
        else out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='orange'></TD></TR>");
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
        out.println("<TD class='choix'><input type='radio' name='choix_couleur' value='blue'></TD>");
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
