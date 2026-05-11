package es.unican.is2.transportes_refact.negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que se encarga de la gestion de los conductores
 */
public class GestionConductores {

	private ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	
	/**
	 * Busca un conductor por su dni
	 * @param dni dni del conductor a buscar
	 * @return el conductor con el dni dado, o null si no existe
	 */
	public Conductor buscaConductor(String dni) { //WMC+1
		for(Conductor c: conductores) //WMC+1 //CCog+1
			if (c.getDni().equals(dni)) //WMC+1 //CCog+1+1
				return c;
		
		return null;
	}
	
	/**
	 * Anhade un conductor si no existe
	 * @param conductor Conductor a anhadir
	 * @return true si se anhade el conductor, o false si no se anhade porque ya existe un conductor con el dni dado
	 */
	public boolean anhadeConductor(Conductor conductor) { //WMC+1
		if (buscaConductor(conductor.getDni()) != null) //WMC+1 //CCog+1
			return false;
		conductores.add(conductor);
		return true;
	}
	
	public List<Conductor> getConductores() { //WMC+1
		return conductores;
	}
	
	/**
	 * Obtiene el/los mejor(es) conductor(es), es decir, el/los que tiene(n) el sueldo mas alto
	 * @return el/los mejor(es) conductor(es)
	 */
	public List<Conductor> mejorConductor() {
		List<Conductor> resultado = new LinkedList<Conductor>();
		double maxSueldo = 0.0;
		for (Conductor c : conductores) { //WMC+1 //CCog+1+1+1
			if (c.sueldo() > maxSueldo) { //WMC+1 //CCog+1+1+1+1
				maxSueldo = c.sueldo();
				resultado.clear();
				resultado.add(c);
			} else if (c.sueldo() == maxSueldo) { //WMC+1 //CCog+1
				resultado.add(c);
			}
		}
		return resultado;
	}
	
}
