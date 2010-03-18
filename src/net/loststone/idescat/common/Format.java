package net.loststone.idescat.common;

public enum Format {
	PHP("php"), XML("xml"), JSON("json");
	
	private String nom;
	
	Format(String nom) {
		this.nom = nom;
	}
	
	public String nom() {
		return nom; 
	}
}
