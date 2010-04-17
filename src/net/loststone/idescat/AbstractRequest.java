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

	protected String BASE = "http://api.idescat.cat/"; 
	protected String PARAM_LANG = "?lang=cat";
	protected String PARAM_CODIFICACIO = "&enc=UTF-8";
	protected String AMPERSAND="&";
	
	/**
	 * Aquest objecte �s el que fa la petici� en si, ja sigui HTTP o 
	 * qualsevol altre.
	 */
	protected InnerRequest innerRequest; 
	
	protected String lang = "";
	protected String versio; 
	protected String format; 
	protected String codifcacio ="";
	
	public AbstractRequest(InnerRequest innerRequest) {
		this.innerRequest = innerRequest;
		this.format = ".xml";
		this.versio = "v1";
	}
	
	public void setLang(Idioma idioma) {
		this.lang =idioma.indicador();
	}
	
	public String getLang() {
		return  PARAM_LANG+this.lang;
	}
	
	public void setVersio(Versio versio) {
		this.versio = versio.nom();
	}
	
	public void setFormat(Format format) {
		this.format = format.nom();
	}
	
	public void setCodificacio(Codificacio codificacio) {
		this.codifcacio = codificacio.nom();
	}
	
	public String getCodificacio() {
		return PARAM_CODIFICACIO + this.codifcacio;
	}
	
	/**
	 * M�tode que realitza la connexi� i consulta a la API.
	 * @throws HttpException En cas de que hi hagi algun error en la consulta HTTP.
	 * @throws IOException
	 */
	public void get() throws HttpException, IOException {
		this.innerRequest.get(this.getUrl());
	}
	
	/**
	 * Retorna el resultat de l'operaci� 'get' 
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
	 * M�tode que retorna la URL que s'utilitzar� per realitzar la consulta. 
	 * @return La url que s'utilitzar� per fer la consulta a l'API.
	 */
	public abstract String getUrl();
}
