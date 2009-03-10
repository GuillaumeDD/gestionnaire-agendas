<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design_accueil" href="accueil.css" />
        
        <title>Agenda</title>
    </head>
    <body>


    <div id="logo">
    </div>

<!-- Haut de page : message d'accueil -->
    <div id="haut_page">
        <div id="message_accueil"> Bonjour Utilisateur1, <br/> nous sommes le 15 février 2009 </div>
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
     <input type="checkbox" name="checkbox1"> <label> Travail </label><br/>
     <input type="checkbox" name="checkbox2"> <label> Agenda Perso </label><br/>
     <input type="checkbox" name="checkbox3"> <label> Sorties </label><br/>
     <input type="checkbox" name="checkbox4"> <label> Sport </label><br/>

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
    <label class ="selection_semaine"> Semaine du ...... au .......  </label>&nbsp;&nbsp
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
