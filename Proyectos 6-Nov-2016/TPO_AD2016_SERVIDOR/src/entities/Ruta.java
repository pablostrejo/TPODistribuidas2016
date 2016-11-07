package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dto.RutaDTO;
import dto.TrayectoDTO;

@Entity
@Table (name = "Rutas")
public class Ruta {

	@Id
	@GeneratedValue
	@Column(name = "idRuta", columnDefinition = "int", nullable=false)
	private int idRuta;
	@OneToMany
	@JoinColumn (name = "idRuta")
	private List<Trayecto> trayectos;
	
	@Column(name = "precio", columnDefinition = "float", nullable=true)
	private float precio;
	
	@ManyToOne
	@JoinColumn (name = "idSucursalOrigen", referencedColumnName = "idSucursal")
	private Sucursal sucursalOrigen;
	
	@ManyToOne
	@JoinColumn (name = "idSucursalDestino", referencedColumnName = "idSucursal")
	Sucursal sucursalDestino;

	public Ruta() {
		super();
	}
	
	public Ruta(int idRuta, List<Trayecto> trayectos, float precio,Sucursal sucursalOrigen,Sucursal sucursalDestino) {
		this.idRuta=idRuta;
		this.trayectos = trayectos;
		this.precio = precio;
		this.sucursalDestino=sucursalDestino;
		this.sucursalOrigen=sucursalOrigen;
	
	}
	public Ruta(int idRuta, List<Trayecto> trayectos, float precio) {
		this.idRuta=idRuta;
		this.trayectos = trayectos;
		this.precio = precio;
	
	}

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
	public List<Trayecto> getTrayectos() {
		return trayectos;
	}
	public void setTrayectos(List<Trayecto> trayectos) {
		this.trayectos = trayectos;
	}
	
	public RutaDTO toDTO(){
		List<TrayectoDTO> trayectosDTO = new ArrayList<TrayectoDTO>();
		for (Trayecto trayecto : trayectos)
			trayectosDTO.add(trayecto.toDTO());
		return new RutaDTO(idRuta, trayectosDTO, precio,sucursalOrigen.toDTO(),sucursalDestino.toDTO());
	}
}
