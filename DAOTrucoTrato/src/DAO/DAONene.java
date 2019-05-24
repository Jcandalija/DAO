package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases_Tablas.Nene;

public class DAONene {

	private final String selectSQLall = "SELECT nombreNene, edad, genero FROM NENE";

	public List<Nene> read() {

		List<Nene> nene = new ArrayList<Nene>();
		String finalSqlSelect = selectSQLall + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreNene = rs.getString(1);
				int edad = rs.getInt(2);
				String genero = rs.getString(3);
				
				Nene custom = new Nene(nombreNene, edad, genero);
				nene.add(custom);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return nene;
	}

	public List<Nene> read(Nene n) {
		return null;
	}

	public void update(List<Nene> ns) {
		for (Nene p : ns) {
			update(p);
		}
	}

	public void update(Nene n) {
		String updateTable = "UPDATE nene ";
		String updateSET = crearCondicionesUpdateSet(n);
		String whereSQL = crearCondicionesSQLWhere(n);

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
	
	public void insert(Nene n) {
		String nombreNene = n.getNombreNene();
		Integer edad = n.getEdad();
		String genero = n.getGenero();
		
		Nene custom = new Nene(nombreNene, edad, genero);
		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			conexion.executeUpdate("INSERT INTO Nene (nombreNene, edad, genero) VALUES ('" + nombreNene + "', " + edad + ", '" + genero + "');");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		
	}

	
	public void delete(Nene n) {
		String updateTable = "DELETE FROM nene ";
		String whereSQL = crearCondicionesSQLWhere(n);

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

	private String crearCondicionesUpdateSet(Nene n) {
		ArrayList<String> params = parametrosNOpk(n);
		String result = " SET " + String.join(", ", params);
		return result;
	}
	
	private String crearCondicionesSQLWhere(Nene n) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(n)) {
			params = parametrosPk(n);
		} else {
			params = parametrosNOpk(n);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	private ArrayList<String> parametrosPk(Nene n) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("nombreNene= '" + n.getNombreNene() + "'");
		return params;
	}

	private ArrayList<String> parametrosNOpk(Nene n) {
		ArrayList<String> params = new ArrayList<String>();
		String nombreNene = n.getNombreNene();
		Integer edad = n.getEdad();
		String genero = n.getGenero();

		if (IntegerNotNullAndGreaterZero(edad)) {
			params.add("edad= " + edad);
		}
		
		if (NotNullOrEmpty(nombreNene)) {
			params.add("nombreNene= '" + nombreNene + "'");
		}
		if (NotNullOrEmpty(genero)) {
			params.add("genero= '" + genero + "'");
		}

		return params;
	}
	
	private boolean HasPK(Nene n) {
		if (NotNullOrEmpty(n.getNombreNene())) {
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
