package dto;

import java.io.Serializable;

public class ClienteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idCliente;
	private String nombre;

	public ClienteDTO() {
		
	}
	
	public ClienteDTO(int idCliente, String nombre) {
		super();
		this.idCliente= idCliente;
		this.nombre = nombre;
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
