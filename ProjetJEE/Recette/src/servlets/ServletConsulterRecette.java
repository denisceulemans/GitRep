package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Etape;
import beans.Ingredient;
import beans.Note;
import beans.Recette;
import beans.Utilisateur;
import dao.DAO;
import dao.DriverACCESS;
import dao.EtapeDAO;
import dao.IngredientDAO;
import dao.NoteDAO;
import dao.RecetteDAO;
import forms.FaireNoteForm;

public class ServletConsulterRecette extends HttpServlet {
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
        	if(session.getAttribute("recetteSelect") != null)
        	{
	        	Recette recette = (Recette) session.getAttribute("recetteSelect");
	        	request.setAttribute("recetteSelect", recette);
	        	
	        	IngredientDAO ingredientDao = new IngredientDAO(DriverACCESS.getInstance());
	        	request.setAttribute("listeIngredientSelect", ingredientDao.select(recette));
	        	
	        	EtapeDAO etapeDao = new EtapeDAO(DriverACCESS.getInstance());
	        	request.setAttribute("listeEtapeSelect", etapeDao.select(recette));
	        	
	        	NoteDAO noteDao = new NoteDAO(DriverACCESS.getInstance());
	        	request.setAttribute("listeNoteSelect", noteDao.select(recette));
	        	
	    		this.getServletContext().getRequestDispatcher("/VIEW/jsp/consulterrecette.jsp").forward(request, response);
        	}
        	else
        	{
        		response.sendRedirect("/Recette/Accueil");
        	}
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
        	if(session.getAttribute("recetteSelect") != null)
        	{
	        	if(request.getParameter("faireNote") != null)
	        	{
	        		FaireNoteForm form = new FaireNoteForm();
	    			Note note = form.faireNote(request);
	    			
	    			Recette recette = (Recette) session.getAttribute("recetteSelect");
		        	request.setAttribute("recetteSelect", recette);
		        	
		        	IngredientDAO ingredientDao = new IngredientDAO(DriverACCESS.getInstance());
		        	request.setAttribute("listeIngredientSelect", ingredientDao.select(recette));
		        	
		        	EtapeDAO etapeDao = new EtapeDAO(DriverACCESS.getInstance());
		        	request.setAttribute("listeEtapeSelect", etapeDao.select(recette));
		        	
		        	NoteDAO noteDao = new NoteDAO(DriverACCESS.getInstance());
		        	request.setAttribute("listeNoteSelect", noteDao.select(recette));
		        	
	    			if(form.getErreurs().isEmpty())
	    			{
	    				this.getServletContext().getRequestDispatcher("/VIEW/jsp/consulterrecette.jsp").forward(request, response);
	    			}
	    			else
	    			{
	    				request.setAttribute("form", form);
	    				System.out.println(form.getErreurs().toString());
	    				this.getServletContext().getRequestDispatcher("/VIEW/jsp/consulterrecette.jsp").forward(request, response);
	    			}	
	    		}
        	}
        	else
        	{
        		response.sendRedirect("/Recette/Accueil");
        	}
        }
	}

}
