package entities;

import javax.persistence.*;

import dto.ProveedorDTO;


@Entity
@Table (name = "Proveedores")
@Inheritance(strategy = InheritanceType.JOINED)
public class Proveedor {
	
	@Id
	@GeneratedValue
	@Column(name = "idProveedor", columnDefinition = "int", nullable=false)
	protected int idProveedor;
	
	@Column(name="compania", columnDefinition = "varchar(50)", nullable=true)
	protected String compania;
	
	@Column(name="tipoMercaderia", columnDefinition= "varchar(50)", nullable=true)
	protected String tipoMercaderia;
	
	public Proveedor() {
		super();
	}
	
	public Proveedor(int idProveedor,String compania, String tipoMercaderia) {
		this.idProveedor=idProveedor;
		this.compania = compania;
		this.tipoMercaderia = tipoMercaderia;
	}
	
	public String getTipoMercaderia() {
		return tipoMercaderia;
	}
	public void setTipoMercaderia(String tipoMercaderia) {
		this.tipoMercaderia = tipoMercaderia;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	
	public ProveedorDTO toDTO(){
		return new ProveedorDTO(idProveedor, compania, tipoMercaderia);		
	}
}
