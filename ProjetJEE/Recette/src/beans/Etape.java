package beans;

public class Etape {
	//Propriétés
	private int id;
	private int numEtape;
	private String explication;
	private Recette recette;
	
	//Constructeurs
	public Etape(){}
	
	public Etape(int id,int numEtape,String explication,Recette recette){
		this.id = id;
		this.numEtape = numEtape;
		this.explication = explication;
		this.recette = recette;
	}
	
	public Etape(int numEtape,String explication,Recette recette){
		this.numEtape = numEtape;
		this.explication = explication;
		this.recette = recette;
	}

	//Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumEtape() {
		return numEtape;
	}

	public void setNumEtape(int numEtape) {
		this.numEtape = numEtape;
	}

	public String getExplication() {
		return explication;
	}

	public void setExplication(String explication) {
		this.explication = explication;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}
}
