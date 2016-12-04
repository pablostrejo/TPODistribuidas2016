package entities;

import javax.persistence.*;
import dto.TransporteDTO;

@Entity
@Table(name="Transportes")
@DiscriminatorValue("Transportes")
public class Transporte extends Proveedor{

	
	@Column(name="tipoTransporte", columnDefinition= "varchar(50)", nullable=true)
	private String tipoTransporte;

	public Transporte() {
		super();
	}
	
	public Transporte(int idProveedor,String compania, String tipoMercaderia,String tipoTransporte) {
		super(idProveedor,compania,tipoMercaderia);
		this.tipoTransporte = tipoTransporte;
	}
	
	public String getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	
	public TransporteDTO toDTO(){
		return new TransporteDTO(idProveedor,compania,tipoMercaderia,tipoTransporte);		
	}
}