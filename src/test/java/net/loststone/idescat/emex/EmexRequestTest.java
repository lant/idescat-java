package net.loststone.idescat.emex;

import static org.junit.Assert.*;

import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.InvalidUrlException;
import net.loststone.idescat.common.Format;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class EmexRequestTest {

  EmexRequest emex = null; 
  
	@Before
	public void setUp() throws Exception {
	  emex = new EmexRequest(mock(InnerRequest.class));
	}

	@Test
	public void testGetDades1() throws InvalidUrlException {
	  //Informació estadística bàsica d'Abella de la Conca (250019), el Pallars Jussà i Catalunya
	  //http://api.idescat.cat/emex/v1/dades.xml?id=250019
	  emex.setFormat(Format.XML);
	  emex.setOperacio(EmexRequest.operacio.dades);
	  emex.setMunicipiId("250019");
	  assertEquals("http://api.idescat.cat/emex/v1/dades.xml?lang=cat&enc=UTF-8&id=250019", emex.getUrl());
	}

	@Test
  public void testGetDades2() throws InvalidUrlException {
	  //Dades de població total, masculina i femenina d'Abrera, el Baix Llobregat i Catalunya
	  //http://api.idescat.cat/emex/v1/dades.xml?id=080018&i=f171,f36,f42
	  emex.setFormat(Format.XML);
    emex.setOperacio(EmexRequest.operacio.dades);
    emex.setMunicipiId("080018");
    emex.addIndicador("f171");
    emex.addIndicador("f36");
    emex.addIndicador("f42");
    assertEquals("http://api.idescat.cat/emex/v1/dades.xml?lang=cat&enc=UTF-8&id=080018&i=f171,f36,f42", emex.getUrl());
  }

	@Test(expected=InvalidUrlException.class)
	public void testGetDadesIncorrecte() throws InvalidUrlException {
	  emex.setFormat(Format.XML);
    emex.setOperacio(EmexRequest.operacio.dades);
    emex.addIndicador("f171");
    emex.addIndicador("f36");
    emex.addIndicador("f42");
    emex.getUrl();
    fail("No hauria d'arribar.");
	}
	
	@Test
  public void testGetNodes() throws InvalidUrlException {
	  //Llista jeràrquica d'àmbits territorials (Catalunya, comarques, municipis) i els seus codis en format JSON
	  //http://api.idescat.cat/emex/v1/nodes.json
	  emex.setFormat(Format.JSON);
	  emex.setOperacio(EmexRequest.operacio.nodes);
	  assertEquals("http://api.idescat.cat/emex/v1/nodes.json?lang=cat&enc=UTF-8",emex.getUrl());
  }
	
	@Test
	public void testSetMunicipiId() {
	  assertEquals(null, emex.getMunicipiId());
	  emex.setMunicipiId("80");
	  assertEquals("80", emex.getMunicipiId());
	}

	
	@Test
	public void testSetOperacio() {
	  emex.setOperacio(EmexRequest.operacio.dades);
	  assertEquals(EmexRequest.operacio.dades, emex.getOperacio());
	  emex.setOperacio(EmexRequest.operacio.nodes);
	  assertEquals(EmexRequest.operacio.nodes, emex.getOperacio());
	}

}
