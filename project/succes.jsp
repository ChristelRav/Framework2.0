<p>Coucou</p>
<%@page import="etu2064.framework.modele.Person"%>
<p>Succes :</p>
<% Person pv = (Person)request.getAttribute("file"); %>
<p>Nom du fichier: <%= pv.getFu().getnameFile() %></p>