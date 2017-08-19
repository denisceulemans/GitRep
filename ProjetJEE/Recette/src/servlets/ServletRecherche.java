package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import forms.RechercheForm;
import beans.*;
import dao.DriverACCESS;
import dao.RecetteDAO;

public class ServletRecherche extends HttpServlet {
	ArrayList<Recette> rechercherecettes = null;
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
			request.setAttribute("recherche", null);
			request.setAttribute("rechercherecettes",null);
			this.getServletContext().getRequestDispatcher("/VIEW/jsp/recherche.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("rechercher") != null)
		{
			RechercheForm form = new RechercheForm();
			
			ArrayList<Recette> rechercheRecettes = form.chercherRecette(request);
			
			request.setAttribute("recherche", request.getAttribute("recherche"));
			request.setAttribute("rechercherecettes", rechercheRecettes);
			request.setAttribute("form", form);
        	System.out.println(form.getErreurs().toString());
			this.getServletContext().getRequestDispatcher("/VIEW/jsp/recherche.jsp").forward(request, response);
		}
		else
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
        		
        		request.setAttribute("recherche", request.getAttribute("recherche"));
        		request.setAttribute("rechercherecettes", listeRecette);
        		
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
}
