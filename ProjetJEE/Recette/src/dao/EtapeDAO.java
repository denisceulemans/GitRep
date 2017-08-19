package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.*;

public class EtapeDAO extends DAO<Etape>{
	
	public EtapeDAO (Connection conn){
		super(conn);
	}

	public boolean create(Etape obj) 
	{           
		try
		{
			String strCreate = "insert into Etape (numero_etape,explication,id_recette) values (" + obj.getNumEtape() + ",'" + obj.getExplication() + "'," + obj.getRecette().getId() + ")";
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

	public boolean delete(Etape obj)
	{
		try
	    {
			String strDelete = "delete from Etape where id_etape = " + obj.getId() + ";";
	    	PreparedStatement s = this.connect.prepareStatement(strDelete);
	    	s.executeUpdate();
	    	return true;
	    }
	    catch (SQLException e){
	    	e.printStackTrace();
	    	return false;
	    }
	}
	   
	public boolean update(Etape obj)
	{
		try
		{
			String strUpdate = "update Etape set numero_etape = " + obj.getNumEtape() + ",explication = '" + obj.getExplication() + "' where id_etape = " + obj.getId() + ";";
		    PreparedStatement s = this.connect.prepareStatement(strUpdate);
		    s.executeUpdate();
		    return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		    return false;
		}
	}
	public ArrayList<Etape> select(Recette recette)
	{
		ArrayList<Etape> listeEtape = new ArrayList<Etape>();
		try
		{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from etape where id_recette = "+recette.getId()+"");
			while(result.next())
		      {
		    	  DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	  Recette recetteTrouver = RecetteDao.find(result.getInt("id_recette"));
		    	  listeEtape.add(new Etape(result.getInt("id_etape"),result.getInt("numero_etape"),result.getString("explication"), recetteTrouver));
		      }
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listeEtape;
	}

	public Etape find(int id_Etape)
	{
		Etape etape = new Etape();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from etape where id_etape = " + id_Etape + "");
			if(result.next()){
				DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	  Recette recette = RecetteDao.find(result.getInt("id_recette"));
				etape = new Etape(result.getInt("id_etape"),result.getInt("numero_etape"),result.getString("explication"),recette);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return etape;
	}
}
