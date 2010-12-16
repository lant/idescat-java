package net.loststone.idescat.indicadors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.loststone.idescat.InnerRequest;
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
  public void testGetUrl() {
  }

  @Test
  public void testIndicadors() {
    assertTrue(indicadorsRequest.getIndicadors().isEmpty());
    
    List<Integer> indicadors = new ArrayList<Integer>() {{
      this.add(0);
      this.add(1);
      this.add(2);
      this.add(3);
      this.add(4);
      this.add(5);
      this.add(6);
    }};
    
    indicadorsRequest.setIndicadors(indicadors);
    assertEquals(7, indicadorsRequest.getIndicadors().size());
    
    Integer[] expectedIntArray = {0,1,2,3,4,5,6};
    assertArrayEquals(expectedIntArray, indicadorsRequest.getIndicadors().toArray());
    
    indicadorsRequest.addIndicador(8001);
    expectedIntArray = new Integer[] {0,1,2,3,4,5,6,8001};
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
