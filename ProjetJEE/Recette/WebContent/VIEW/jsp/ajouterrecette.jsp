<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter recette</title>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/menu.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/page.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/ajouterrecette.css"/>
</head>
<body>
	 <div class="page">
		<%@ include file="menu.jsp" %>
		<div class="recherche">
			<h1>Ajouter une recette</h1>
			<form method="post" action="AjouterRecette">
			<table class="info">
				<tr>
					<td>Nom : </td>
					<td>
						<input type="text" name="nom" id="nom" value="${recette.nom}" size="30"/>
						<span class="erreur">${form.erreurs['nom']}</span>
					</td>
				</tr>
				<tr>
					<td>Avis : </td>
					<td>
						<input type="text" name="avis" id="avis" value="${recette.avis}" size="100"/>
						<span class="erreur">${form.erreurs['avis']}</span>
					</td>
				</tr>
				<tr>
					<td>Type de recette : </td>
					<td>
						<input type="text" name="type" id="type" value="${recette.type}" size="30"/>
						<span class="erreur">${form.erreurs['type']}</span>
					</td>
				</tr>
				<tr>
					<td>Temps total : </td>
					<td>
						<input type="text" name="temps" id="temps" value="${recette.temps_total}" size="4"/>
						<span class="erreur">${form.erreurs['temps']}</span>
					</td>
				</tr>
			</table>
			
			<table class="info" id="ingredients">
				<caption>Ingrédients</caption>
				<%
					ArrayList<Ingredient> listeIngredient = (ArrayList<Ingredient>)request.getAttribute("listeIngredient");
					for(int i=0;i<listeIngredient.size();i++)
					{
						%>
						<tr>
						<td><input readonly="readonly" type="text" id="ingredient<%=i%>" name="ingredient<%=i%>" value="<%=listeIngredient.get(i).getDescription()%>" /></td>
						<td><input class="bouton" type="submit" name="supprimeIngredient<%=i%>" id="supprimeIngredient<%=i%>" value ="Supprimer cet ingrédient"/></td>
						</tr>
						<%
					}
				%>
			</table>
			<table class="info">
				<tr>
					<td>Ingrédient : </td>
					<td><input type="text" name="ingredient" id="ingredient" value="" size="50"/></td>
				</tr>
				<tr>
					<td><input class="bouton" type="submit" name="ajouteringredient" id="ajoutingredient" value="Ajouter un ingrédient"/></td>
					<td><span class="erreur">${form.erreurs['ingredient']}</span></td>
				</tr>
			</table>
			<table class="info" id="etapes">
				<caption>Etapes</caption>
				<%
					ArrayList<Etape> listeEtape = (ArrayList<Etape>)request.getAttribute("listeEtape");
					for(int i=0;i<listeEtape.size();i++)
					{
						%>
						<tr>
						<td><input readonly="readonly" type="text" id="etape<%=i%>" name="etape<%=i%>" value="<%=listeEtape.get(i).getExplication()%>" /></td>
						<td><input class="bouton" type="submit" name="supprimeEtape<%=i%>" id="supprimeEtape<%=i%>" value ="Supprimer cette étape"/></td>
						</tr>
						<%
					}
				%>
			</table>
			<table class="info">
				<tr>
					<td>Etape : </td>
					<td><input type="text" name="etape" id="etape" value="" size="100"/></td>
				</tr>
				<tr>
					<td><input class="bouton" type="submit" name="ajouteretape" id="ajoutetape" value="Ajouter une étape"/></td>
					<td><span class="erreur">${form.erreurs['etape']}</span></td>
				</tr>
			</table>
			<input style="margin-bottom: 20px;" type="submit" value="Ajouter une recette" name="ajouterrecette"/>
			</form>
		</div>
	</div>
</body>
</html>