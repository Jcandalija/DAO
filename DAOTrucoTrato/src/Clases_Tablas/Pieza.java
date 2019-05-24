package Clases_Tablas;

public class Pieza {
	
	private int codigoPieza; // PRIMARY KEY
	private int codigoDisfraz;
	private String nombre;
	private String color;
	private String descripcion;
	
	public Pieza(int codigoPieza, int codigoDisfraz, String nombre, String color, String descripcion) {
		super();
		this.codigoPieza = codigoPieza;
		this.codigoDisfraz = codigoDisfraz;
		this.nombre = nombre;
		this.color = color;
		this.descripcion = descripcion;
	}

	public Pieza(int codigoDisfraz, String nombre, String color, String descripcion) {
		super();
		this.codigoDisfraz = codigoDisfraz;
		this.nombre = nombre;
		this.color = color;
		this.descripcion = descripcion;
	}

	public Pieza() {
		super();
	}

	public int getCodigoPieza() {
		return codigoPieza;
	}

	public void setCodigoPieza(int codigoPieza) {
		this.codigoPieza = codigoPieza;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Pieza [codigoPieza=" + codigoPieza + ", codigoDisfraz=" + codigoDisfraz + ", nombre=" + nombre
				+ ", color=" + color + ", descripcion=" + descripcion + "]";
	}
	
	

}
