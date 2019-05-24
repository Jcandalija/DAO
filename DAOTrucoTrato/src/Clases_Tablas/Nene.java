package Clases_Tablas;

public class Nene {
	
	private String nombreNene; // PRIMARY KEY
	private Integer edad; 
	private String genero;
	
	public Nene(String nombreNene, Integer edad, String genero) {
		super();
		this.nombreNene = nombreNene;
		this.edad = edad;
		this.genero = genero;
	}

	public Nene(int edad, String genero) {
		super();
		this.edad = edad;
		this.genero = genero;
	}
	
	public Nene() {
		super();
	}

	public String getNombreNene() {
		return nombreNene;
	}

	public void setNombreNene(String nombreNene) {
		this.nombreNene = nombreNene;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Nene [nombreNene=" + nombreNene + ", edad=" + edad + ", genero=" + genero + "]";
	}
	
	
	
}
