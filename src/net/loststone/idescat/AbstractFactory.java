package net.loststone.idescat;

import net.loststone.idescat.common.Idioma;

public abstract class AbstractFactory {
	
	protected String lang;
	protected String versio; 
	protected String format; 
	protected String codifcacio;
	
	
	public abstract IndicadorsRequest getIndicadorsRequest();
	
	public void setLang(Idioma idioma) {
		
	}
}
