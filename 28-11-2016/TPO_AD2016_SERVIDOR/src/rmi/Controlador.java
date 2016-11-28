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
		/*Date dat = new Date(2016-11-20);
		
		Vehiculo vehi = new Vehiculo(8, "Camioneta", 70, 3000, 25, 50, 50, 60, 1000, "En Deposito", false, false, dat, Controlador.getInstance().obtenerPlanDeMantenimiento(4));
		                    
		vehi.setAlto(10);
		hbtDAO.altaVehiculo(vehi);
		*/
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

	public EnvioDTO obtenerEnvioDePedido(int idPedido) {
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

	public List<VehiculoDTO> obtenerVehiculosListos() {
		List<VehiculoDTO> vehDTO = new ArrayList<VehiculoDTO>();
		List<Vehiculo> vehEnt = hbtDAO.obtenerVehiculosListos();
		for(Vehiculo v: vehEnt){
			vehDTO.add(v.toDTO());
		}
		return vehDTO;
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

	public List<ViajeDTO> generarViajes() {
		List<ViajeDTO> viajes = new ArrayList<ViajeDTO>();
		List<Vehiculo> vehiculosDisp = new ArrayList<Vehiculo>();
		float tiempo = 0;
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
				
				// tengo que recorrer esos envios para ver si sale un viaje o no.
				for(Envio e : envios){ // no conviene hacer un for comun? asi puedo mover los indices...
					int volumenEnvio = 0;
					List<Carga> cargasP = e.getPedido().getCargas();
					for( Carga c : cargasP){
						volumenEnvio += c.getVolumen();
					}
					// Elijo Vehiculo de menor a mayor volumen !
					Vehiculo vehi = vehiculosDisp.get(0);
					float setentaVehiculo = (vehi.getVolumen()* 70)/ 100;
					
					// Se genera un viaje si el volumen de las cargas esta entre 70 y 100% del vehiculo
					// y el volumen del envio no supera la capacidad del vehiculo
					if((volumenEnvio >= setentaVehiculo) && (volumenEnvio <= vehi.getVolumen())){
						
						Viaje viaj = new Viaje();
						vehi.setEstado("Utilizado");
						viaj.setVehiculo(vehi);
						
						viaj.setSucursalOrigen(hbtDAO.obtenerSucursalEntity(Integer.valueOf(e.getPedido().getSucursalOrigen()).intValue()));

						RutaDTO ruta = new RutaDTO();
						ruta = Controlador.getInstance().obtenerMejorRuta(
								viaj.getSucursalOrigen().toDTO(),
							    Controlador.getInstance().obtenerSucursal(Integer.valueOf(e.getPedido().getSucursalDestino()).intValue()));
						//viaj.setSucursalDestino(Controlador.getInstance().obtenerSucursal(e.getPedido().getSucursalDestino()));
						viaj.setSucursalDestino(hbtDAO.obtenerSucursalEntity(ruta.getTrayectos().get(0).getSucursalDestino().getIdSucursal()));		
						
						List<Envio> enviosViaje = new ArrayList<Envio>();
						enviosViaje.add(e);
						viaj.setEnvios(enviosViaje);
						
						Date salida = Calendar.getInstance().getTime();
						
						for(TrayectoDTO t:ruta.getTrayectos()){
							tiempo = t.getTiempo()+ tiempo ;
						}
						long m =(long) tiempo;
						long milisegundos = 60000;
						Date auxiliar = Calendar.getInstance().getTime();
						long minutosAux = m * milisegundos;
						Date auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
						viaj.setFechaLlegada(auxiliar2);
						m=(long) ruta.getTrayectos().get(0).getTiempo();
						milisegundos = 60000;
						auxiliar = Calendar.getInstance().getTime();
						minutosAux = m * milisegundos;
						auxiliar2 = new Date(auxiliar.getTime() + minutosAux);
						viaj.setFinalizado(false);
						e.setFechaLlegada(auxiliar2);
						
						
						Controlador.getInstance().modificarVehiculo(vehi);
						
						Controlador.getInstance().guardarViaje(viaj);

						// agrego la lista de viajes para que lo devuelva y los muestre en la pagina 
						viajes.add(viaj.toDTO());
					}
					else{ // NO llené un vehiculo todavía, voy guardando los envios, hasta llenar un vehiculo y generar un viaje
						
					}
						
					
				}
					
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
				pedidoDTO.isSolicitaAvionetaParticular(), ClienteToEntity(pedidoDTO.getCliente()));
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
