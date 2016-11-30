package dto;

import java.io.Serializable;
import java.sql.Date;

public class VehiculoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idVehiculo;
	private String tipo;
	private int volumen;
	private int peso;
	private int ancho;
	private int alto;
	private int profundidad;
	private int tara;
	private int kilometraje;
	private String estado;
	private Boolean enGarantia;
	private Boolean trabajoEspecifico;
	private Date fechaUltimoControl;
	private PlanDeMantenimientoDTO planDeMantenimiento;
	
	public VehiculoDTO() {
		super();
	}
	public VehiculoDTO(int idVehiculo, String tipo, int volumen, int peso, int ancho,
			int alto, int profundidad, int tara, int kilometraje,
			String estado, Boolean enGarantia, Boolean trabajoEspecifico, Date fechaUltimoControl,
			PlanDeMantenimientoDTO planDeMantenimiento) {
		super();
		this.idVehiculo=idVehiculo;
		this.tipo = tipo;
		this.volumen = volumen;
		this.peso = peso;
		this.ancho = ancho;
		this.alto = alto;
		this.profundidad = profundidad;
		this.tara = tara;
		this.kilometraje = kilometraje;
		this.estado = estado;
		this.enGarantia=enGarantia;
		this.trabajoEspecifico = trabajoEspecifico;
		this.fechaUltimoControl = fechaUltimoControl;
		this.planDeMantenimiento = planDeMantenimiento;
	}
	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public int getTara() {
		return tara;
	}
	public void setTara(int tara) {
		this.tara = tara;
	}
	public int getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(int kilometraje) {
		this.kilometraje = kilometraje;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Boolean isTrabajoEspecifico() {
		return trabajoEspecifico;
	}
	public void setTrabajoEspecifico(Boolean trabajoEspecifico) {
		this.trabajoEspecifico = trabajoEspecifico;
	}
	public Date getFechaUltimoControl() {
		return fechaUltimoControl;
	}
	public void setFechaUltimoControl(Date fechaUltimoControl) {
		this.fechaUltimoControl = fechaUltimoControl;
	}
	public PlanDeMantenimientoDTO getPlanDeMantenimiento() {
		return planDeMantenimiento;
	}
	public void setPlanDeMantenimiento(PlanDeMantenimientoDTO planDeMantenimiento) {
		this.planDeMantenimiento = planDeMantenimiento;
	}

	public boolean isEnGarantia() {
		return enGarantia;
	}

	public void setEnGarantia(boolean enGarantia) {
		this.enGarantia = enGarantia;
	}
}
