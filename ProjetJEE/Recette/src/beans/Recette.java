package beans;

public class Recette 
{
	//Propriétés
	private int id;
	private String nom;
	private String avis;
	private String type;
	private String temps_total;
	private Utilisateur user;
	
	//Constructeurs
    public Recette() {}
	
	public Recette(int id, String nom, String avis, String type, String temps_total, Utilisateur user) 
	{
		this.id = id;
		this.nom = nom;
		this.avis = avis;
		this.type = type;
		this.temps_total = temps_total;
		this.user = user;
	}
	
	public Recette(String nom, String avis, String type, String temps_total, Utilisateur user) 
	{
		this.nom = nom;
		this.avis = avis;
		this.type = type;
		this.temps_total = temps_total;
		this.user = user;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemps_total() {
		return temps_total;
	}

	public void setTemps_total(String temps_total) {
		this.temps_total = temps_total;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
