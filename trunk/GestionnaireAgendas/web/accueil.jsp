<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design_accueil" href="CSS/accueil.css" />
        
        <title>Agenda</title>
    </head>
    <body>
    <%@page import="java.util.GregorianCalendar" %>
    <%@page import="GestionAgenda.*" %>
    <%@page import="service.*" %>
    <%@page import="service.sql.*" %>
    <%@page import="Authentification.*" %>
    <%@page import="java.sql.*" %>
    <%@page import="java.util.logging.*" %>
    <%@page import="java.text.*" %>
    <%@page import="java.util.*" %>

    <% GregorianCalendar today = new GregorianCalendar();

        DateFormat dateFormatBDD = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat2 = new SimpleDateFormat("EEEEEEEE");
        DateFormat dateFormatJour = DateFormat.getDateInstance(DateFormat.FULL);
        java.util.Date dateDate = today.getTime();

        String dateJourUS = dateFormatBDD.format(dateDate);
        String dateJourSemaine = dateFormatJour.format(dateDate);
        String jourSemaine = dateFormat2.format(dateDate);

        session.setAttribute("dateDuJourUS",dateJourUS);    // ex : 2009-04-27 Format de la BDD
        session.setAttribute("jourSemaine",jourSemaine);    // ex : lundi
        session.setAttribute("dateDuJour",dateJourSemaine); // ex : lundi 27 avril 2009

        Utilisateur moi = new Utilisateur(1);
        session.setAttribute("utilisateur",moi);

        //Chargement du portefeuille d'agendas
        PortefeuilleAgenda port = new PortefeuilleAgenda((Utilisateur)session.getAttribute("utilisateur"));
        port.initialiser();
        session.setAttribute("portefeuille", port);

        session.setAttribute("agenda_select",null);
        session.setAttribute("agendaID",null);

        //Chargement de la semaine courante
        CalendrierSQL calend = new CalendrierSQL();
        ArrayList<String> jours_semaine = new ArrayList();
        jours_semaine=calend.findWeekByID(calend.findWeekOfADay((String)session.getAttribute("dateDuJourUS")));

        session.setAttribute("jour1",jours_semaine.get(1));
        session.setAttribute("jour7",jours_semaine.get(13));

     %>

    <div id="logo">
    </div>

<!-- Haut de page : message d'accueil -->
    <div id="haut_page">
        <div id="message_accueil"> Bonjour  <%=session.getAttribute("user")%> , <br/> nous sommes le <%=session.getAttribute("dateDuJour")%> </div>
        <div id="deconnexion">
                <form method="post" action="identification.jsp" >
                <input type="submit" class="out" id="disconnect" value="" ><br/>
                <label class ="bouton_deco"> Déconnexion </label>
                </form>
        </div>
    </div>


    <div id="menu_gauche"></div>
    <div id="menu_gauche_content">
     <br/><br/>

<!-- Sélection des agendas -->
     <form method="post" action="accueil.jsp">
     &nbsp;&nbsp;&nbsp;<label class="titre1">Mes agendas</label><br/><br/>

     <%
     for(Agenda ag : ((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgendas().values())
        {
        out.println("<input type='checkbox' name='"+ag.getAgendaID()+"'> <label>"+ ag.getNom()+" </label><br/>");
        }
     
     %>

     <input type="submit" class="afficher" name="afficher" value="" ><br/>
     <label class ="bouton_afficher"> Afficher </label><br/><br/>
     </form>

<!-- Création d'un nouvel agenda -->
     <div id="nouvel_agenda">
     <form method="post" action="new_agenda.jsp">
     <input type="submit" class="new_agenda" name="new_agenda" value="" ><br/>
     <label class ="bouton_new_agenda"> Nouvel agenda </label>
     </form>
     </div><br/>


<!-- Modification d'un agenda -->
     <div id="modif_agenda">
     <form method="post" action="modif_agenda.jsp">
     <input type="submit" class="modifier_agenda" name="modifier_agenda" value="" ><br/>
     <label class ="bouton_modif_agenda"> Paramètres des agendas </label>
     </form>
     </div><br/>

<!-- Création d'un rendez-vous (ou evènement) -->
     <div id="nouvel_rdv">
     <form method="post" action="new_rdv.jsp">
     <input type="submit" class="new_rdv" name="new_rdv" value="" ><br/>
     <label class ="bouton_new_rdv"> Nouvel evènement </label>
     </form>
     </div><br/>

    </div>

<!-- Agenda hebdomadaire -->
    <div id="corps_calendrier"></div>
    
    <div id="corps_calendrier_content">

    <div id="selection_semaine">
        <form method="post" action="accueil.jsp">
    <input type="submit" class="semaine_precedente" name="semaine_prededente" value="" >&nbsp;&nbsp
    <label class ="selection_semaine"> Semaine du <%=session.getAttribute("jour1")%> au <%=session.getAttribute("jour7")%>  </label>&nbsp;&nbsp
    <input type="submit" class="semaine_suivante" name="semaine_suivante" value="" >
        </form>
    </div>


    <div id="entete_calendrier"><TABLE>
       <TR class="jours"><TD class="col_jours" >Lundi</TD><TD class="col_jours" >Mardi</TD><TD class="col_jours" >Mercredi</TD><TD class="col_jours" >Jeudi</TD><TD class="col_jours" >Vendredi</TD><TD class="col_jours" >Samedi</TD><TD class="col_jours" >Dimanche</TD></TR>
    </TABLE></div>
    
    <div id="calendrier_hebdo">
        <TABLE>
            <%! int compteur_ligne=1; %>
            <% for(compteur_ligne=1;compteur_ligne<=48;compteur_ligne++)
                {out.println("<TR class='agenda'><TD class='col_agenda' > </TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD></TR>");}
            %>
        </TABLE>
    </div>


    </div>


    </body>
</html>
