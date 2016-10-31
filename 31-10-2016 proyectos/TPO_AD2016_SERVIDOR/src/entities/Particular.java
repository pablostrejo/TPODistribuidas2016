package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import dto.HabilitadoDTO;
import dto.ParticularDTO;

@Entity
@Table (name = "Particulares")
public class Particular extends Cliente {

	@Column (columnDefinition = "int", nullable = true)
	private int DNI;
	
	@Column (columnDefinition = "varchar(50)", nullable = true)
	private String apellido;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "idCliente")
	private List<Habilitado> habilitados;
	
	public Particular() {
		super();
	}
	
	public Particular(int idCliente, String nombre, int DNI, String apellido, List<Habilitado> habilitados) {
		super(idCliente, nombre);
		this.DNI = DNI;
		this.apellido = apellido;
		this.habilitados = habilitados;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Habilitado> getHabilitados() {
		return habilitados;
	}

	public void setHabilitados(List<Habilitado> habilitados) {
		this.habilitados = habilitados;
	}
	
	public ParticularDTO toDTO(){
		List<HabilitadoDTO> habilitadosDTO = new ArrayList<HabilitadoDTO>();
		for (Habilitado habilitado : habilitados)
			habilitadosDTO.add(habilitado.toDTO());
		ParticularDTO particularDTO = new ParticularDTO(idCliente,nombre, DNI, apellido, habilitadosDTO);
		return particularDTO;
	}
}
