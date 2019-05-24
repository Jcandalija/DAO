package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases_Tablas.Nene;
import Clases_Tablas.Pieza;

public class DAOPieza {

	private final String selectSQLall = "SELECT codigoPieza, codigoDisfraz, nombre, color, descripcion FROM PIEZA";

	public List<Pieza> read() {

		List<Pieza> pieza = new ArrayList<Pieza>();
		String finalSqlSelect = selectSQLall + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				Integer codigoPieza = rs.getInt(1);
				Integer codigoDisfraz = rs.getInt(2);
				String nombre = rs.getString(3);
				String color = rs.getString(4);
				String descripcion = rs.getString(4);
				
				Pieza custom = new Pieza(codigoPieza, codigoDisfraz, nombre, color, descripcion);
				pieza.add(custom);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return pieza;
	}

	public List<Pieza> read(Pieza p) {
		return null;
	}

	public void update(List<Pieza> ps) {
		for (Pieza p : ps) {
			update(p);
		}
	}

	public void update(Pieza p) {
		String updateTable = "UPDATE nene ";
		String updateSET = crearCondicionesUpdateSet(p);
		String whereSQL = crearCondicionesSQLWhere(p);

		String finalSqlUpdate = updateTable + updateSET + whereSQL + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeUpdate(finalSqlUpdate);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
	}
	
	public void insert(Pieza p) {
		Integer codigoPieza = p.getCodigoPieza();
		Integer codigoDisfraz = p.getCodigoDisfraz();
		String nombre = p.getNombre();
		String color = p.getColor();
		String descripcion = p.getDescripcion();
		
		Pieza custom = new Pieza(codigoPieza, codigoDisfraz, nombre, color, descripcion);
		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			conexion.executeUpdate("INSERT INTO Pieza (codigoPieza, codigoDisfraz, nombre, color, descripcion) VALUES (" + codigoPieza + ", " + codigoDisfraz + ", '" + nombre + "','" + color + "','" + descripcion + "' );");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
	}

	
	public void delete(Pieza p) {
		String updateTable = "DELETE FROM pieza ";
		String whereSQL = crearCondicionesSQLWhere(p);

		String finalSqlUpdate = updateTable + whereSQL + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeUpdate(finalSqlUpdate);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
	}

	private String crearCondicionesUpdateSet(Pieza p) {
		ArrayList<String> params = parametrosNOpk(p);
		String result = " SET " + String.join(", ", params);
		return result;
	}
	
	private String crearCondicionesSQLWhere(Pieza p) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(p)) {
			params = parametrosPk(p);
		} else {
			params = parametrosNOpk(p);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	private ArrayList<String> parametrosPk(Pieza p) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("codigoPieza= '" + p.getCodigoPieza() + "'");
		return params;
	}

	private ArrayList<String> parametrosNOpk(Pieza p) {
		ArrayList<String> params = new ArrayList<String>();
		Integer codigoDisfraz = p.getCodigoDisfraz();
		String nombre = p.getNombre();
		String color = p.getColor();
		String descripcion = p.getDescripcion();

		if (IntegerNotNullAndGreaterZero(codigoDisfraz)) {
			params.add("codigoDisfraz= " + codigoDisfraz);
		}
		
		if (NotNullOrEmpty(nombre)) {
			params.add("nombre= '" + nombre + "'");
		}
		if (NotNullOrEmpty(color)) {
			params.add("color= '" + color + "'");
		}
		if (NotNullOrEmpty(descripcion)) {
			params.add("color= '" + descripcion + "'");
		}

		return params;
	}
	
	private boolean HasPK(Pieza p) {
		if (IntegerNotNullAndGreaterZero(p.getCodigoDisfraz())) {
			return true;
		}
		return false;
	}
	
	private boolean IntegerNotNullAndGreaterZero(Integer i) {
		if (i == null || i <= 0) {
			return false;
		}
		return true;

	}
	
	private boolean NotNullOrEmpty(String str) {
		if (str != null && !str.trim().isEmpty()) {
			return true;
		}
		return false;
	}
	
	

}
