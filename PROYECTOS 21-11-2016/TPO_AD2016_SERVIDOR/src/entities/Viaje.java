package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import dto.EnvioDTO;
import dto.ViajeDTO;

@Entity
@Table (name = "Viajes")
public class Viaje {

	@Id
	@GeneratedValue
	@Column (nullable = false)
	private int idViaje;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idViaje")
	private List<Envio> envios;
	
	@Column (columnDefinition = "datetime", nullable = true)
	private Date fechaLlegada;
	
	@ManyToOne 
	@JoinColumn(name="sucursalOrigen")
	private Sucursal sucursalOrigen;
	
	@ManyToOne 
	@JoinColumn(name="sucursalDestino")
	private Sucursal sucursalDestino;
	
	@Column (columnDefinition = "bit", nullable = true)
	private boolean finalizado;
	
	@OneToOne
	@JoinColumn (name = "idVehiculo")
	private Vehiculo vehiculo;
	
	public Viaje() {
	}
	
	public Viaje(int idViaje, List<Envio> envios, Date fechaLlegada, Sucursal sucursalOrigen,
			Sucursal sucursalDestino, boolean finalizado, Vehiculo vehiculo) {
		super();
		this.idViaje=idViaje;
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

	public List<Envio> getEnvios() {
		return envios;
	}

	public void setEnvios(List<Envio> envios) {
		this.envios = envios;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public ViajeDTO toDTO(){
		List<EnvioDTO> enviosDTO = new ArrayList<EnvioDTO>();
		for(Envio envio : envios)
			enviosDTO.add(new EnvioDTO(envio.getIdEnvio(), envio.getFechaSalida(), envio.getFechaLlegada(),
					envio.isCumpleCondicionesCarga(), envio.getEstado(), 
					envio.getPedido().toDTO(), envio.getPrioridad()));
		
		return new ViajeDTO(idViaje, enviosDTO, fechaLlegada, sucursalOrigen.toDTONoRecursivo(), 
				sucursalDestino.toDTONoRecursivo(),finalizado, vehiculo.toDTO());
	}
}
