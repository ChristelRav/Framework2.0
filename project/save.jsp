<%@page import="etu2064.framework.modele.Person"%>
<p>Valeur:</p>
<% Person e =(Person)request.getAttribute("form"); %>
<% out.print(e.getnom()); %>
<% out.print(e.getage()); %>
