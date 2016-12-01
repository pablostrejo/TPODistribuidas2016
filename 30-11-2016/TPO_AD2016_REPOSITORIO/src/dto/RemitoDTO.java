package dto;

import java.io.Serializable;

public class RemitoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idRemito;
	private int idPedido;
	
	public RemitoDTO(){
	}

	public int getIdRemito() {
		return idRemito;
	}

	public void setIdRemito(int idRemito) {
		this.idRemito = idRemito;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPEdido(int idPedido) {
		this.idPedido = idPedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RemitoDTO(int idRemito, int idPedido) {
		super();
		this.idRemito = idRemito;
		this.idPedido = idPedido;
	}
}
