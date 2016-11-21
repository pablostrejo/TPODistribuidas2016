package dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idProducto;
	private String nombre;
	private String tipo;
	
	public ProductoDTO(){
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProductoDTO(int idProducto, String nombre, String tipo) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	
}
