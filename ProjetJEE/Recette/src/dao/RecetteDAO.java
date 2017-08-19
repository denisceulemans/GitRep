package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import beans.Recette;
import beans.Utilisateur;

public class RecetteDAO extends DAO<Recette> {

	public RecetteDAO(Connection conn) 
	{
		super(conn);
	}

	public boolean create(Recette obj) 
	{           
		try
		{
			String sql = "{call creerRecette(?,?,?,?,?,?)}";
			CallableStatement call = this.connect.prepareCall(sql); 
			call.setString(1, obj.getNom());
			call.setString(2, obj.getAvis());
			call.setString(3, obj.getType());
			call.setString(4, obj.getTemps_total());
			call.setInt(5, obj.getUser().getId());
			call.registerOutParameter(6, java.sql.Types.INTEGER); 
			
			call.executeUpdate();
			
			obj.setId(call.getInt(6));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(Recette obj)
	{
		try
	    {
			String strDelete = "delete from Recette where nom_recette = '" + obj.getNom() + "';";
	    	PreparedStatement s = this.connect.prepareStatement(strDelete);
	    	s.executeUpdate();
	    	return true;
	    }
	    catch (SQLException e){
	    	e.printStackTrace();
	    	return false;
	    }
	}
	   
	public boolean update(Recette obj)
	{
		try
		{
			String strUpdate = "update Recette set avis_recette = '" + obj.getAvis() +
							   "',type_recette = '" + obj.getType() + "',temps_total = '" +
						       obj.getTemps_total() + "' where nom_recette = '" + obj.getNom() + "';";
		    PreparedStatement s = this.connect.prepareStatement(strUpdate);
		    s.executeUpdate();
		    return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		    return false;
		}
	}

	public ArrayList<Recette> select()
	{
		ArrayList<Recette> listeRecette = new ArrayList<Recette>();
		Recette recette = new Recette();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from recette");
			while(result.next())
		    {
				DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
		      	Utilisateur utilisateur = UtilisateurDao.find(result.getInt("id_utilisateur"));
				recette = new Recette(result.getInt("id_recette"),result.getString("nom_recette"),result.getString("avis_recette"),result.getString("type_recette"),result.getString("temps_total"),utilisateur);
				listeRecette.add(recette);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listeRecette;
	}
	public ArrayList<Recette> selectUtilisateur(Utilisateur user)
	{
		ArrayList<Recette> listeRecette = new ArrayList<Recette>();
		Recette recette = new Recette();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from recette where id_utilisateur = "+user.getId()+"");
			while(result.next())
		    {
				DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
		      	Utilisateur utilisateur = UtilisateurDao.find(result.getInt("id_utilisateur"));
				recette = new Recette(result.getInt("id_recette"),result.getString("nom_recette"),result.getString("avis_recette"),result.getString("type_recette"),result.getString("temps_total"),utilisateur);
				listeRecette.add(recette);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return listeRecette;
	}
	
	public Recette find(int id_Recette)
	{
		ArrayList<Recette> listeRecette = new ArrayList<Recette>();
		Recette recette = new Recette();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from recette where id_recette = " + id_Recette + "");
			if(result.next()){
				DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
		      	Utilisateur utilisateur = UtilisateurDao.find(result.getInt("id_utilisateur"));
				recette = new Recette(result.getInt("id_recette"),result.getString("nom_recette"),result.getString("avis_recette"),result.getString("type_recette"),result.getString("temps_total"),utilisateur);
				listeRecette.add(recette);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return recette;
	}
	
	public ArrayList<Recette> selectRecherche(String recherche, String typeRecherche)
	{
		//pr�paration au mot cl� de la recherche pour le "like"
		String[] likes = (recherche.trim()).split(" ");
		String like="%";
		for(int i=0;i<likes.length;i++)
		{
			like = like +likes[i]+"%";
		}
		
		//requ�te suivant le type de recherche
		String requete = "";
		ArrayList<Recette> listeRecette = new ArrayList<Recette>();
		Recette recette = new Recette();
		if(recherche != null)
		{
			switch(typeRecherche)
			{
				case "nom" :
					requete = "select * from recette where nom_recette like '"+like+"'";
					break;
				case "type" :
					requete = "select * from recette where type_recette like '"+like+"'";
					break;
				case "tempstotal" : 
					requete = "select * from recette where temps_total like '"+like+"'";
					break;
			}
			try
			{
				ResultSet result = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery(requete);
				if(result.next())
				{
					DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
			      	Utilisateur utilisateur = UtilisateurDao.find(result.getInt("id_utilisateur"));
					recette = new Recette(
							result.getInt("id_recette"),
							result.getString("nom_recette"),
							result.getString("avis_recette"),
							result.getString("type_recette"),
							result.getString("temps_total"),
							utilisateur);
					listeRecette.add(recette);
				}
				return listeRecette;
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return null;
	}
}