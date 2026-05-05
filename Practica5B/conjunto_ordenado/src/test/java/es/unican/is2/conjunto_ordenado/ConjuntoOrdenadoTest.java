package es.unican.is2.conjunto_ordenado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba de la clase ConjuntoOrdenado
 */
public class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> sut;

    @BeforeEach
    public void setUp() {
        sut = new ConjuntoOrdenado<Integer>();
    }
    
    @Test
    public void addTest() {

        assertThrows(NullPointerException.class, () -> sut.add(null));
        assertEquals(0, sut.size());

        assertTrue(sut.add(-1));
        assertEquals(-1, sut.get(0));
        assertEquals(1, sut.size());

        assertTrue(sut.add(2));
        assertEquals(2, sut.get(1));
        assertEquals(2, sut.size());

        assertTrue(sut.add(-2));
        assertEquals(-2, sut.get(0));
        assertEquals(3, sut.size());

        assertTrue(sut.add(1));
        assertEquals(1, sut.get(2));
        assertEquals(4, sut.size());

        assertFalse(sut.add(1));
        assertEquals(4, sut.size());

        assertThrows(NullPointerException.class, () -> sut.add(null));
        assertEquals(4, sut.size());
    }

    @Test
    public void getTest() {

        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0));

        sut.add(1);
        assertEquals(1, sut.get(0));
        assertEquals(1, sut.size()); // Para comprobar que por error el get() no elimina elementos.

        sut.add(2);
        sut.add(3);
        assertEquals(1, sut.get(0));
        assertEquals(2, sut.get(1));
        assertEquals(3, sut.get(2));
        assertEquals(3, sut.size()); // Para comprobar que por error el get() no elimina elementos.

        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-5));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(5));
        assertEquals(3, sut.size()); // Para comprobar que por error el get() no elimina elementos en caso de indice erroneo.
    }

    @Test
    public void removeTest() {

        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.add(4);
        sut.add(5);

        assertEquals(1, sut.remove(0));
        assertEquals(4, sut.size());
        assertEquals(2, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(4, sut.get(2));
        assertEquals(5, sut.get(3));

        assertEquals(4, sut.remove(2));
        assertEquals(3, sut.size());
        assertEquals(2, sut.get(0));
        assertEquals(3, sut.get(1));
        assertEquals(5, sut.get(2));

        assertEquals(5, sut.remove(2));
        assertEquals(2, sut.size());
        assertEquals(2, sut.get(0));
        assertEquals(3, sut.get(1));

        sut.remove(1);
        assertEquals(2, sut.remove(0));
        assertEquals(0, sut.size());

        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(0));
        sut.add(1);
        sut.add(2);
        sut.add(3);
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-5));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(3));
        assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(5));
        assertEquals(3, sut.size()); // Para comprobar que si el índice es incorrecto remove() no elimina nada.
    }

    @Test
    public void sizeTest() {

        assertEquals(0, sut.size());

        sut.add(1);
        assertEquals(1, sut.size());

        sut.add(2);
        assertEquals(2, sut.size());

        sut.add(3);
        sut.add(4);
        sut.add(5);
        assertEquals(5, sut.size());
    }

    @Test
    public void clearTest() {
        
        sut.clear();
        assertEquals(0, sut.size());

        sut.add(1);
        sut.clear();
        assertEquals(0, sut.size());

        sut.add(1);
        sut.add(2);
        sut.clear();
        assertEquals(0, sut.size());

        sut.add(1);
        sut.add(2);
        sut.add(3);
        sut.add(4);
        sut.add(5);
        sut.clear();
        assertEquals(0, sut.size());
    }
}
