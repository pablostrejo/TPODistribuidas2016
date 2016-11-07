package dao;

import java.util.ArrayList;
import java.util.List;

import hbt.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dto.*;
import entities.*;

public class HibernateDAO {

	protected static HibernateDAO instancia;
	protected static SessionFactory sessionFactory = null;
	protected static Session session = null;
	
	public static HibernateDAO getInstancia() {
		if (instancia == null) {
			sessionFactory = HibernateUtil.getSessionfactory();
			instancia = new HibernateDAO();
		}
		return instancia;
	}
	
	public Session getSession() {
		if (session == null || !session.isOpen()) {
			session = sessionFactory.openSession();
		}
		return session;
	}
	
	public void closeSession() {
		if (session.isOpen()) {
			session.close();
		}
	}
	
	// aca hace el guardar 
	public void guardarPedido(Pedido pedido) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.save(pedido);
			s.getTransaction().commit();
			this.closeSession();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ErrorDAO: " + pedido.getClass().getName() + ".guardar");
		}
	}

	public List<SucursalDTO> obtenerSucursales() {
		List<SucursalDTO> sucsDTO = new ArrayList<SucursalDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		
		try {
			@SuppressWarnings("unchecked")
			List<Sucursal> sucsEntities = (List<Sucursal>) s.createQuery("FROM Sucursal").list();
			if(sucsEntities.size()>0){
				for(Sucursal suc: sucsEntities){
					sucsDTO.add(suc.toDTO());
				}
			}
			return sucsDTO;
			
		} catch (Exception e){
			System.out.println(e);
			System.out.println("Error al obtener las sucursales");
		}
		this.closeSession();
		return sucsDTO;
	}

	public List<ClienteDTO> obtenerClientes() {
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<Cliente> clieEntities = (List<Cliente>) s.createQuery("FROM Cliente").list();
			if(clieEntities.size()>0){
				for (Cliente c: clieEntities) {
					clientesDTO.add(c.toDTO());
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("Error al obtener los clientes");
		}
		this.closeSession();
		return clientesDTO;
	}

	public ClienteDTO obtenerClientePorID(int id) {
		ClienteDTO clienteDTO = new ClienteDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Cliente cliEnt = (Cliente) s.createQuery("SELECT c FROM Cliente c WHERE idCliente = :nro ").setParameter("nro", id).uniqueResult();
			clienteDTO = cliEnt.toDTO();
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener el cliente por ID");
		}
		this.closeSession();
		return clienteDTO;
	}

	public List<PedidoDTO> obtenerPedidos() {
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			@SuppressWarnings("unchecked")
			List<Pedido> pediEnt = (List<Pedido>) s.createQuery("FROM Pedido").list();
			if(pediEnt.size()>0){
				for (Pedido p: pediEnt) {
					pedidosDTO.add(p.toDTO());
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los pedidos");
		}
		this.closeSession();
		return pedidosDTO;
	}

	public List<RutaDTO> obtenerRutas() {
		List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			@SuppressWarnings("unchecked")
			List<Ruta> rutaEnt = (List<Ruta>) s.createQuery("FROM Ruta").list();
			if(rutaEnt.size()>0){
				for (Ruta p: rutaEnt) {
					rutasDTO.add(p.toDTO());
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener las rutas");
		}
		this.closeSession();
		return rutasDTO;
	}

	public EnvioDTO obtenerEnvioDePedido(int idPedido) {
		EnvioDTO envioDTO = new EnvioDTO();
		Envio e=new Envio();
		return null;
	}

	public SucursalDTO obtenerSucursal(String sucursalOrigen) {
		SucursalDTO sucursalDTO = new SucursalDTO();
		return null;
	}

	public ViajeDTO obtenerViajeDeEnvio(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
}