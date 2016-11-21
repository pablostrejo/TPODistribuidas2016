package dto;

import java.io.Serializable;
import java.util.List;

public class ParticularDTO extends ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int DNI;
	private String apellido;
	private List<HabilitadoDTO> habilitados;
	
	public ParticularDTO() {
		super();
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int DNI) {
		this.DNI = DNI;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public ParticularDTO(int idCliente, String nombre, int DNI, String apellido, List<HabilitadoDTO> habilitados) {
		super(idCliente, nombre);
		this.DNI = DNI;
		this.apellido = apellido;
		this.habilitados = habilitados;
	}

	public List<HabilitadoDTO> getHabilitados() {
		return habilitados;
	}

	public void setHabilitados(List<HabilitadoDTO> habilitados) {
		this.habilitados = habilitados;
	}
}
