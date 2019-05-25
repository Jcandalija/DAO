package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases_Tablas.Disfraz;
import Clases_Tablas.Nene;
import Clases_Tablas.Vecino;
import Clases_Tablas.Visita;

public class DAODisfraz {

	private final String selectSQLall = "SELECT nombreNene, codigoDisfraz, nombre, talla, descripcion FROM DISFRAZ";
	private final String sqlPK = "SELECT COUNT(*) FROM DISFRAZ WHERE codigoDisfraz = ";

	public List<Disfraz> read() {

		List<Disfraz> disfraz = new ArrayList<Disfraz>();
		String finalSqlSelect = selectSQLall + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreNene = rs.getString(1);
				Integer codigoDisfraz = rs.getInt(2);
				String nombre = rs.getString(3);
				String talla = rs.getString(4);
				String descripcion = rs.getString(5);
				
				Disfraz custom = new Disfraz(nombreNene, codigoDisfraz, nombre, talla, descripcion);
				disfraz.add(custom);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return disfraz;
	}

	public List<Disfraz> read(Disfraz d) {
		return null;
	}

	public boolean buscarPk(Integer numero) {
		ConexionMariaDB conexion = new ConexionMariaDB();
		Integer nombreNene = 0;

		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(sqlPK.concat("" + numero + ""));
			while (rs.next()) {
				nombreNene = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}

		if (nombreNene > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void update(List<Disfraz> ns) {
		for (Disfraz p : ns) {
			update(p);
		}
	}

	public void update(Disfraz d) {
		String updateTable = "UPDATE disfraz ";
		String updateSET = crearCondicionesUpdateSet(d);
		String whereSQL = crearCondicionesSQLWhere(d);

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
	
	public void insert(Disfraz d) {
		String nombreNene = d.getNombreNene();
		Integer codigoDisfraz = d.getCodigoDisfraz();
		String nombre = d.getNombreNene();
		String talla = d.getTalla();
		String descripcion = d.getDescripcion();
			
		
		Disfraz custom = new Disfraz(nombreNene, codigoDisfraz, nombre, talla, descripcion);
		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			conexion.executeUpdate("INSERT INTO Disfraz (nombreNene, codigoDisfraz, nombre, talla, descripcion) VALUES ('" + nombreNene + "', " + codigoDisfraz + ", '" 
			+ nombre + "', '" + talla + "','" + descripcion + "');");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
	}

	
	public void delete(Disfraz d) {
		String updateTable = "DELETE FROM disfraz ";
		String whereSQL = crearCondicionesSQLWhere(d);

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
	
	// SEGUIR EL PROXIMO DIA AQUI!!!!!!
	
	private String crearCondicionesUpdateSet(Disfraz d) {
		ArrayList<String> params = parametrosNOpk(d);
		String result = " SET " + String.join(", ", params);
		return result;
	}
	
	private String crearCondicionesSQLWhere(Disfraz d) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(d)) {
			params = parametrosPk(d);
		} else {
			params = parametrosNOpk(d);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	private ArrayList<String> parametrosPk(Disfraz d) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("codigoDisfraz= " + d.getCodigoDisfraz() + "");
		return params;
	}

	private ArrayList<String> parametrosNOpk(Disfraz d) {
		ArrayList<String> params = new ArrayList<String>();
		String nombreNene = d.getNombreNene();
		String nombre = d.getNombre();
		String talla = d.getTalla();
		String descripcion = d.getDescripcion();
		
		if (NotNullOrEmpty(nombreNene)) {
			params.add("nombreNene= '" + nombreNene + "'");
		}
		
		if (NotNullOrEmpty(nombre)) {
			params.add("nombre= '" + nombre + "'");
		}
		if (NotNullOrEmpty(talla)) {
			params.add("talla= '" + talla + "'");
		}
		if (NotNullOrEmpty(descripcion)) {
			params.add("descripcion= '" + descripcion + "'");
		}

		return params;
	}
	
	private boolean HasPK(Disfraz d) {
		if (IntegerNotNullAndGreaterZero(d.getCodigoDisfraz())) {
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
