package es.unican.is2.seguros_gui;

import static org.junit.Assert.assertEquals;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.seguros_business.GestionSeguros;
import es.unican.is2.seguros_common.IClientesDAO;
import es.unican.is2.seguros_common.ISegurosDAO;
import es.unican.is2.seguros_daoh2.ClientesDAO;
import es.unican.is2.seguros_daoh2.H2ServerConnectionManager;
import es.unican.is2.seguros_daoh2.SegurosDAO;

import java.sql.SQLException;
import es.unican.is2.seguros_common.DataAccessException;

public class VistaAgenteIT {

    private FrameFixture demo;

    @BeforeEach
    public void setUp() {
        IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
        demo = new FrameFixture(gui);
		gui.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    public void vistaAgenteIT() throws DataAccessException {
        demo.button("btnBuscar").requireText("Buscar");

        demo.textBox("txtDNICliente").enterText("33333333A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Luis");
        demo.textBox("txtTotalCliente").requireText("0.0");
        demo.list().requireItemCount(0);

        demo.textBox("txtDNICliente").setText("");
        demo.textBox("txtDNICliente").enterText("22222222A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Ana");
        demo.textBox("txtTotalCliente").requireText("600.0");
        demo.list().requireItemCount(1);
        assertEquals("2222AAA TERCEROS_LUNAS", demo.list().valueAt(0));

        demo.textBox("txtDNICliente").setText("");
        demo.textBox("txtDNICliente").enterText("11111111A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Juan");
        demo.textBox("txtTotalCliente").requireText("1820.0");
        demo.list().requireItemCount(3);
        assertEquals("1111AAA TERCEROS", demo.list().valueAt(0));
        assertEquals("1111BBB TODO_RIESGO", demo.list().valueAt(1));
        assertEquals("1111CCC TERCEROS", demo.list().valueAt(2));

        demo.textBox("txtDNICliente").setText("");
        demo.textBox("txtDNICliente").enterText("12345678A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("DNI No Válido");
        demo.textBox("txtTotalCliente").requireEmpty();
        demo.list().requireItemCount(0);

        demo.textBox("txtDNICliente").setText("");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("DNI No Válido");
        demo.textBox("txtTotalCliente").requireEmpty();
        demo.list().requireItemCount(0);

        // Provocamos error de acceso a BBDD
        try {
			H2ServerConnectionManager.getConnection().close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}

        demo.textBox("txtDNICliente").setText("11111111A");
        demo.button("btnBuscar").click();
        demo.textBox("txtNombreCliente").requireText("Error BBDD");
        demo.textBox("txtTotalCliente").requireEmpty();
        demo.list().requireItemCount(0);
    }
}
