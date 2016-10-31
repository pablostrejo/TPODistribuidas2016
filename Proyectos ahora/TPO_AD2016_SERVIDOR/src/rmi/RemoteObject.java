package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import dao.HibernateDAO;
import dto.CargaDTO;
import dto.ClienteDTO;
import dto.DireccionDTO;
import dto.PedidoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import entities.Carga;
import entities.Cliente;
import entities.Direccion;
import entities.Pedido;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

	private static final long serialVersionUID = 1L;

	protected RemoteObject() throws RemoteException {
		super();
	}
	
	public void altaPedido(PedidoDTO pedidoDTO) { /// acá haria el guardar del pedido....
		Pedido pedido = new Pedido();
		pedido = PedidoToEntity(pedidoDTO);

		Controlador.getInstance().guardarPedido(pedido);
	}
	
	public List<SucursalDTO> obtenerSucursales() throws RemoteException {
		return Controlador.getInstance().obtenerSucursales();
	}
	
	public List<ClienteDTO> obtenerClientes() {
		return Controlador.getInstance().obtenerClientes();
	}


	public List<ViajeDTO> obtenerViajes() {
		// TODO Auto-generated method stub
		return null;
	}

	public ViajeDTO obtenerViaje(ViajeDTO viajeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PedidoDTO> obtenerPedidos() {
		// TODO Auto-generated method stub
		return null;
	}

	public VehiculoDTO obtenerVehiculo(VehiculoDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	public ViajeDTO obtenerViajePorVehiculo(VehiculoDTO vehiculo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void actualizarViaje(ViajeDTO viaje) {
		// TODO Auto-generated method stub
		
	}

	public List<VehiculoDTO> obtenerVehiculos() {
		// TODO Auto-generated method stub
		return null;
	}

	public void enviar() {
		// TODO Auto-generated method stub
		
	}

	public ClienteDTO obtenerClientePorID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void altaCarga(){
		
	}
	
	private Pedido PedidoToEntity(PedidoDTO pedidoDTO){
		List<Carga> cargas = new ArrayList<Carga>();
		for(CargaDTO cargaDTO : pedidoDTO.getCargas())
			cargas.add(CargaToEntity(cargaDTO));
		Pedido p=new Pedido(pedidoDTO.getIdPedido(),DireccionToEntity(pedidoDTO.getDireccionCarga()), DireccionToEntity(pedidoDTO.getDireccionDestino()),
				pedidoDTO.getFechaCarga(), pedidoDTO.getHoraInicio(), pedidoDTO.getHoraFin(), pedidoDTO.getFechaMaxima(),
				cargas, pedidoDTO.getPrecio(), pedidoDTO.getSucursalDestino(),
				null, pedidoDTO.isSolicitaTransporteDirecto(),
				pedidoDTO.isSolicitaAvionetaParticular(), ClienteToEntity(pedidoDTO.getCliente()));
		p.setSucursalOrigen(pedidoDTO.getSucursalOrigen());
		return p;
	}
	
	private Carga CargaToEntity(CargaDTO cargaDTO){
		return new Carga(cargaDTO.getIdCarga(),cargaDTO.getPeso(), cargaDTO.getAncho(), cargaDTO.getAlto(), cargaDTO.getProfundidad(),
				cargaDTO.getVolumen(), cargaDTO.getFragilidad(), cargaDTO.getTratamiento(), cargaDTO.getApilable(),
				cargaDTO.isRefrigerable(), cargaDTO.getCondiciones(), cargaDTO.isDespachado(),
				cargaDTO.getTipoMercaderia());
	}
	
	
	
	private Direccion DireccionToEntity (DireccionDTO direccionDTO){
		return new Direccion(direccionDTO.getIdDireccion(),direccionDTO.getCalle(), direccionDTO.getNumero(), direccionDTO.getPiso(), 
								direccionDTO.getDepartamento(), direccionDTO.getCP());
	}
	
	private Cliente ClienteToEntity (ClienteDTO clienteDTO){
		return new Cliente(clienteDTO.getIdCliente(),clienteDTO.getNombre());
	}
	
}
