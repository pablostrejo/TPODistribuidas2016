package dto;

import java.io.Serializable;

public class SeguroDTO extends ProveedorDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	public SeguroDTO(int idProveedor, String compania, String tipoMercaderia){
		super(idProveedor,compania, tipoMercaderia);
	}

}
