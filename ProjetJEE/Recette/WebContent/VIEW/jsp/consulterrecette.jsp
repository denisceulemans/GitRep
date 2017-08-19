<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="beans.*"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulter une recette</title>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/menu.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/page.css"/>
<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/consulterrecette.css"/>
</head>
<body>
	<div class="page">
		<%@ include file="menu.jsp" %>
		
		<div class="consulterrecette">
			<h1>Consulter une recette</h1>
			<form method="post" action="ConsulterRecette">
		<%
		//chargement de la recette, des ingrédients, des étapes et des notes
			Recette recette = (Recette)request.getAttribute("recetteSelect");
			ArrayList<Ingredient> listeIngredients = (ArrayList<Ingredient>)request.getAttribute("listeIngredientSelect");
			ArrayList<Etape> listeEtapes = (ArrayList<Etape>)request.getAttribute("listeEtapeSelect");
			ArrayList<Note> listeNotes = (ArrayList<Note>)request.getAttribute("listeNoteSelect");
			
			if(recette != null)
			{
		%>
		<h3>Recette</h3>
		<table class="info">
			<tr>
				<td>Nom : </td>
				<td><%=recette.getNom()%></td>
			</tr>
			<tr>
				<td>Avis : </td>
				<td><%=recette.getAvis()%></td>
			</tr>
			<tr>
				<td>Type : </td>
				<td><%=recette.getType()%></td>
			</tr>
			<tr>
				<td>Temps total : </td>
				<td><%=recette.getTemps_total()%></td>
			</tr>
			<tr>
				<td>Créateur de la recette : </td>
				<td><%=recette.getUser().getNom()%> <%=recette.getUser().getPrenom()%></td>
			</tr>
		</table>
		<%}else{ %>
		<p>Pas de recette</p>
		<%
			}
			
			if(listeIngredients != null)
			{
		%>
		<h3>Ingrédients</h3>
		<table class="info">
		<%
			for(int i=0;i<listeIngredients.size();i++)
			{
		%>
			<tr>
				<td><%=listeIngredients.get(i).getDescription()%></td>
			</tr>
		<%}%>
		</table>
		<%}else{%>
			<p>Pas d'ingrédients</p>
		<%
			}
			
			if(listeEtapes != null)
			{
		%>
		<h3>Etapes</h3>
		<table class="info">
		<tr>
			<td>Numéro</td>
			<td>Explication</td>
		</tr>
		<%
			for(int i=0;i<listeEtapes.size();i++)
			{
				
			%>
			<tr>
				<td><%=listeEtapes.get(i).getNumEtape()%></td>
				<td><%=listeEtapes.get(i).getExplication()%></td>
			</tr>
		<%}%>
		</table>
		<%}else{%>
			<p>Pas d'étapes</p>
		<%
			}
			
			if(listeNotes != null)
			{
				double val = 0;
				int nbrNotes = 0;
				for(int i=0;i<listeNotes.size();i++)
				{
					if(listeNotes.get(i).getCoteNote() != -1)
					{
						val += listeNotes.get(i).getCoteNote();
						nbrNotes++;
					}
					
				}
				val /= nbrNotes;

				DecimalFormat f = new DecimalFormat();
				f.setMaximumFractionDigits(2);
		%>
		<h3>Notes</h3>
		<p><%=f.format(val)%>/10</p>
		<table class="info">
		<%
			for(int i=0;i<listeNotes.size();i++)
			{
		%>
			<tr>
				<td><%=listeNotes.get(i).getUtilisateur().getNom()%> <%=listeNotes.get(i).getUtilisateur().getPrenom() %></td>
				<td><%=listeNotes.get(i).getCommentaire()%></td>
				<td><%=listeNotes.get(i).getCoteNote() %>/10</td>
			</tr>
		<%}%>
		</table>
		<%}else{%>
			<p>Pas de notes</p>
		<%}%>
		<table class="form">
			<tr>
				<td class="titre">Commentaire : <span class="erreur">${form.erreurs['commentaire']}</span></td>
				<td><textarea name="commentaire" id="commentaire" rows="4" cols="70" maxlength="300" style="height:100px;" value="${note.commentaire}"></textarea></td>
			</tr>
			<tr>
				<td class="titre">Note :</td>
				<td>
					<select name="note">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
  					</select>
  				</td> 
			</tr>
			<tr>
				<td colspan="2"><input class="bouton" type="submit" name="faireNote" id="faireNote" value="Envoyer"/></td>
			</tr>
		</table>
		</form>
		</div>
	</div>
</body>
</html>