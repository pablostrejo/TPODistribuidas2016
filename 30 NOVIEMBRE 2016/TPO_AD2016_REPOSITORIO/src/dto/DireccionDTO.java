package dto;

import java.io.Serializable;

public class DireccionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int idDireccion;
	private String calle;
	private int numero;
	private int piso;
	private String departamento;
	private String CP;
	
	public DireccionDTO(){
	}
	
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String CP) {
		this.CP = CP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DireccionDTO(int idDireccion, String calle, int numero, int piso, String departamento, String CP) {
		super();
		this.setIdDireccion(idDireccion);
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.departamento = departamento;
		this.CP = CP;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}	
}
