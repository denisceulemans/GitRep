<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des recettes</title>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/menu.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/page.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/listerecettes.css"/>
</head>
<body>
	<div class="page">
		<%@ include file="menu.jsp" %>
		<div class="listerecettes">
			<h1>Listes des recettes</h1>
			<form method="post" action="ListeRecettes">
			
			<%
			ArrayList<Recette> listerecettes = (ArrayList<Recette>)request.getAttribute("listerecettes");
			
			if(listerecettes != null)
			{%>
			<table class="tableRecette">
			<tr>
				<td>Nom</td>
				<td>Type</td>
				<td>Temps total</td>
				<td>Selection</td>
			</tr>
			<%
			for(int i=0;i<listerecettes.size();i++)
			{
			%>
				<tr class="recette">
					<td><%=listerecettes.get(i).getNom()%></td>
					<td><%=listerecettes.get(i).getType()%></td>
					<td><%=listerecettes.get(i).getTemps_total()%></td>
					<td>
						<input type="submit" value="S�lectionner" name="valider<%=i%>"/>
					</td>
				</tr>
			<%}}else{%>
			<p>Pas de recettes</p>
			<%}%>
			</table>
			</form>
		</div>
	</div>
</body>
</html>