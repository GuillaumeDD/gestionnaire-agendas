<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" media="screen" type="text/css" title="Design" href="design.css" />
        <title>Gestionnaire d'agendas - Accueil</title>
    </head>
    <body>
    <div id="titre"> Gestionnaire d'agendas </div>

<%
    String connect=request.getParameter("connect");
    if(connect!=null)
        {
        String login =request.getParameter("user");
        out.println("<p>"+login+"</p>");
        }
    %>

    <div id="login">

    <form method="post" action="accueil.jsp">

    <label class="titre">  Connexion  </label><br/><br/>

    <label class="form">  Nom d'utilisateur :  </label>  <input type="text" name="user"><br/><br/>
    <label class="form">  Mot de passe :  </label>  <input type="text" name="mdp" ><br/><br/><br/>


    <input type="submit" class="in" id="connect" name="connect" value="" ><br/>
    <label class ="bouton"> Se connecter </label>

    </form>

    </div>
    
    
    </body>
</html>
