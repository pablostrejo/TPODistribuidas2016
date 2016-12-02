package entities;

import javax.persistence.*;

import dto.SeguroDTO;

@Entity
@Table(name="Seguros")
public class Seguro extends Proveedor {
	
	public Seguro(int idProveedor, String compania, String tipoMercaderia){
		super(idProveedor,compania, tipoMercaderia);
	}
	public Seguro(){
		super();
	}
	public SeguroDTO toDTO(){
		return new SeguroDTO(idProveedor,compania, tipoMercaderia);
	}

}
