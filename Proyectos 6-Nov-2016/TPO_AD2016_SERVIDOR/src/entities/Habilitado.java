package entities;
import javax.persistence.*;

import dto.HabilitadoDTO;

@Entity
@Table (name = "Habilitados")

public class Habilitado {
	
	@Id
	@Column (columnDefinition = "varchar(50)", nullable = true)
	private String dniHabilitado;
	
	@Column (columnDefinition = "varchar(50)", nullable = true)
	private String nombre;
	
	public Habilitado() {
		super();
	}
	
	public Habilitado(String dniHabilitado, String nombre) {
		this.dniHabilitado = dniHabilitado;
		this.nombre = nombre;
	}

	public String getDniHabilitado() {
		return dniHabilitado;
	}

	public void setDniHabilitado(String dniHabilitado) {
		this.dniHabilitado = dniHabilitado;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public HabilitadoDTO toDTO(){
		return new HabilitadoDTO(dniHabilitado, nombre);
	}
}
