package net.loststone.idescat.indicadors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.loststone.idescat.InnerRequest;
import net.loststone.idescat.InvalidUrlException;
import net.loststone.idescat.common.Format;
import net.loststone.idescat.indicadors.IndicadorsRequest.operacio;

import org.junit.Before;
import org.junit.Test;

public class IndicadorsRequestTest {

  IndicadorsRequest indicadorsRequest = null;

  @Before
  public void setUp() throws Exception {
    indicadorsRequest = new IndicadorsRequest(mock(InnerRequest.class));
  }

  @Test
  public void testGetDades1() throws InvalidUrlException {
    // Sèries per a Catalunya i Espanya dels indicadors 0801 i 0802, en format XML
    // http://api.idescat.cat/indicadors/v1/dades.xml?p=i/0801,0802
    indicadorsRequest.addIndicador("0801");
    indicadorsRequest.addIndicador("0802");
    indicadorsRequest.setFormat(Format.XML);
    indicadorsRequest.setOperacio(IndicadorsRequest.operacio.dades);
    assertEquals("http://api.idescat.cat/indicadors/v1/dades.xml?lang=cat&enc=UTF-8&i=0801,0802&max=6&min=6&tt=0",
        indicadorsRequest.getUrl());
  }

  @Test
  public void testGetDades2() throws InvalidUrlException {

    // Indicadors dels darrers 14 dies (inclòs avui), 
    // però garantint l'obtenció de 3 indicadors i limitant el resultat a
    // un màxim de 10, en format XML
    // http://api.idescat.cat/indicadors/v1/dades.xml?p=tt/14;max/10;min/3
    indicadorsRequest.setFormat(Format.XML);
    indicadorsRequest.setOperacio(IndicadorsRequest.operacio.dades);
    indicadorsRequest.setMin(3);
    indicadorsRequest.setMax(10);
    indicadorsRequest.setTt(14);
    assertEquals("http://api.idescat.cat/indicadors/v1/dades.xml?lang=cat&enc=UTF-8&max=10&min=3&tt=14", 
        indicadorsRequest.getUrl()); 
  }

  @Test
  public void testGetDades3() throws InvalidUrlException {

    // Darrers 3 indicadors, en format JSON
    // http://api.idescat.cat/indicadors/v1/dades.json?p=tt/0;max/3;min/3
    indicadorsRequest.setOperacio(IndicadorsRequest.operacio.dades);
    indicadorsRequest.setFormat(Format.JSON);
    indicadorsRequest.setTt(0);
    indicadorsRequest.setMax(3);
    indicadorsRequest.setMin(3);
    assertEquals("http://api.idescat.cat/indicadors/v1/dades.json?lang=cat&enc=UTF-8&max=3&min=3&tt=0", 
        indicadorsRequest.getUrl());
  }

  @Test
  public void testGetDades4() throws InvalidUrlException {

    // Indicadors dels darrers 3 dies (inclòs avui) sense importar el nombre màxim ni el mínim, en format PHP
    // serialitzat
    // http://api.idescat.cat/indicadors/v1/dades.php?p=tt/3;max/0;min/0
    indicadorsRequest.setOperacio(IndicadorsRequest.operacio.dades);
    indicadorsRequest.setFormat(Format.PHP);
    indicadorsRequest.setMax(0);
    indicadorsRequest.setMin(0);
    indicadorsRequest.setTt(3);
    assertEquals("http://api.idescat.cat/indicadors/v1/dades.php?lang=cat&enc=UTF-8&max=0&min=0&tt=3", 
        indicadorsRequest.getUrl());

  }

  @Test
  public void testGetDades5() throws InvalidUrlException {

    // Tots els indicadors existents sense importar el temps transcorregut, en format XML
    // http://api.idescat.cat/indicadors/v1/dades.xml?p=tt/0;max/0;min/0
    indicadorsRequest.setOperacio(IndicadorsRequest.operacio.dades);
    indicadorsRequest.setTt(0);
    indicadorsRequest.setMax(0);
    indicadorsRequest.setMin(0);
    assertEquals("http://api.idescat.cat/indicadors/v1/dades.xml?lang=cat&enc=UTF-8&max=0&min=0&tt=0", 
        indicadorsRequest.getUrl());
  }

  @Test
  public void testGetNodes() throws InvalidUrlException {
    // Arbre dels indicadors disponibles de l'apartat de Conjuntura (0), en format XML
    // http://api.idescat.cat/indicadors/v1/nodes.xml?p=i/0
    indicadorsRequest.setOperacio(IndicadorsRequest.operacio.nodes);
    assertEquals("http://api.idescat.cat/indicadors/v1/nodes.xml?lang=cat&enc=UTF-8", indicadorsRequest.getUrl());
  }

  @Test
  public void testIndicadors() {
    assertTrue(indicadorsRequest.getIndicadors().isEmpty());

    List<String> indicadors = new ArrayList<String>() {
      {
        this.add("0");
        this.add("1");
        this.add("2");
        this.add("3");
        this.add("4");
        this.add("5");
        this.add("6");
      }
    };

    indicadorsRequest.setIndicadors(indicadors);
    assertEquals(7, indicadorsRequest.getIndicadors().size());

    String[] expectedIntArray = { "0", "1", "2", "3", "4", "5", "6" };
    assertArrayEquals(expectedIntArray, indicadorsRequest.getIndicadors().toArray());

    indicadorsRequest.addIndicador("8001");
    expectedIntArray = new String[] { "0", "1", "2", "3", "4", "5", "6", "8001" };
    assertArrayEquals(expectedIntArray, indicadorsRequest.getIndicadors().toArray());
  }

  @Test
  public void testSetMax() {
    assertEquals(6, indicadorsRequest.getMax());
    indicadorsRequest.setMax(10);
    assertEquals(10, indicadorsRequest.getMax());
  }

  @Test
  public void testSetMin() {
    assertEquals(6, indicadorsRequest.getMin());
    indicadorsRequest.setMin(10);
    assertEquals(10, indicadorsRequest.getMin());
  }

  @Test
  public void testSetTt() {
    // provem el de defecte.
    assertEquals(0, indicadorsRequest.getTt());

    indicadorsRequest.setTt(10);
    assertEquals(10, indicadorsRequest.getTt());
  }

  @Test
  public void testGetOperacioDades() {
    indicadorsRequest.setOperacio(operacio.dades);
    assertEquals(indicadorsRequest.getOperacio(), IndicadorsRequest.operacio.dades);
  }

  @Test
  public void testGetOperacioNodes() {
    indicadorsRequest.setOperacio(operacio.nodes);
    assertEquals(indicadorsRequest.getOperacio(), IndicadorsRequest.operacio.nodes);
  }

}
