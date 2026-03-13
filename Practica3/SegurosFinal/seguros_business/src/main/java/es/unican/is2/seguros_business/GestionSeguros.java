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
        Cliente c = clienteDAO.cliente(dni);
        if (c == null){
            return null;
        }
        if (seguroDAO.seguroPorMatricula(s.getMatricula()) != null){
            throw new OperacionNoValida("El seguro ya existe");
        }
        c.getSeguros().add(s);
        clienteDAO.actualizaCliente(c);
        seguroDAO.creaSeguro(s);
        return s;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clienteDAO.cliente(dni);
        Seguro s = seguroDAO.seguroPorMatricula(matricula);
        if (c == null || s == null){
            return null;
        }
        if (!c.getSeguros().contains(s)){
            throw new OperacionNoValida("El seguro no pertenece al dni indicado");
        }
        c.getSeguros().remove(s);
        clienteDAO.actualizaCliente(c);
        seguroDAO.eliminaSeguro(s.getId());
        return s;
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = seguroDAO.seguroPorMatricula(matricula);
        if (s == null){
            return null;
        }
        s.setConductorAdicional(conductor);
        seguroDAO.actualizaSeguro(s);
        return s;
    }

    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        return clienteDAO.creaCliente(c);
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida,DataAccessException {
        Cliente c = clienteDAO.cliente(dni);
        if (c == null){
            return null;
        }
        int numSeguros = c.getSeguros().size();
        if (numSeguros > 0){
            throw new OperacionNoValida("El cliente tiene algún seguro a su nombre");
        }
        return clienteDAO.eliminaCliente(dni);
    }

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        return clienteDAO.cliente(dni);
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        return seguroDAO.seguroPorMatricula(matricula);
    }
}
