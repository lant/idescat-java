package net.loststone.idescat.poblacions;

import static org.junit.Assert.*;

import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.InvalidUrlException;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.common.Idioma;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
public class PoblacionsRequestTest {

  PoblacionsRequest poblacions = null; 
  
	@Before
	public void setUp() throws Exception {
	  poblacions = new PoblacionsRequest(mock(InnerRequest.class));
	}

	@Test
	public void testGetUrl1() throws InvalidUrlException {
	  //Llista d'entitats territorials que comencen amb "ab", separades per salts de línia
	  //http://api.idescat.cat/pob/v1/sug.txt?p=q/ab
	  poblacions.setFormat(Format.TXT);
	  poblacions.setFiltreQ("ab");
	  poblacions.setOperacio(PoblacionsRequest.operacio.sug);
	  assertEquals("http://api.idescat.cat/pob/v1/sug.txt?lang=cat&enc=UTF-8&q=ab", poblacions.getUrl());
	}
	
	@Test
  public void testGetUrl2() throws InvalidUrlException {
	  //Llista de municipis i nuclis de població que comencen amb "ab", en format text
	  //http://api.idescat.cat/pob/v1/sug.txt?p=q/ab;tipus/mun,np
	  poblacions.setFormat(Format.TXT);
	  poblacions.setFiltreQ("ab");
	  poblacions.setOperacio(PoblacionsRequest.operacio.sug);
	  poblacions.afegirFiltreTipus(PoblacionsRequest.tipus.mun);
	  poblacions.afegirFiltreTipus(PoblacionsRequest.tipus.np);
	  assertEquals("http://api.idescat.cat/pob/v1/sug.txt?lang=cat&enc=UTF-8&q=ab&tipus=mun,np",poblacions.getUrl());
	}

	@Test
  public void testGetUrl3() throws InvalidUrlException {
	  //Població de totes les comarques de Catalunya, en format XML
	  //http://api.idescat.cat/pob/v1/cerca.xml?p=tipus/com
	  poblacions.setFormat(Format.XML);
	  poblacions.setOperacio(PoblacionsRequest.operacio.cerca);
	  poblacions.afegirFiltreTipus(PoblacionsRequest.tipus.com);
	  assertEquals("http://api.idescat.cat/pob/v1/cerca.xml?lang=cat&enc=UTF-8&tipus=com&selec=0&orderby=tipus&posicio=0",
	      poblacions.getUrl());
  }
	
	@Test
  public void testGetUrl4() throws InvalidUrlException {
	  //Població de les entitats territorials que es diuen "abrera" o contenen aquest nom, en format JSON
	  //http://api.idescat.cat/pob/v1/cerca.json?p=q/abrera;sim/0,1
	  poblacions.setFormat(Format.JSON);
	  poblacions.setOperacio(PoblacionsRequest.operacio.cerca);
	  poblacions.setFiltreQ("abrera");
	  poblacions.afegirSim(0);
	  poblacions.afegirSim(1);
	  assertEquals("http://api.idescat.cat/pob/v1/cerca.json?lang=cat&enc=UTF-8&q=abrera&sim=0,1&selec=0&orderby=tipus&posicio=0",
	      poblacions.getUrl());
  }
	
	@Test
  public void testGetUrl5() throws InvalidUrlException {
	  //Resultat destacat per a la cerca "abrera", en anglès i en format JSON amb funció de devolució ("f")
	  //http://api.idescat.cat/pob/v1/cerca.json?p=q/abrera;selec/1&callback=f&lang=en
	  poblacions.setFormat(Format.JSON);
	  poblacions.setOperacio(PoblacionsRequest.operacio.cerca);
	  poblacions.setFiltreQ("abrera");
	  poblacions.setLang(Idioma.EN);
	  poblacions.setSelec(true);
	  assertEquals("http://api.idescat.cat/pob/v1/cerca.json?lang=en&enc=UTF-8&q=abrera&selec=1&orderby=tipus&posicio=0",
	      poblacions.getUrl());
  }
	
	@Test
	public void testGetServei() {
	  assertEquals("pob", poblacions.getServei());
	}


	@Test
	public void testFiltreQ() {
	  assertEquals(null, poblacions.getFiltreQ());
	  poblacions.setFiltreQ("bla");
	  assertEquals("bla", poblacions.getFiltreQ());
	}

	@Test
	public void testAfegirFiltreTipus() {
	  assertEquals(0, poblacions.getFiltreTipus().size());
	  poblacions.afegirFiltreTipus(PoblacionsRequest.tipus.cat);
	  assertEquals(1, poblacions.getFiltreTipus().size());
	  assertEquals(PoblacionsRequest.tipus.cat, poblacions.getFiltreTipus().get(0));
	  poblacions.afegirFiltreTipus(PoblacionsRequest.tipus.cat);
    assertEquals(1, poblacions.getFiltreTipus().size());
    assertEquals(PoblacionsRequest.tipus.cat, poblacions.getFiltreTipus().get(0));
	}

	@Test
	public void testSim() {
	  assertEquals(0, poblacions.getSim().size());
	  poblacions.afegirSim(-1);
	  assertEquals(0, poblacions.getSim().size());
	  poblacions.afegirSim(3);
	  assertEquals(0, poblacions.getSim().size());
	  poblacions.afegirSim(0);
	  assertEquals(1, poblacions.getSim().size());
	  assertEquals(new Integer(0), poblacions.getSim().get(0));
	  poblacions.afegirSim(0);
	  assertEquals(1, poblacions.getSim().size());
    assertEquals(new Integer(0), poblacions.getSim().get(0));
    poblacions.afegirSim(1);
    poblacions.afegirSim(2);
    assertEquals(3, poblacions.getSim().size());
    assertEquals(new Integer(0), poblacions.getSim().get(0));
    assertEquals(new Integer(1), poblacions.getSim().get(1));
    assertEquals(new Integer(2), poblacions.getSim().get(2));
    
	}

	@Test
	public void testSelec() {
	  assertFalse(poblacions.getSelec());
	  poblacions.setSelec(true);
		assertTrue(poblacions.getSelec());
		poblacions.setSelec(false);
		assertFalse(poblacions.getSelec());
	}

	@Test
	public void testSetOrderBy() {
		poblacions.setOrderBy(PoblacionsRequest.order.nom);
		assertEquals(PoblacionsRequest.order.nom, poblacions.getOrderBy());
		poblacions.setOrderBy(PoblacionsRequest.order.tipus);
    assertEquals(PoblacionsRequest.order.tipus, poblacions.getOrderBy());
	}

	@Test
	public void testSetPosicio() {
	  assertEquals(0,poblacions.getPosicio());
		poblacions.setPosicio(80);
		assertEquals(80,poblacions.getPosicio());
		poblacions.setPosicio(-80);
		assertEquals(80,poblacions.getPosicio());
	}

	@Test
	public void testGetOperacio() {
		poblacions.setOperacio(PoblacionsRequest.operacio.cerca);
		assertEquals(PoblacionsRequest.operacio.cerca, poblacions.getOperacio());
		poblacions.setOperacio(PoblacionsRequest.operacio.sug);
    assertEquals(PoblacionsRequest.operacio.sug, poblacions.getOperacio());
	}

}
