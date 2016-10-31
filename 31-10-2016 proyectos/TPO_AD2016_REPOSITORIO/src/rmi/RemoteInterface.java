package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.SucursalDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;

public interface RemoteInterface extends Remote {

	public List<SucursalDTO> obtenerSucursales() throws RemoteException;
	public void altaPedido(PedidoDTO pedidoDTO) throws RemoteException;
	public List<ViajeDTO> obtenerViajes() throws RemoteException;
	public ViajeDTO obtenerViaje(ViajeDTO viajeDTO) throws RemoteException;
	public List<PedidoDTO> obtenerPedidos() throws RemoteException;
	
	public List<ClienteDTO> obtenerClientes() throws RemoteException;
	public VehiculoDTO obtenerVehiculo(VehiculoDTO v) throws RemoteException;
	public ViajeDTO obtenerViajePorVehiculo(VehiculoDTO vehiculo) throws RemoteException;
	public void actualizarViaje(ViajeDTO viaje) throws RemoteException;
	public List<VehiculoDTO> obtenerVehiculos() throws RemoteException;
	public void enviar() throws RemoteException;
	public ClienteDTO obtenerClientePorID(int id) throws RemoteException;
	
}

