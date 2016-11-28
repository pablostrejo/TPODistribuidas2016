package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import dto.CargaDTO;
import dto.PedidoDTO;


@Entity
@Table (name = "Pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue
	@Column (nullable = false)
	private int idPedido;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn (name = "idDireccionCarga", referencedColumnName = "idDireccion")
	private Direccion direccionCarga;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn (name = "idDireccionDestino", referencedColumnName = "idDireccion")
	private Direccion direccionDestino;
	
	@Column (columnDefinition = "datetime", nullable = true)
	private Date fechaCarga;
	// cambio chequear diagrama
	@Column (nullable = true)
	private Date horaInicio;
	// cambio chequear diagrama
	@Column (nullable = true)
	private Date horaFin;
	
	@Column (columnDefinition = "datetime", nullable = true)
	private Date fechaMaxima;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "idPedido",referencedColumnName="idPedido")
	private List<Carga> cargas;
	
	@Column (nullable = true)
	private float precio;
	
	// tal vez seria mas facil hacer este dato un String para no complicarla...
	//@ManyToOne (cascade = CascadeType.ALL)
	//@JoinColumn (name = "idSucursal")
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String sucursalDestino;
	
	// tal vez seria mas facil hacer este dato un String para no complicarla...
	//@ManyToOne (cascade = CascadeType.ALL)
	//@JoinColumn (name = "idSucursal")
	@Column (columnDefinition = "varchar(40)", nullable = true)
	private String sucursalOrigen;

	@Column (columnDefinition = "bit", nullable = true)
	private boolean solicitaTransporteDirecto;
	
	@Column (columnDefinition = "bit", nullable = true)
	private boolean solicitaAvionetaParticular;
	
	@ManyToOne // oneToOne
	@JoinColumn (name = "idCliente")
	private Cliente cliente;
	
	@Column(columnDefinition="varchar(50)", nullable=true)
	private String estado;
	
	public Pedido() {
	}
	
	public Pedido(int idPedido,Direccion direccionCarga, Direccion direccionDestino,
			Date fechaCarga, Date horaInicio, Date horaFin, Date fechaMaxima,
			List<Carga> cargas, float precio, String sucursalDestino, String sucursalOrigen,
			boolean solicitaTransporteDirecto,
			boolean solicitaAvionetaParticular, Cliente cliente) {
		super();
		this.idPedido=idPedido;
		this.direccionCarga = direccionCarga;
		this.direccionDestino = direccionDestino;
		this.fechaCarga = fechaCarga;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fechaMaxima = fechaMaxima;
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
	
	public String getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public Direccion getDireccionCarga() {
		return direccionCarga;
	}

	public void setDireccionCarga(Direccion direccionCarga) {
		this.direccionCarga = direccionCarga;
	}

	public Direccion getDireccionDestino() {
		return direccionDestino;
	}

	public void setDireccionDestino(Direccion direccionDestino) {
		this.direccionDestino = direccionDestino;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
		
	public PedidoDTO toDTO(){
		List<CargaDTO> cargasDTO = new ArrayList<CargaDTO>();
		for(Carga carga : cargas)
			cargasDTO.add(carga.toDTO());
		PedidoDTO pedidoDTO = new PedidoDTO(idPedido,direccionCarga.toDTO(), direccionDestino.toDTO(), fechaCarga, horaInicio,
				horaFin, fechaMaxima,cargasDTO, precio, sucursalDestino, sucursalOrigen, 
				solicitaTransporteDirecto, solicitaAvionetaParticular, cliente.toDTO());	
		pedidoDTO.setSucursalOrigen(sucursalOrigen);
		return pedidoDTO;	
	}
}
