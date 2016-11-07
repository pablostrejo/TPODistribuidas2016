package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import dto.*;
import entities.*;

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
	
	public ClienteDTO obtenerClientePorID(int id) {
		return Controlador.getInstance().obtenerClientePorID(id);
	}
	
	public List<SucursalDTO> obtenerSucursales() throws RemoteException {
		return Controlador.getInstance().obtenerSucursales();
	}
	
	public List<ClienteDTO> obtenerClientes() {
		return Controlador.getInstance().obtenerClientes();
	}
	
	public void enviar() {
	
		List<PedidoDTO> pedidos = Controlador.getInstance().obtenerPedidos();
		List<EnvioDTO> envios=new ArrayList<EnvioDTO>();
		List<EnvioDTO> enviosAux=new ArrayList<EnvioDTO>();
		// me fijo que pedidos tienen cargas sin despachar...
		pedidos=ordenarPedidosPorPrioridad(pedidos);
		
		//ORDERNAR PEDIDOS por prioridad y si la carga no esta despacha entonces es que no llego al destino final
				Date fechaLlegada = Calendar.getInstance().getTime();
				List<VehiculoDTO> vehiculos = new ArrayList<VehiculoDTO>();
				ViajeDTO viaje = new ViajeDTO();
				SucursalDTO sucursalOrigen = new SucursalDTO();
				float carga=0;
				RutaDTO ruta = new RutaDTO();
				SucursalDTO sucursal = new SucursalDTO();
				MapaDeRutaDTO mp = Controlador.getInstance().mapadeRuta;
				cargarMapaDeRuta();
				boolean nul=false;
				float tiempo=0;
				
				for(PedidoDTO p:pedidos){
					 nul=false;
					 EnvioDTO envio = Controlador.getInstance().obtenerEnvioDePedido(p.getIdPedido());
				if(envio==null){
					envio=new EnvioDTO();
					 
					  sucursalOrigen = Controlador.getInstance().obtenerSucursal(p.getSucursalOrigen());
					  nul=true;
					  envio.setEstado("listo");
					
				}
				 if(!envio.getEstado().equals("despachado"))
				 {
		         if(envio.getEstado().equals("listo")){
		        	 EnvioDTO envio2=envio;
		        	 if(!nul){
		        		 ViajeDTO viajeAux = Controlador.getInstance().obtenerViajeDeEnvio(envio.getIdEnvio());
		        		 hbtDAO.borrar(ViajeToEntity(viajeAux));
		        	 }
					envio2.setCumpleCondicionesCarga(true);
					envio2.setPedido(p);
					envio2.setFechaSalida(Calendar.getInstance().getTime());
					envio2.setPrioridad(1);
					envio2.setFechaLlegada(fechaLlegada);
					 if(!nul)
						sucursalOrigen=obtenerSucursal(envio2.getSucursalOrigen());
						
					envio2.setSucursalOrigen(sucursalOrigen.getNombre());
					envio=envio2;			
				}
				    
				    carga=0;
					for(CargaDTO c:p.getCargas()){
						carga=c.getVolumen()+carga;	
					}
					vehiculos = obtenerVehiculosListos();
					if(vehiculos!=null){
						VehiculoDTO v=vehiculos.get(0);
						float volu=(v.getVolumen()*70)/100;
						if(carga>volu && carga<v.getVolumen()){
							v.setEstado("En uso");
							List<EnvioDTO>e=new ArrayList<EnvioDTO>();
							envio.setEstado("despachado");
							envio.setPedido(p);
							envios.add(envio);
							e.add(envio);
							viaje.setEnvios(e);
							viaje.setSucursalOrigen(sucursalOrigen);
					 
							sucursal =  obtenerSucursal(p.getSucursalDestino());
							ruta = obtenerMejorRuta(sucursalOrigen,sucursal);
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
					
					
					hbtDAO.modificar(VehiculoToEntity(v));
					///////////
				 
						hbtDAO.modificar(EnvioToEntity(envio));
						else
							//////////
					//hbtDAO.guardar(EnvioToEntity(envio));
					hbtDAO.guardar(ViajeToEntity(viaje));
				
					}
					
					else{
						 
						envio.setPedido(p);
						enviosAux.add(envio);
						
					}
					
				    }
				 }
				}
				if(enviosAux.size()!=0){
					
					List<EnvioDTO>en=new ArrayList<EnvioDTO>();
					float volu,cargaAux;
					volu=cargaAux=0;
					vehiculos=obtenerVehiculos();
					List<SucursalDTO> sucursales = hbtDAO.obtenerSucursales();
					for(SucursalDTO sOrigen:sucursales){
					for(SucursalDTO s:sucursales){
					for(VehiculoDTO v:vehiculos){
						if(!v.getEstado().equals("En Deposito") || !v.getEstado().equals("En Mantenimiento")){
							carga=0;
						  volu=(v.getVolumen()*70)/100;
						  en=new ArrayList<EnvioDTO>();
						for(EnvioDTO e:enviosAux){
							if(e.getPedido().getCargas().get(0).getTipoMercaderia()==v.getTipo()){
							cargaAux=0;
							
							if(obtenerSucursal(e.getSucursalOrigen())==sOrigen && proxDestino(e.getPedido())==s){
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
						 
						for(TrayectoDTO t:ruta.getTrayectos())
						{
							tiempo=t.getTiempo()+tiempo;
						}
						long m=(long) tiempo;
						long milisegundos=60000;
						Date auxiliar= Calendar.getInstance().getTime();
						long minutosAux=m*milisegundos;
						Date auxiliar2=new Date(auxiliar.getTime() + minutosAux);
						viaje.setFechaLlegada(auxiliar2);
						viaje.setFinalizado(false);
						
						for(EnvioDTO e:en)
						{
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
						hbtDAO.modificar(VehiculoToEntity(v));
						hbtDAO.guardar(ViajeToEntity(viaje));
						}
						}
					}
					
					}
					System.out.println("s"+sOrigen.getIdSucursal());
					}
					
				}
				System.out.println("SALI");
			}		
	}

	public void cargarMapaDeRuta(){
		List<RutaDTO> rutas = Controlador.getInstance().obtenerRutas();
		Controlador.getInstance().mapadeRuta =  new MapaDeRutaDTO();
		Controlador.getInstance().mapadeRuta.setRutas(rutas);
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
	
	//Agregue cosas para que trate de enviar   los pedidos prioritarios aunque no llegue al 70%
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
}