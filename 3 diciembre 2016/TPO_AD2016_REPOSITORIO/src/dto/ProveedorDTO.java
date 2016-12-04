package dto;

import java.io.Serializable;

public class ProveedorDTO implements Serializable {

	private static final long serialVersionUID = -558533216070923399L;
	private int idProveedor;
	private String compania;
	private String tipoMercaderia;
	public String getTipoMercaderia() {
		return tipoMercaderia;
	}
	public void setTipoMercaderia(String tipoMercaderia) {
		this.tipoMercaderia = tipoMercaderia;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public ProveedorDTO(int idProveedor, String compania, String tipoMercaderia) {
		super();
		this.idProveedor = idProveedor;
		this.compania = compania;
		this.tipoMercaderia = tipoMercaderia;
	}
}
