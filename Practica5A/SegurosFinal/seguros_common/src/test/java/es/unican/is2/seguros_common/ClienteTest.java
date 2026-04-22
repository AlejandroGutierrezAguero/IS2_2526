package es.unican.is2.seguros_common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    public void totalSegurosTest() throws DatoNoValidoException {
        Cliente sut = new Cliente("12345678A", "Pepe", true);
        assertEquals(0, sut.totalSeguros());
        Seguro seg1 = new Seguro(1, "4444DDD", 110, Cobertura.TERCEROS, LocalDate.now(), null);
        sut.setMinusvalia(false);
        sut.getSeguros().add(seg1);
        assertEquals(336, sut.totalSeguros());
        Seguro seg2 = new Seguro(1, "4444DDD", 90, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(1), null);
        sut.setMinusvalia(true);
        sut.getSeguros().add(seg2);
        assertEquals(882, sut.totalSeguros());
        Seguro seg3 = new Seguro(1, "4444DDD", 110, Cobertura.TERCEROS, LocalDate.now(), null);
        sut.setMinusvalia(false);
        sut.getSeguros().add(seg3);
        assertEquals(1512, sut.totalSeguros());
    }

    @Test
    public void equalsTest() throws DatoNoValidoException {
        Cliente sut = new Cliente("12345678A", "Pepe", false);
        Cliente aux = new Cliente("12345678A", "Pepe", false);
        assertTrue(sut.equals(aux));
        aux.setDni("87654321B");
        assertFalse(sut.equals(aux));
        aux = null;
        assertFalse(sut.equals(aux));
        aux = sut;
        assertTrue(sut.equals(aux));
        Integer aux2 = 1;
        assertFalse(sut.equals(aux2));
    }

    @Test
    public void constructorTest() throws DatoNoValidoException {
        Cliente sut = new Cliente("12345678A", "Juan", false);
        assertEquals("12345678A", sut.getDni());
        assertEquals("Juan", sut.getNombre());
        assertFalse(sut.getMinusvalia());
        assertThrows(DatoNoValidoException.class, () -> new Cliente(null, "Juan", false));
        assertThrows(DatoNoValidoException.class, () -> new Cliente("12345678A", null, false));
    }
}
