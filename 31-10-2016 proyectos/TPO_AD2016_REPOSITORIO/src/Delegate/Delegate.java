package Delegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rmi.RemoteInterface;
import dto.CargaDTO;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;


public class Delegate {

	private static Delegate instance;
	private static RemoteInterface remoteObject;
	public static List<CargaDTO> cargas;
	public static String username;
	
	public Delegate(){
//		LocateRegistry.getRegistry(1099);
		try {
			remoteObject = (RemoteInterface) Naming.lookup("//localhost/TPODistribuidas");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Delegate getInstance(){
		if(instance==null)
			instance= new Delegate();
		if(cargas==null)
		   cargas=new ArrayList<CargaDTO>();
		return instance;
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
	// 1
	public List<SucursalDTO> obtenerSucursales() {
		try {
			return remoteObject.obtenerSucursales();
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE obtener sucursales");
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

	public void actualizarViaje(ViajeDTO viaje) {
		try {
			 remoteObject.actualizarViaje(viaje);
		}
		catch(Exception e){
			System.out.println("SALI POR EL CATCH DEL DELEGATE");
			e.printStackTrace();
		 
		}	
	}
	// 2
	public List<ClienteDTO> obtenerClientes() {
		try {
			return remoteObject.obtenerClientes();
		}
		catch (Exception e) {
			System.out.println("SALI POR EL CATCH DEL DELEGATE obtener clientes");
			e.printStackTrace();
			return null;
		}
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
	
	public List<VehiculoDTO> obtenerVehiculos() {	
		try {
			return remoteObject.obtenerVehiculos();
		} 
		catch (Exception e) {
			
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return null;

	}	
	
	public VehiculoDTO obtenerVehiculo (int id) {
		VehiculoDTO vehiculo = new VehiculoDTO();
		vehiculo.setIdVehiculo(id);
		try {
			return remoteObject.obtenerVehiculo(vehiculo);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void despachar () {
		
		try {
			System.out.println("Enviados");
			remoteObject.enviar();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// faltan los demás metodos...............  =(
}

