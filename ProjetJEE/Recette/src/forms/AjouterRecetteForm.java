package forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Etape;
import beans.Ingredient;
import beans.Note;
import beans.Recette;
import beans.Utilisateur;
import dao.DriverACCESS;
import dao.EtapeDAO;
import dao.IngredientDAO;
import dao.RecetteDAO;

public class AjouterRecetteForm 
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

	public Recette ajouterRecette(HttpServletRequest request)
	{
		//gestion recette
		Recette recette = lireRecette(request);
		
		//si pas d'erreurs � ce stade la, continuer
		if(erreurs.isEmpty())
		{	
			//gestion des �tapes
			ArrayList<Etape> listeEtape = lireEtape(request);
			
			//gestion des ingr�dients
			ArrayList<Ingredient> listeIngredient = lireIngredient(request);
			
			//si toujours pas d'erreurs, create la recette, les etapes et les ingr�dients
			if(erreurs.isEmpty())
			{
				RecetteDAO recetteDao = new RecetteDAO(DriverACCESS.getInstance());
				recetteDao.create(recette);
				
				for(int k=0;k<listeEtape.size();k++)
				{
					EtapeDAO etapeDao = new EtapeDAO(DriverACCESS.getInstance());
					listeEtape.get(k).setRecette(recette);
					etapeDao.create(listeEtape.get(k));
				}
				for(int k=0;k<listeIngredient.size();k++)
				{
					IngredientDAO ingredientDao = new IngredientDAO(DriverACCESS.getInstance());
					listeIngredient.get(k).setRecette(recette);
					ingredientDao.create(listeIngredient.get(k));
				}
			}
		}
		
		return recette;
	}
	public Recette lireRecette(HttpServletRequest request)
	{
		//gestion de la recette
		String nom = getVal(request, "nom");
		String avis = getVal(request, "avis");
		String type = getVal(request, "type");
		String temps_total = getVal(request, "temps");
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("sessionUtilisateur");
		
		Recette recette = new Recette();
		
		//nom
		try 
        {
            validNom(nom);
        } 
        catch (Exception e) 
        {
            setErreur("nom", e.getMessage());
        }
		
		//avis
		try 
        {
            validAvis(avis);
        } 
        catch (Exception e) 
        {
            setErreur("avis", e.getMessage());
        }
				
		//type
		try 
        {
            validType(type);
        } 
        catch (Exception e) 
        {
            setErreur("type", e.getMessage());
        }
				
		//temps total
		try 
        {
            validTemps(temps_total);
        } 
        catch (Exception e) 
        {
            setErreur("temps", e.getMessage());
        }
		
		//utilisateur
		try 
        {
            validUtilisateur(user);
        } 
        catch (Exception e) 
        {
            setErreur("utilisateur", e.getMessage());
        }
			
		//set recette
		recette.setAvis(avis);
		recette.setNom(nom);
		recette.setTemps_total(temps_total);
		recette.setType(type);
		recette.setUser(user);
		
		return recette;
				
	}
	public ArrayList<Ingredient> lireIngredient(HttpServletRequest request)
	{
		ArrayList<Ingredient> listeIngredient = new ArrayList<Ingredient>();
		int j=0;
		while(request.getParameter("ingredient"+j)!=null)
		{
			Ingredient ingredient = new Ingredient(request.getParameter("ingredient"+j),null);
			//ingr�dient
			try
			{
				validIngredient(ingredient,j);
			}
			catch(Exception e)
			{
				setErreur("ingredient"+j, e.getMessage());
			}
			listeIngredient.add(ingredient);
			
			j++;
		}
		return listeIngredient;
	}
	public ArrayList<Etape> lireEtape(HttpServletRequest request)
	{
		ArrayList<Etape> listeEtape = new ArrayList<Etape>();
		int i=0;
		while(request.getParameter("etape"+i)!=null)
		{
			Etape etape = new Etape(i+1,request.getParameter("etape"+i),null);
			//�tape
			try
			{
				validEtape(etape,i);
			}
			catch(Exception e)
			{
				setErreur("Etape"+i, e.getMessage());
			}
			listeEtape.add(etape);
			
			i++;
		}
		return listeEtape;
	}
	public ArrayList<Ingredient> ajouterIngredient(HttpServletRequest request)
	{
		ArrayList<Ingredient> listeIngredient = lireIngredient(request);
		
		Ingredient ingredient = new Ingredient(getVal(request, "ingredient"),null);
		
		try
		{
			validIngredient(ingredient);
		}
		catch(Exception e)
		{
			setErreur("ingredient", e.getMessage());
		}
		
		if(erreurs.isEmpty())
		{
			listeIngredient.add(ingredient);
		}
		return listeIngredient;
	}
	public ArrayList<Etape> ajouterEtape(HttpServletRequest request)
	{
		ArrayList<Etape> listeEtape = lireEtape(request);
		
		Etape etape = new Etape(0,getVal(request, "etape"),null);
		
		try
		{
			validEtape(etape);
		}
		catch(Exception e)
		{
			setErreur("etape", e.getMessage());
		}
		
		if(erreurs.isEmpty())
		{
			listeEtape.add(etape);
		}
		return listeEtape;
	}
	//---------------------------------------------------------------------------------------
	//nom
    private void validNom(String nom) throws Exception 
    {
        if (nom == null) 
        {
            throw new Exception("Nom manquant");
        }
    }
    //avis
    private void validAvis(String avis) throws Exception 
    {
        if (avis == null) 
        {
            throw new Exception("Avis manquant");
        }
    }
    //type
    private void validType(String type) throws Exception 
    {
        if (type == null) 
        {
            throw new Exception("Type manquant");
        }
    }
    //temps
    private void validTemps(String temps) throws Exception 
    {
        if (temps == null) 
        {
            throw new Exception("Temps manquant");
        }
    }
    //utilisateur
    private void validUtilisateur(Utilisateur utilisateur) throws Exception 
    {
        if (utilisateur == null) 
        {
            throw new Exception("Utilisateur manquant");
        }
    }
    
    //-----------------------------------------------------------------------------
    //�tape
    private void validEtape(Etape etape,int i) throws Exception 
    {
        if (etape == null) 
        {
            throw new Exception("Etape "+i+" manquant");
        }
        else
        {
        	if(etape.getExplication() == null)
        	{
        		throw new Exception("Explication de l'�tape "+i+" manquante");
        	}
        }
    }
    private void validEtape(Etape etape) throws Exception
    {

        if (etape == null) 
        {
            throw new Exception("Etape � ajouter manquant");
        }
        else
        {
        	if(etape.getExplication() == null)
        	{
        		throw new Exception("Explication de l'�tape � ajouter manquante");
        	}
        }
    }
    //ingr�dient
    private void validIngredient(Ingredient ingredient,int i) throws Exception 
    {
        if (ingredient == null) 
        {
            throw new Exception("Ingredient "+i+" manquant");
        }
        else
        {
        	if(ingredient.getDescription() == null)
        	{
        		throw new Exception("Description de l'ingr�dient "+i+" manquante");
        	}
        }
    }
    private void validIngredient(Ingredient ingredient) throws Exception
    {
    	if (ingredient == null) 
        {
            throw new Exception("Ingredient � aujouter manquant");
        }
        else
        {
        	if(ingredient.getDescription() == null)
        	{
        		throw new Exception("Description de l'ingr�dient � ajouter manquante");
        	}
        }
    }
}
