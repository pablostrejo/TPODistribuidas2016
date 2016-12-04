package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	
	public int calcularKm() {
		int km = 0;
		for(Trayecto t : trayectos){
			km = t.getKm() + km;
		}
		return km;
	}
	
	public boolean estaIncluidoEnLaRuta(int origen, int destino) {
		boolean sucOrigenAux = false;
		for(Trayecto t : this.getTrayectos()){
			if(t.getSucursalOrigen().getIdSucursal() == origen){
				sucOrigenAux = true;
			}
			if((t.getSucursalDestino().getIdSucursal() == destino) && (sucOrigenAux)){
				return true;
			}
		}
		return false;
	}
	
	public Date obtenerFecha(int destino) {
		float tiempo = 0;
		for(Trayecto t : this.getTrayectos()){
			tiempo = tiempo + t.getTiempo(); 
			if(t.getSucursalDestino().getIdSucursal() == destino){
				long m =(long) tiempo;
				long milisegundos = 60000;
				Date auxiliar = Calendar.getInstance().getTime();
				long minutosAux = m * milisegundos;
				Date auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
				return auxiliar2;
			}
		}
		return null;
	}
}