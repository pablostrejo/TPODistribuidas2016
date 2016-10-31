package dto;


public class PrecioVehiculoDTO {

	private int idPrecioVehiculo;
	private String tipoVehiculo;
	private float precio;
	public int getIdPrecioVehiculo() {
		return idPrecioVehiculo;
	}
	public void setIdPrecioVehiculo(int idPrecioVehiculo) {
		this.idPrecioVehiculo = idPrecioVehiculo;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public PrecioVehiculoDTO(int idPrecioVehiculo, String tipoVehiculo, float precio) {
		this.idPrecioVehiculo = idPrecioVehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.precio = precio;
	}	
}
