package es.unican.is2.transportes_refact.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteMercanciasPeligrosasTest {

    @Test
    public void testConstructor() {

        // Casos validos
    	TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(1, 1);
    	assertEquals(1, sut.getHoras());
    	assertEquals(1, sut.getToneladas());
    	
    	// Casos no validos
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(0, 1));
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(-1, 1));
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(1, 0));
    	assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(1, -1));
    	
    }
    
    @Test
    public void testGetExtra() {
    	
    	// Casos validos
    	TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(1, 1);
    	assertEquals(57, sut.getExtra());
    	
    	sut = new TransporteMercanciasPeligrosas(1, 2);
    	assertEquals(59, sut.getExtra());
    }

}
