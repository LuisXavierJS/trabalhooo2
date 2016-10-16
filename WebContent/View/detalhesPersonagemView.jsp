<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <%@ page import="Model.Personagem" %>
    <%@ page import="Model.Movimento" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DETALHES PERSONAGEM</title>
</head>
<body>
<form action="../SessionNavServlet" method="GET"><input type="submit" value="LOGOUT"/></form>
<form action="../SessionNavServlet" method="POST"><input type="hidden" name="target" id="target" value="View/homeView.jsp"/><input type="submit" value="HOME"/></form>
<form action="../SessionNavServlet" method="POST"><input type="hidden" name="target" id="target" value="View/visualizarPersonagensView.jsp"/><input type="submit" value="Voltar à Lista!"/></form>

<% if(request.getSession().getAttribute("user")==null)response.sendRedirect("../index.jsp");else{%>
<%
@SuppressWarnings("unchecked")
Personagem p = (Personagem) request.getSession().getAttribute("lastSelectedCharacter");
List<Movimento> movimentos =(List<Movimento>) request.getSession().getAttribute("lastSelectedCharacterMoves");
%>
<table class="charBase"">
<tr>
<th>
	<table>
	<tr><th>Nome:</th><th><%=p.getNomePersonagem() %></th></tr>
	<tr><th>Classe:</th><th><%=p.getClassePersonagem() %></th></tr>
	<tr><th>Poder:</th><th><%=p.getPoderDoPersonagem() %></th></tr>
	</table>
</th>
<th>
	<img style="max-height:300px;max-width:300px;" src="<%=p.getUrlImagemPersonagem() %>">
</th>
</tr>
</table>
<br/>
<table class="charAttributes">
<tr>
<th>HP:<br/><%=p.getHp() %> </th>
<th>Atk:<br/><%=p.getAtaqueFisico() %></th>
<th>SpAtk:<br/><%=p.getAtaqueEspecial() %></th>
<th>Def:<br/><%=p.getDefesaFisica() %></th>
<th>SpDef:<br/><%=p.getDefesaEspecial() %></th>
<th>Speed:<br/><%=p.getVelocidade() %></th>
</tr>
</table>
<%int i=0;
for(Movimento m : movimentos){
%>
<br/>
<table class="charMoves" style="border-top: 1px solid #cdd0d4;">
<tr><th><%=m.getNomeMovimento() %></th><th>Ataque <%=m.getClassificacaoMovimento() %></th></tr>
<tr><th>Poder do Ataque:</th><th><%=m.getPoderMovimento() %></th></tr>
<tr><th>Precisão do Ataque:</th><th><%=m.getPrecisaoMovimento() %></th></tr>
</table>
<%i++;}} %>
<form method="POST" action="../DetalhePersonagemServlet">
<input type="checkbox" name="gerarImagem" value="gerarImagem" id="gerarImagem">Adicionar Imagem ao documento PDF<br/>
<input type="submit" value="GERAR PDF">
</form>
</body>
</html>