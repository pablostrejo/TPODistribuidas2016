package dto;

import java.io.Serializable;

public class FacturaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idFactura;
	private int idPedido;
	private float precio;
	
	public FacturaDTO(){
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPEdido(int idPedido) {
		this.idPedido = idPedido;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FacturaDTO(int idFactura, int idPedido, float precio) {
		super();
		this.idFactura = idFactura;
		this.idPedido = idPedido;
		this.precio = precio;
	}

}
