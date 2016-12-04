package Strategy;

import dto.VehiculoDTO;
import entities.Vehiculo;

public interface PoliticaMantenimiento {


	void mandarAMantenimiento(VehiculoDTO vehiculoDTO);


}
