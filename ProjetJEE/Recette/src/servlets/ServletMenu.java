package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletMenu extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.sendRedirect("/Recette/Connexion");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("accueil") != null)
		{
			response.sendRedirect("/Recette/Accueil");
		}
		else if(request.getParameter("recherche") != null)
		{
			response.sendRedirect("/Recette/Recherche");
		}
		else if(request.getParameter("mesrecettes") != null)
		{
			response.sendRedirect("/Recette/MesRecettes");
		}
		else if(request.getParameter("listerecettes") != null)
		{
			response.sendRedirect("/Recette/ListeRecettes");
		}
	}

}
