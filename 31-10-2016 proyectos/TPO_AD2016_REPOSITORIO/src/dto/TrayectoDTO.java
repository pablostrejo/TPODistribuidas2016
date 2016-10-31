package dto;

import java.io.Serializable;
import java.sql.Date;

public class TrayectoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8276738872174890036L;
	private int idTrayecto;
	private SucursalDTO sucursalOrigen;
	private SucursalDTO sucursalDestino;
	private float tiempo;
	private int km;
	private float precio;
	public int getIdTrayecto() {
		return idTrayecto;
	}
	public void setIdTrayecto(int idTrayecto) {
		this.idTrayecto = idTrayecto;
	}
	public SucursalDTO getSucursalOrigen() {
		return sucursalOrigen;
	}
	public void setSucursalOrigen(SucursalDTO sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}
	public SucursalDTO getSucursalDestino() {
		return sucursalDestino;
	}
	public void setSucursalDestino(SucursalDTO sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}
	public float getTiempo() {
		return tiempo;
	}
	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}
	public int getKm() {
		return km;
	}
	public void setKm(int km) {
		this.km = km;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public TrayectoDTO(int idTrayecto, SucursalDTO sucursalOrigen,
			SucursalDTO sucursalDestino, float tiempo, int km, float precio) {
		super();
		this.idTrayecto = idTrayecto;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.tiempo = tiempo;
		this.km = km;
		this.precio = precio;
	}
}
