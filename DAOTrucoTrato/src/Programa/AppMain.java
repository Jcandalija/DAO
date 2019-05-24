package Programa;

import java.util.List;
import java.util.Scanner;

import Clases_Tablas.Disfraz;
import Clases_Tablas.Nene;
import Clases_Tablas.Pieza;
import Clases_Tablas.Vecino;
import Clases_Tablas.Visita;
import DAO.DAODisfraz;
import DAO.DAONene;
import DAO.DAOPieza;
import DAO.DAOVecino;
import DAO.DAOVisita;

public class AppMain {

	public static void main(String[] args) {

		int opcion = 0;
		int opcion2 = 0;
		int opcion3 = 0;
		Scanner scstring = new Scanner(System.in);
		Scanner scint = new Scanner(System.in);

		do {

			System.out.println("\n========== MANTENIMIENTO BBDD =============\n");
			System.out.println("Elige una opcion");
			System.out.println("1. Ver datos");
			System.out.println("2. Insertar datos");
			System.out.println("3. Modificar datos");
			System.out.println("4. Eliminar datos");
			System.out.println("5. Salir");
			opcion = scint.nextInt();

			switch (opcion) {
			case 1:

				do {
					System.out.println("\n=== VER DATOS ===");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion2 = scint.nextInt();

					switch (opcion2) {
					case 1:
						
						SelectAllNenes();
						break;
					case 2:
						SellectAllVecino();
						break;
					case 3:
						SelectAllVisitas();
						break;
					case 4:
						SelectAllPieza();
						break;
					case 5:
						SelectAllDisfraz();
					}
				} while (opcion2 != 6);
				break;

			case 2:
				do {
					System.out.println("\n===INSERTAR DATOS===");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion3 = scint.nextInt();
					
					switch (opcion3) {
					case 1 :
						System.out.println("Introduce el nombre del nene");
						String nombreNene = scstring.nextLine();
						System.out.println("Introduce la edad");
						Integer edad = scint.nextInt();
						System.out.println("Introduce el genero");
						String genero = scstring.nextLine();
						
						Nene n = new Nene(nombreNene, edad, genero);
						InsertNene(n);
						break;
					
					case 2 :
						System.out.println("Introduce el nombre del vecino");
						String nombreVecino = scstring.nextLine();
						System.out.println("Introduce su piso");
						Integer piso = scint.nextInt();
						System.out.println("Introduce el puerta");
						Integer puerta = scint.nextInt();
						
						Vecino v = new Vecino(nombreVecino, piso, puerta);
						InsertVecino(v);
						break;
						
					case 3 :
						System.out.println("Mostrando registros de la tabla NENE.\n");
						SelectAllNenes();
						System.out.println("\nMostrando registros de la tabla VECINO.\n");
						SellectAllVecino();
						System.out.println("\nDebe insertar un nombre de nene y un nombre de vecino ya existente.\n");
						System.out.println("Introduce el nombre del nen.e");
						String nombreNeneVisita = scstring.nextLine();
						System.out.println("Introduce el nombre del vecino.");
						String nombreVecinoVisita = scstring.nextLine();
						System.out.println("Introduce las chuches.");
						String chuches = scstring.nextLine();
						
						Visita vs = new Visita(nombreNeneVisita, nombreVecinoVisita, chuches);
						InsertVisita(vs);
						break;
					
					case 4 :
						System.out.println("Mostrando registros de la tabla DISFRAZ");
						SelectAllDisfraz();
						
						System.out.println("Debe insertar un codigo de disfraz ya existente.\n");
						System.out.println("Inserte un codigo de disfraz.");
						Integer codigoDisfrazPieza = scint.nextInt();
						System.out.println("Introduzca el codigo de la pieza");
						Integer codigoPieza = scint.nextInt();
						System.out.println("Introduce el nombre.");
						String nombreDisfrazPieza = scstring.nextLine();
						System.out.println("Introduce el color.");
						String colorPieza = scstring.nextLine();
						System.out.println("Introduce la descripcion.");
						String descripcionPieza = scstring.nextLine();
						
						Pieza p = new Pieza(codigoPieza, codigoDisfrazPieza, nombreDisfrazPieza, colorPieza, descripcionPieza);
						InsertPieza(p);
						
						break;
					
					case 5 : 
						System.out.println("Mostrando registros de la tabla NENE.");
						SelectAllNenes();
						System.out.println("\nDebe insertar un nene ya existente.");
						
						System.out.println("Introduce el nombre del nene.");
						String nombreNeneDisfraz = scstring.nextLine();
						System.out.println("Introduce el codigo del disfraz.");
						Integer codigoDisfraz = scint.nextInt();
						System.out.println("Introduce el nombre");
						String nombreDisfraz = scstring.nextLine();
						System.out.println("Introduce la talla");
						String talla = scstring.nextLine();
						System.out.println("Introduce la descripcion");
						String descripcion = scstring.nextLine();
						
						Disfraz d = new Disfraz(nombreNeneDisfraz, codigoDisfraz, nombreDisfraz, talla, descripcion);
						InsertDisfraz(d);
					}
				} while (opcion3 != 6);
				break;
				
			case 3 :
				do {
					System.out.println("\nEscoge la tabla");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion3 = scint.nextInt();
				} while (opcion3 != 6);
				break;
				
			case 4 :
				do {
					System.out.println("\nEscoge la tabla");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion3 = scint.nextInt();
				} while (opcion3 != 6);
				break;
			}

		} while (opcion != 5);

		// SelectAllNenes();
		// UpdateNene();
		// DeleteNene();
		// InsertNene();

		// SelectAllVecino();
		// UpdateVecino();
		// DeleteVecino();
		// InsertVecino();

		// SelectAllVisitas();
		// UpdateVisita();
		// DeleteVisita();
		// InsertVisita();

		// SelectAllDisfraz();
		// InsertDisfraz();
		// UpdateDisfraz();
		// DeleteDisfraz();

		// SelectAllPieza();
		// UpdatePiezas();
		// InsertPieza();
		// DeleteDisfraz();

	}

	private static void SelectAllNenes() {
		DAONene daoNene = new DAONene();
		List<Nene> nenes = daoNene.read();
		if (nenes.size() == 0) {
			System.out.println("No existen registros para la tabla NENE.");
		} else {
			for (Nene n : nenes) {
				System.out.println(n.toString());
			}
		}
		
	}

	private static void UpdateNene() {
		DAONene daoNene = new DAONene();
		Nene n = new Nene("Javier", 26, "Hombre");
		daoNene.update(n);
	}

	private static void DeleteNene() {
		DAONene daoNene = new DAONene();
		Nene n = new Nene();
		n.setNombreNene("Javier");
		daoNene.delete(n);
	}

	private static void InsertNene(Nene n) {
		DAONene daoNene = new DAONene();
		//Nene nene = new Nene(n.getNombreNene(), n.getEdad(), n.getGenero());
		daoNene.insert(n);
	}

	///////////////////////////////////////////

	private static void SellectAllVecino() {
		DAOVecino daoVecino = new DAOVecino();
		List<Vecino> vecinos = daoVecino.read();
		if (vecinos.size() == 0) {
			System.out.println("No existen registros para la tabla VECINO.");
		} else {
			for (Vecino v : vecinos) {
				System.out.println(v.toString());
			}
		}
		
	}

	private static void UpdateVecino() {
		DAOVecino daoVecino = new DAOVecino();
		Vecino v = new Vecino("Javier", 4, 2);
		daoVecino.update(v);
	}

	private static void DeleteVecino() {
		DAOVecino daoVecino = new DAOVecino();
		Vecino v = new Vecino();
		v.setNombreVecino("Javier");
		daoVecino.delete(v);
	}

	private static void InsertVecino(Vecino v) {
		DAOVecino daoVecino = new DAOVecino();
		Vecino n = new Vecino(v.getNombreVecino(), v.getPiso(), v.getPuerta());
		daoVecino.insert(n);
	}

	//////////////////////////////////////////

	private static void SelectAllVisitas() {
		DAOVisita daoVisita = new DAOVisita();
		List<Visita> visita = daoVisita.read();
		if (visita.size() == 0) {
			System.out.println("No existen registros para la tabla VISITAS.");
		} else {
			for (Visita v : visita) {
				System.out.println(v.toString());
			}
		}
		
	}

	private static void UpdateVisita() {
		DAOVisita daoVisita = new DAOVisita();
		Visita v = new Visita("Javier", "Javier", "chocolate");
		daoVisita.update(v);
	}

	private static void DeleteVisita() {
		DAOVisita daoVisita = new DAOVisita();
		Visita v = new Visita();
		v.setNombreNene("Javier");
		v.setNombreVecino("Javier");
		daoVisita.delete(v);
	}

	private static void InsertVisita(Visita v) {
		DAOVisita daoVisita = new DAOVisita();
		Visita n = new Visita(v.getNombreNene(), v.getNombreVecino(), v.getChuches());
		daoVisita.insert(n);
	}

	///////////////////////////////////////////

	private static void SelectAllDisfraz() {
		DAODisfraz daoDisfraz = new DAODisfraz();
		List<Disfraz> disfraz = daoDisfraz.read();
		if (disfraz.size() == 0) {
			System.out.println("No existen registros para la tabla DISFRAZ.");
		} else {
			for (Disfraz d : disfraz) {
				System.out.println(d.toString());
			}
		}
		
	}

	private static void UpdateDisfraz() {
		DAODisfraz daoDisfraz = new DAODisfraz();
		Disfraz d = new Disfraz("Javier", 1111, "sup", "ll", "patatites");
		daoDisfraz.update(d);
	}

	private static void DeleteDisfraz() {
		DAODisfraz daoDisfraz = new DAODisfraz();
		Disfraz d = new Disfraz();
		d.setCodigoDisfraz(1234);
		daoDisfraz.delete(d);
	}

	private static void InsertDisfraz(Disfraz d) {
		DAODisfraz daoDisfraz = new DAODisfraz();
		daoDisfraz.insert(d);

	}

	//////////////////////////////////////////

	private static void SelectAllPieza() {
		DAOPieza daoPieza = new DAOPieza();
		List<Pieza> pieza = daoPieza.read();
		if (pieza.size() == 0) {
			System.out.println("No existen registros para la tabla PIEZA.");
		} else {
			for (Pieza p : pieza) {
				System.out.println(p.toString());
			}
		}
		
	}

	private static void UpdatePiezas() {
		DAOPieza daoPieza = new DAOPieza();
		Pieza p = new Pieza(1111, 1111, "dssd", "rojo", "megushta");
		daoPieza.update(p);
	}

	private static void DeletePieza() {
		DAOPieza daoPieza = new DAOPieza();
		Pieza p = new Pieza();
		p.setCodigoDisfraz(1111);
		daoPieza.delete(p);

	}

	private static void InsertPieza(Pieza p) {
		DAOPieza daoPieza = new DAOPieza();
		daoPieza.insert(p);
	}
}