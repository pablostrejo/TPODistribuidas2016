package dto;

import java.io.Serializable;



public class PlanDeMantenimientoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idProveedor;
	private int diasProxControl;
	private int diasDemora;
	private int kmProxControl;
	public int getDiasProxControl() {
		return diasProxControl;
	}
	public void setDiasProxControl(int diasProxControl) {
		this.diasProxControl = diasProxControl;
	}
	public int getDiasDemora() {
		return diasDemora;
	}
	public void setDiasDemora(int diasDemora) {
		this.diasDemora = diasDemora;
	}
	public int getKmProxControl() {
		return kmProxControl;
	}
	public void setKmProxControl(int kmProxControl) {
		this.kmProxControl = kmProxControl;
	}
	public PlanDeMantenimientoDTO(int idProveedor, int diasProxControl, int diasDemora, int kmProxControl) {
		this.setIdProveedor(idProveedor);
		this.diasProxControl = diasProxControl;
		this.diasDemora = diasDemora;
		this.kmProxControl = kmProxControl;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
}
