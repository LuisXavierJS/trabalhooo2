<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="Model.Personagem" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VISUALIZAR PERSONAGEM</title>
</head>
<body>
<form action="../SessionNavServlet" method="GET"><input type="submit" value="LOGOUT"/></form>
<form action="../SessionNavServlet" method="POST"><input type="hidden" name="target" id="target" value="View/homeView.jsp"/><input type="submit" value="HOME"/></form>
<br>
<% if(request.getSession().getAttribute("user")==null)response.sendRedirect("../index.jsp");
@SuppressWarnings("unchecked")
List<Personagem> personagens =(List<Personagem>) request.getSession().getAttribute("personagens");
int i=0;
for(Personagem p : personagens){
%>
<table class="charDetail" id="personagem<%=i%>" style="border-top: 1px solid #cdd0d4; border-bottom: 1px solid #cdd0d4;">
<tr>
<th>
	<img width="100" height="100" src="<%=p.getUrlImagemPersonagem() %>"></th>
<th>
	<table>
	<tr><th>Nome:</th><th><%=p.getNomePersonagem() %></th></tr>
	<tr><th>Classe:</th><th><%=p.getClassePersonagem() %></th></tr>
	<tr><th>Poder:</th><th><%=p.getPoderDoPersonagem() %></th></tr>
	<tr><th>Detalhes:</th><th><form action="../DetalhePersonagemServlet" method="GET"><input type="hidden" name="indexPersonagemNaLista" value="<%=i%>">
	<input type="submit" name="detailsOfChar" id="detailsOfChar" value="Ficha Completa"></form></th></tr>
	
	</table>
</th>
</tr>
</table>
<%i++;} %>
</body>
</html>