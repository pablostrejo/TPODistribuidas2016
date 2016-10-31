package dto;

import java.io.Serializable;
import java.util.List;


public class MapaDeRutaDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1904773340299900076L;
	private int idMapaDeRuta;
	private List<RutaDTO> rutas;
	public int getIdMapaDeRuta() {
		return idMapaDeRuta;
	}
	public void setIdMapaDeRuta(int idMapaDeRuta) {
		this.idMapaDeRuta = idMapaDeRuta;
	}
	public List<RutaDTO> getRutas() {
		return rutas;
	}
	public void setRutas(List<RutaDTO> rutas) {
		this.rutas = rutas;
	}
	
}
