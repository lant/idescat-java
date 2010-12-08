package net.loststone.idescat.common;

public enum Idioma {
	EN ("en","Angles"),
	CA ("ca","Catala"),
	ES ("es","Espanyol");
	
	private String m; 
	private String ll; 
	
	
	Idioma(String m, String ll) {
		this.m = m; 
		this.ll = ll; 
	}
	
	public String indicador() { return m; }
	public String nom() { return ll; }
}
