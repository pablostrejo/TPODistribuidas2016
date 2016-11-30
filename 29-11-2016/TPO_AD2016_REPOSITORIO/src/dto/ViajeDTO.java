package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ViajeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idViaje;
	private List<EnvioDTO> envios;
	private Date fechaLlegada;
	private SucursalDTO sucursalOrigen;
	private SucursalDTO sucursalDestino;
	private VehiculoDTO vehiculo;
	private boolean finalizado;
	
	public ViajeDTO() {
	
	}
	public ViajeDTO(int idViaje, List<EnvioDTO> envios, Date fechaLlegada,
			SucursalDTO sucursalOrigen, SucursalDTO sucursalDestino,
			boolean finalizado, VehiculoDTO vehiculo) {
		super();
		this.idViaje = idViaje;
		this.envios = envios;
		this.fechaLlegada = fechaLlegada;
		this.sucursalOrigen = sucursalOrigen;
		this.sucursalDestino = sucursalDestino;
		this.finalizado = finalizado;
		this.vehiculo = vehiculo;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}

	public List<EnvioDTO> getEnvios() {
		return envios;
	}

	public void setEnvios(List<EnvioDTO> envios) {
		this.envios = envios;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
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

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public VehiculoDTO getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoDTO vehiculo) {
		this.vehiculo = vehiculo;
	}
}
