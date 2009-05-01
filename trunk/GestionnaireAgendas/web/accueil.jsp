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
    <%@page import="java.util.logging.*" %>
    <%@page import="java.text.*" %>
    <%@page import="java.util.*" %>

    <%
        GregorianCalendar today = new GregorianCalendar();
        DateFormat dateFormatBDD = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat2 = new SimpleDateFormat("EEEEEEEE");
        DateFormat dateFormatJour = DateFormat.getDateInstance(DateFormat.FULL);
        CalendrierSQL calend = new CalendrierSQL();
        ArrayList<String> jours_semaine = new ArrayList();

        // Formatages différents de la date du jour
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

        //Flèches pour changer la semaine courante du calendrier
        String semaine_prec = request.getParameter("semaine_precedente");
        String semaine_suiv = request.getParameter("semaine_suivante");
        if(semaine_prec != null)
            {session.setAttribute("IdSemaine",(Integer)session.getAttribute("IdSemaine")-1);}
        else if(semaine_suiv != null)
            {session.setAttribute("IdSemaine",(Integer)session.getAttribute("IdSemaine")+1);}
        else
            {session.setAttribute("IdSemaine",calend.findWeekOfADay((String)session.getAttribute("dateDuJourUS")));}


        //Chargement de la semaine courante
        jours_semaine=calend.findWeekByID((Integer)session.getAttribute("IdSemaine"));
        session.setAttribute("jour1",jours_semaine.get(1));
        session.setAttribute("jour7",jours_semaine.get(13));

        Agenda a = new Agenda();
        HashMap<Integer,Date> dates_semaine = new HashMap();
        
        for(int i=1;i<=13;i=i+2)
             {dates_semaine.put(i,a.StringtoDateFR(jours_semaine.get(i)));
        }

        //Sélection des agendas
        HashMap<Long,Agenda> agendas_coches = new HashMap();
        String afficher = request.getParameter("afficher");
        if(afficher != null)
            {
            for(Agenda ag : ((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgendas().values())
                {
                 String cocher = request.getParameter(Long.toString(ag.getAgendaID()));
                 if(cocher != null)
                     {
                     agendas_coches.put(ag.getAgendaID(), ag);
                     session.setAttribute(Long.toString(ag.getAgendaID()),true);
                     }
                 else session.setAttribute(Long.toString(ag.getAgendaID()),false);
                }
            session.setAttribute("map_agendas_coches",agendas_coches);
            }
        else session.setAttribute("map_agendas_coches",((PortefeuilleAgenda)session.getAttribute("portefeuille")).getAgendas());
        
            
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
         if(session.getAttribute(Long.toString(ag.getAgendaID())) != null)
             {
                if(((Boolean)session.getAttribute(Long.toString(ag.getAgendaID())))==true)
                    out.println("<input type='checkbox' name='"+ag.getAgendaID()+"' checked='checked' > <label>"+ ag.getNom()+" </label><br/>");
                else
                    out.println("<input type='checkbox' name='"+ag.getAgendaID()+"' > <label>"+ ag.getNom()+" </label><br/>");
             }
         else out.println("<input type='checkbox' name='"+ag.getAgendaID()+"' > <label>"+ ag.getNom()+" </label><br/>");
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
        <div id="fleches"><form method="post" action="accueil.jsp">
    <input type="submit" class="semaine_precedente" name="semaine_precedente" value="" >&nbsp;&nbsp
    <label class ="selection_semaine"> Semaine du <%=session.getAttribute("jour1")%> au <%=session.getAttribute("jour7")%>  </label>&nbsp;&nbsp
    <input type="submit" class="semaine_suivante" name="semaine_suivante" value="" >
        </form></div>
        <div id="modif_event">
     <form method='post' action='modif_rdv.jsp'>
     <input type="submit" class="modif_event" name="modif_event" value="" >
         <label class ="bouton_modif_event"> Editer </label>
        </div>
    </div>


    <div id="entete_calendrier"><TABLE>
       <TR class="jours"><TD class="col_heures">Heures</TD><TD class="col_jours" >Lundi</TD><TD class="col_jours" >Mardi</TD><TD class="col_jours" >Mercredi</TD><TD class="col_jours" >Jeudi</TD><TD class="col_jours" >Vendredi</TD><TD class="col_jours" >Samedi</TD><TD class="col_jours" >Dimanche</TD></TR>
    </TABLE></div>
    
    <div id="calendrier_hebdo">
        
            <!--int compteur_ligne=0;
            for(compteur_ligne=0;compteur_ligne<=47;compteur_ligne++)
                {out.println("<TR class='agenda'><TD class='col_agenda' > </TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD><TD class='col_agenda' ></TD></TR>");}
            -->
        <TABLE VALIGN='top'><TR>
            <%!int compteur_ligne=0;%>
            <%

            out.println("<TD class='col_heures'><TABLE WIDTH=45><TR>");
                for(compteur_ligne=1;compteur_ligne<=48;compteur_ligne++)
                        if(((float)compteur_ligne)%2==0)
                            out.println("<TR class='agenda'><TD>"+(compteur_ligne/2)+"</TD></TR>");
                        else out.println("<TR class='agenda'><TD></TD></TR>");
            out.println("</TR></TABLE></TD>");


            for(Date d:dates_semaine.values())
                {
                boolean vide=true;
                out.println("<TD class='col_agenda'><TABLE WIDTH=125 VALIGN='top'><TR>");
                int nb_col=0;
                for(Agenda ag : ((HashMap<Long,Agenda>)session.getAttribute("map_agendas_coches")).values())
                    {
                    HashMap<Long,Evenement> events = ag.getEvenementsByDate(d);
                    if(!events.isEmpty())
                        nb_col++;
                    }
                for(Agenda ag : ((HashMap<Long,Agenda>)session.getAttribute("map_agendas_coches")).values())
                    {
                    HashMap<Long,Evenement> events = ag.getEvenementsByDate(d);
                    if(!events.isEmpty())
                        {
                        out.println("<TD><TABLE WIDTH="+(int)(117/nb_col)+" VALIGN='top'>");
                        vide=false;
                        for(compteur_ligne=1;compteur_ligne<=48;compteur_ligne++)
                        {
                        boolean stop=false;
                        Iterator<Long> it = events.keySet().iterator();
                        while(it.hasNext() && stop==false)
                            {
                            Long cle = it.next();
                            if((((events.get(cle)).getHeureDebut())*2) <= compteur_ligne && (((events.get(cle)).getHeureFin())*2) > compteur_ligne)
                                {
                                out.println("<TR class='agenda1' BGCOLOR="+ag.getColor()+" ><TD class='event'>"+(events.get(cle)).getObjet()+"<input type='radio' name='select_event' value='"+(events.get(cle)).getEventID()+"'></TD></TR>");
                                stop=true;
                                }
                            }
                        if(stop==false) out.println("<TR class='agenda'><TD></TD></TR>");
                        }
                        out.println("</TABLE></TD>");
                        }
                     
                    }
                if(vide==true)
                       {for(compteur_ligne=1;compteur_ligne<=48;compteur_ligne++)
                            out.println("<TR class='agenda'><TD></TD></TR>");}


                   out.println("</TR></TABLE></TD>");
                   
                }
             %>
       </form></TR></TABLE>
    </div>


    </div>



    </body>
</html>
