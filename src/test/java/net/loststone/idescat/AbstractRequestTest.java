package net.loststone.idescat;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class AbstractRequestTest {
	
	AbstractRequest absReq; 
	
	@Before
	public void setUp() {
		absReq = mock(AbstractRequest.class);
	}
	
	@Test public void testSetLang() {}
	@Test public void testGetLang() {}
	@Test public void setVersio() {}
	@Test public void setFormat() {}
	@Test public void setCodificacio() {}
	@Test public void getCodificacio() {}
	@Test public void inicialitzarParametres() {}
	
}