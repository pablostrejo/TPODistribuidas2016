package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	public boolean buscarDestino(int idDestino) throws RemoteException{
		return Controlador.getInstance().buscarDestino(idDestino);
	}

	public PedidoDTO obtenerPedido(int idPedido) throws RemoteException{
		return Controlador.getInstance().obtenerPedido(idPedido);
	}

	public List<EnvioDTO> obtenerEnvios() throws RemoteException{
		return Controlador.getInstance().obtenerEnvios();
	}
	
	public void altaPedido(PedidoDTO pedidoDTO) throws RemoteException{ 
		Controlador.getInstance().guardarPedido(pedidoDTO);
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
		return Controlador.getInstance().despacharViajes(sucursal);
	}

	public void cargarMapaDeRuta() throws RemoteException{
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

	public List<VehiculoDTO> obtenerVehiculos() throws RemoteException{
		return Controlador.getInstance().obtenerVehiculos();
	}

	public void altaCarga(){
		
	}

	public void actualizarMapaDeRutas(TrayectoDTO t) {
		t.setSucursalOrigen(Controlador.getInstance().obtenerSucursal(t.getSucursalOrigen()));
		t.setSucursalDestino(Controlador.getInstance().obtenerSucursal(t.getSucursalDestino()));
		Controlador.getInstance().modificarTrayecto(t);
		try {
			cargarMapaDeRuta();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
				Controlador.getInstance().modificarVehiculo(vehiculoDTO);
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
				Controlador.getInstance().modificarVehiculo(vehiculoDTO);
			}
		}
		return (boolean) resp;
	}

}