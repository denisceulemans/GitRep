package beans;

public class Note {
	//Propriétés
	private Utilisateur utilisateur;
	private Recette recette;
	private String commentaire;
	private int coteNote;
	
	//Constructeurs
	public Note(){}
	
	public Note(Utilisateur utilisateur,Recette recette,String commentaire,int coteNote){
		this.utilisateur = utilisateur;
		this.recette = recette;
		this.commentaire = commentaire;
		this.coteNote = coteNote;
	}
	
	//Getters et Setters
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public int getCoteNote() {
		return coteNote;
	}

	public void setCoteNote(int coteNote) {
		this.coteNote = coteNote;
	}
}
