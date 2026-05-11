package es.unican.is2.transportes_refact.negocio;

/* Clase que representa un transporte de mercancias peligrosas realizado por un conductor */
public class TransporteMercanciasPeligrosas extends TransporteMercancias {
	
	private static final int EXTRA_POR_TRANSPORTE_MERCANCIAS_PELIGROSAS = 50;

	/**
	 * Constructor de la clase TransporteMercanciasPeligrosas
	 * @param horas Horas que ha durado el transporte
	 * @param toneladas Toneladas transportadas en el transporte
	 * @throws IllegalArgumentException si algun parametro no es valido
	 */
	public TransporteMercanciasPeligrosas(double horas, int toneladas) throws IllegalArgumentException { //WMC+1
		super (horas, toneladas);
	}

	/**
	 * Calcula el importe extra de un determinado transporte de mercancias peligrosas
	 * @return importe extra de un determinado transporte de mercancias peligrosas
	 */
	public double getExtra() {
		return super.getExtra() +  EXTRA_POR_TRANSPORTE_MERCANCIAS_PELIGROSAS;
	}
}