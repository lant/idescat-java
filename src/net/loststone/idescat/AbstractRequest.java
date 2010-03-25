package net.loststone.idescat;

import java.io.IOException;

import net.loststone.idescat.common.Codificacio;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.common.Idioma;
import net.loststone.idescat.common.Versio;

import org.apache.commons.httpclient.HttpException;

/**
 * Classe base de les Requests. 
 *
 */
public abstract class AbstractRequest {

	protected final String BASE = "http://api.idescat.cat/"; 
	protected final String PARAM_LANG = "?lang=";
	protected final String PARAM_CODIFICACIO = "?enc=";
	
	/**
	 * Aquest objecte és el que fa la petició en si, ja sigui HTTP o 
	 * qualsevol altre.
	 */
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
	
	/**
	 * Mètode que realitza la connexió i consulta a la API.
	 * @throws HttpException En cas de que hi hagi algun error en la consulta HTTP.
	 * @throws IOException
	 */
	public void get() throws HttpException, IOException {
		this.innerRequest.get(this.getUrl());
	}
	
	/**
	 * Retorna el resultat de l'operació 'get' 
	 * @return El resultat, ja sigui en xml, json o qualsevol altre format sempre i quant
	 * s'hagi realitzat la consulta. null altrament. 
	 * @throws IOException
	 */
	public String getResult() throws IOException {
		return this.innerRequest.getResult();
	}
	
	/**
	 * Mostra si la consulta s'ha realitzat correctament.
	 * @return Cert si la consulta ha funcionat, fals altrament. 
	 */
	public boolean getSuccess() {
		return this.innerRequest.getSuccess();
	}
	
	/**
	 * Mètode que retorna la URL que s'utilitzarà per realitzar la consulta. 
	 * @return La url que s'utilitzarà per fer la consulta a l'API.
	 */
	public abstract String getUrl();
}
