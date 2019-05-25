package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Clases_Tablas.Nene;
import Clases_Tablas.Vecino;

public class DAOVecino {

	private final String selectSQLall = "SELECT nombreVecino, piso, puerta FROM VECINO";
	private final String sqlPK = "SELECT COUNT(*) FROM VECINO WHERE nombreVecino = ";

	public List<Vecino> read() {

		List<Vecino> vecino = new ArrayList<Vecino>();
		String finalSqlSelect = selectSQLall + ";";

		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(finalSqlSelect);
			while (rs.next()) {
				String nombreVecino = rs.getString(1);
				int piso = rs.getInt(2);
				int puerta = rs.getInt(3);

				Vecino custom = new Vecino(nombreVecino, piso, puerta);
				vecino.add(custom);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}
		return vecino;
	}

	public List<Vecino> read(Vecino n) {
		return null;
	}

	public boolean buscarPk(String nombre) {
		ConexionMariaDB conexion = new ConexionMariaDB();
		Integer nombreNene = 0;

		try {
			conexion.connect();
			ResultSet rs = conexion.executeSelect(sqlPK.concat("'" + nombre + "'"));
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

	public void update(List<Vecino> ns) {
		for (Vecino p : ns) {
			update(p);
		}
	}

	public void update(Vecino v) {
		String updateTable = "UPDATE vecino ";
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

	public void insert(Vecino v) {
		String nombreVecino = v.getNombreVecino();
		Integer piso = v.getPiso();
		Integer puerta = v.getPuerta();

		Vecino custom = new Vecino(nombreVecino, piso, puerta);
		ConexionMariaDB conexion = new ConexionMariaDB();
		try {
			conexion.connect();
			conexion.executeUpdate("INSERT INTO Vecino (nombreVecino, piso, puerta) VALUES ('" + nombreVecino + "', "
					+ piso + ", '" + puerta + "');");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			conexion.disconnect();
		}

	}

	public void delete(Vecino v) {
		String updateTable = "DELETE FROM vecino ";
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

	private String crearCondicionesUpdateSet(Vecino v) {
		ArrayList<String> params = parametrosNOpk(v);
		String result = " SET " + String.join(", ", params);
		return result;
	}

	private String crearCondicionesSQLWhere(Vecino v) {
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

	private ArrayList<String> parametrosPk(Vecino v) {
		ArrayList<String> params = new ArrayList<String>();
		params.add("nombreVecino= '" + v.getNombreVecino() + "'");
		return params;
	}

	private ArrayList<String> parametrosNOpk(Vecino v) {
		ArrayList<String> params = new ArrayList<String>();
		Integer piso = v.getPiso();
		Integer puerta = v.getPuerta();

		if (IntegerNotNullAndGreaterZero(piso)) {
			params.add("piso= " + piso);
		}

		if (IntegerNotNullAndGreaterZero(puerta)) {
			params.add("puerta= " + puerta);
		}

		return params;
	}

	private boolean HasPK(Vecino v) {
		if (NotNullOrEmpty(v.getNombreVecino())) {
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
