package net.loststone.idescat;

import net.loststone.idescat.indicadors.IndicadorsRequest;

/**
 * Classe base de les Factories de Requests. 
 * @author marc
 */
public abstract class AbstractFactory {
	
	/**
	 * Crea un objecte per fer una petici— a la API d'Indicadors.
	 * @return Un objecte a punt per ser utilitzat per consultar l'API.
	 */
	public abstract IndicadorsRequest getIndicadorsRequest();
	
}
