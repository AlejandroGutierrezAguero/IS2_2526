package es.unican.is2.seguros_common;

import java.time.LocalDate;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
	private static final double PRECIO_TODO_RIESGO = 1000.0;
	private static final double PRECIO_TERCEROS_LUNAS = 600.0;
	private static final double PRECIO_TERCEROS = 400.0;
	private static final int LIMITE_INF_POTENCIA_NIVEL1 = 90;
	private static final int LIMITE_SUP_POTENCIA_NIVEL1 = 110;
	private static final double MULTIPLICADOR_POTENCIA_NIVEL1 = 1.05;
	private static final double MULTIPLICADOR_POTENCIA_NIVEL2 = 1.20;
	private static final double MULTIPLICADOR_PRIMER_ANHO = 0.8;

	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;

	/**
	 * Constructor del Seguro con parametros
	 * @param id id del seguro
	 * @param matricula matricula del vehiculo asociado al seguro
	 * @param potencia potencia del vehiculo asociado al seguro
	 * @param cobertura cobertura del seguro
	 * @param fechaInicio fecha de inicio del seguro
	 * @param conductorAdicional conductor adicional asociado al seguro
	 * @throws DatoNoValidoException si algun dato introducido es incorrecto
	 */
	public Seguro (long id, String matricula, int potencia, Cobertura cobertura, LocalDate fechaInicio, String conductorAdicional) throws DatoNoValidoException {
		if (matricula == null || potencia <= 0 || cobertura == null || fechaInicio == null) {
			throw new DatoNoValidoException();
		}
		this.id = id;
		this.matricula = matricula;
		this.potencia = potencia;
		this.cobertura = cobertura;
		this.fechaInicio = fechaInicio;
		this.conductorAdicional = conductorAdicional;
	}

	/**
	 * Constructor del Seguro sin parametros
	 */
	public Seguro() {
		
	}

	/**
	 * Retorna el identificador del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 *  Asigna el valor de la matrícula del seguro
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Retorna la fecha de contratacion del seguro
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del seguro
	 * @param fechaInicio La fecha de inicio del seguro
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Asigna el tipo de cobertura del seguro
	 * @param cobertura El tipo de cobertura del seguro
	 */	
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;		
}

	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * Retorna el conductor adicional del seguro, si lo hay
	 * @return El conductor adicional si lo hay
	 * 		null en caso contrario
	 */
	public String getConductorAdicional() {
		return conductorAdicional;
	}

	/**
	 * Asigna el conductor adicional del seguro
	 * @param conductorAdicional
	 */
	public void setConductorAdicional(String conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}
    
    /**
     * Retorna el precio del seguro. 
	 * El precio se calcula a partir de la cobertura, la potencia del coche y el tiempo que lleva contratado el seguro
	 * @return El precio del seguro
	 *         0 si el seguro todavía no está en vigor (no se ha alcanzado su fecha de inicio)
     */
	public double precio() {
		if (LocalDate.now().isBefore(fechaInicio)){
			return 0.0;
		}
		
		double precio = 0.0;
		switch (cobertura){
			case TODO_RIESGO:
				precio = PRECIO_TODO_RIESGO;
				break;
			case TERCEROS_LUNAS:
				precio = PRECIO_TERCEROS_LUNAS;
				break;
			case TERCEROS:
				precio = PRECIO_TERCEROS;
				break;
		}

		if (potencia >= LIMITE_INF_POTENCIA_NIVEL1 && potencia <= LIMITE_SUP_POTENCIA_NIVEL1){
			precio = precio * MULTIPLICADOR_POTENCIA_NIVEL1;
		} else if (potencia > LIMITE_SUP_POTENCIA_NIVEL1){
			precio = precio * MULTIPLICADOR_POTENCIA_NIVEL2;
		}

		if (LocalDate.now().isBefore(fechaInicio.plusYears(1).plusDays(1))){
			precio = precio * MULTIPLICADOR_PRIMER_ANHO;
		}
		return precio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguro other = (Seguro) obj;
		if (!matricula.equals(other.matricula)) {
			return false;
		}
		return true;
	}
	
}
