package dao;

import java.util.ArrayList;
import java.util.List;

import hbt.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dto.ClienteDTO;
import dto.SucursalDTO;
import entities.Cliente;
import entities.Pedido;
import entities.Sucursal;

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
	public void guardarPedido(Pedido entidad) {
		Transaction t = null;
		Session s = sessionFactory.getCurrentSession();
		try {
			t = (Transaction) s.beginTransaction();

			s.save(entidad);
			System.out.println("Object Saved");
			t.commit();

		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + entidad.getClass().getName() + ".guardar");
		}
	}

	public List<SucursalDTO> obtenerSucursales() {
		List<SucursalDTO> sucsDTO = new ArrayList<SucursalDTO>();
		Session s = sessionFactory.getCurrentSession();
		try {
			@SuppressWarnings("unchecked")
			List<Sucursal> sucsEntities = (List<Sucursal>) s.createQuery("SELECT * FROM Sucursal").list();
			if(sucsEntities!= null){
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
		Session s = this.getSession();
		try {
			@SuppressWarnings("unchecked")
			List<Cliente> clieEntities = (List<Cliente>) s.createQuery("SELECT * FROM Cliente").list();
			if(clieEntities!= null){
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
}
