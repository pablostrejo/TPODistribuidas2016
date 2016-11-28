package entities;

import javax.persistence.*;

import dto.VehiculoDTO;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="Vehiculos")
public class Vehiculo {

	
	@Id
	@GeneratedValue
	@Column(name = "idVehiculo", columnDefinition = "int", nullable=false)
	private int idVehiculo;
	
	@Column(name="tipo", columnDefinition = "varchar(50)", nullable=true)
	private String tipo;
	
	@Column(name="volumen", columnDefinition = "int", nullable=true)
	private int volumen;
	
	@Column(name="peso", columnDefinition = "int", nullable=true)
	private int peso;
	
	@Column(name="ancho", columnDefinition = "int", nullable=true)
	private int ancho;
	
	@Column(name="alto", columnDefinition = "int", nullable=true)
	private int alto;
	
	@Column(name="profundidad", columnDefinition = "int", nullable=true)
	private int profundidad;
	
	@Column(name="tara", columnDefinition = "int", nullable=true)
	private int tara;
	
	@Column(name="kilometraje", columnDefinition = "int", nullable=true)
	private int kilometraje;
	
	@Column(name="estado", columnDefinition = "varchar(50)", nullable=true)
	private String estado;
	
	@Column(name="trabajoEspecifico", columnDefinition = "bit", nullable=true)
	private Boolean trabajoEspecifico;
        
	@Column(name="especificacion", columnDefinition = "bit", nullable=true)
	private Boolean enGarantia;
        
	@Column(name="fechaUltimaControl", columnDefinition = "date", nullable=true) //datetime
	private Date fechaUltimoControl;
	
	@OneToOne
	@JoinColumn (name = "idPlanDeMantenimiento")
	private PlanDeMantenimiento planDeMantenimiento;
	// 	private List<PlanDeMantenimiento> planesDeMantenimiento;
	
	public Vehiculo() {
		super();
	}
	
	public Vehiculo(int idVehiculo,String tipo, int volumen, int peso, int ancho,
			int alto, int profundidad, int tara, int kilometraje,
	    	String estado, Boolean enGarantia, Boolean trabajoEspecifico, Date fechaUltimoControl,
			PlanDeMantenimiento planDeMantenimiento) {
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
	public Date getFechaUltimoControl() {
		return fechaUltimoControl;
	}
	public void setFechaUltimoControl(Date fechaUltimoControl) {
		this.fechaUltimoControl = fechaUltimoControl;
	}
	public PlanDeMantenimiento getPlanDeMantenimiento() {
		return planDeMantenimiento;
	}
	public void setPlanDeMantenimiento(PlanDeMantenimiento planDeMantenimiento) {
		this.planDeMantenimiento = planDeMantenimiento;
	}

	public Boolean getTrabajoEspecifico() {
		return trabajoEspecifico;
	}
	public void setTrabajoEspecifico(Boolean trabajoEspecifico) {
		this.trabajoEspecifico = trabajoEspecifico;
	}
	public Boolean getEnGarantia() {
		return enGarantia;
	}
	public void setEnGarantia(Boolean enGarantia) {
		this.enGarantia = enGarantia;
	}
	public VehiculoDTO toDTO(){
        return new VehiculoDTO (idVehiculo, tipo, volumen, peso, ancho, alto, profundidad, tara, 
                        kilometraje, estado, enGarantia, trabajoEspecifico, fechaUltimoControl, 
                        planDeMantenimiento.toDTO());
	}
}
