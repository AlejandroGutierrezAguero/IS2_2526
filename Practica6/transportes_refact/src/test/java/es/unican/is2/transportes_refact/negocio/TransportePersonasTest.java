package es.unican.is2.transportes_refact.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransportePersonasTest {

    @Test
    public void testConstructor() {

        // Casos validos
    	TransportePersonas sut = new TransportePersonas(1, 1);
    	assertEquals(1, sut.getHoras());
    	assertEquals(1, sut.getPersonas());
    	
    	// Casos no validos
    	assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(0, 1));
    	assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(-1, 1));
    	assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(1, 0));
    	assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(1, -1));
    	
    }
    
    @Test
    public void testGetExtra() {
    	
    	// Casos validos
    	TransportePersonas sut = new TransportePersonas(1, 1);
    	assertEquals(5.5, sut.getExtra());
    	
    	sut = new TransportePersonas(1, 10);
    	assertEquals(6, sut.getExtra());
    }

}
