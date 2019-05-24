package Clases_Tablas;

public class Disfraz {
	
	private String nombreNene;
	private int codigoDisfraz; // PRIMARY KEY
	private String nombre;
	private String talla;
	private String descripcion;
	
	public Disfraz(String nombreNene, int codigoDisfraz, String nombre, String talla, String descripcion) {
		super();
		this.nombreNene = nombreNene;
		this.codigoDisfraz = codigoDisfraz;
		this.nombre = nombre;
		this.talla = talla;
		this.descripcion = descripcion;
	}

	public Disfraz(String nombreNene, String nombre, String talla, String descripcion) {
		super();
		this.nombreNene = nombreNene;
		this.nombre = nombre;
		this.talla = talla;
		this.descripcion = descripcion;
	}

	public Disfraz() {
		super();
	}

	public String getNombreNene() {
		return nombreNene;
	}

	public void setNombreNene(String nombreNene) {
		this.nombreNene = nombreNene;
	}

	public int getCodigoDisfraz() {
		return codigoDisfraz;
	}

	public void setCodigoDisfraz(int codigoDisfraz) {
		this.codigoDisfraz = codigoDisfraz;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Disfraz [nombreNene=" + nombreNene + ", codigoDisfraz=" + codigoDisfraz + ", nombre=" + nombre
				+ ", talla=" + talla + ", descripcion=" + descripcion + "]";
	}
	
	

}
