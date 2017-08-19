package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Recette;
import beans.Utilisateur;
import dao.DriverACCESS;
import dao.RecetteDAO;
import dao.UtilisateurDAO;

public class ServletListeRecettes extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
        if(session.getAttribute("sessionUtilisateur") == null)
        {
        	response.sendRedirect("/Recette/Connexion");
        }
        else
        {
        	RecetteDAO recetteDao = new RecetteDAO(DriverACCESS.getInstance());
    		ArrayList<Recette> listeRecette = recetteDao.select();
    		
    		request.setAttribute("listerecettes", listeRecette);
        	
        	this.getServletContext().getRequestDispatcher("/VIEW/jsp/listerecettes.jsp").forward(request, response);
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
        if(session.getAttribute("sessionUtilisateur") == null)
        {
        	response.sendRedirect("/Recette/Connexion");
        }
        else
        {
        	RecetteDAO recetteDao = new RecetteDAO(DriverACCESS.getInstance());
    		ArrayList<Recette> listeRecette = recetteDao.select();
    		
    		request.setAttribute("listerecettes", listeRecette);
    		
    		for(int i=0;i<listeRecette.size();i++)
    		{
    			if(request.getParameter("valider"+i) != null)
    			{
    				session.setAttribute("recetteSelect",listeRecette.get(i));
    				response.sendRedirect("/Recette/ConsulterRecette");
    			}
    		}
        }

	}

}