<%@page import="etu2064.framework.modele.Person"%>
<p>Valeur:</p>
<% Person e =(Person)request.getAttribute("form"); %>
<p>Nom :<% out.print(e.getnom()); %></p>
<p>Age :<% out.print(e.getage()); %></p>
