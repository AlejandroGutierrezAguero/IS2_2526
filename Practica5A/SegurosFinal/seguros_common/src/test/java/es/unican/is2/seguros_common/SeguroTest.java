package es.unican.is2.seguros_common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class SeguroTest {

    @Test
    public void precioTest() throws DatoNoValidoException {
                
        Seguro sut = new Seguro(1, "4444DDD", 90, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(1), null);
        assertEquals(840, sut.precio());
        sut = new Seguro(1, "4444DDD", 100, Cobertura.TERCEROS_LUNAS, LocalDate.now().minusMonths(1), null);
        assertEquals(504, sut.precio());
        sut = new Seguro(1, "4444DDD", 110, Cobertura.TERCEROS, LocalDate.now(), null);
        assertEquals(336, sut.precio());
        sut = new Seguro(1, "4444DDD", 111, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(1).minusDays(1), null);
        assertEquals(1200, sut.precio());
        sut = new Seguro(1, "4444DDD", 120, Cobertura.TERCEROS_LUNAS, LocalDate.now().minusYears(2), null);
        assertEquals(720, sut.precio());
        sut = new Seguro(1, "4444DDD", 1, Cobertura.TERCEROS, LocalDate.now().plusDays(1), null);
        assertEquals(0, sut.precio());
        sut = new Seguro(1, "4444DDD", 89, Cobertura.TERCEROS, LocalDate.now().plusMonths(1), null);
        assertEquals(0, sut.precio());
        sut = new Seguro(1, "4444DDD", 50, Cobertura.TERCEROS, LocalDate.now(), null);
        assertEquals(320, sut.precio());
    }

    @Test
    public void equalsTest() throws DatoNoValidoException {
        Seguro sut = new Seguro(1, "4444DDD", 90, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(1), null);
        Seguro aux = new Seguro(2, "4444DDD", 100, Cobertura.TODO_RIESGO, LocalDate.now().minusYears(1), null);
        assertTrue(sut.equals(aux));
        aux.setMatricula("5555EEE");
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
        Seguro sut = new Seguro(1000, "4444DDD", 1, Cobertura.TERCEROS, LocalDate.of(2026, 4 , 20), "Pepe");
        assertEquals(1000, sut.getId());
        assertEquals("4444DDD", sut.getMatricula());
        assertEquals(1, sut.getPotencia());
        assertEquals(Cobertura.TERCEROS, sut.getCobertura());
        assertEquals(LocalDate.of(2026, 4, 20), sut.getFechaInicio());
        assertEquals("Pepe", sut.getConductorAdicional());
        sut = new Seguro(1000, "4444DDD", 100, Cobertura.TERCEROS, LocalDate.of(2026, 4 , 20), null);
        assertEquals(1000, sut.getId());
        assertEquals("4444DDD", sut.getMatricula());
        assertEquals(100, sut.getPotencia());
        assertEquals(Cobertura.TERCEROS, sut.getCobertura());
        assertEquals(LocalDate.of(2026, 4, 20), sut.getFechaInicio());
        assertEquals(null, sut.getConductorAdicional());
        assertThrows(DatoNoValidoException.class,
            () -> new Seguro(1000, null, 1, Cobertura.TERCEROS, LocalDate.of(2026, 4 , 20), "Pepe"));
        assertThrows(DatoNoValidoException.class,
            () -> new Seguro(1000, "4444DDD", 0, Cobertura.TERCEROS, LocalDate.of(2026, 4 , 20), "Pepe"));
        assertThrows(DatoNoValidoException.class,
            () -> new Seguro(1000, "4444DDD", -5, Cobertura.TERCEROS, LocalDate.of(2026, 4 , 20), "Pepe"));
        assertThrows(DatoNoValidoException.class,
            () -> new Seguro(1000, "4444DDD", 1, null, LocalDate.of(2026, 4 , 20), "Pepe"));
        assertThrows(DatoNoValidoException.class,
            () -> new Seguro(1000, "4444DDD", 1, Cobertura.TERCEROS, null, "Pepe"));
    }
}
