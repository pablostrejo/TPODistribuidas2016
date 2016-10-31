package dto;

import java.io.Serializable;

public class HabilitadoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String dniHabilitado;
	
	public HabilitadoDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDniHabilitado() {
		return dniHabilitado;
	}

	public void setDniHabilitado(String dniHabilitado) {
		this.dniHabilitado = dniHabilitado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HabilitadoDTO(String nombre, String dniHabilitado) {
		this.nombre = nombre;
		this.dniHabilitado = dniHabilitado;
	}	
}
