package net.loststone.idescat;

import net.loststone.idescat.indicadors.IndicadorsRequest;

/**
 * Factory que utilitza HTTP per realizar les consultes a la API.
 * @author marc
 */
public class HttpFactory extends AbstractFactory {

	@Override
	public IndicadorsRequest getIndicadorsRequest() {
		return new IndicadorsRequest(new HttpInnerRequest());
	}

}
