package es.unican.is2.transportes_refact.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteMercanciasTest {

    @Test
    public void testConstructor() {

        // Casos validos
    	TransporteMercancias sut = new TransporteMercancias(1, 1);
    	assertEquals(1, sut.getHoras());
    	assertEquals(1, sut.getToneladas());
    	
    	// Casos no validos
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(-1, 1));
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(1, 0));
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(1, -1));
    	
    }
    
    @Test
    public void testGetExtra() {
    	
    	// Casos validos
    	TransporteMercancias sut = new TransporteMercancias(1, 1);
    	assertEquals(7, sut.getExtra());
    	
    	sut = new TransporteMercancias(1, 2);
    	assertEquals(9, sut.getExtra());
    }

}
