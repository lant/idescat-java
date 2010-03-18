package net.loststone.idescat;

public class HttpFactory extends AbstractFactory {

	@Override
	public IndicadorsRequest getIndicadorsRequest() {
		IndicadorsRequest indReq = new IndicadorsRequest(new HttpInnerRequest());
		return indReq;
	}

}
