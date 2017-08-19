package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import beans.Note;
import beans.Recette;
import beans.Utilisateur;
import dao.DAO;
import dao.DriverACCESS;
import dao.NoteDAO;
import dao.UtilisateurDAO;

public class FaireNoteForm 
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
	
	public Note faireNote(HttpServletRequest request)
	{
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		Recette recette = (Recette) request.getSession().getAttribute("recetteSelect");
		String commentaire = getVal(request,"commentaire");
		int coteNote = Integer.parseInt(request.getParameter("note"));
		
		Note note = new Note();
		
		//utilisateur
		try 
        {
            validUtilisateur(utilisateur);
        } 
        catch (Exception e) 
        {
            setErreur("utilisateur", e.getMessage());
        }
		
		//recette
		try 
        {
            validRecette(recette);
        } 
        catch (Exception e) 
        {
            setErreur("recette", e.getMessage());
        }
		
		//commentaire
		try 
        {
            validCommentaire(commentaire);
        } 
        catch (Exception e) 
        {
            setErreur("commentaire", e.getMessage());
        }
		
		//set note
		note.setUtilisateur(utilisateur);
		note.setRecette(recette);
		note.setCommentaire(commentaire);
		note.setCoteNote(coteNote);
		
		//si pas d'erreur, traiter la note (create ou update)
		if (erreurs.isEmpty()) 
        {
            NoteDAO noteDao = new NoteDAO(DriverACCESS.getInstance());
    		noteDao.traitementNote(note);
        }
		
		return note;
    }
	
	//---------------------------------------------------------------------------
	//utilisateur
    private void validUtilisateur(Utilisateur utilisateur) throws Exception 
    {
        if (utilisateur == null) 
        {
            throw new Exception("Utilisateur manquant");
        }
    }
    //recette
    private void validRecette(Recette recette) throws Exception 
    {
        if (recette == null) 
        {
            throw new Exception("Recette manquante");
        }
    }
    //commentaire
    private void validCommentaire(String commentaire) throws Exception 
    {
        if (commentaire == null) 
        {
            throw new Exception("Commentaire manquant");
        }
    }
}