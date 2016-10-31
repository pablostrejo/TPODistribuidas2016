package dto;

import java.io.Serializable;
import java.util.List;

public class SucursalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idSucursal;
	private String nombre;
	private DireccionDTO ubicacion;
	private List<ViajeDTO> viajes;
	
	public SucursalDTO() {
		
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DireccionDTO getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(DireccionDTO ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<ViajeDTO> getViajes() {
		return viajes;
	}

	public void setViajes(List<ViajeDTO> viajes) {
		this.viajes = viajes;
	}

	public SucursalDTO(int idSucursal, String nombre, DireccionDTO ubicacion,
			List<ViajeDTO> viajes) {
		super();
		this.idSucursal = idSucursal;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.viajes = viajes;
	}
}
