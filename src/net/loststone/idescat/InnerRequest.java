package net.loststone.idescat;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

/**
 * Aquesta interficie es la que han d'implementar les classes que fan el request
 * HTTP en si. 
 * @author lant
 *
 */
public interface InnerRequest {
	void get(String url) throws HttpException, IOException;
	String getResult() throws IOException;
	boolean getSuccess();
}

