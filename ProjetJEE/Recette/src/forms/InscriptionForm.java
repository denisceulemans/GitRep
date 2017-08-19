package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Utilisateur;
import dao.DAO;
import dao.DriverACCESS;
import dao.UtilisateurDAO;

public final class InscriptionForm 
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
    
    public Utilisateur inscrireUtilisateur(HttpServletRequest request) 
    {
    	String nom = getVal(request, "nom");
    	String prenom = getVal(request, "prenom");;
    	String login = getVal(request, "login");
        String mdp = getVal(request, "mdp");
        String confirmation = getVal(request, "confirmation");
        String email = getVal(request, "email");

        Utilisateur utilisateur = new Utilisateur();
        
        //nom
        try 
        {
            validNom(nom);
        } 
        catch (Exception e) 
        {
            setErreur("nom", e.getMessage());
        }
        
        //prenom
        try 
        {
            validPrenom(prenom);
        } 
        catch (Exception e) 
        {
            setErreur("prenom", e.getMessage());
        }
        
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
        try {
            validMdp(mdp, confirmation);
        }
        catch (Exception e) 
        {
            setErreur("mdp", e.getMessage());
            setErreur("confirmation", null);
        }
        
        //email
        try 
        {
            validEmail(email);
        } 
        catch (Exception e) 
        {
            setErreur("email", e.getMessage());
        }
        
        //set de l'utilisateur
        utilisateur.setNom(nom);
        utilisateur.setPrenom(prenom);
        utilisateur.setLogin(login);
        utilisateur.setMotDePasse(mdp);
        utilisateur.setEmail(email);

        if (erreurs.isEmpty()) 
        {
        	DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
            UtilisateurDao.create(utilisateur);
        }
        
        return utilisateur;
    }
    //--------------------------------------------------------------------------
    
    //nom
    private void validNom(String nom) throws Exception 
    {
        if (nom == null) 
        {
            throw new Exception("Nom manquant");
        }
    }
    
    //prenom
    private void validPrenom(String prenom) throws Exception 
    {
        if (prenom == null) 
        {
            throw new Exception("Pr�nom manquant");
        }
    }
    
    //email
    private void validEmail(String email) throws Exception 
    {
    	if (email == null) 
        {
            throw new Exception("Email manquant");
        }
    }

    //mdp
    private void validMdp(String mdp, String confirmation) throws Exception 
    {
        if (mdp != null && confirmation != null) {
            if (!mdp.equals(confirmation))
            {
                throw new Exception("Les mots de passe diff�rent");
            } 
            else if (mdp.length() < 5) 
            {
                throw new Exception("Le nombre de caract�res du mot de passe doit �tre entre 5 et 20");
            }
        } 
        else 
        {
            throw new Exception("Mot(s) de passe manquant(s)");
        }
    }

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
}
