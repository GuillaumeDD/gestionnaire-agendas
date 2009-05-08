<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@include file="authentifier.jsp" %>

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
        <div id="message_accueil"> Bonjour <%=session.getAttribute("login")%>, <br/> nous sommes le <%=session.getAttribute("dateDuJour")%> </div>
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

     //Chargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("user"));
        port.initialiser();

        String event_select = request.getParameter("select_event");
        if( event_select != null)
            session.setAttribute("SelectEventID", Long.parseLong(event_select));
            
        
      for(Agenda a:port.getAgendas().values())
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
        String heure_debut1 = request.getParameter("heure_debut_rdv1");
        String heure_debut2 = request.getParameter("heure_debut_rdv2");
        String heure_fin1 = request.getParameter("heure_fin_rdv1");
        String heure_fin2 = request.getParameter("heure_fin_rdv2");
        String description = request.getParameter("maDescription");
        float hDebut=0,hFin=0,minDebut=0,minFin=0;
        hDebut= Float.parseFloat(heure_debut1);
        minDebut= Float.parseFloat(heure_debut2);
        hFin= Float.parseFloat(heure_fin1);
        minFin= Float.parseFloat(heure_fin2);

        float heureDebut,heureFin;
        heureDebut=hDebut+(minDebut/60);
        heureFin=hFin+(minFin/60);

        try
          {
           port.modifierEvenement((Long)session.getAttribute("SelectAgendaID"),(Long)session.getAttribute("SelectEventID"),objet,lieu,description,date,heureDebut,heureFin);
           out.println("<div id='message_ok'> L'évènement a été modifié. </div>");
           }
        catch(EvenementSimultaneException e3)
                {out.println("<div id='message_erreur'> ERREUR : Evènement simultané existant. </div>");}
        catch(ChampsMalRenseignesException e4)
                {out.println("<div id='message_erreur'> ERREUR : Champs mal renseignés. </div>");}

        //Enregistrement des modifications et rechargement du portefeuille d'agendas
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save(port);
        port.initialiser();

        }

      if(suppression!=null)
          {
          port.supprimerEvenement((Long)session.getAttribute("SelectAgendaID"),(Long)session.getAttribute("SelectEventID"));
          session.setAttribute("SelectEventID",null);
          session.setAttribute("SelectAgendaID",null);
          out.println("<div id='message_ok'> L'évènement a été supprimé. </div>");

        //Enregistrement des modifications et rechargement du portefeuille d'agendas
        PortefeuilleAgendaSQL pa_sql = new PortefeuilleAgendaSQL();
        pa_sql.save(port);
        port.initialiser();

        }
      }
    %>


        <br/>
        <form method="post" action="modif_rdv.jsp" >

        <div id="cadre_titre"><label class ="titre_form_rdv"> Modification d'un évènement </label></div><br/>

        <% if(session.getAttribute("SelectEventID")!=null && session.getAttribute("SelectAgendaID")!=null)
            {
            out.println("<label class ='form_new_rdv'> Objet : </label><input type='text' name='objet_rdv' value='"+port.getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getObjet()+"'><br/><br/>");
            out.println("<label class ='form_new_rdv'> Date : </label><input type='text' name='date_rdv' value='"+port.getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getDate()+"'>&nbsp;&nbsp;&nbsp;&nbsp;<label class='info'>Format: AAAA-MM-JJ</label><br/><br/>");
            out.println("<label class ='form_new_rdv'> Lieu : </label><input type='text' name='lieu_rdv' value='"+port.getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getLieu()+"'><br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de début : </label>");
            float heureDeDebut=port.getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getHeureDebut();
            float heureDeFin=port.getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getHeureFin();
            int h1=(int)heureDeDebut;
            int m1=(int)((heureDeDebut - (int)heureDeDebut)*60);
            int h2=(int)heureDeFin;
            int m2=(int)((heureDeFin - (int)heureDeFin)*60);
            int i=0;
            out.println("<select name='heure_debut_rdv1'>");
            for(i=0;i<=23;i++)
                if(i==h1)
                    out.println("<option value='"+i+"' selected>"+ i +"</option>");
                else out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");
            out.println("<select name='heure_debut_rdv2'>");
            for(i=0;i<=59;i++)
                if(i==m1)
                    out.println("<option value='"+i+"' selected>"+ i +"</option>");
                else out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");

            out.println("<br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de fin : </label>");

            out.println("<select name='heure_fin_rdv1'>");
            for(i=0;i<=23;i++)
                if(i==h2)
                    out.println("<option value='"+i+"' selected>"+ i +"</option>");
                else out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");
            out.println("<select name='heure_fin_rdv2'>");
            for(i=0;i<=59;i++)
                if(i==m2)
                    out.println("<option value='"+i+"' selected>"+ i +"</option>");
                else out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");
            out.println("<br/><br/>");
            out.println("<label class ='form_new_rdv'> Description : </label><textarea rows='5' cols='30' name='maDescription' id='description_agenda'>"+ port.getAgenda((Long)session.getAttribute("SelectAgendaID")).getEvenement((Long)session.getAttribute("SelectEventID")).getDescription()+"</textarea><br/><br/>");
            }
      else
          {
            out.println("<label class ='form_new_rdv'> Objet : </label><input type='text' name='objet_rdv' value=''><br/><br/>");
            out.println("<label class ='form_new_rdv'> Date : </label><input type='text' name='date_rdv' value=''>&nbsp;&nbsp;&nbsp;&nbsp;<label class='info'>Format: AAAA-MM-JJ</label><br/><br/>");
            out.println("<label class ='form_new_rdv'> Lieu : </label><input type='text' name='lieu_rdv' value=''><br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de début : </label>");
            int i=0;
            out.println("<select name='heure_debut_rdv1'>");
            for(i=0;i<=23;i++)
            out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");
            out.println("<select name='heure_debut_rdv2'>");
            for(i=0;i<=59;i++)
            out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");

            out.println("<br/><br/>");
            out.println("<label class ='form_new_rdv'> Heure de fin : </label>");

            out.println("<select name='heure_fin_rdv1'>");
            for(i=0;i<=23;i++)
                out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");
            out.println("<select name='heure_fin_rdv2'>");
            for(i=0;i<=59;i++)
                out.println("<option value='"+i+"' >"+ i +"</option>");
            out.println("</select>");
            out.println("<br/><br/>");
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
