<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


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
        <div id="message_accueil"> Bonjour Utilisateur1, <br/> nous sommes le 15 février 2009 </div>
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
        <br/>
        <form method="post" action="new_rdv.jsp" >

        <div id="cadre_titre"><label class ="titre_form_rdv"> Création d'un évènement </label></div><br/>
        <label class ="form_new_rdv"> Objet : </label><input type="text" name="objet_rdv"><br/><br/>
        <label class ="form_new_rdv"> Date : </label><input type="text" name="date_rdv"><br/><br/>
        <label class ="form_new_rdv"> Lieu : </label><input type="text" name="lieu_rdv"><br/><br/>
        <label class ="form_new_rdv"> Heure de début : </label><input type="text" name="heure_debut_rdv"><br/><br/>
        <label class ="form_new_rdv"> Heure de fin : </label><input type="text" name="heure_fin_rdv"><br/><br/>
        <label class ="form_new_rdv"> Agenda : </label>
        <select name="agenda" >
           <option value="travail">Travail</option>
           <option value="perso">Agenda Perso</option>
           <option value="sorties">Sorties</option>
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