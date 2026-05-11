package es.unican.is2.transportes_refact.negocio;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado. 
 */
public class Conductor {

	private static final int SUELDO_BASE = 700;
	private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;

	/**
	 * Constructor de la clase Conductor
	 * @param dni dni del conductor
	 * @param nombre nombre del conductor
	 * @param apellido1 primer apellido del conductor
	 * @param apellido2 segundo apellido del conductor
	 * @param direccion direccion del conductor
	 */
	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) { //WMC+1
		if (dni == null || nombre == null || apellido1 == null || direccion == null) { //WMC+1+1+1+1 //CCog+1+1
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
	}

	public String getDni() { //WMC+1
		return dni;
	}

	public String getNombre() { //WMC+1
		return nombre;
	}

	public String getApellido1() { //WMC+1
		return apellido1;
	}

	public String getApellido2() { //WMC+1
		return apellido2;
	}

	public String getDireccion() { //WMC+1
		return direccion;
	}

	/**
	 * Calcula el sueldo del conductor
	 * @return sueldo del conductor
	 */
	public double sueldo() { //WMC+1
		double sueldoTransportes = 0;
		for (Transporte t : transportes) { //WMC+1 //CCog+1
			sueldoTransportes += t.getExtra();
		}
		return SUELDO_BASE + sueldoTransportes;
	}

	/**
	 * Anhade un transporte a un conductor
	 * @param t transporte a anhadir al conductor
	 */
	public void anhadeTransporte(Transporte t) { //WMC+1
		transportes.add(t);
	}

}
