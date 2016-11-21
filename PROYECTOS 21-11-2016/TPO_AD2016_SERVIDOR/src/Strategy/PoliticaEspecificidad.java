package Strategy;

import dto.VehiculoDTO;
import entities.Vehiculo;

public class PoliticaEspecificidad implements PoliticaMantenimiento{

	public void mandarAMantenimiento(VehiculoDTO vehiculoDTO) {
		vehiculoDTO.setEstado("En mantenimiento por trabajo especifico.");
		
	}

	

}
