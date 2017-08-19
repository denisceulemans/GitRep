<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/page.css"/>
		<link type="text/css" rel="stylesheet" href="/Recette/VIEW/css/inscription.css"/>
    </head>
    <body>
    <div class="page">
    	<div class="inscription">
        <form method="post" action="Inscription">
        	<h1>Inscription</h1>
			<table>
				<tr>
					<td>Nom : </td>
					<td>
	               		<input type="text" id="nom" name="nom" value="${utilisateur.nom}" size="20" maxlength="20" />
	                	<span class="erreur">${form.erreurs['nom']}</span>
                	</td>
                </tr>
                <tr>
                	<td>Prénom : </td>
					<td>
                		<input type="text" id="prenom" name="prenom" value="${utilisateur.prenom}" size="20" maxlength="20" />
                		<span class="erreur">${form.erreurs['prenom']}</span>
                	</td>
                </tr>
                <tr>
	                <td>Login : </td>
					<td>
		                <input type="text" id="login" name="login" value="${utilisateur.login}" size="20" maxlength="20" />
		                <span class="erreur">${form.erreurs['login']}</span>
	                </td>
                </tr>
                <tr>
	                <td>Mot de passe : </td>
					<td>
		                <input type="password" id="mdp" name="mdp" value="" size="20" maxlength="20" />
		                <span class="erreur">${form.erreurs['mdp']}</span>
	                </td>
                </tr>
                <tr>
                	<td>Confirmation du mot de passe : </td>
					<td>
		                <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
		                <span class="erreur">${form.erreurs['confirmation']}</span>
                	</td>
              	</tr>
                <tr>
                	<td>Adresse email : </td>
					<td>
		                <input type="text" id="email" name="email" value="${utilisateur.email}" size="20" maxlength="60" />
		                <span class="erreur">${form.erreurs['email']}</span>
	                </td>
                </tr>
                <tr>
					<td><a href="/Recette/Connexion"><input class="bouton" type="button" value="Retour à la connexion" /></a></td>
					<td><input class="bouton" type="submit" name ="inscription" value="Inscription"/></td>
				</tr>
	        </table>
        </form>
        </div>
    </div>
    </body>
</html>