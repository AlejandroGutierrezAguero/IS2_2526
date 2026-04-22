package es.unican.is2.seguros_common;

import java.time.LocalDate;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
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
				precio = 1000.0;
				break;
			case TERCEROS_LUNAS:
				precio = 600.0;
				break;
			case TERCEROS:
				precio = 400.0;
				break;
		}

		if (potencia >= 90 && potencia <= 110){
			precio = precio * 1.05;
		} else if (potencia > 110){
			precio = precio * 1.20;
		}

		if (LocalDate.now().isBefore(fechaInicio.plusYears(1).plusDays(1))){
			precio = precio * 0.8;
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
