<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design_new_agenda" href="new_agenda.css" />
        <title>Modifier Agenda</title>
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

<!-- Formulaire de modification d'un agenda  -->
    <div id="cadre_creation"></div>

    <div id="cadre_creation_content">
        <br/>
        <form method="post" action="modif_agenda.jsp" >

        <div id="cadre_titre"><label class ="titre_creation_agenda"> Paramètres des agendas </label></div><br/>

        <!-- Sélection de lagenda à modifier -->
        <label class ="form_new_agenda">Agenda : </label>
        <select name="agenda" name="agenda">
           <option value="travail">Travail</option>
           <option value="perso">Agenda Perso</option>
           <option value="sorties">Sorties</option>
        </select>
        &nbsp;<input type="submit" class="select_agenda" name="select" value="" ><br/><br/>

        
        <!-- Paramètres de cet agenda -->
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
