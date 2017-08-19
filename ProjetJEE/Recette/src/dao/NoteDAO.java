package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import beans.Note;
import beans.Recette;
import beans.Utilisateur;

public class NoteDAO extends DAO<Note>{
	
	public NoteDAO(Connection conn){
		super(conn);
	}

	public boolean create(Note obj) 
	{           
		try
		{
			String strCreate = "insert into Note (id_utilisateur,id_recette,commentaire,cote_note) values ("
			+ obj.getUtilisateur().getId() + "," + obj.getRecette().getId() + ",'" + obj.getCommentaire() + "'," + obj.getCoteNote() + ")";
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

	public boolean delete(Note obj)
	{
		try
	    {
			String strDelete = "delete from Note where id_utilisateur = " + obj.getUtilisateur().getId() + " and id_recette = " + obj.getRecette().getId() + ";";
	    	PreparedStatement s = this.connect.prepareStatement(strDelete);
	    	s.executeUpdate();
	    	return true;
	    }
	    catch (SQLException e){
	    	e.printStackTrace();
	    	return false;
	    }
	}
	   
	public boolean update(Note obj)
	{
		try
		{
			String strUpdate = "update Note set commentaire = '" + obj.getCommentaire() + "',cote_note = " +
						obj.getCoteNote() + " where id_utilisateur = " + obj.getUtilisateur().getId() + 
						" and id_recette = " + obj.getRecette().getId() + "";
		    PreparedStatement s = this.connect.prepareStatement(strUpdate);
		    s.executeUpdate();
		    return true;
		}
		catch (SQLException e){
			e.printStackTrace();
		    return false;
		}
	}

	public ArrayList<Note> select(Recette recette)
	{
		ArrayList<Note> listeNote = new ArrayList<Note>();
		Note note = new Note();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from note where id_recette = "+recette.getId()+"");
			while(result.next())
		      {
				DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
		      	Utilisateur utilisateur = UtilisateurDao.find(result.getInt("id_utilisateur"));
		      	DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	Recette recetteTrouver = RecetteDao.find(result.getInt("id_recette"));
				note = new Note(utilisateur,recetteTrouver,result.getString("commentaire"),result.getInt("cote_note"));
				listeNote.add(note);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return listeNote;
	}
	
	public Note find(int id_Note)
	{
		Note note = new Note();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from note where id_note = " + id_Note + "");
			if(result.next()){
				DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
		      	Utilisateur utilisateur = UtilisateurDao.find(result.getInt("id_utilisateur"));
		      	DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	Recette recette = RecetteDao.find(result.getInt("id_recette"));
		      	note = new Note(utilisateur,recette,result.getString("commentaire"),result.getInt("cote_note"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return note;
	}
	public Note select(Utilisateur utilisateur,Recette recette)
	{
		Note note = new Note();
		  
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("select * from note where id_utilisateur = " + utilisateur.getId() + " and id_recette = "+recette.getId()+"");
			if(result.next()){
				DAO<Utilisateur> UtilisateurDao = new UtilisateurDAO(DriverACCESS.getInstance());
		      	Utilisateur utilisateurTrouver = UtilisateurDao.find(result.getInt("id_utilisateur"));
		      	DAO<Recette> RecetteDao = new RecetteDAO(DriverACCESS.getInstance());
		      	Recette recetteTrouver = RecetteDao.find(result.getInt("id_recette"));
		      	note = new Note(utilisateur,recette,result.getString("commentaire"),result.getInt("cote_note"));
		      	return note;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	public void traitementNote(Note note)
	{
		NoteDAO noteDao = new NoteDAO(DriverACCESS.getInstance()); 
		Note n = select(note.getUtilisateur(),note.getRecette());
		if(n == null)
		{
			noteDao.create(note);
		}
		else
		{
			noteDao.update(note);
		}
	}
}
