package rmi;

import java.util.List;

import Strategy.PoliticaMantenimiento;
import hbt.HibernateUtil;
import dao.HibernateDAO;
import dto.*;
import entities.*;

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

	public SucursalDTO obtenerSucursal(String sucursalOrigen) {
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
		return hbtDAO.obtenerVehiculosListos();
	}

	public RutaDTO obtenerMejorRuta(SucursalDTO sucursalOrigen, SucursalDTO sucursalDestino) {
		MapaDeRutaDTO mp = mapadeRuta;
		cargarMapaDeRuta();
		float precioMin=999999999;
		RutaDTO ruta=new RutaDTO();
		int km=99999999;
		 
		for(RutaDTO r: mp.getRutas()){
			if(r.getSucursalOrigen().getIdSucursal()== sucursalOrigen.getIdSucursal() && r.getSucursalDestino().getIdSucursal()== sucursalDestino.getIdSucursal()){
				int kmAux = r.calcularKm();
				if(kmAux<km){
					km=kmAux;
					ruta=r;
				}
				else if(kmAux==km){
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

	/*public ViajeDTO obtenerViajeDeEnvio(ViajeDTO viajeDTO) {
		return hbtDAO.obtenerViajeDeEnvio(viajeDTO);
	}*/
}
