package dto;

import java.io.Serializable;
import java.util.List;

public class RutaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4870172584415686445L;
	private int idRuta;
	private List<TrayectoDTO> trayectos;
	private float precio;
	private SucursalDTO sucursalOrigen;
	private SucursalDTO sucursalDestino;
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public List<TrayectoDTO> getTrayectos() {
		return trayectos;
	}
	public void setTrayectos(List<TrayectoDTO> trayectos) {
		this.trayectos = trayectos;
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
	public RutaDTO(){
	}
	public RutaDTO(int idRuta, List<TrayectoDTO> trayectos, float precio) {
		this.idRuta=idRuta;
		this.trayectos = trayectos;
		this.precio = precio;
	}
	public RutaDTO(int idRuta, List<TrayectoDTO> trayectos, float precio,
			SucursalDTO sucursalOrigen, SucursalDTO sucursalDestino) {
		this.idRuta = idRuta;
		this.trayectos = trayectos;
		this.precio = precio;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
	}
	
	public int calcularKm(){
		int km=0;
		for(TrayectoDTO t:trayectos){
			km=t.getKm()+km;
		}
		return km;
	}
}
