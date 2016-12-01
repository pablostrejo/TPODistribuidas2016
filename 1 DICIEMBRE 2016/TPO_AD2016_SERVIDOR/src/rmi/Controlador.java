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
		Date dat = new Date();
		
		System.out.println("fecha: " + dat);
		
		//Vehiculo vehi = new Vehiculo(8, "Camioneta", 70, 3000, 25, 50, 50, 60, 1000, "En Deposito", false, false, dat, Controlador.getInstance().obtenerPlanDeMantenimiento(4));
		                    
		//vehi.setAlto(10);
		//hbtDAO.altaVehiculo(vehi);
		//*/
	}

	private PlanDeMantenimiento obtenerPlanDeMantenimiento(int i) {
		return hbtDAO.obtenerPlanDeMantenimiento(i);
	}

	public void guardarPedido(Pedido pedido) {
		hbtDAO.guardarPedido(pedido);
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

	public RutaDTO obtenerMejorRuta(SucursalDTO sucursalOrigen, SucursalDTO sucursalDestino) {
		MapaDeRutaDTO mp = mapadeRuta;
		cargarMapaDeRuta();
		float precioMin=999999999;
		RutaDTO ruta=new RutaDTO();
		int km = 99999999;
		 
		for(RutaDTO r: mp.getRutas()){
			if(r.getSucursalOrigen().getIdSucursal()== sucursalOrigen.getIdSucursal() && r.getSucursalDestino().getIdSucursal()== sucursalDestino.getIdSucursal()){
				int kmAux = r.calcularKm();
				if(kmAux<km){
					km=kmAux;
					ruta=r;
				}
				else if(kmAux == km){
					if(r.getPrecio()<precioMin){
						precioMin=r.getPrecio();
						ruta=r;
					}
				}
			}
		}
		return ruta;
	}

	private void cargarMapaDeRuta() {
		List<RutaDTO> rutas = hbtDAO.obtenerRutas();
		mapadeRuta = new MapaDeRutaDTO();
		mapadeRuta.setRutas(rutas);
	}

	public void modificarVehiculo(Vehiculo vehiculo) {
		hbtDAO.modificarVehiculo(vehiculo);
	}
	
	public void modificarTrayecto(Trayecto trayecto) {
		hbtDAO.modificarTrayecto(trayecto);
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

//	private List<Pedido> obtenerPedidosEnDeposito() {
//		return hbtDAO.obtenerPedidosEnDeposito();
//	}

	public List<ViajeDTO> despacharViajes(int sucursal) {
		List<RutaDTO> rutas = hbtDAO.obtenerRutas();
		mapadeRuta = new MapaDeRutaDTO();
		mapadeRuta.setRutas(rutas);
		
		List<ViajeDTO> viajes = new ArrayList<ViajeDTO>();
		List<Vehiculo> vehiculosDisp = new ArrayList<Vehiculo>();
		vehiculosDisp = obtenerVehiculosListos();
		
		if (vehiculosDisp != null) {
			List<Pedido> pedidos = hbtDAO.obtenerPedidosEnDeposito(sucursal);
			List<Pedido> pedidosADespachar = new ArrayList<Pedido>();
			if(pedidos != null){
				for(Pedido p : pedidos){ 
					RutaDTO ruta = obtenerRuta(p, mapadeRuta);
					Date fechaTrayecto = obtenerFechaTrayectoPedido(p, ruta);
					System.out.println("FECHA TRAYECTO" + fechaTrayecto);
					if (fechaTrayecto.after(p.getFechaMaxima())) {
						pedidosADespachar.add(p);
					}
				}
				
				
				
				if (pedidosADespachar != null) {
					for(Pedido p : pedidosADespachar){ 
						// Genero un envios
						generarViaje(viajes, p, vehiculosDisp, mapadeRuta, 0);
					}
				}
				
				pedidos = hbtDAO.obtenerPedidosEnDeposito(sucursal);
				
				for(Pedido p : pedidos){ 
					int vehiculo = llenaAlgunVehiculo(p, vehiculosDisp);
					if (vehiculo > 0) {
						// Genero un envio
						generarViaje(viajes, p, vehiculosDisp, mapadeRuta, vehiculo);
					}
				}
				
			}
		}
		
		return viajes;
	}
	
	// devuelve 
	private int llenaAlgunVehiculo(Pedido p, List<Vehiculo> vehiculosDisp) {
		List<Carga> cargas = p.getCargas();
		float volumenCargas = 0;
		for(Carga c : cargas){
			volumenCargas = volumenCargas + c.getVolumen();
		}
		for(Vehiculo v: vehiculosDisp){
			float vol70 = (v.getVolumen() *70/100);
			if(volumenCargas >= vol70 && volumenCargas <= v.getVolumen()){
				return v.getIdVehiculo();
			}
		}
		return 0;
	}

	private Date obtenerFechaTrayectoPedido(Pedido p, RutaDTO ruta) {
		float tiempo = 0;
		for(TrayectoDTO t : ruta.getTrayectos()){
			tiempo = tiempo + t.getTiempo(); 
			if(t.getSucursalDestino().getIdSucursal() == Integer.parseInt((p.getSucursalDestino()))){
				long m =(long) tiempo;
				long milisegundos = 60000;
				Date auxiliar = Calendar.getInstance().getTime();
				long minutosAux = m * milisegundos;
				Date auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
				return auxiliar2;
			}
		}
		
		return null;
	}

	private RutaDTO obtenerRuta(Pedido p, MapaDeRutaDTO mp) {
		
		int sucOrigen  = Integer.parseInt(p.getSucursalOrigen());
		int sucDestino = Integer.parseInt(p.getSucursalDestino()); 
		
		
		for(RutaDTO r: mp.getRutas()){
			boolean sucOrigenAux = false;
			for(TrayectoDTO t : r.getTrayectos()){
				if(t.getSucursalOrigen().getIdSucursal() == sucOrigen){
					sucOrigenAux = true;
				}
				if((t.getSucursalDestino().getIdSucursal() == sucDestino) && (sucOrigenAux)){
					return r;
				}
			}
		}
		
		return null;
	}

	private void generarViaje(List<ViajeDTO> viajes, Pedido p, List<Vehiculo> vehiculosDisp, MapaDeRutaDTO mp, int idVehiculo) {
		RutaDTO ruta = obtenerRuta(p, mp);
		Date fechaTrayecto = obtenerFechaTrayectoPedido(p, ruta);
		System.out.println("fecha trayecto" + fechaTrayecto);
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
		Vehiculo vehiculo = new Vehiculo();
		if(idVehiculo > 0){
			vehiculo = hbtDAO.obtenerVehiculo(idVehiculo);
		}else{
			vehiculo = obtenerVehiculoVolumen(p, vehiculosDisp);
		}
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
		List<Carga> cargas = p.getCargas();
		float volumenCargas = 0;
		for(Carga c : cargas){
			volumenCargas = volumenCargas + c.getVolumen();
		}
		for(Vehiculo v: vehiculosDisp){
			if(volumenCargas <= v.getVolumen()){
				return hbtDAO.obtenerVehiculo(v.getIdVehiculo());
			}
		}
		return null;
	}

	public List<ViajeDTO> generarViajes() {
		List<ViajeDTO> viajes = new ArrayList<ViajeDTO>();
		List<Vehiculo> vehiculosDisp = new ArrayList<Vehiculo>();
		vehiculosDisp = hbtDAO.obtenerVehiculosListos();
		
		if(vehiculosDisp != null){
			List<Pedido> pedidos = hbtDAO.obtenerPedidosEnDeposito();
		
			if(pedidos != null){
				List<Envio> envios = new ArrayList<Envio>();
				for(Pedido p : pedidos){ 						// por cada pedido genero un envio
					Envio envi = new Envio();
					envi.setSucursalOrigen(p.getSucursalOrigen());
					envi.setEstado("En Deposito");
					envi.setPedido(p);
					envios.add(envi);
				}
				if (vehiculosDisp!= null)
					viajes=armarPedidoConVto(envios, vehiculosDisp);

				if (vehiculosDisp!= null)
					viajes=armarPedidoSinVto(envios, vehiculosDisp);
				
					
			}
			else{
				System.out.println("NO tengo pedidos en deposito :) ");
			}
			
		}
		else{
			System.out.println("NO tengo vehiculos disponibles :( ");
		}
		return viajes;	
	}

	private List<ViajeDTO> armarPedidoConVto(List<Envio> envios, List<Vehiculo> vehiculosDisp) {
		
		
		
		
		return null;
	}

	private float calcularVolumenEnvio(Envio e){
		float volumen = 0;
		List<Carga> cargas = e.getPedido().getCargas();
		for(Carga c : cargas){
			volumen = volumen + c.getVolumen();
		}
		return volumen;
	}
	
	private float calcularVolumenEnviosPendientes(List<Envio> envios){
		float volumen = 0;
		for(Envio e : envios){
			volumen = volumen + calcularVolumenEnvio(e);
		}
		return volumen;
	}
	
	
	private List<ViajeDTO> armarPedidoSinVto(List<Envio> envios, List<Vehiculo> vehiculosDisp){
		float tiempo = 0;
		List<ViajeDTO> viajes = new ArrayList<ViajeDTO>();
		List<Envio> enviosPendientes = new ArrayList<Envio>();
		
		// tengo que recorrer esos envios para ver si sale un viaje o no.
		
		for(Envio e : envios){ // no conviene hacer un for comun? asi puedo mover los indices...
			
			float volumenEnvio = calcularVolumenEnvio(e);
			
			// Elijo Vehiculo de menor a mayor volumen!
			Vehiculo vehi = vehiculosDisp.get(0);
			float setentaVehiculo = (float) (vehi.getVolumen() * (0.7));
			
			// Se genera un viaje si el volumen de las cargas esta entre 70 y 100% del vehiculo
			// y el volumen del envio no supera la capacidad del vehiculo
			
			if((volumenEnvio >= setentaVehiculo) && (volumenEnvio <= vehi.getVolumen())){
				
				Viaje viaj = new Viaje();
						
				vehi.setEstado("En viaje");
				viaj.setVehiculo(vehi);
				
				viaj.setSucursalOrigen(hbtDAO.obtenerSucursalEntity(Integer.valueOf(e.getPedido().getSucursalOrigen()).intValue()));

				RutaDTO ruta = new RutaDTO();
				ruta = 	Controlador.getInstance().obtenerMejorRuta (viaj.getSucursalOrigen().toDTO(),
																	obtenerSucursal(Integer.valueOf(e.getPedido().getSucursalDestino()).intValue()));
				
				viaj.setSucursalDestino(hbtDAO.obtenerSucursalEntity(ruta.getTrayectos().get(0).getSucursalDestino().getIdSucursal()));		
				
				List<Envio> enviosViaje = new ArrayList<Envio>();
				enviosViaje.add(e);
				viaj.setEnvios(enviosViaje);
				
				Date salida = Calendar.getInstance().getTime();
				
				for(TrayectoDTO t:ruta.getTrayectos()){
					tiempo = t.getTiempo()+ tiempo ;
				}
				
				long m = (long) tiempo;
				long milisegundos = 60000;
				Date auxiliar = Calendar.getInstance().getTime();
				long minutosAux = m * milisegundos;
				Date auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
				viaj.setFechaLlegada(auxiliar2);
				
				m =(long) ruta.getTrayectos().get(0).getTiempo();
				milisegundos = 60000;
				auxiliar = Calendar.getInstance().getTime();
				minutosAux = m * milisegundos;
				auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
				viaj.setFinalizado(false);
				e.setFechaLlegada(auxiliar2);
							
				modificarVehiculo(vehi);
				
				Controlador.getInstance().guardarViaje(viaj);

				// agrego la lista de viajes para que lo devuelva y los muestre en la pagina 
				viajes.add(viaj.toDTO());
				
			}else{ // NO llené un vehiculo todavía, voy guardando los envios, hasta llenar un vehiculo y generar un viaje
				
				enviosPendientes.add(e);
				float volumenPendientes = calcularVolumenEnviosPendientes(enviosPendientes);				
				Vehiculo vehiculo = vehiculosDisp.get(0);
				float setenta = (float) (vehiculo.getVolumen() * (0.7));
				
				// Se genera un viaje si el volumen de las cargas esta entre 70 y 100% del vehiculo
				// y el volumen del envio no supera la capacidad del vehiculo
				
				if((volumenPendientes >= setenta) && (volumenPendientes <= vehiculo.getVolumen())){
					
					Viaje viaje = new Viaje();
							
					vehiculo.setEstado("En viaje");
					viaje.setVehiculo(vehi);
					
					viaje.setSucursalOrigen(hbtDAO.obtenerSucursalEntity(Integer.valueOf(e.getPedido().getSucursalOrigen()).intValue()));

					RutaDTO ruta = new RutaDTO();
					ruta = 	Controlador.getInstance().obtenerMejorRuta(
							viaje.getSucursalOrigen().toDTO(),
						    Controlador.getInstance().obtenerSucursal(Integer.valueOf(e.getPedido().getSucursalDestino()).intValue()));
							viaje.setSucursalDestino(hbtDAO.obtenerSucursalEntity(ruta.getTrayectos().get(0).getSucursalDestino().getIdSucursal()));		
					
					List<Envio> enviosViaje = new ArrayList<Envio>();

					for(Envio env : enviosPendientes){
					    enviosViaje.add(env);
					}

					viaje.setEnvios(enviosViaje);
					
					Date salida = Calendar.getInstance().getTime();
					
					for(TrayectoDTO t : ruta.getTrayectos()){
						tiempo = t.getTiempo() + tiempo ;
					}
					
					long m = (long) tiempo;
					long milisegundos = 60000;
					Date auxiliar = Calendar.getInstance().getTime();
					long minutosAux = m * milisegundos;
					Date auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
					viaje.setFechaLlegada(auxiliar2);
					m = (long) ruta.getTrayectos().get(0).getTiempo();
					milisegundos = 60000;
					auxiliar = Calendar.getInstance().getTime();
					minutosAux = m * milisegundos;
					auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
					viaje.setFinalizado(false);
					e.setFechaLlegada(auxiliar2);
					
					modificarVehiculo(vehi);
					Controlador.getInstance().guardarViaje(viaje);

					// agrego la lista de viajes para que lo devuelva y los muestre en la pagina 
					viajes.add(viaje.toDTO());
					enviosPendientes = new ArrayList<Envio>();
				}
			}
		}
	
		return viajes;
	
	}
	
	public List<ViajeDTO> enviar() {
	 return null;	
	}
	
	/*
	public List<ViajeDTO> enviar() {
		List<ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		List<Pedido> pedidos = hbtDAO.obtenerPedidosEnDeposito();
		List<Envio> envios = new ArrayList<Envio>();
		List<Envio> enviosAux = new ArrayList<Envio>();
		// me fijo que pedidos tienen cargas sin despachar...
		pedidos = ordenarPedidosPorPrioridad(pedidos);
		
		//ORDERNAR PEDIDOS por prioridad y si la carga no esta despacha entonces es que no llego al destino final
				Date fechaLlegada = Calendar.getInstance().getTime();
				List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
				ViajeDTO viaje = new ViajeDTO(); ////////////////////////////////////////////////////////
				SucursalDTO sucursalOrigen = new SucursalDTO();
				float carga = 0;
				RutaDTO ruta = new RutaDTO();
				SucursalDTO sucursalDestino = new SucursalDTO();
				MapaDeRutaDTO mp = Controlador.getInstance().mapadeRuta;
				cargarMapaDeRuta();
				boolean nul = false;
				float tiempo = 0;
				
				for(Pedido p : pedidos){
					 nul = false;
					 Envio envio = hbtDAO.obtenerEnvioDePedido(p.getIdPedido());
				if(envio==null){
					envio= new Envio();
					 
					  sucursalOrigen = Controlador.getInstance().obtenerSucursal(Integer.valueOf(p.getSucursalOrigen()).intValue());
					  nul=true;
					  envio.setEstado("listo");
					
				}
				if(!envio.getEstado().equals("despachado")){
					if(envio.getEstado().equals("listo")){
						EnvioDTO envio2=envio;
						if(!nul){
							ViajeDTO viajeAux = Controlador.getInstance().obtenerViajeDeEnvio(envio.getIdEnvio());
							Controlador.getInstance().borrarViaje(ViajeToEntity(viajeAux));
						}
						envio2.setCumpleCondicionesCarga(true);
						envio2.setPedido(p);
						envio2.setFechaSalida(Calendar.getInstance().getTime());
						envio2.setPrioridad(1);
						envio2.setFechaLlegada(fechaLlegada);
						if(!nul)
							sucursalOrigen = Controlador.getInstance().obtenerSucursal(Integer.valueOf(envio2.getSucursalOrigen()).intValue());
						
						envio2.setSucursalOrigen(sucursalOrigen.getNombre());
						envio = envio2;			
					}
				    
				    carga = 0;
					for(CargaDTO c:p.getCargas()){
						carga = c.getVolumen()+carga;	
					}
					vehiculos = Controlador.getInstance().obtenerVehiculosListos();
					if(vehiculos!=null){
						VehiculoDTO v = vehiculos.get(0);
						float volu = (v.getVolumen()* 70)/ 100;
						if(carga > volu && carga < v.getVolumen()){
							v.setEstado("En uso");
							List<EnvioDTO>e=new ArrayList<EnvioDTO>();
							envio.setEstado("despachado");
							envio.setPedido(p);
							envios.add(envio);
							e.add(envio);
							viaje.setEnvios(e);
							viaje.setSucursalOrigen(sucursalOrigen);
					 
							sucursalDestino =  Controlador.getInstance().obtenerSucursal(Integer.valueOf(p.getSucursalDestino()).intValue());
							ruta = Controlador.getInstance().obtenerMejorRuta(sucursalOrigen, sucursalDestino);
							viaje.setSucursalDestino(ruta.getTrayectos().get(0).getSucursalDestino());		
							viaje.setVehiculo(v);
							Date llegada=Calendar.getInstance().getTime();
					 
							for(TrayectoDTO t:ruta.getTrayectos()){
								tiempo=t.getTiempo()+tiempo;
							}
							long m=(long) tiempo;
							long milisegundos=60000;
							Date auxiliar= Calendar.getInstance().getTime();
							long minutosAux=m*milisegundos;
							Date auxiliar2=new Date(auxiliar.getTime() + minutosAux);
							viaje.setFechaLlegada(auxiliar2);
							m=(long) ruta.getTrayectos().get(0).getTiempo();
							milisegundos=60000;
							auxiliar= Calendar.getInstance().getTime();
							minutosAux=m*milisegundos;
							auxiliar2=new Date(auxiliar.getTime() + minutosAux);
							viaje.setFinalizado(false);
							envio.setFechaLlegada(auxiliar2);
							v.setEstado("En uso");
					
					
							Controlador.getInstance().modificarVehiculo(VehiculoToEntity(v));
//							
//				 	
//							hbtDAO.modificar(EnvioToEntity(envio));
//							else
//							
							//hbtDAO.guardar(EnvioToEntity(envio));
							
							Controlador.getInstance().guardarViaje(ViajeToEntity(viaje));
							
							// carga el viaje en el array list que devuelve 
							viajesDTO.add(viaje);
							
					}else{
						envio.setPedido(p);
						enviosAux.add(envio);
					 }
				    }
				}
				}
				if(enviosAux.size()!=0){
					
					List<EnvioDTO> en = new ArrayList<EnvioDTO>();
					float volu, cargaAux;
					volu = 0;
					cargaAux = 0;
					vehiculos = obtenerVehiculos();
					List<SucursalDTO> sucursales = Controlador.getInstance().obtenerSucursales();
					for(SucursalDTO sOrigen:sucursales){
						for(SucursalDTO s:sucursales){
							for(VehiculoDTO v:vehiculos){
								if(!v.getEstado().equals("En Deposito") || !v.getEstado().equals("En Mantenimiento")){
									carga=0;
									volu = (v.getVolumen()*70)/100;
									en=new ArrayList<EnvioDTO>();
									for(EnvioDTO e:enviosAux){
										if(e.getPedido().getCargas().get(0).getTipoMercaderia()==v.getTipo()){
											cargaAux=0;
											if(Controlador.getInstance().obtenerSucursal(Integer.valueOf(e.getSucursalOrigen()).intValue())==sOrigen && proxDestino(e.getPedido())==s){
												for(CargaDTO c:e.getPedido().getCargas()){
												cargaAux=carga+c.getVolumen();
												if(cargaAux>volu && cargaAux<v.getVolumen()){
													carga=c.getVolumen()+carga;	
													en.add(e);
												}
												}
											}
										}
									}
									if(carga>volu && carga<v.getVolumen()){
										sucursalOrigen=sOrigen;
										viaje.setEnvios(en);
										viaje.setSucursalOrigen(sucursalOrigen);
										viaje.setSucursalDestino(s);	
										viaje.setVehiculo(v);
										Date llegada=Calendar.getInstance().getTime();
										 
										for(TrayectoDTO t:ruta.getTrayectos()){
											tiempo=t.getTiempo()+tiempo;
										}
										long m=(long) tiempo;
										long milisegundos=60000;
										Date auxiliar= Calendar.getInstance().getTime();
										long minutosAux=m*milisegundos;
										Date auxiliar2=new Date(auxiliar.getTime() + minutosAux);
										viaje.setFechaLlegada(auxiliar2);
										viaje.setFinalizado(false);
										
										for(EnvioDTO e:en){
											m=(long) ruta.getTrayectos().get(0).getTiempo();
											milisegundos=60000;
											auxiliar= Calendar.getInstance().getTime();
											minutosAux=m*milisegundos;
											auxiliar2=new Date(auxiliar.getTime() + minutosAux);
							 
											e.setFechaLlegada(auxiliar2);
											e.setEstado("despachado");
							
											//hbtDAO.guardar(EnvioToEntity(e));
										}
										v.setEstado("En uso");
										viaje.setEnvios(en);
										Controlador.getInstance().modificarVehiculo(VehiculoToEntity(v));
										Controlador.getInstance().guardarViaje(ViajeToEntity(viaje));
										viajesDTO.add(viaje);
									}
								}
							}
						}
						System.out.println("s"+sOrigen.getIdSucursal());
					}
				}
				System.out.println("SALI");
				return viajesDTO;
			
	}

	*/
	
	private Viaje ViajeToEntity(ViajeDTO viajeDTO){
		List<Envio> envios = new ArrayList<Envio>();
		for(EnvioDTO envioDTO: viajeDTO.getEnvios())
			envios.add(EnvioToEntity(envioDTO));
		return new Viaje (viajeDTO.getIdViaje(),envios, viajeDTO.getFechaLlegada(), SucursalToEntity(viajeDTO.getSucursalOrigen()),
				SucursalToEntity(viajeDTO.getSucursalDestino()), viajeDTO.isFinalizado(),VehiculoToEntity(viajeDTO.getVehiculo()));
	}
	private Vehiculo VehiculoToEntity(VehiculoDTO vehiculoDTO){

		return new Vehiculo (vehiculoDTO.getIdVehiculo(),vehiculoDTO.getTipo(), vehiculoDTO.getVolumen(), vehiculoDTO.getPeso(),
				vehiculoDTO.getAncho(), vehiculoDTO.getAlto(), vehiculoDTO.getProfundidad(), vehiculoDTO.getTara(),
				vehiculoDTO.getKilometraje(), vehiculoDTO.getEstado(),  vehiculoDTO.isEnGarantia(), vehiculoDTO.isTrabajoEspecifico(), 
				vehiculoDTO.getFechaUltimoControl(), PlanDeMantenimientoToEntity(vehiculoDTO.getPlanDeMantenimiento()));

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
	private SucursalDTO proxDestino(PedidoDTO pedido) {
		MapaDeRutaDTO mp = Controlador.getInstance().mapadeRuta;
		cargarMapaDeRuta();
		SucursalDTO s = Controlador.getInstance().obtenerSucursal(Integer.valueOf(pedido.getSucursalOrigen()).intValue());
		SucursalDTO sd = Controlador.getInstance().obtenerSucursal(Integer.valueOf(pedido.getSucursalDestino()).intValue());
		RutaDTO r = Controlador.getInstance().obtenerMejorRuta(s, sd);
		return r.getTrayectos().get(0).getSucursalDestino();
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
	private Envio EnvioToEntity(EnvioDTO envioDTO){
		 Envio e = new Envio (envioDTO.getIdEnvio(),envioDTO.getFechaSalida(), envioDTO.getFechaLlegada(), envioDTO.isCumpleCondicionesCarga(),
				envioDTO.getEstado(), PedidoToEntity(envioDTO.getPedido()), envioDTO.getPrioridad());
		
		 e.setSucursalOrigen(envioDTO.getSucursalOrigen());
		 return e;
	}
	private List<Pedido> ordenarPedidosPorPrioridad(List<Pedido>pedidos) {
		
		List<Pedido> aux = new ArrayList<Pedido>();
		for(Pedido p:pedidos){
			 
				if(!p.getCargas().get(0).isDespachado()){
					aux.add(p);
				}
		}
		pedidos=new ArrayList<Pedido>();
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
	/*public ViajeDTO obtenerViajeDeEnvio(ViajeDTO viajeDTO) {
		return hbtDAO.obtenerViajeDeEnvio(viajeDTO);
	}*/
}
