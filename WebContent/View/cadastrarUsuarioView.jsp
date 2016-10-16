<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADASTRAR USUARIO</title>
</head>
<body>

<form action="../AutenticacaoServlet" method="GET">
	Username: <input type="text" name="login" id="login"/><br/>
	Password: <input type="text" name="senha" id="senha"/>
	<input type="submit" value="CADASTRAR"/>
</form>
</body>
</html>