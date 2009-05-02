<%@include file="fonctions/sessionUtils.jsp" %>
<%@page import="GestionAgenda.*" %>
<%@page import="service.*" %>
<%@page import="service.sql.*" %>
<%@page import="Exception.*" %>
<%@page import="Authentification.*" %>
<%@page session="true" %>
<%
if(!estConnecte((Integer)session.getAttribute("userid"),request.getRemoteAddr())){
    response.sendRedirect("identification.jsp");
}else{
    Session s = getSession((Integer)session.getAttribute("userid"), request.getRemoteAddr());
    if(s == null){
        response.sendRedirect("identification.jsp");
    }
}
%>