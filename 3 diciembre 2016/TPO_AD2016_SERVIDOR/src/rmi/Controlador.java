package rmi;

import hbt.HibernateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Strategy.PoliticaMantenimiento;
import dao.HibernateDAO;
import dto.CargaDTO;
import dto.ClienteDTO;
import dto.DireccionDTO;
import dto.EnvioDTO;
import dto.MapaDeRutaDTO;
import dto.PedidoDTO;
import dto.PlanDeMantenimientoDTO;
import dto.RutaDTO;
import dto.SucursalDTO;
import dto.TrayectoDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;
import entities.Carga;
import entities.Cliente;
import entities.Direccion;
import entities.Envio;
import entities.Pedido;
import entities.PlanDeMantenimiento;
import entities.Ruta;
import entities.Sucursal;
import entities.Trayecto;
import entities.Vehiculo;
import entities.Viaje;

public class Controlador {

	private static Controlador instance = null;
	private static HibernateDAO hbtDAO;
	public MapaDeRutaDTO mapadeRuta;
	PoliticaMantenimiento politicaMantenimiento;

	private Controlador() {
		hbtDAO = HibernateDAO.getInstancia();
	}

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		HibernateUtil test = new HibernateUtil();
				
		//Vehiculo vehi = new Vehiculo(8, "Camioneta", 70, 3000, 25, 50, 50, 60, 1000, "En Deposito", false, 
		//false, dat, Controlador.getInstance().obtenerPlanDeMantenimiento(4), 2);
		                    
		//vehi.setAlto(10);
		//hbtDAO.altaVehiculo(vehi);
		//*/
	}

	public void guardarPedido(PedidoDTO pedido) {
		Pedido pedi = PedidoToEntity(pedido);
		hbtDAO.guardarPedido(pedi);
	}

	public List<SucursalDTO> obtenerSucursales() {
		return hbtDAO.obtenerSucursales();
	}

	public List<ClienteDTO> obtenerClientes() {
		return hbtDAO.obtenerClientes();
	}

	public ClienteDTO obtenerClientePorID(int id) {
		return hbtDAO.obtenerClientePorID(id);
	}

	public List<PedidoDTO> obtenerPedidos() {
		return hbtDAO.obtenerPedidos();
	}

	public List<RutaDTO> obtenerRutas() {
		return hbtDAO.obtenerRutas();
	}

	public Envio obtenerEnvioDePedido(int idPedido) {
		return hbtDAO.obtenerEnvioDePedido(idPedido);
	}

	public SucursalDTO obtenerSucursal(int sucursalOrigen) {
		return hbtDAO.obtenerSucursal(sucursalOrigen);
	}
	
	public SucursalDTO obtenerSucursal(SucursalDTO sucursalOrigen) {
		return hbtDAO.obtenerSucursal(sucursalOrigen);
	}

	public ViajeDTO obtenerViajeDeEnvio(int idEnvio) {
		return hbtDAO.obtenerViajeDeEnvio(idEnvio);
	}

	public void borrarViaje(Viaje viaje) {
		hbtDAO.borrarViaje(viaje);
		
	}

	public List<Vehiculo> obtenerVehiculosListos() {
		return hbtDAO.obtenerVehiculosListos();

	}

	public void modificarVehiculo(VehiculoDTO vehiculo) {
		Vehiculo vehiculoEntity = VehiculoToEntity(vehiculo);
		hbtDAO.modificarVehiculo(vehiculoEntity);
	}
	
	public void modificarVehiculo(Vehiculo vehiculo) {
		hbtDAO.modificarVehiculo(vehiculo);
	}
	
	public void modificarTrayecto(TrayectoDTO trayecto) {
		Trayecto trayecEnt = TrayectoToEntity(trayecto);
		hbtDAO.modificarTrayecto(trayecEnt);
	}

	public void guardarViaje(Viaje viaje) {
		hbtDAO.guardarViaje(viaje);
	}

	public List<ViajeDTO> obtenerViajes() {
		List<ViajeDTO> viajes = hbtDAO.obtenerViajes();
		return viajes;
	}

	public ViajeDTO obtenerViajeDeEnvio(ViajeDTO viajeDTO) {
		return hbtDAO.obtenerViajeDeEnvio(viajeDTO);
	}

	public VehiculoDTO obtenerVehiculo(VehiculoDTO v) {
		return hbtDAO.obtenerVehiculo(v);
	}

	public boolean buscarDestino(int idDestino) {
		return hbtDAO.buscarDestino(idDestino);
	}

	public PedidoDTO obtenerPedido(int idPedido) {
		return hbtDAO.obtenerPedido(idPedido);
	}

	public List<EnvioDTO> obtenerEnvios() {
		return hbtDAO.obtenerEnvios();
	}

	public List<VehiculoDTO> obtenerVehiculos() {
		return hbtDAO.obtenerVehiculos();
	}
	
	public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
		return hbtDAO.obtenerPedidosPorCliente(idCliente);
	}

	public List<ViajeDTO> despacharViajes(int sucursal) {
		List<RutaDTO> rutas = hbtDAO.obtenerRutas();
		mapadeRuta = new MapaDeRutaDTO();
		mapadeRuta.setRutas(rutas);
		
		List<ViajeDTO> viajes = new ArrayList<ViajeDTO>();
		List<Vehiculo> vehiculosDisp = new ArrayList<Vehiculo>();
		vehiculosDisp = obtenerVehiculosListos();
		
		if (vehiculosDisp.size() > 0) {
			List<Pedido> pedidos = hbtDAO.obtenerPedidosEnDeposito(sucursal);
			List<Pedido> pedidosADespachar = new ArrayList<Pedido>();
			if(pedidos.size() > 0){
				for(Pedido p : pedidos){ 
					Ruta ruta = obtenerUnaRuta(p, mapadeRuta);
					Date fechaTrayecto = obtenerFechaTrayectoPedido(p, ruta);
					if (fechaTrayecto.after(p.getFechaMaxima())) {
						float precio = p.calcularPrecioPedido(ruta);
						p.setPrecio(precio);
						pedidosADespachar.add(p);
					}
				}
				
				
				if (pedidosADespachar.size() > 0) {
					for(Pedido p : pedidosADespachar){ 
						// Genero un envio
						generarViaje(viajes, p, vehiculosDisp, mapadeRuta);
					}
				}
				
				// -----------------------------------------------------------
				// -----------------------------------------------------------
				// Me fijo si puedo llenar vehiculos con los pedidos restantes
				llenarVehiculosParaEnvio(sucursal, viajes, vehiculosDisp, mapadeRuta);
				// -----------------------------------------------------------
				// -----------------------------------------------------------
			}
		}
		
		return viajes;
	}
	
	
	
	
	
	
	// -----------------------------------------------------------
	// -----------------------------------------------------------
	private void llenarVehiculosParaEnvio(int sucursal, List<ViajeDTO> viajes, List<Vehiculo> vehiculosDisp, MapaDeRutaDTO mp) {
		
		if (vehiculosDisp.size() >0) {
			for (Vehiculo v : vehiculosDisp) {
				List<Pedido> ped = new ArrayList<Pedido>();
				Ruta r = llenarVehiculo(sucursal, mp, v, ped);
				
				if (r != null) {
					generarViajeMultiplePedidos(viajes, ped, r, v.getIdVehiculo());
				}
			}
		}
		
	}
	
	private Ruta llenarVehiculo(int sucursal, MapaDeRutaDTO mp, Vehiculo v, List<Pedido> pedidosReturn) {
		List<Pedido> pedidos = hbtDAO.obtenerPedidosEnDeposito(sucursal);
		//List<Pedido> pedidosReturn = new ArrayList<Pedido>();
		Ruta r = null;
		float volumneCargasAcum = 0;
		float volumneCargasAux = 0;
		
		float vol70 = v.getVol70();
		boolean primera = true;
		
		if (pedidos != null) {
			for (Pedido p : pedidos) {
				
				boolean cont = false;
				
				if (primera) {
					r = obtenerUnaRuta(p, mp);
					primera = false;
					cont = true;
				} else {
					if (vaHaciaLaRuta(p, r)) {
						cont = true;
					} else {
						cont = false;
					}
				}
				
				if (cont) {
					volumneCargasAux = volumneCargasAcum;
					
					float volumenCargas = p.getVolumenCargas();
					
					volumneCargasAcum = volumneCargasAcum + volumenCargas;
					
					if (volumneCargasAcum <= v.getVolumen()) {
						pedidosReturn.add(p);
					} else {
						volumneCargasAcum = volumneCargasAux;
					}
				}
		
			}
		}
		
		if (volumneCargasAcum >= vol70 && volumneCargasAcum <= v.getVolumen()) {
			return r;
		} else {
			return null;
		}
		
	}
	
	private Ruta obtenerUnaRuta(Pedido p, MapaDeRutaDTO mp) {
		
		for(RutaDTO r: mp.getRutas()){
			// CONVERTIR LA RUTA A ENTITY
			if (vaHaciaLaRuta(p, RutaToEntity(r))) {
				return RutaToEntity(r);
			}
		}
		
		return null;
	}
	
	private boolean vaHaciaLaRuta (Pedido p, Ruta r) {
		return r.estaIncluidoEnLaRuta(Integer.parseInt(p.getSucursalOrigen()), Integer.parseInt(p.getSucursalDestino()));
	}
	
	private Date obtenerFechaTrayectoPedido(Pedido p, Ruta ruta) {
		return ruta.obtenerFecha(Integer.parseInt(p.getSucursalDestino()));
	}

	private void generarViajeMultiplePedidos(List<ViajeDTO> viajes, List<Pedido> pedidos, Ruta ruta, int idVehiculo) {
		
		Viaje viaje = new Viaje();
		List<Envio> envios = new ArrayList<Envio>();
		
		SucursalDTO sucursalOrigen = null;
		SucursalDTO sucursalDestino = null;
		Date fechaTrayecto = null;
		
		for(Pedido p : pedidos) {
			if (fechaTrayecto == null) {
				fechaTrayecto = obtenerFechaTrayectoPedido(p, ruta);
			} else {
				Date proFecha = obtenerFechaTrayectoPedido(p, ruta);
				if (proFecha.after(fechaTrayecto)) {
					fechaTrayecto = proFecha;
				}
			}
			
			Date hoy = Calendar.getInstance().getTime();
			
			p.setEstado("En viaje");
			
			Envio envio = new Envio();
			envio.setSucursalOrigen(p.getSucursalOrigen());
			envio.setPedido(p);
			envio.setFechaSalida(hoy);
			envio.setFechaLlegada(fechaTrayecto);
			envio.setEstado("En viaje");
			
			envios.add(envio);
			
			sucursalOrigen = hbtDAO.obtenerSucursal(Integer.valueOf(p.getSucursalOrigen()).intValue());
			sucursalDestino = hbtDAO.obtenerSucursal(Integer.valueOf(p.getSucursalDestino()).intValue());
		}
		

		Vehiculo vehiculo = hbtDAO.obtenerVehiculo(idVehiculo);

		vehiculo.setEstado("En viaje");
		

		Sucursal sucOrigen = SucursalToEntity(sucursalOrigen);
		Sucursal sucDestino = SucursalToEntity(sucursalDestino);

		viaje.setVehiculo(vehiculo);
		viaje.setSucursalOrigen(sucOrigen);
		viaje.setSucursalDestino(sucDestino);
		viaje.setFechaLlegada(fechaTrayecto);
		viaje.setEnvios(envios);
		
		modificarVehiculo(vehiculo);
		
		guardarViaje(viaje);
		
		viajes.add(viaje.toDTO());
		
	}
	
	private void generarViaje(List<ViajeDTO> viajes, Pedido p, List<Vehiculo> vehiculosDisp, MapaDeRutaDTO mp) {
		Ruta ruta = obtenerUnaRuta(p, mp);
		Date fechaTrayecto = obtenerFechaTrayectoPedido(p, ruta);
		Date hoy = Calendar.getInstance().getTime();
		
		List<Envio> envios = new ArrayList<Envio>();
		
		p.setEstado("En viaje");
		
		Envio envio = new Envio();
		envio.setSucursalOrigen(p.getSucursalOrigen());
		envio.setPedido(p);
		envio.setFechaSalida(hoy);
		envio.setFechaLlegada(fechaTrayecto);
		envio.setEstado("En viaje");
		
		envios.add(envio);
		Vehiculo vehiculo = obtenerVehiculoVolumen(p, vehiculosDisp);

		vehiculo.setEstado("En viaje");
		
		SucursalDTO sucursalOrigen = hbtDAO.obtenerSucursal(Integer.valueOf(p.getSucursalOrigen()).intValue());
		SucursalDTO sucursalDestino = hbtDAO.obtenerSucursal(Integer.valueOf(p.getSucursalDestino()).intValue());
		
		Sucursal sucOrigen = SucursalToEntity(sucursalOrigen);
		Sucursal sucDestino = SucursalToEntity(sucursalDestino);
		
		Viaje viaje = new Viaje();
		viaje.setVehiculo(vehiculo);
		viaje.setSucursalOrigen(sucOrigen);
		viaje.setSucursalDestino(sucDestino);
		viaje.setFechaLlegada(fechaTrayecto);
		viaje.setEnvios(envios);
		
		modificarVehiculo(vehiculo);
		
		guardarViaje(viaje);
		
		viajes.add(viaje.toDTO());
		
		for(Vehiculo v: vehiculosDisp){
			if(vehiculo.getIdVehiculo() == v.getIdVehiculo()){
				vehiculosDisp.remove(v);
			}
		}
		
	}
	
	private Vehiculo obtenerVehiculoVolumen(Pedido p, List<Vehiculo> vehiculosDisp) {
		
		float volumenCargas = p.getVolumenCargas();
		
		for(Vehiculo v: vehiculosDisp){
			if(v.podesLlevarEstasCargas(volumenCargas)){
				return hbtDAO.obtenerVehiculo(v.getIdVehiculo());
			}
		}
		
		return null;
	}

	
	
	
	
	// ----------------TO ENTITY ------------------ //
	// -------------------------------------------- //
	
	private Ruta RutaToEntity(RutaDTO rutaDTO){
		List<Trayecto> trayectos = new ArrayList<Trayecto>();
		for(TrayectoDTO trayectoDTO: rutaDTO.getTrayectos())
		trayectos.add(TrayectoToEntity(trayectoDTO));
		return new Ruta (rutaDTO.getIdRuta(), trayectos, rutaDTO.getPrecio(), SucursalToEntity(rutaDTO.getSucursalOrigen()), 
		SucursalToEntity(rutaDTO.getSucursalDestino())); 
		}
		private Trayecto TrayectoToEntity(TrayectoDTO trayectoDTO) {
		return new Trayecto(trayectoDTO.getIdTrayecto(), SucursalToEntity(trayectoDTO.getSucursalOrigen()), 
		SucursalToEntity(trayectoDTO.getSucursalDestino()), trayectoDTO.getTiempo(), 
		trayectoDTO.getKm(), trayectoDTO.getPrecio());
	}

	private Vehiculo VehiculoToEntity(VehiculoDTO vehiculoDTO){
		return new Vehiculo (vehiculoDTO.getIdVehiculo(),vehiculoDTO.getTipo(), vehiculoDTO.getVolumen(), vehiculoDTO.getPeso(),
				vehiculoDTO.getAncho(), vehiculoDTO.getAlto(), vehiculoDTO.getProfundidad(), vehiculoDTO.getTara(),
				vehiculoDTO.getKilometraje(), vehiculoDTO.getEstado(),  vehiculoDTO.isEnGarantia(), vehiculoDTO.isTrabajoEspecifico(), 
				vehiculoDTO.getFechaUltimoControl(), PlanDeMantenimientoToEntity(vehiculoDTO.getPlanDeMantenimiento()), SucursalToEntity(obtenerSucursal(Integer.parseInt(vehiculoDTO.getSucursal()))));
	}
	private PlanDeMantenimiento PlanDeMantenimientoToEntity (PlanDeMantenimientoDTO planDeMantenimientoDTO){
		return new PlanDeMantenimiento(planDeMantenimientoDTO.getIdPlanDeMantenimiento(),planDeMantenimientoDTO.getDiasProxControl(),
				planDeMantenimientoDTO.getDiasDemora(), planDeMantenimientoDTO.getKmProxControl());
	}
	private Sucursal SucursalToEntity (SucursalDTO sucursalDTO){
		return new Sucursal (sucursalDTO.getIdSucursal(),sucursalDTO.getNombre(), DireccionToEntity(sucursalDTO.getUbicacion()), null);
	}

	private Direccion DireccionToEntity (DireccionDTO direccionDTO){
		return new Direccion(direccionDTO.getIdDireccion(),direccionDTO.getCalle(), direccionDTO.getNumero(), direccionDTO.getPiso(), 
								direccionDTO.getDepartamento(), direccionDTO.getCP());
	}
	private Carga CargaToEntity(CargaDTO cargaDTO){
		return new Carga(cargaDTO.getPeso(), cargaDTO.getAncho(), cargaDTO.getAlto(), cargaDTO.getProfundidad(),
				cargaDTO.getVolumen(), cargaDTO.getFragilidad(), cargaDTO.getTratamiento(), cargaDTO.getApilable(),
				cargaDTO.isRefrigerable(), cargaDTO.getCondiciones(), cargaDTO.isDespachado(),
				cargaDTO.getTipoMercaderia());
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
	private Cliente ClienteToEntity (ClienteDTO clienteDTO){
		return new Cliente(clienteDTO.getIdCliente(),clienteDTO.getNombre());
	}

}
