package rmi;

import java.util.List;

import hbt.HibernateUtil;
import dao.HibernateDAO;
import dto.ClienteDTO;
import dto.SucursalDTO;
import entities.Pedido;

public class Controlador {

	private static Controlador instance = null;
	private static HibernateDAO hbtDAO;

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
}
