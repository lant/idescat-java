package net.loststone.idescat.common;

public enum Versio {
	V1("v1");
	
	private final String nom; 
	
	Versio(String vers) {
		this.nom = vers;
	}
	
	public String nom() {
		return nom;
	}
}
