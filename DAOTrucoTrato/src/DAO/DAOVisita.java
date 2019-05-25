package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases_Tablas.Nene;
import Clases_Tablas.Vecino;
import Clases_Tablas.Visita;

public class DAOVisita {

	private final String selectSQLall = "SELECT nombreNene, nombreVecino, chuches FROM VISITA";
	private final String sqlPK1 = "SELECT COUNT(*) FROM VECINO WHERE nombreVecino = ";
	private final String sqlPK2 = "SELECT COUNT(*) FROM NENE WHERE nombreNene = ";

	public List<Visita> read() {

		List<Visita> visita = new ArrayList<Visita>();
		String finalSqlSelect = selectSQLall + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreNene = rs.getString(1);
				String nombreVecino = rs.getString(2);
				String chuches = rs.getString(3);

				Visita custom = new Visita(nombreNene, nombreVecino, chuches);
				visita.add(custom);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return visita;
	}

	public List<Visita> read(Visita v) {
		return null;
	}

	public boolean buscarPk1(String nombre) {
		ConexionMariaDB conexion = new ConexionMariaDB();
		Integer nombreNene = 0;

		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(sqlPK1.concat("'" + nombre + "'"));
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
	
	public boolean buscarPk2(String nombre) {
		ConexionMariaDB conexion = new ConexionMariaDB();
		Integer nombreNene = 0;

		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(sqlPK2.concat("'" + nombre + "'"));
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

	public void update(List<Visita> ns) {
		for (Visita p : ns) {
			update(p);
		}
	}

	public void update(Visita v) {
		String updateTable = "UPDATE visita ";
		String updateSET = crearCondicionesUpdateSet(v);
		String whereSQL = crearCondicionesSQLWhere(v);

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

	public void insert(Visita v) {
		String nombreNene = v.getNombreNene();
		String nombreVecino = v.getNombreVecino();
		String chuches = v.getChuches();

		Visita custom = new Visita(nombreNene, nombreVecino, chuches);
		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			conexion.executeUpdate("INSERT INTO Visita (nombreNene, nombreVecino, chuches) VALUES ('" + nombreNene
					+ "', '" + nombreVecino + "', '" + chuches + "');");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}

	}

	public void delete(Visita v) {
		String updateTable = "DELETE FROM visita ";
		String whereSQL = crearCondicionesSQLWhere(v);

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

	private String crearCondicionesUpdateSet(Visita v) {
		ArrayList<String> params = parametrosNOpk(v);
		String result = " SET " + String.join(", ", params);
		return result;
	}

	private String crearCondicionesSQLWhere(Visita v) {
		String result = "";
		ArrayList<String> params;
		if (HasPK(v)) {
			params = parametrosPk(v);
		} else {
			params = parametrosNOpk(v);
		}

		if (params.size() > 0) {
			result = " WHERE " + String.join(" AND ", params);
		}

		return result;
	}

	private ArrayList<String> parametrosPk(Visita v) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("nombreNene= '" + v.getNombreNene() + "'");
		params.add("nombreVecino= '" + v.getNombreVecino() + "'");
		return params;
	}

	private ArrayList<String> parametrosNOpk(Visita v) {
		ArrayList<String> params = new ArrayList<String>();
		String chuches = v.getChuches();

		if (NotNullOrEmpty(chuches)) {
			params.add("chuches= '" + chuches + "'");
		}

		return params;
	}

	private boolean HasPK(Visita v) {
		if (NotNullOrEmpty(v.getNombreVecino()) && NotNullOrEmpty(v.getNombreNene())) {
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
