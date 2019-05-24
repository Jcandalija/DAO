package Clases_Tablas;

public class Visita {
	
	private String nombreNene; // PRIMARY KEY
	private String nombreVecino; // PRIMARY KEY
	private String chuches;
	
	public Visita(String nombreNene, String nombreVecino, String chuches) {
		super();
		this.nombreNene = nombreNene;
		this.nombreVecino = nombreVecino;
		this.chuches = chuches;
	}

	public Visita(String chuches) {
		super();
		this.chuches = chuches;
	}

	public Visita() {
		super();
	}

	public String getNombreNene() {
		return nombreNene;
	}

	public void setNombreNene(String nombreNene) {
		this.nombreNene = nombreNene;
	}

	public String getNombreVecino() {
		return nombreVecino;
	}

	public void setNombreVecino(String nombreVecino) {
		this.nombreVecino = nombreVecino;
	}

	public String getChuches() {
		return chuches;
	}

	public void setChuches(String chuches) {
		this.chuches = chuches;
	}

	@Override
	public String toString() {
		return "Visita [nombreNene=" + nombreNene + ", nombreVecino=" + nombreVecino + ", chuches=" + chuches + "]";
	}
	

}
