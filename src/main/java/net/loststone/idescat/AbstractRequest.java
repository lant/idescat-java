package net.loststone.idescat;

import java.io.IOException;

import net.loststone.idescat.common.Codificacio;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.common.Idioma;
import net.loststone.idescat.common.Versio;

import org.apache.commons.httpclient.HttpException;
import net.loststone.idescat.InvalidUrlException; 

/**
 * Classe base de les Requests. Conte la URL de la peticio i permet configurar els 
 * parametres comuns de tots els metodes d'Idescat.
 */
public abstract class AbstractRequest {

	// URL d'idescat.
	protected String BASE = "http://api.idescat.cat/"; 
	// per defecte retorna el resultat en catala.
	protected String PARAM_LANG = "?lang=";
	// per defecte retorna el resultat en codificacio UTF-8
	protected String PARAM_CODIFICACIO = "&enc=";
	protected String AMPERSAND="&";
	
	/**
	 * Aquest objecte es el que fa la peticio en si, ja sigui HTTP o 
	 * qualsevol altre.
	 */
	protected InnerRequest innerRequest; 
	
	protected String lang = "cat";
	protected String versio; 
	protected String format; 
	protected String codifcacio ="UTF-8";
	
	public AbstractRequest(InnerRequest innerRequest) {
		this.innerRequest = innerRequest;
		this.format = "xml";
		this.versio = "v1";
	}
	
	/**
	 * Configura la peticio perque retorni el resultat en l'idioma especificat.
	 * @param idioma Idioma en el que es retornara el resultat.
	 */
	public void setLang(Idioma idioma) {
		this.lang =idioma.indicador();
	}
	
	/**
	 * Retorna el parametre HTTP que s'utilitzara per especificar l'idioma.
	 * @return
	 */
	public String getLang() {
		return  PARAM_LANG+this.lang;
	}
	
	/**
	 * Configura la versio de l'API d'Idescat a utilitzar.
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
		if (formatPermes(format)) {
			this.format = format.nom();
		}
	}
	
	/**
	 * Configura la codificacio en que es retornaran les dades. 
	 * @param codificacio
	 */
	public void setCodificacio(Codificacio codificacio) {
		this.codifcacio = codificacio.nom();
	}
	
	/**
	 * Retorna el parametre HTTP que s'utilitzara per la codificacio del resultat.
	 * @return
	 */
	public String getCodificacio() {
		return PARAM_CODIFICACIO + this.codifcacio;
	}
	
	/**
	 * Metode que realitza la connexio i consulta a la API.
	 * @throws HttpException En cas de que hi hagi algun error en la consulta HTTP.
	 * @throws IOException
	 */
	public void get() throws HttpException, IOException, InvalidUrlException {
		this.innerRequest.get(this.getUrl());
	}
	
	/**
	 * Retorna el resultat de l'operacio 'get' 
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
	 * Metode que retorna la URL que s'utilitzara per realitzar la consulta. 
	 * @return La url que s'utilitzara per fer la consulta a l'API.
	 */
	public abstract String getUrl() throws InvalidUrlException;

	/**
	 * Operacio que torna cert si l'operacio permet aquest format de sortida. No totes les operacions
	 * suporten els mateixos format.s
	 * @param format 
	 * @return Cert sii la operacio permet aquest format de sortida.
	 */
	protected abstract boolean formatPermes(Format format);
	
	/**
	 * Retorna el servei seleccionat. 
	 * @return
	 */
	protected abstract String getServei();

	/**
	 * Retorna la operacio seleccionada.
	 * @return
	 */
	protected abstract String getOperacioString();
	
	/**
	 * Totes les operacions tenen parametres comuns. Aquest metode els inicialitza.
	 * @param resultat
	 */
	// TODO com a variable protegida de la classe abstracte.
	protected void inicialitzarParametres(StringBuffer resultat) throws InvalidUrlException {
	  if (getServei() == null) {
	    throw new InvalidUrlException("Missing service name.");
	  } else if (getOperacioString() == null) {
	    throw new InvalidUrlException("Missing Operation."); 
	  }
	  // http://api.idescat.cat
	  
		resultat.append(this.BASE);
		resultat.append(getServei());
		resultat.append("/");
		resultat.append(this.versio);
		resultat.append("/");
		resultat.append(this.getOperacioString());
    resultat.append(".");
		resultat.append(this.format);
		
		// parametres generals
		resultat.append(getLang());
		resultat.append(getCodificacio());		
	}

	protected void setConector(StringBuffer resultat, boolean first) {
		if (first)
			resultat.append("?");
		else 
			resultat.append(this.AMPERSAND);
	}

}
