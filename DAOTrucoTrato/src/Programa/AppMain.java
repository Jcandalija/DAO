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
		int opcion4 = 0;
		int opcion5 = 0;


		Scanner scstring = new Scanner(System.in);
		Scanner scstring2 = new Scanner(System.in);
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
					System.out.println("\n========= VER REGISTROS =========");
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
					System.out.println("\n========= INSERTAR REGISTROS =========");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion3 = scint.nextInt();

					switch (opcion3) {
					case 1:
						System.out.println("Introduce el nombre del nene");
						String nombreNene = scstring.nextLine();
						System.out.println("Introduce la edad");
						Integer edad = scint.nextInt();
						System.out.println("Introduce el genero");
						String genero = scstring.nextLine();

						Nene n = new Nene(nombreNene, edad, genero);

						DAONene daoNene = new DAONene();
						if (daoNene.buscarPk(nombreNene)) {
							System.out.println("\nYa existe un NENE con ese nombre");
						} else {
							InsertNene(n);
							System.out.println("Registro insertado correctamente");
						}

						break;

					case 2:
						System.out.println("Introduce el nombre del vecino");
						String nombreVecino = scstring.nextLine();
						System.out.println("Introduce su piso");
						Integer piso = scint.nextInt();
						System.out.println("Introduce el puerta");
						Integer puerta = scint.nextInt();

						Vecino v = new Vecino(nombreVecino, piso, puerta);

						DAOVecino daoVecino = new DAOVecino();

						if (daoVecino.buscarPk(nombreVecino)) {
							System.out.println("\nYa existe un VECINO con ese nombre.");
						} else {
							InsertVecino(v);
							System.out.println("Registro insertado correctamente");
						}

						break;

					case 3:
						System.out.println("Mostrando registros de la tabla NENE.");
						SelectAllNenes();
						System.out.println("\nMostrando registros de la tabla VECINO.");
						SellectAllVecino();
						System.out.println("\nDebe insertar un nombre de nene y un nombre de vecino ya existente.\n");
						System.out.println("Introduce el nombre del nene.");
						String nombreNeneVisita = scstring.nextLine();
						System.out.println("Introduce el nombre del vecino.");
						String nombreVecinoVisita = scstring.nextLine();
						System.out.println("Introduce las chuches.");
						String chuches = scstring.nextLine();

						Visita vs = new Visita(nombreNeneVisita, nombreVecinoVisita, chuches);
						DAOVisita daoVisita = new DAOVisita();

						if (daoVisita.buscarPk1(nombreVecinoVisita) && daoVisita.buscarPk2(nombreNeneVisita)) {
							InsertVisita(vs);
							System.out.println("Registro insertado correctamente");
						} else {
							System.out.println("\nAlguna de las claves primarias no existe, comprueba los registros");
						}

						break;

					case 4:
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

						Pieza p = new Pieza(codigoPieza, codigoDisfrazPieza, nombreDisfrazPieza, colorPieza,
								descripcionPieza);
						DAODisfraz daoDisfraz = new DAODisfraz();

						if (daoDisfraz.buscarPk(codigoDisfrazPieza)) {
							InsertPieza(p);
							System.out.println("Registro insertado correctamente");
						} else {
							System.out.println(
									"\nNo existe ningun registro asociado a codigoDisfraz en la tabla DISFRAZ. Revise los datos.");
						}

						break;

					case 5:
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

						DAONene daoNene2 = new DAONene();
						if (daoNene2.buscarPk(nombreNeneDisfraz)) {
							InsertDisfraz(d);
							System.out.println("Registro insertado correctamente");
						} else {
							System.out.println(
									"\nNo existe ningun registro asociado a nombreNene en la tabla NENE. Revise los datos.");
						}

					}
				} while (opcion3 != 6);
				break;

			case 3:
				do {
					System.out.println("\n========= ACTUALIZAR REGISTROS =========");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion4 = scint.nextInt();

					switch (opcion4) {

					case 1:
						System.out.println("\nMostrando registros de la tabla NENE");
						SelectAllNenes();
						System.out.println("\nIntroduce el nombre del NENE a actualizar");
						String nombreNene = scstring.nextLine();

						DAONene daoNene = new DAONene();
						if (daoNene.buscarPk(nombreNene)) {
							System.out.println("\nIntroduce la edad");
							Integer edadAc = scstring.nextInt();
							String nombreNeneAc = "test";
							System.out.println("\nIntroduce el genero");
							String generoAc = scstring2.nextLine();

							Nene n = new Nene(nombreNeneAc, edadAc, generoAc);
							UpdateNene(n);
							System.out.println("\nRegistro actualizado correctamente.");

						} else {
							System.out.println("\nNo existe el nene " + nombreNene + " en el registro.");
						}
						break;
						
					case 2 :
						System.out.println("\nMostrando registros de la tabla VECINO.");
						SellectAllVecino();
						System.out.println("\nIntroduce el nombre del VECINO a actualizar.");
						String nombreVecino = scstring.nextLine();
						
						DAOVecino daoVecino = new DAOVecino();
						if(daoVecino.buscarPk(nombreVecino)) {
							System.out.println("\nIntroduce el piso");
							Integer pisoAc = scint.nextInt();
							System.out.println("\nIntroduce la puerta");
							Integer puertaAc = scint.nextInt();
							
							Vecino v = new Vecino(nombreVecino, pisoAc, puertaAc);
							UpdateVecino(v);
							System.out.println("\nRegistro actualizado correctamente.");
						} else {
							System.out.println("\nNo existe el vecino " + nombreVecino + " en el registro.");
						}
						
					case 3 :
						System.out.println("\nMostrando registros de la tabla VISITAS.");
						SelectAllVisitas();
						System.out.println("\nIntroduce el nombre del NENE.");
						String nombreNeneV = scstring.nextLine();
						System.out.println("\nIntroduce el nombre del VECINO.");
						String nombreVecinoV = scstring.nextLine();
						
						DAOVisita daoVisita = new DAOVisita();
						if (daoVisita.buscarPk1(nombreVecinoV) && daoVisita.buscarPk2(nombreNeneV)) {
							System.out.println("\nIntroduce las chuches.");
							String chuches = scstring.nextLine();
							Visita v = new Visita(nombreNeneV, nombreVecinoV, chuches);
							UpdateVisita(v);
							System.out.println("\nRegistro actualizado correctamente.");
						} else {
							System.out.println("Alguna de las claves primarias no existe. Revise los datos.");
						}
					case 4 :
						System.out.println("\nMostrando registros de la tabla PIEZA.");
						SelectAllPieza();
						System.out.println("Introduce el codigo de la pieza");
						Integer codigoPieza = scstring.nextInt();
						
						DAOPieza daoPieza = new DAOPieza();
						if (daoPieza.buscarPk(codigoPieza)) {
							System.out.println("Introduce el codigo del disfraz.");
							Integer codigoDisfraz = scint.nextInt();
							DAODisfraz daoDisfraz = new DAODisfraz();
							if(!daoDisfraz.buscarPk(codigoDisfraz)) {
								System.out.println("\nNo existe el disfraz con codigo " + codigoDisfraz + ". Revise los datos.");
							} else {
								System.out.println("Introduce el nombre");
								String nombre = scstring.nextLine();
								System.out.println("Introduce el color");
								String color = scstring.nextLine();
								System.out.println("Introduce la descripcion");
								String descripcion = scstring.nextLine();
								
								Pieza p = new Pieza(codigoPieza, codigoDisfraz, nombre, color, descripcion);
								UpdatePiezas(p);
								System.out.println("\nRegistro actualizado correctamente");
							}
						} else {
							System.out.println("No existe la pieza con codigo " + codigoPieza + " en el registro.");
						}
						break;
						
					case 5 :
						System.out.println("\nMostrando registros de la tabla DISFRAZ.");
						SelectAllDisfraz();
						System.out.println("\nIntroduce el codigo del DISFRAZ");
						Integer codigoDisfraz = scstring.nextInt();
						
						DAODisfraz daoDisfraz = new DAODisfraz();
						if (daoDisfraz.buscarPk(codigoDisfraz)) {
							System.out.println("Introduce el nombre del nene.");
							String nombreNene2 = scstring2.nextLine();
							DAONene daoNene2 = new DAONene();
							if(!daoNene2.buscarPk(nombreNene2)) {
								System.out.println("\nNo existe el nene " + nombreNene2 + ". Revise los datos.");
							} else {
								System.out.println("Introduce el nombre");
								String nombre = scstring2.nextLine();
								System.out.println("Introduce el talla");
								String talla = scstring2.nextLine();
								System.out.println("Introduce la descripcion");
								String descripcion = scstring2.nextLine();
								
								Disfraz d = new Disfraz(nombreNene2, codigoDisfraz, nombre, talla, descripcion);
								UpdateDisfraz(d);
								System.out.println("\nRegistro actualizado correctamente");
							}
						} else {
							System.out.println("No existe la pieza con codigo " + codigoDisfraz + " en el registro.");
						}
						break;
						
					}
				} while (opcion4 != 6);

				break;

			case 4:
				do {
					System.out.println("\n========= BORRAR REGISTROS =========");
					System.out.println("1. Nene");
					System.out.println("2. Vecino");
					System.out.println("3. Visita");
					System.out.println("4. Pieza");
					System.out.println("5. Disfraz");
					System.out.println("6. Salir");
					opcion5 = scint.nextInt();

					switch (opcion5) {

					case 1:
						System.out.println("\nMostrando registros de la tabla NENE.");
						SelectAllNenes();
						System.out.println("\nIntroduce el nombre del NENE a borrar.");
						String neneBorrar = scstring.nextLine();

						DAONene daoNene = new DAONene();
						if (daoNene.buscarPk(neneBorrar)) {
							Nene n = new Nene();
							n.setNombreNene(neneBorrar);
							DeleteNene(n);
							System.out.println(neneBorrar + " borrado del registro.");
						} else {
							System.out.println("No existe " + neneBorrar + " en la tabla NENE.");
						}
						break;

					case 2:
						System.out.println("\nMostrando registros de la tabla VECINO. ");
						SellectAllVecino();
						System.out.println("\nIntroduce el nombre del VECINO a borrar.");
						String vecinoBorrar = scstring.nextLine();

						DAOVecino daoVecino = new DAOVecino();
						if (daoVecino.buscarPk(vecinoBorrar)) {
							Vecino v = new Vecino();
							v.setNombreVecino(vecinoBorrar);
							DeleteVecino(v);
							System.out.println(vecinoBorrar + " borrado del registro");
						} else {
							System.out.println("No existe " + vecinoBorrar + " en la tabla VECINO");
						}
						break;

					case 3:
						System.out.println("\nMostrando registros de la tabla NENE.");
						SelectAllNenes();
						System.out.println("\nMostrando registros de la tabla VECINO.");
						SellectAllVecino();
						System.out.println("Introduce el nombre del NENE a borrar");
						String neneBorrarVisita = scstring.nextLine();
						System.out.println("Introduce el nombre del VECINO a borrar");
						String vecinoBorrarVisita = scstring.nextLine();

						DAOVisita daoVisitia = new DAOVisita();
						if (daoVisitia.buscarPk1(vecinoBorrarVisita) && daoVisitia.buscarPk2(neneBorrarVisita)) {
							Visita v = new Visita();
							v.setNombreVecino(vecinoBorrarVisita);
							v.setNombreNene(neneBorrarVisita);
							DeleteVisita(v);
							System.out.println("VISITA entre " + neneBorrarVisita + " y " + vecinoBorrarVisita
									+ " borrada del registro");
						} else {
							System.out.println("Alguno de las nombres no existe en el registro. Comprueba los datos");
						}
						break;

					case 4:
						System.out.println("\nMostrando registros de la tabla PIEZA.");
						SelectAllPieza();
						System.out.println("\nIntroduce el codigo de la pieza a borrar");
						Integer piezaBorrar = scstring.nextInt();

						DAOPieza daoPieza = new DAOPieza();
						if (daoPieza.buscarPk(piezaBorrar)) {
							Pieza p = new Pieza();
							p.setCodigoPieza(piezaBorrar);
							DeletePieza(p);
							System.out.println("\nPIEZA " + piezaBorrar + " borrada del regsitro.");
						} else {
							System.out.println("\nNo existe la pieza " + piezaBorrar + " en el registro.");
						}
						break;

					case 5:
						System.out.println("\nMostrando registros de la tabla DISFRAZ");
						SelectAllDisfraz();
						System.out.println("\nIntroduce el codigo del disfraz a borrar");
						Integer disfrazBorrar = scint.nextInt();

						DAODisfraz daoDisfraz = new DAODisfraz();
						if (daoDisfraz.buscarPk(disfrazBorrar)) {
							Disfraz d = new Disfraz();
							d.setCodigoDisfraz(disfrazBorrar);
							DeleteDisfraz(d);
							System.out.println("\nDisfraz " + disfrazBorrar + " borrado del registro.");
						} else {
							System.out.println("\nNo existe el disfraz " + disfrazBorrar + " en el registro.");
						}
						break;

					}

				} while (opcion5 != 6);
				break;
			}

		} while (opcion != 5);

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

	private static void UpdateNene(Nene n) {
		DAONene daoNene = new DAONene();
		daoNene.update(n);
	}

	private static void DeleteNene(Nene n) {
		DAONene daoNene = new DAONene();
		daoNene.delete(n);
	}

	private static void InsertNene(Nene n) {
		DAONene daoNene = new DAONene();
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

	private static void UpdateVecino(Vecino v) {
		DAOVecino daoVecino = new DAOVecino();
		daoVecino.update(v);
	}

	private static void DeleteVecino(Vecino v) {
		DAOVecino daoVecino = new DAOVecino();
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

	private static void UpdateVisita(Visita v) {
		DAOVisita daoVisita = new DAOVisita();
		daoVisita.update(v);
	}

	private static void DeleteVisita(Visita v) {
		DAOVisita daoVisita = new DAOVisita();
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

	private static void UpdateDisfraz(Disfraz d) {
		DAODisfraz daoDisfraz = new DAODisfraz();
		daoDisfraz.update(d);
	}

	private static void DeleteDisfraz(Disfraz d) {
		DAODisfraz daoDisfraz = new DAODisfraz();
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

	private static void UpdatePiezas(Pieza p) {
		DAOPieza daoPieza = new DAOPieza();
		daoPieza.update(p);
	}

	private static void DeletePieza(Pieza p) {
		DAOPieza daoPieza = new DAOPieza();
		daoPieza.delete(p);

	}

	private static void InsertPieza(Pieza p) {
		DAOPieza daoPieza = new DAOPieza();
		daoPieza.insert(p);
	}
}