package es.unican.is2.transportes_refact.negocio;

/* Clase que representa un transporte realizado por un conductor */
public abstract class Transporte {
	
	private static final int EXTRA_BASE_POR_HORA = 5;
	private double horas;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 */ 
	public Transporte(double horas) throws IllegalArgumentException { //WMC+1
		if (horas <= 0) { //WMC+1 //CCog+1
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}
	
	/**
	 * Calcula el importe extra de un determinado transporte
	 * @return importe extra de un determinado transporte
	 */
	public double getExtra() { //WMC+1
		return EXTRA_BASE_POR_HORA * horas;
	}
	
	public double getHoras() { //WMC+1
		return horas;
	}
}
