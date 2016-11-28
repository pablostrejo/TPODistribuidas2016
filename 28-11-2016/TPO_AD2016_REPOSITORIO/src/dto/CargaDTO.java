package dto;

import java.io.Serializable;

public class CargaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idCarga;
	private float peso;
	private float ancho;
	private float alto;
	private float profundidad;
	private float volumen;
	private String fragilidad;
	private String tratamiento;
	private int apilable;
	private boolean refrigerable;
	private String condiciones;
	private boolean despachado;
	private String tipoMercaderia;
	
	public CargaDTO() {
		
	}

	public int getIdCarga() {
		return idCarga;
	}

	public void setIdCarga(int idCarga) {
		this.idCarga = idCarga;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAncho() {
		return ancho;
	}

	public void setAncho(float ancho) {
		this.ancho = ancho;
	}

	public float getAlto() {
		return alto;
	}

	public void setAlto(float alto) {
		this.alto = alto;
	}

	public float getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(float profundidad) {
		this.profundidad = profundidad;
	}

	public float getVolumen() {
		return volumen;
	}

	public void setVolumen(float volumen) {
		this.volumen = volumen;
	}

	public String getFragilidad() {
		return fragilidad;
	}

	public void setFragilidad(String fragilidad) {
		this.fragilidad = fragilidad;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public int getApilable() {
		return apilable;
	}

	public void setApilable(int apilable) {
		this.apilable = apilable;
	}

	public boolean isRefrigerable() {
		return refrigerable;
	}

	public void setRefrigerable(boolean refrigerable) {
		this.refrigerable = refrigerable;
	}

	public String getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(String condiciones) {
		this.condiciones = condiciones;
	}

	public boolean isDespachado() {
		return despachado;
	}

	public void setDespachado(boolean despachado) {
		this.despachado = despachado;
	}

	public String getTipoMercaderia() {
		return tipoMercaderia;
	}

	public void setMercaderia(String tipoMercaderia) {
		this.tipoMercaderia = tipoMercaderia;
	}
	
	public CargaDTO(int idCarga,float peso, float ancho, float alto, float profundidad,
			float volumen, String fragilidad, String tratamiento, int apilable,
			boolean refrigerable, String condiciones, boolean despachado,
			String tipoMercaderia) {
		super();
		this.idCarga=idCarga;
		this.peso = peso;
		this.ancho = ancho;
		this.alto = alto;
		this.profundidad = profundidad;
		this.volumen = volumen;
		this.fragilidad = fragilidad;
		this.tratamiento = tratamiento;
		this.apilable = apilable;
		this.refrigerable = refrigerable;
		this.condiciones = condiciones;
		this.despachado = despachado;
		this.tipoMercaderia = tipoMercaderia;
	}
}