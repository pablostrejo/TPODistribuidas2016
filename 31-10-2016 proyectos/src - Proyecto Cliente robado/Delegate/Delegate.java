package Delegate;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

 
import java.util.Properties;

import dto.*;
import rmi.RemoteInterface;
import rmi.RmiStarter;


public class Delegate extends RmiStarter {

	private static Delegate instance;
	private static RemoteInterface remoteObject;
	public static List<CargaDTO> cargas;
	public static String username;
	
	public Delegate(){
		super(Delegate.class);
	}
	
	public static Delegate getInstance()
	{
		if(instance==null)
			instance= new Delegate();
		if(cargas==null)
		   cargas=new ArrayList<CargaDTO>();
		return instance;
	}
	
	@Override
	public void doCustomRmiHandling(){
	 
		Registry registry;
		try
		{ System.setProperty("java.security.policy", "java.policy");

		 
			registry = LocateRegistry.getRegistry(1099);
			remoteObject = (RemoteInterface) registry.lookup("//localhost/REG");
 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void altaPedido(PedidoDTO pedidoDTO){
		
		try{
			remoteObject.altaPedido(pedidoDTO);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<PedidoDTO> obtenerPedidos() {
		try {
			return remoteObject.obtenerPedidos();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<SucursalDTO> obtenerSucursales() {
		try {
			return remoteObject.obtenerSucursales();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ViajeDTO> obtenerViajes() {
		try {
			return remoteObject.obtenerViajes();
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}
	
	public VehiculoDTO getVehiculo(int id) {
		try {
			VehiculoDTO v=new VehiculoDTO();
			v.setIdVehiculo(id);
			return remoteObject.obtenerVehiculo(v);
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}

	public ViajeDTO obtenerViajePorVehiculo(VehiculoDTO vehiculo) {
		try {
			return remoteObject.obtenerViajePorVehiculo(vehiculo);
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}

	public void actualiarViaje(ViajeDTO viaje) {
		try {
			 remoteObject.actualiarViaje(viaje);
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
		 
		}	
	}

	public void altaFactura(FacturaDTO f) {
	 
	    try {
	    	remoteObject.altaFactura(f);
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
		 
		}
	}
 
	public void actualizarPedido(PedidoDTO pedido)
	{
		try {
			remoteObject.actualizarPedido(pedido);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public List<ClienteDTO> obtenerClientes() {
		try {
			return remoteObject.obtenerClientes();
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}

	public List<ViajeDTO> controlarPedidosDeCliente(ClienteDTO c) {
		 try {
			return remoteObject.controlarPedidosDeCliente(c);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String validarCredenciales(String username, String password) {
		// TODO Auto-generated method stub
		String string=null;;
		try {
			string=remoteObject.validarCredenciales(username, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}

	public List<CargaDTO> obtenerCargasDeUnPedido(PedidoDTO pedido) {
		try {
			return remoteObject.obtenerCargasDeUnPedido(pedido);
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}
	
	public PedidoDTO obtenerPedido (int idPedido) {
		try {
			return remoteObject.obtenerPedido(idPedido);
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}

	public List<HabilitadoDTO> obtenerHabilitados() {
		try {
			return remoteObject.obtenerHabilitados();
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<TransporteDTO> obtenerTransportes() {
		try {
			return remoteObject.obtenerTransportes();
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}

	public List<EnvioDTO> obtenerEnvios(String username) {
		try {
			System.out.println("DELEGATE "+username);
			return remoteObject.obtenerEnvios(username);
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}

	public void actualizarMapaDeRuta(TrayectoDTO t)
	{
		 try {
			remoteObject.actualizarMapaDeRutas(t);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public List<VehiculoDTO> obtenerVehiculos() {	
		 try {
			 return remoteObject.obtenerVehiculos();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return null;

	}	

	
	public boolean ControlarVehiculo(int id){
		VehiculoDTO vehiculoDTO=getVehiculo(id);
		try {
			return remoteObject.ControlarVehiculo(vehiculoDTO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ClienteDTO obtenerClientePorID(int id) {
		try {
			return remoteObject.obtenerClientePorID(id);
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
			return null;
		}
	}
	
	public VehiculoDTO obtenerVehiculo (int id) {
		VehiculoDTO vehiculo = new VehiculoDTO();
		vehiculo.setIdVehiculo(id);
		try {
			return remoteObject.obtenerVehiculo(vehiculo);
		}
		catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void despachar () {
		
		try {
			System.out.println("Enviados");
			remoteObject.enviar();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}

