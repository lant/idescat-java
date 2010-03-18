package net.loststone.idescat;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpInnerRequest implements InnerRequest {
		
	HttpClient httpClient; 
	HttpMethod method; 
	int statusCode; 
	boolean executada; 
	
	public HttpInnerRequest() {
		httpClient = new HttpClient();
		executada = false; 
	}
	
	public void get(String url) throws HttpException, IOException {
		method = new GetMethod(url);
		executada = true;
		httpClient.executeMethod(method);
	}

	public String getResult() throws IOException {
		if (executada)
			return new String(method.getResponseBody());
		else 
			return null;
	}

	public boolean getSuccess() {
		if (executada)
			return (statusCode == HttpStatus.SC_OK);
		else
			return false; 
	}

}
