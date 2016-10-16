<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>USER HOME PAGE</title>
</head>
<body>
<form action="../SessionNavServlet" method="GET"><input type="submit" value="LOGOUT"/></form>
<% if(request.getSession().getAttribute("user")==null)response.sendRedirect("../index.jsp");%>
<form action="../PersonagensServlet" method="GET"><input type="hidden" name="target" id="target" value="View/visualizarPersonagensView.jsp"/><input type="submit" value="MEUS PERSONAGENS"/></form>
<form action="../SessionNavServlet" method="POST"><input type="hidden" name="target" id="target" value="View/cadastrarPersonagemView.jsp"/><input type="submit" value="CRIAR PERSONAGEM"/></form>
</body>
</html>