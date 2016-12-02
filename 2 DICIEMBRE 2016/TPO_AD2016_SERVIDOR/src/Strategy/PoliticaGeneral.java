package Strategy;

import dto.VehiculoDTO;
import entities.Vehiculo;

public class PoliticaGeneral implements PoliticaMantenimiento{


	public void mandarAMantenimiento(VehiculoDTO vehiculoDTO) {
		vehiculoDTO.setEstado("En mantenimiento general.");
		
	}
}
