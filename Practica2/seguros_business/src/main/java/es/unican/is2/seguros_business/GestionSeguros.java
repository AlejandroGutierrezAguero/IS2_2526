package es.unican.is2.seguros_business;

import es.unican.is2.seguros_common.IGestionSeguros;
import es.unican.is2.seguros_common.Cliente;
import es.unican.is2.seguros_common.DataAccessException;
import es.unican.is2.seguros_common.IGestionClientes;
import es.unican.is2.seguros_common.IInfoSeguros;
import es.unican.is2.seguros_common.OperacionNoValida;
import es.unican.is2.seguros_common.Seguro;
import es.unican.is2.seguros_common.IClientesDAO;
import es.unican.is2.seguros_common.ISegurosDAO;

/**
 * Clase de negocio que gestiona los seguros de los clientes
 */
public class GestionSeguros implements IGestionSeguros, IGestionClientes, IInfoSeguros {

    private IClientesDAO clienteDAO;
    private ISegurosDAO seguroDAO;

    public GestionSeguros(IClientesDAO daoClientes, ISegurosDAO daoSeguros) {
        this.clienteDAO = daoClientes;
        this.seguroDAO = daoSeguros;
    }

    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        return null;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        return null;
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        return null;
    }

    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        return null;
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida,DataAccessException {
        return null;
    }

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        return null;
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        return null;
    }
}
