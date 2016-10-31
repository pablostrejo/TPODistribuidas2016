package dto;


import java.io.Serializable;
//import java.sql.Date;
import java.util.*;
import java.util.List;

public class PedidoDTO implements Serializable{
		
	public String getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	private static final long serialVersionUID = 1L;
	
	private int idPedido;
	private DireccionDTO direccionCarga;
	private DireccionDTO direccionDestino;
	private Date fechaCarga;
	private int horaInicio;
	private int horaFin;
	private Date fechaMaxima;
	private List<CargaDTO> cargas;
	private List<HabilitadoDTO> habilitados;
	private float precio;
	private String sucursalDestino;
	private String sucursalOrigen;
	private boolean solicitaTransporteDirecto;
	private boolean solicitaAvionetaParticular;
	private ClienteDTO cliente;
	
	public PedidoDTO(){
	}

	public PedidoDTO(int idPedido,DireccionDTO direccionCarga, DireccionDTO direccionDestino,
			Date fechaCargaDate, int horaInicio, int horaFin, Date fechaMaximaDate,
			List<CargaDTO> cargas, float precio, String sucursalDestino, String sucursalOrigen,
			boolean solicitaTransporteDirecto,
			boolean solicitaAvionetaParticular, ClienteDTO cliente) {
		super();
		this.idPedido=idPedido;
		this.direccionCarga = direccionCarga;
		this.direccionDestino = direccionDestino;
		this.fechaCarga = fechaCargaDate;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fechaMaxima = fechaMaximaDate;
		this.cargas = cargas;
		this.precio = precio;
		this.sucursalDestino = sucursalDestino;
		this.sucursalOrigen = sucursalOrigen;
		this.solicitaTransporteDirecto = solicitaTransporteDirecto;
		this.solicitaAvionetaParticular = solicitaAvionetaParticular;
		this.cliente = cliente;
	}
	


	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public DireccionDTO getDireccionCarga() {
		return direccionCarga;
	}

	public void setDireccionCarga(DireccionDTO direccionCarga) {
		this.direccionCarga = direccionCarga;
	}

	public DireccionDTO getDireccionDestino() {
		return direccionDestino;
	}

	public void setDireccionDestino(DireccionDTO direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public List<HabilitadoDTO> getHabilitados() {
		return habilitados;
	}

	public void setHabilitados(List<HabilitadoDTO> habilitados) {
		this.habilitados = habilitados;
	}

	public List<CargaDTO> getCargas() {
		return cargas;
	}

	public void setCargas(List<CargaDTO> cargas) {
		this.cargas = cargas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(String sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public boolean isSolicitaTransporteDirecto() {
		return solicitaTransporteDirecto;
	}

	public void setSolicitaTransporteDirecto(boolean solicitaTransporteDirecto) {
		this.solicitaTransporteDirecto = solicitaTransporteDirecto;
	}

	public boolean isSolicitaAvionetaParticular() {
		return solicitaAvionetaParticular;
	}

	public void setSolicitaAvionetaParticular(boolean solicitaAvionetaParticular) {
		this.solicitaAvionetaParticular = solicitaAvionetaParticular;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
}
