package net.loststone.idescat;

import java.io.IOException;

import net.loststone.idescat.common.Codificacio;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.common.Idioma;
import net.loststone.idescat.common.Versio;

import org.apache.commons.httpclient.HttpException;

public abstract class AbstractRequest {

	private static final String PARAM_LANG = "?lang=";
	private static final String PARAM_CODIFICACIO = "?enc=";
	
	protected InnerRequest innerRequest; 
	
	protected String lang;
	protected String versio; 
	protected String format; 
	protected String codifcacio;
	
	public AbstractRequest(InnerRequest innerRequest) {
		this.innerRequest = innerRequest;
		this.format = ".xml";
		this.versio = "v1";
	}
	
	public void setLang(Idioma idioma) {
		this.lang = PARAM_LANG+idioma.indicador();
	}
	
	public void setVersio(Versio versio) {
		this.versio = versio.nom();
	}
	
	public void setFormat(Format format) {
		this.format = "."+format.nom();
	}
	
	public void setCodificacio(Codificacio codificacio) {
		this.codifcacio = PARAM_CODIFICACIO + codificacio.nom();
	}
	
	public void get() throws HttpException, IOException {
		this.innerRequest.get(this.getUrl());
	}
	
	public String getResult() throws IOException {
		return this.innerRequest.getResult();
	}
	
	public boolean getSuccess() {
		return this.innerRequest.getSuccess();
	}
	
	public abstract String getUrl();
}
