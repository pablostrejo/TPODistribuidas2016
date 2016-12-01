package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import Strategy.*;
import dto.*;
import entities.*;

public class RemoteObject extends UnicastRemoteObject implements RemoteInterface {

	private static final long serialVersionUID = 1L;

	protected RemoteObject() throws RemoteException {
		super();
	}
	
	public void altaPedido(PedidoDTO pedidoDTO) throws RemoteException{ 
		Pedido pedido = new Pedido();
		pedido = PedidoToEntity(pedidoDTO);

		Controlador.getInstance().guardarPedido(pedido);
	}
	
	public ClienteDTO obtenerClientePorID(int id) throws RemoteException{
		return Controlador.getInstance().obtenerClientePorID(id);
	}
	
	public List<SucursalDTO> obtenerSucursales() throws RemoteException {
		return Controlador.getInstance().obtenerSucursales();
	}
	
	public List<ClienteDTO> obtenerClientes() throws RemoteException{
		return Controlador.getInstance().obtenerClientes();
	}
	
	public List<VehiculoDTO> obtenerVehiculosListos() throws RemoteException{
		List<VehiculoDTO> vehDTO = new ArrayList<VehiculoDTO>();
		List<Vehiculo> vehi = Controlador.getInstance().obtenerVehiculosListos();
		for(Vehiculo v : vehi){
			vehDTO.add(v.toDTO());
		}
		return vehDTO;
	}
	
	public List<ViajeDTO> generarViajes(int sucursal) throws RemoteException {
		// return Controlador.getInstance().generarViajes();
		return Controlador.getInstance().despacharViajes(sucursal);
	}

	public List<ViajeDTO> enviar() throws RemoteException {
		
		return Controlador.getInstance().enviar();
		
	}

	private SucursalDTO proxDestino(PedidoDTO pedido) {
		MapaDeRutaDTO mp = Controlador.getInstance().mapadeRuta;
		cargarMapaDeRuta();
		SucursalDTO s = Controlador.getInstance().obtenerSucursal(Integer.valueOf(pedido.getSucursalOrigen()).intValue());
		SucursalDTO sd = Controlador.getInstance().obtenerSucursal(Integer.valueOf(pedido.getSucursalDestino()).intValue());
		RutaDTO r = Controlador.getInstance().obtenerMejorRuta(s, sd);
		return r.getTrayectos().get(0).getSucursalDestino();
	}

	public void cargarMapaDeRuta(){
		List<RutaDTO> rutas = Controlador.getInstance().obtenerRutas();
		Controlador.getInstance().mapadeRuta =  new MapaDeRutaDTO();
		Controlador.getInstance().mapadeRuta.setRutas(rutas);
	}

	public List<ViajeDTO> obtenerViajes() throws RemoteException{
		return Controlador.getInstance().obtenerViajes();
	}

	public ViajeDTO obtenerViaje(ViajeDTO viajeDTO) throws RemoteException{
		return Controlador.getInstance().obtenerViajeDeEnvio(viajeDTO);
	}

	public List<PedidoDTO> obtenerPedidos() throws RemoteException{
		return Controlador.getInstance().obtenerPedidos();
	}

	public VehiculoDTO obtenerVehiculo(VehiculoDTO v) throws RemoteException{
		return Controlador.getInstance().obtenerVehiculo(v);
	}

	public ViajeDTO obtenerViajePorVehiculo(VehiculoDTO vehiculo) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

	public void actualizarViaje(ViajeDTO viaje) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	public List<VehiculoDTO> obtenerVehiculos() throws RemoteException{
		return Controlador.getInstance().obtenerVehiculos();
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
				pedidoDTO.isSolicitaAvionetaParticular(), ClienteToEntity(pedidoDTO.getCliente()), pedidoDTO.getEstado());
		p.setSucursalOrigen(pedidoDTO.getSucursalOrigen());
		return p;
	}
	
	private Carga CargaToEntity(CargaDTO cargaDTO){
		return new Carga(cargaDTO.getPeso(), cargaDTO.getAncho(), cargaDTO.getAlto(), cargaDTO.getProfundidad(),
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
	
	
	private Sucursal SucursalToEntity (SucursalDTO sucursalDTO){
		return new Sucursal (sucursalDTO.getIdSucursal(),sucursalDTO.getNombre(), DireccionToEntity(sucursalDTO.getUbicacion()), null);
	}

	private Envio EnvioToEntity(EnvioDTO envioDTO){
		 Envio e = new Envio (envioDTO.getIdEnvio(),envioDTO.getFechaSalida(), envioDTO.getFechaLlegada(), envioDTO.isCumpleCondicionesCarga(),
				envioDTO.getEstado(), PedidoToEntity(envioDTO.getPedido()), envioDTO.getPrioridad());
		
		 e.setSucursalOrigen(envioDTO.getSucursalOrigen());
		 return e;
	}
	
	private PlanDeMantenimiento PlanDeMantenimientoToEntity (PlanDeMantenimientoDTO planDeMantenimientoDTO){
		return new PlanDeMantenimiento(planDeMantenimientoDTO.getIdPlanDeMantenimiento(),planDeMantenimientoDTO.getDiasProxControl(),
				planDeMantenimientoDTO.getDiasDemora(), planDeMantenimientoDTO.getKmProxControl());
	}
	
	private Vehiculo VehiculoToEntity(VehiculoDTO vehiculoDTO){

		return new Vehiculo (vehiculoDTO.getIdVehiculo(),vehiculoDTO.getTipo(), vehiculoDTO.getVolumen(), vehiculoDTO.getPeso(),
				vehiculoDTO.getAncho(), vehiculoDTO.getAlto(), vehiculoDTO.getProfundidad(), vehiculoDTO.getTara(),
				vehiculoDTO.getKilometraje(), vehiculoDTO.getEstado(),  vehiculoDTO.isEnGarantia(), vehiculoDTO.isTrabajoEspecifico(), 
				vehiculoDTO.getFechaUltimoControl(), PlanDeMantenimientoToEntity(vehiculoDTO.getPlanDeMantenimiento()));

	}
	
	private Viaje ViajeToEntity(ViajeDTO viajeDTO){
		List<Envio> envios = new ArrayList<Envio>();
		for(EnvioDTO envioDTO: viajeDTO.getEnvios())
			envios.add(EnvioToEntity(envioDTO));
		return new Viaje (viajeDTO.getIdViaje(),envios, viajeDTO.getFechaLlegada(), SucursalToEntity(viajeDTO.getSucursalOrigen()),
				SucursalToEntity(viajeDTO.getSucursalDestino()), viajeDTO.isFinalizado(),VehiculoToEntity(viajeDTO.getVehiculo()));
	}

	private Trayecto TrayectoToEntity(TrayectoDTO trayectoDTO){
		return new Trayecto(trayectoDTO.getIdTrayecto(),SucursalToEntity(trayectoDTO.getSucursalOrigen()), SucursalToEntity(trayectoDTO.getSucursalDestino()),
				trayectoDTO.getTiempo(), trayectoDTO.getKm(), trayectoDTO.getPrecio());	
	}

	
	//Agregue cosas para que trate de enviar los pedidos prioritarios aunque no llegue al 70%
	private List<PedidoDTO> ordenarPedidosPorPrioridad(List<PedidoDTO>pedidos) {
			
		List<PedidoDTO> aux = new ArrayList<PedidoDTO>();
		for(PedidoDTO p:pedidos){
			 
				if(!p.getCargas().get(0).isDespachado()){
					aux.add(p);
				}
		}
		pedidos=new ArrayList<PedidoDTO>();
		pedidos=aux;
		PedidoDTO pedAux=new PedidoDTO();
		 for(int i=0;i<pedidos.size();i++){
	     
			 for(int j=0;j<pedidos.size();j++){
				 if(pedidos.get(j).getFechaMaxima().before(pedidos.get(i).getFechaMaxima())){
					/*   pedAux=pedidos.get(i);
						pedAux2=pedidos.get(j);
	                                  pedidos.remove(j);
	                                  pedidos.remove(i);
	                                 pedidos.add(i,pedAux2);
					 pedidos.add(j,pedAux);
					 */
				 }
			 }
		 }
		 return pedidos;
	}

	public void actualizarMapaDeRutas(TrayectoDTO t) {
		t.setSucursalOrigen(Controlador.getInstance().obtenerSucursal(t.getSucursalOrigen()));
		t.setSucursalDestino(Controlador.getInstance().obtenerSucursal(t.getSucursalDestino()));
		Controlador.getInstance().modificarTrayecto(TrayectoToEntity(t));
		cargarMapaDeRuta();
	}

	public boolean ControlarVehiculo(VehiculoDTO vehiculoDTO) throws RemoteException{
		/*Obtengo la fecha en la que deberian hacerle el mantenimiento al vehiculo*/
		Calendar c = Calendar.getInstance();
		c.setTime(vehiculoDTO.getFechaUltimoControl()); 
		c.add(Calendar.DATE, vehiculoDTO.getPlanDeMantenimiento().getDiasProxControl()); 
		Date fecha = c.getTime();
		
		boolean resp=false;
		if(vehiculoDTO.getEstado().equals("En Deposito")){	
			System.out.println(vehiculoDTO.getKilometraje()+vehiculoDTO.getPlanDeMantenimiento().getKmProxControl());
			
			if(vehiculoDTO.getKilometraje()>=vehiculoDTO.getPlanDeMantenimiento().getKmProxControl()){	
				resp=true;
				if(vehiculoDTO.isEnGarantia()){
					System.out.println("Está en garantia");
					Controlador.getInstance().politicaMantenimiento = new PoliticaGarantia();
					Controlador.getInstance().politicaMantenimiento.mandarAMantenimiento(vehiculoDTO);
				}
				else if(vehiculoDTO.isTrabajoEspecifico()){
					System.out.println("NOOOOOOOOO Está en garantia");
					Controlador.getInstance().politicaMantenimiento = new PoliticaEspecificidad();
					Controlador.getInstance().politicaMantenimiento.mandarAMantenimiento(vehiculoDTO);
				}
				else{
					
					Controlador.getInstance().politicaMantenimiento = new PoliticaGeneral();
					Controlador.getInstance().politicaMantenimiento.mandarAMantenimiento(vehiculoDTO);
				}
				
				vehiculoDTO.getPlanDeMantenimiento().setKmProxControl(vehiculoDTO.getKilometraje()+200);
				vehiculoDTO.getPlanDeMantenimiento().setDiasProxControl(60);
				Controlador.getInstance().modificarVehiculo(VehiculoToEntity(vehiculoDTO));
			}
			/*Me fijo si la fecha calculada al principio esta antes que la de hoy, si lo esta, mando mantenimiento*/
			else if(fecha.before(new Date())) {
				resp=true;
				if(vehiculoDTO.isEnGarantia()) {
					
					Controlador.getInstance().politicaMantenimiento = new PoliticaGarantia();
					Controlador.getInstance().politicaMantenimiento.mandarAMantenimiento(vehiculoDTO);
				}
				else if(vehiculoDTO.isTrabajoEspecifico()){
					
					Controlador.getInstance().politicaMantenimiento = new PoliticaEspecificidad();
					Controlador.getInstance().politicaMantenimiento.mandarAMantenimiento(vehiculoDTO);
				}
				else {
					
					Controlador.getInstance().politicaMantenimiento = new PoliticaGeneral();
					Controlador.getInstance().politicaMantenimiento.mandarAMantenimiento(vehiculoDTO);
				}
				
				vehiculoDTO.getPlanDeMantenimiento().setKmProxControl(vehiculoDTO.getKilometraje()+200);
				vehiculoDTO.getPlanDeMantenimiento().setDiasProxControl(60);
				Controlador.getInstance().modificarVehiculo(VehiculoToEntity(vehiculoDTO));
			}
		}
		return (boolean) resp;
	}

	public boolean buscarDestino(int idDestino) {
		return Controlador.getInstance().buscarDestino(idDestino);
	}

	public PedidoDTO obtenerPedido(int idPedido) {
		return Controlador.getInstance().obtenerPedido(idPedido);
	}

	public List<EnvioDTO> obtenerEnvios() {
		return Controlador.getInstance().obtenerEnvios();
	}

	public List<PedidoDTO> obtenerPedidosEnDeposito() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}