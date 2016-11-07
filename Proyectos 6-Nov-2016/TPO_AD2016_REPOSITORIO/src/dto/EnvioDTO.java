package dto;

import java.io.Serializable;
import java.util.Date;

public class EnvioDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idEnvio;
	private Date fechaSalida;
	private Date fechaLlegada;
	private boolean cumpleCondicionesCarga;
	private String estado;
	private PedidoDTO pedido;
	private int prioridad;
	
	public EnvioDTO() {
		
	}
	public EnvioDTO(int idEnvio, Date fechaSalida, Date fechaLlegada,
			boolean cumpleCondicionesCarga, String estado, PedidoDTO pedido,
			int prioridad) {
		super();
		this.idEnvio = idEnvio;
		this.fechaSalida = fechaSalida;
		this.fechaLlegada = fechaLlegada;
		this.cumpleCondicionesCarga = cumpleCondicionesCarga;
		this.estado = estado;
		this.pedido = pedido;
		this.prioridad = prioridad;
	}

	public int getIdEnvio() {
		return idEnvio;
	}

	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public boolean isCumpleCondicionesCarga() {
		return cumpleCondicionesCarga;
	}

	public void setCumpleCondicionesCarga(boolean cumpleCondicionesCarga) {
		this.cumpleCondicionesCarga = cumpleCondicionesCarga;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;		
	}
}
