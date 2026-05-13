package es.unican.is2.transportes_refact.negocio;

/* Clase que representa un transporte de mercancias realizado por un conductor */
public class TransporteMercancias extends Transporte {
	
	private static final int EXTRA_POR_TONELADA = 2;
	private int toneladas;
	
	/**
	 * Constructor de la clase TransporteMercancias
	 * @param horas Horas que ha durado el transporte
	 * @param toneladas Toneladas transportadas en el transporte
	 * @throws IllegalArgumentException si algun parametro no es valido
	 */
	public TransporteMercancias(double horas, int toneladas) throws IllegalArgumentException { //WMC+1
		super (horas);
		if (toneladas <= 0) { //WMC+1 //CCog+1
			throw new IllegalArgumentException();
		}
		this.toneladas = toneladas;
	}

	/**
	 * Calcula el importe extra de un determinado transporte de mercancias
	 * @return importe extra de un determinado transporte de mercancias
	 */
	public double getExtra() { //WMC+1
		return super.getExtra() +  (toneladas * EXTRA_POR_TONELADA);
	}
	
	public int getToneladas() { //WMC+1
		return toneladas;
	}
}