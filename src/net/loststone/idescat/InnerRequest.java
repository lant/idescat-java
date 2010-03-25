package net.loststone.idescat;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

/**
 * Aquesta interficie és la que han d'implementar les classes que fan el request
 * a la API en si. Han de gestionar els errors de connexió i altres per separar-ho
 * del domini. 
 * @author lant
 *
 */
public interface InnerRequest {
	
	/**
	 * Mètode que realitza la connexió al servidor i invoca el mètode GET donada una url.
	 * @param url URL a la qual es farà l'HTTP GET
	 * @throws HttpException
	 * @throws IOException
	 */
	void get(String url) throws HttpException, IOException;
	
	/**
	 * Mètode que retorna el resultat del mètode GET.
	 * @return El resultat en format String.
	 * @throws IOException
	 */
	String getResult() throws IOException;
	
	/**
	 * Mètode que ens diu si la connexió s'ha realizat correctament.
	 * @return cert en cas de que tot hagi anat bé, fals altrament.
	 */
	boolean getSuccess();
}

