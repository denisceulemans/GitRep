package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import forms.InscriptionForm;

public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        this.getServletContext().getRequestDispatcher("/VIEW/jsp/inscription.jsp").forward(request, response);
    }
	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	if(request.getParameter("inscription") != null)
    	{
	        InscriptionForm form = new InscriptionForm();
	        Utilisateur utilisateur = form.inscrireUtilisateur(request);
			
	        HttpSession session = request.getSession();
	        
	        request.setAttribute("form", form);
	        request.setAttribute("utilisateur", utilisateur);
	        
	        if (form.getErreurs().isEmpty())
	        {
	            session.setAttribute("sessionUtilisateur", utilisateur);
	            response.sendRedirect("/Recette/Connexion");
	        } 
	        else {
	            session.setAttribute("sessionUtilisateur", null);
	            this.getServletContext().getRequestDispatcher("/VIEW/jsp/inscription.jsp").forward(request, response);
	        }
    	}
    }
}
