package net.loststone.idescat.common;

public enum Codificacio {
	UTF8("utf-8"), ISO88591("iso-8859-1");
	
	private String nom; 
	
	Codificacio(String nom) {
		this.nom = nom; 
	}
	
	public String nom() {
		return this.nom; 
	}
}
