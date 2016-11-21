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
	private float volumen;
	private float peso;
	private float ancho;
	private float alto;
	private float profundidad;
	private float tara;
	private int kilometraje;
	private String estado;
	private Boolean enGarantia;
	private Boolean trabajoEspecifico;
	private Date fechaUltimoControl;
	private PlanDeMantenimientoDTO planDeMantenimiento;
	
	public VehiculoDTO() {
		super();
	}
	public VehiculoDTO(int idVehiculo,String tipo, float volumen, float peso, float ancho,
			float alto, float profundidad, float tara, int kilometraje,
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
	public float getVolumen() {
		return volumen;
	}
	public void setVolumen(float volumen) {
		this.volumen = volumen;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getAncho() {
		return ancho;
	}
	public void setAncho(float ancho) {
		this.ancho = ancho;
	}
	public float getAlto() {
		return alto;
	}
	public void setAlto(float alto) {
		this.alto = alto;
	}
	public float getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(float profundidad) {
		this.profundidad = profundidad;
	}
	public float getTara() {
		return tara;
	}
	public void setTara(float tara) {
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
