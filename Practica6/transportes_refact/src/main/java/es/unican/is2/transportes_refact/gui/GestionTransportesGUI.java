package es.unican.is2.transportes_refact.gui;

import java.util.List;

import es.unican.is2.transportes_refact.negocio.Conductor;
import es.unican.is2.transportes_refact.negocio.Transporte;
import es.unican.is2.transportes_refact.negocio.TransporteMercancias;
import es.unican.is2.transportes_refact.negocio.TransporteMercanciasPeligrosas;
import es.unican.is2.transportes_refact.negocio.TransportePersonas;
import es.unican.is2.transportes_refact.negocio.GestionConductores;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes
 */
public class GestionTransportesGUI {

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) { //WMC+1
		// opciones del menu
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1, 
		SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		// crea la empresa de transportes
		GestionConductores gestionConductores = new GestionConductores();
		
		// crea la ventana de menu
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
		
		int opcion;

		// lazo de espera de comandos del usuario
		while(true) { //WMC+1 //CCog+1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { //CCog+1+1
			case  ANHADE_CONDUCTOR: //WMC+1
				anhadeConductor(gestionConductores);
				break;

			case ANHADE_TRANSPORTE: //WMC+1
				anhadeTransporte(gestionConductores);
				break;
				
			case SUELDO_CONDUCTOR: //WMC+1
				sueldoConductor(gestionConductores);
 				break;

			case MEJOR_CONDUCTOR: //WMC+1
				mejorConductor(gestionConductores);
				break;
			}
		}
	}

	/**
	 * Muestra el/los mejor(es) conductores, es decir el/los que tiene(n) el sueldo mas alto
	 * @param gestionConductores Gestor de los conductores de la empresa
	 */
	private static void mejorConductor(GestionConductores gestionConductores) {
		List<Conductor> resultado = gestionConductores.mejorConductor();	
		String msj = "";
		if (resultado.size() == 0) { //WMC+1 //CCog+1+1+1
			msj = "No hay conductores";
		} else { //CCog+1
			for (Conductor conductor : resultado) { //WMC+1 //CCog+1+1+1+1
				msj += conductor.getNombre() + " "+conductor.sueldo()+"\n";
			}
		}
		mensaje("MEJOR CONDUCTOR", msj);
	}

	/**
	 * Muestra el sueldo de un conductor con dni dado
	 * @param gestionConductores Gestor de los conductores de la empresa
	 */
	private static void sueldoConductor(GestionConductores gestionConductores) {
		Lectura lectura = new Lectura("Transportes Peligrosos");
		lectura.creaEntrada("DNI", "");
		lectura.esperaYCierra();
		String dni = lectura.leeString("DNI");
		Conductor conductor = gestionConductores.buscaConductor(dni);
		if (conductor!=null){ //WMC+1 //CCog+1+1+1
			mensaje("Sueldo", "El sueldo del conductor es: "+conductor.sueldo());
		} else { //CCog+1
			mensaje("ERROR", "No existe un conductor con DNI "+dni);
		}
	}

	/**
	 * Anhade un transporte a un conductor con dni dado
	 * @param gestionConductores Gestor de los conductores de la empresa
	 */
	private static void anhadeTransporte(GestionConductores gestionConductores) {
		Lectura lectura = new Lectura("Nuevo transporte");
		lectura.creaEntrada("DNI", "");
		lectura.creaEntrada("Tipo Transporte: P | M | MP", "");
		lectura.creaEntrada("Horas", 0);
		lectura.creaEntrada("Personas", 0);
		lectura.creaEntrada("Toneladas", 0);
		lectura.esperaYCierra();
		String dni = lectura.leeString("DNI");
		String tipo = lectura.leeString("Tipo Transporte: P | M | MP");
		int horas = lectura.leeInt("Horas");
		int personas = lectura.leeInt("Personas");
		int toneladas = lectura.leeInt("Toneladas");

		Transporte transporte = null;
		Conductor conductor = gestionConductores.buscaConductor(dni);
		if (conductor!=null) { //WMC+1 //CCog+1+1+1
			switch (tipo) { //CCog+1+1+1+1
				case "P": //WMC+1
					transporte = new TransportePersonas(horas, personas);
					break;
				case "M": //WMC+1
					transporte = new TransporteMercancias(horas, toneladas);
					break;
				case "MP": //WMC+1
					transporte = new TransporteMercanciasPeligrosas(horas, toneladas);
					break;	
			}
			conductor.anhadeTransporte(transporte);
		} else { //CCog+1
			mensaje("ERROR", "No existe un conductor con DNI "+dni);
		}
	}

	/**
	 * Anhade un nuevo conductor a la empresa
	 * @param gestionConductores Gestor de los conductores de la empresa
	 */
	private static void anhadeConductor(GestionConductores gestionConductores) {
		Lectura lectura = new Lectura("Datos Conductor");
		lectura.creaEntrada("DNI", "");
		lectura.creaEntrada("Nombre","");
		lectura.creaEntrada("Apellido1", "");
		lectura.creaEntrada("Apellido2", "");
		lectura.creaEntrada("Direccion", "");
		lectura.esperaYCierra();
		String dni = lectura.leeString("DNI");
		String nombre = lectura.leeString("Nombre");
		String apellido1 = lectura.leeString("Apellido1");
		String apellido2 = lectura.leeString("Apellido2");
		String direccion = lectura.leeString("Direccion");
		// Anhade el conductor
		if (!gestionConductores.anhadeConductor(new Conductor (dni, nombre, apellido1, apellido2, direccion))) //WMC+1 //CCog+1+1+1
			mensaje("ERROR", "Ya existe un conductor con DNI "+dni);
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { //WMC+1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
