package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Utilisateur;
import forms.ConnexionForm;

public class ServletConnexion extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        this.getServletContext().getRequestDispatcher("/VIEW/jsp/connexion.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	if(request.getParameter("connexion") != null)
    	{
            ConnexionForm form = new ConnexionForm();
            Utilisateur utilisateur = form.connecterUtilisateur(request);

            HttpSession session = request.getSession();

            if (form.getErreurs().isEmpty()) 
            {
                session.setAttribute("sessionUtilisateur", utilisateur);
                response.sendRedirect("/Recette/Accueil");
            }
            else 
            {
            	request.setAttribute("utilisateur", utilisateur);
            	request.setAttribute("form", form);
            	System.out.println(form.getErreurs().toString());
                session.setAttribute("sessionUtilisateur", null);
                this.getServletContext().getRequestDispatcher("/VIEW/jsp/connexion.jsp").forward(request, response);
            }
    	}
        
    }
}