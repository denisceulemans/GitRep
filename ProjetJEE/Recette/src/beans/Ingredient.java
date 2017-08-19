package beans;

public class Ingredient {
	//Propriétés
	private int id;
	private String description;
	private Recette recette;

	//Constructeurs
	public Ingredient(){}
	
	public Ingredient(int id,String description, Recette recette){
		this.id = id;
		this.description = description;
		this.recette = recette;
	}
	
	public Ingredient(String description, Recette recette){
		this.description = description;
		this.recette = recette;
	}
	
	//Getters et Setters
	public int getId() {
		return id;
	}

	public void setIdIngredient(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}
}
