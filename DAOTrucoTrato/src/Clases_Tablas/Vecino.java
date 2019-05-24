package Clases_Tablas;

public class Vecino {
	
	private String nombreVecino; // PRIMARY KEY
	private int piso;
	private int puerta;
	
	public Vecino(String nombreVecino, int piso, int puerta) {
		super();
		this.nombreVecino = nombreVecino;
		this.piso = piso;
		this.puerta = puerta;
	}

	public Vecino(int piso, int puerta) {
		super();
		this.piso = piso;
		this.puerta = puerta;
	}
	
	public Vecino() {
		super();
	}

	public String getNombreVecino() {
		return nombreVecino;
	}

	public void setNombreVecino(String nombreVecino) {
		this.nombreVecino = nombreVecino;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getPuerta() {
		return puerta;
	}

	public void setPuerta(int puerta) {
		this.puerta = puerta;
	}

	@Override
	public String toString() {
		return "Vecino [nombreVecino=" + nombreVecino + ", piso=" + piso + ", puerta=" + puerta + "]";
	}
	
	

}
