package net.loststone.idescat;

import java.io.IOException;

import net.loststone.idescat.common.Codificacio;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.common.Idioma;
import net.loststone.idescat.common.Versio;

import org.apache.commons.httpclient.HttpException;

/**
 * Classe base de les Requests. Conté la URL de la petició i permet configurar els 
 * paràmetres comuns de tots els mètodes d'Idescat.
 */
public abstract class AbstractRequest {

	// URL d'idescat.
	protected String BASE = "http://api.idescat.cat/"; 
	// per defecte retorna el resultat en catala.
	protected String PARAM_LANG = "?lang=cat";
	// per defecte retorna el resultat en codificació UTF-8
	protected String PARAM_CODIFICACIO = "&enc=UTF-8";
	protected String AMPERSAND="&";
	
	/**
	 * Aquest objecte ÔøΩs el que fa la peticiÔøΩ en si, ja sigui HTTP o 
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
	
	/**
	 * Configura la petició perquè retorni el resultat en l'idioma especificat.
	 * @param idioma Idioma en el que es retornarà el resultat.
	 */
	public void setLang(Idioma idioma) {
		this.lang =idioma.indicador();
	}
	
	/**
	 * Retorna el paràmetre HTTP que s'utilitzarà per especificar l'idioma.
	 * @return
	 */
	public String getLang() {
		return  PARAM_LANG+this.lang;
	}
	
	/**
	 * Configura la versió de l'API d'Idescat a utilitzar.
	 * @param versio
	 */
	public void setVersio(Versio versio) {
		this.versio = versio.nom();
	}
	
	/**
	 * Configura el format en el qual es retornaran les dades.
	 * @param format Format en el que es retornaran les dades. 
	 */
	public void setFormat(Format format) {
		this.format = format.nom();
	}
	
	/**
	 * Configura la codificació en que es retornaran les dades. 
	 * @param codificacio
	 */
	public void setCodificacio(Codificacio codificacio) {
		this.codifcacio = codificacio.nom();
	}
	
	/**
	 * Retorna el paràmetre HTTP que s'utilitzarà per la codificació del resultat.
	 * @return
	 */
	public String getCodificacio() {
		return PARAM_CODIFICACIO + this.codifcacio;
	}
	
	/**
	 * MÔøΩtode que realitza la connexiÔøΩ i consulta a la API.
	 * @throws HttpException En cas de que hi hagi algun error en la consulta HTTP.
	 * @throws IOException
	 */
	public void get() throws HttpException, IOException {
		this.innerRequest.get(this.getUrl());
	}
	
	/**
	 * Retorna el resultat de l'operaciÔøΩ 'get' 
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
	 * MÔøΩtode que retorna la URL que s'utilitzarÔøΩ per realitzar la consulta. 
	 * @return La url que s'utilitzarÔøΩ per fer la consulta a l'API.
	 */
	public abstract String getUrl();
}
