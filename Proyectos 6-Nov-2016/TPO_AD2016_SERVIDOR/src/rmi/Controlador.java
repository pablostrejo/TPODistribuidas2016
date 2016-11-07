package rmi;

import java.util.List;

import hbt.HibernateUtil;
import dao.HibernateDAO;
import dto.*;
import entities.*;

public class Controlador {

	private static Controlador instance = null;
	private static HibernateDAO hbtDAO;
	public MapaDeRutaDTO mapadeRuta;

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

	public ViajeDTO obtenerViajeDeEnvio(int idEnvio) {
		return hbtDAO.obtenerViajeDeEnvio(idEnvio);
	}
}
