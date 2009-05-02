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
        <link rel="stylesheet" media="screen" type="text/css" title="Design_new_rdv" href="CSS/new_rdv.css" />
        <title>Modification Evènement</title>
        
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

<!-- Formulaire de modification des paramètres d'un evenement ou suppression de l'evenement -->
    <div id="cadre_creation"></div>

    <div id="cadre_creation_content">

     <%
        String event_select = request.getParameter("select_event");
        if( event_select != null)
            session.setAttribute("SelectEventID", Long.parseLong(event_select));
            
        
      for(Agenda a:((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgendas().values())
          {
          if(a.getEvenement((Long)session.getAttribute("SelectEventID")) != null)      
              session.setAttribute("SelectAgendaID", a.getAgendaID());
              
          }
      
      String modification=request.getParameter("modifier");
      String suppression=request.getParameter("supprimer");

      if(session.getAttribute("SelectEventID")!=null && session.getAttribute("SelectAgendaID")!=null)
      {
      if(modification!=null)
        {
        String objet = request.getParameter("objet_rdv");
        String lieu = request.getParameter("lieu_rdv");
        String date = request.getParameter("date_rdv");
        String heure_debut = request.getParameter("heure_debut_rdv");
        String heure_fin = request.getParameter("heure_fin_rdv");
        String description = request.getParameter("maDescription");
        float heureDebut=0,heureFin=0;
        if(heure_debut!="") heureDebut= Float.parseFloat(heure_debut);
        if(heure_fin!="") heureFin= Float.parseFloat(heure_fin);

        try
          {
           ((PortefeuilleAgenda)session.getAttribute("portefeuille")).modifierEvenement((Long)session.getAttribute("SelectAgendaID"),(Long)session.getAttribute("SelectEventID"),objet,lieu,description,date,heureDebut,heureFin);
           out.println("<div id='message_ok'> L'évènement a été modifié. </div>");
           }
        catch(EvenementSimultaneException e3)
                {out.println("<div id='message_erreur'> ERREUR : Evènement simultané existant. </div>");}
        catch(ChampsMalRenseignesException e4)
                {out.println("<div id='message_erreur'> ERREUR : Champs mal renseignés. </div>");}

        //Enregistrement des modifications
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save((PortefeuilleAgenda)session.getAttribute("portefeuille"));

        //Rechargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();
        session.setAttribute("portefeuille", port);

        }

      if(suppression!=null)
          {
          ((PortefeuilleAgenda)session.getAttribute("portefeuille")).supprimerEvenement((Long)session.getAttribute("SelectAgendaID"),(Long)session.getAttribute("SelectEventID"));
          session.setAttribute("SelectEventID",null);
          session.setAttribute("SelectAgendaID",null);
          out.println("<div id='message_ok'> L'évènement a été supprimé. </div>");

        //Enregistrement des modifications
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save((PortefeuilleAgenda)session.getAttribute("portefeuille"));

        //Rechargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();
        session.setAttribute("portefeuille", port);
        }
      }
    %>


        <br/>
        <form method="post" action="modif_rdv.jsp" >

        <div id="cadre_titre"><label class ="titre_form_rdv"> Modification d'un évènement </label></div><br/>

        <% if(session.getAttribute("SelectEventID")!=null && session.getAttribute("SelectAgendaID")!=null)
            {
            out.println("<label class ='form_new_rdv'> Objet : </label><input type='text' name='objet_rdv' value='"+((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getObjet()+"'><br/><br/>");
            out.println("<label class ='form_new_rdv'> Date : </label><input type='text' name='date_rdv' value='"+((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getDate()+"'>&nbsp;&nbsp;&nbsp;&nbsp;<label class='info'>Format: AAAA-MM-JJ</label><br/><br/>");
            out.println("<label class ='form_new_rdv'> Lieu : </label><input type='text' name='lieu_rdv' value='"+((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getLieu()+"'><br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de début : </label><input type='text' name='heure_debut_rdv' value='"+((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getHeureDebut()+"'>&nbsp;&nbsp;&nbsp;&nbsp;<label class='info'>Exemple: pour 10h30 écrire 10,5</label><br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de fin : </label><input type='text' name='heure_fin_rdv' value='"+ ((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getHeureFin()+"'><br/><br/>");
            out.println("<label class ='form_new_rdv'> Description : </label><textarea rows='5' cols='30' name='maDescription' id='description_agenda'>"+ ((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getDescription()+"</textarea><br/><br/>");
            }
      else
          {
            out.println("<label class ='form_new_rdv'> Objet : </label><input type='text' name='objet_rdv' value=''><br/><br/>");
            out.println("<label class ='form_new_rdv'> Date : </label><input type='text' name='date_rdv' value=''>&nbsp;&nbsp;&nbsp;&nbsp;<label class='info'>Format: AAAA-MM-JJ</label><br/><br/>");
            out.println("<label class ='form_new_rdv'> Lieu : </label><input type='text' name='lieu_rdv' value=''><br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de début : </label><input type='text' name='heure_debut_rdv' value=''>&nbsp;&nbsp;&nbsp;&nbsp;<label class='info'>Exemple: pour 10h30 écrire 10,5</label><br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de fin : </label><input type='text' name='heure_fin_rdv' value=''><br/><br/>");
            out.println("<label class ='form_new_rdv'> Description : </label><textarea rows='5' cols='30' name='maDescription' id='description_agenda'></textarea><br/><br/>");
            }

        %>
        <div id="bouton_supprimer">
        <input type="submit" class="supprimer_rdv" name="supprimer" value="" ><br/>
        <label class ="bouton_supprimer"> Supprimer </label>
        </div>

        <div id="bouton_valider">
        <input type="submit" class="valider_rdv" name="modifier" value="" ><br/>
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
