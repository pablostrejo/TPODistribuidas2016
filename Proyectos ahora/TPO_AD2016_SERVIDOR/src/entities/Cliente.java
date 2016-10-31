package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import dto.ClienteDTO;

@Entity
@Table (name = "Clientes")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {
	
	@Id
	@GeneratedValue // duda si conviene o no esto....
	@Column(name = "idCliente", columnDefinition = "int", nullable=false)
	protected int idCliente;
	
	@Column(name = "nombre", columnDefinition = "varchar(50)", nullable=true)
	protected String nombre;

	public Cliente() {
		
	}

	public Cliente(int idCliente, String nombre) {
		super();
		this.idCliente= idCliente;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ClienteDTO toDTO(){
		return new ClienteDTO(idCliente,nombre);		
	}

}
