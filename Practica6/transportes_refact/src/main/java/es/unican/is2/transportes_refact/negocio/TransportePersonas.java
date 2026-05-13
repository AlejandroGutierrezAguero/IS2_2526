package es.unican.is2.transportes_refact.negocio;

/* Clase que representa un transporte de personas realizado por un conductor */
public class TransportePersonas extends Transporte {
	
	private static final double EXTRA_NO_COLECTIVO_POR_HORA = 0.5;
	private static final double EXTRA_COLECTIVO_POR_HORA = 1;
	private static final int LIMITE_COLECTIVO = 10;
	private int personas;
	
	/**
	 * Constructor de la clase TransportePersonas
	 * @param horas Horas que ha durado el transporte
	 * @param personas Personas transportadas en el transporte
	 * @throws IllegalArgumentException si algun parametro no es valido
	 */
	public TransportePersonas(double horas, int personas) throws IllegalArgumentException { //WMC+1
		super (horas);
		if (personas <= 0) { //WMC+1 //CCog+1
			throw new IllegalArgumentException();
		}
		this.personas = personas;
	}

	/**
	 * Calcula el importe extra de un determinado transporte de personas
	 * @return importe extra de un determinado transporte de personas
	 */
	public double getExtra() { //WMC+1
		double extraPersonas;
		if (personas < LIMITE_COLECTIVO) { //WMC+1 //CCog+1
			extraPersonas = super.getHoras() * EXTRA_NO_COLECTIVO_POR_HORA;
		} else { //CCog+1
			extraPersonas = super.getHoras() * EXTRA_COLECTIVO_POR_HORA;
		}
		return super.getExtra() +  extraPersonas;
	}
	
	public int getPersonas() { //WMC+1
		return personas;
	}
}