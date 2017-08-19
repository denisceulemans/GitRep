package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.DriverACCESS;
import dao.UtilisateurDAO;

public final class ConnexionForm 
{
	//code de base form
    private Map<String, String> erreurs = new HashMap<String, String>();
    public Map<String, String> getErreurs() 
    {
        return erreurs;
    }
    private void setErreur(String champ, String message) 
    {
        erreurs.put(champ, message);
    }
    
    private static String getVal(HttpServletRequest request, String val) 
    {
        String valeur = request.getParameter(val);
        if (valeur == null || valeur.trim().length() == 0) 
        {
            return null;
        } 
        else 
        {
            return valeur;
        }
    }
    //--------------------------------------------------------------------------

    public Utilisateur connecterUtilisateur(HttpServletRequest request)
    {
        String login = getVal(request, "login");
        String mdp = getVal(request, "mdp");

        Utilisateur utilisateur = new Utilisateur();

        //login
        try 
        {
            validLogin(login);
        } 
        catch (Exception e) 
        {
            setErreur("login", e.getMessage());
        }

        //mdp
        try 
        {
            validMdp(mdp);
        }
        catch (Exception e) 
        {
            setErreur("mdp", e.getMessage());
        }
        
        //si pas d'erreur, recherche de l'utilisateur
        if(erreurs.isEmpty())
        {
	        try 
	        {
	        	utilisateur = rechercheUtilisateur(login, mdp);
	        }
	        catch (Exception e) 
	        {
	            setErreur("connexion", e.getMessage());
	        }
        }

        return utilisateur;
    }
    
    //-------------------------------------------------------------
    
    //login
    private void validLogin(String login) throws Exception 
    {
    	if (login != null) 
    	{
            if (login.length() < 5) 
            {
                throw new Exception("Le nombre de caract�res du login doit �tre entre 5 et 20");
            }
        } 
    	else 
    	{
            throw new Exception("Login manquant");
        }
    }

    //mdp
    private void validMdp(String mdp) throws Exception 
    {
        if (mdp != null) 
        {
            if (mdp.length() < 5) 
            {
                throw new Exception("Le nombre de caract�res du mot de passe doit �tre entre 5 et 20");
            }
        } 
        else 
        {
            throw new Exception("Mot de passe manquant");
        }
    }
    
    //------------------------------------------------------------------------------------
  //recherche de l'utilisateur
    private Utilisateur rechercheUtilisateur(String login, String motDePasse) throws Exception 
    {
    	UtilisateurDAO utilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
    	Utilisateur utilisateur = utilisateurDao.authentification(login, motDePasse);
    	if (utilisateur == null)
    	{
        	throw new Exception("Login et/ou mot de passe incorrect");
        }
        return utilisateur;
    }
}