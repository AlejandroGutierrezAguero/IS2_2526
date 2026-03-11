package es.unican.is2.seguros_main;

import es.unican.is2.seguros_common.IClientesDAO;
import es.unican.is2.seguros_common.ISegurosDAO;
import es.unican.is2.seguros_business.GestionSeguros;
import es.unican.is2.seguros_gui.VistaAgente;
import es.unican.is2.seguros_daoh2.SegurosDAO;
import es.unican.is2.seguros_daoh2.ClientesDAO;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);
	}

}
