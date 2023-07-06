<%@page import="etu2064.framework.modele.Person"%>
<p>Valeur:</p>
<% Person e =(Person)request.getAttribute("pers"); %>
<% out.print(e.getnom()); %>
<% out.print(e.getage()); %>
