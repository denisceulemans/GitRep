<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mes recettes</title>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/menu.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/page.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/mesrecettes.css"/>
</head>
<body>
	<div class="page">
		<%@ include file="menu.jsp" %>
		<div class="mesrecettes">
			<h1>Mes recettes</h1>
			<form method="post" action="MesRecettes">
			<input style="margin-bottom: 20px;" class="bouton" type="submit" name="ajouter" id="ajouter" value="Ajouter une recette"/>
			<%
			ArrayList<Recette> mesRecettes = (ArrayList<Recette>)request.getAttribute("mesrecettes");
			
			if(mesRecettes != null)
			{%>
			<table class="tableRecette">
			<tr>
				<td>Nom</td>
				<td>Type</td>
				<td>Temps total</td>
				<td>Selection</td>
			</tr>
			<%
			for(int i=0;i<mesRecettes.size();i++)
			{
			%>
				<tr class="recette">
					<td><%=mesRecettes.get(i).getNom()%></td>
					<td><%=mesRecettes.get(i).getType()%></td>
					<td><%=mesRecettes.get(i).getTemps_total()%></td>
					<td>
						<input type="submit" value="Sélectionner" name="valider<%=i%>"/>
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