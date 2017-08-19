package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.Ingredient;
import beans.Recette;

public class IngredientDAO extends DAO<Ingredient>{
	
	public IngredientDAO(Connection conn){
		super(conn);
	}

	public boolean create(Ingredient obj) 
	{           
		try
		{
			String strCreate = "insert into Ingredient (description, id_recette) values ('" + obj.getDescription() + "'," + obj.getRecette().getId() + ")";
			PreparedStatement s = this.connect.prepareStatement(strCreate);
			s.executeUpdate();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Ingredient obj)
	{
		try
	    {
			String strDelete = "delete from Ingredient where id_ingredient = " + obj.getId() + ";";
	    	PreparedStatement s = this.connect.prepareStatement(strDelete);
	    	s.executeUpdate();
	    	return true;
	    }
	    catch (SQLException e){
	    	e.printStackTrace();
	    	return false;
	    }
	}
	   
	public boolean update(Ingredient obj)
	{
		try
		{
			String strUpdate = "update Ingredient set description = '" + obj.getDescription() + "' where id_ingredient = " + obj.getId() + ";";
		    PreparedStatement s = this.connect.prepareStatement(strUpdate);
		    s.executeUpdate();
		    return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		    return false;
		}
	}

	public ArrayList<Ingredient> select(Recette recette)
	{
		ArrayList<Ingredient> listeIngredient = new ArrayList<Ingredient>();
		Ingredient ingredient = new Ingredient();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from ingredient where id_recette = "+recette.getId()+"");
			while(result.next())
		    {
				DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	Recette recetteTrouver = RecetteDao.find(result.getInt("id_recette"));
				ingredient = new Ingredient(result.getInt("id_ingredient"),result.getString("description"),recetteTrouver);
				listeIngredient.add(ingredient);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listeIngredient;
	}
	
	public Ingredient find(int id_Ingredient)
	{
		Ingredient ingredient = new Ingredient();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from ingredient where id_ingredient = " + id_Ingredient + "");
			if(result.next()){
				DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	Recette recette = RecetteDao.find(result.getInt("id_recette"));
				ingredient = new Ingredient(result.getInt("id_ingredient"),result.getString("description"),recette);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return ingredient;
	}
}
