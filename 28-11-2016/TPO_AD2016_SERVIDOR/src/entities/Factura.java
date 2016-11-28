package entities;

import javax.persistence.*;

import dto.FacturaDTO;

@Entity
@Table (name= "Facturas")
public class Factura {
	
	@Id
	@GeneratedValue
	@Column (nullable = false)
	private int idFactura;
	
	@OneToOne
	@JoinColumn (name = "idPedido")
	private Pedido pedido;
	
	@Column (nullable = true)
	private float precio;
	
	public Factura(){
	}
	
	public Factura(int idFactura,Pedido pedido, float precio) {
		this.idFactura=idFactura;
		this.pedido = pedido;
		this.precio = precio;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public FacturaDTO toDTO(){
		return new FacturaDTO (idFactura, pedido.toDTO().getIdPedido(), precio);		
	}
}
