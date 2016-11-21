package dao;

import java.util.ArrayList;
import java.util.List;

import hbt.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import rmi.Controlador;
import dto.*;
import entities.*;

public class HibernateDAO {

	protected static HibernateDAO instancia;
	protected static SessionFactory sessionFactory = null;
	protected static Session session = null;
	
	public static HibernateDAO getInstancia(){
		if (instancia == null){
			sessionFactory = HibernateUtil.getSessionfactory();
			instancia = new HibernateDAO();
		}
		return instancia;
	}
	
	public Session getSession(){
		if (session == null || !session.isOpen()){
			session = sessionFactory.openSession();
		}
		return session;
	}
	
	public void closeSession(){
		if (session.isOpen()){
			session.close();
		}
	}
	
	// aca hace el guardar 
	public void guardarPedido(Pedido pedido){
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.save(pedido);
			s.getTransaction().commit();
			this.closeSession();
			
		} catch (Exception e) {
			s.getTransaction().rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + pedido.getClass().getName() + ".guardar");
		}
		s.close();
	}

	public List<SucursalDTO> obtenerSucursales(){
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
		s.close();
		return sucsDTO;
	}

	public List<ClienteDTO> obtenerClientes(){
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<Cliente> clieEntities = (List<Cliente>) s.createQuery("FROM Cliente").list();
			if(clieEntities.size()>0){
				for (Cliente c: clieEntities){
					clientesDTO.add(c.toDTO());
				}
				System.out.println(clieEntities + " clieEntities");
				System.out.println(clientesDTO + " clientesDTO");
			}
		}
		catch(Exception e) {
			System.out.println(e);
			System.out.println("Error al obtener los clientes");
		}
		s.close();
		return clientesDTO;
	}

	public ClienteDTO obtenerClientePorID(int id){
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
		s.close();
		return clienteDTO;
	}

	public List<PedidoDTO> obtenerPedidos(){
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
		s.close();
		return pedidosDTO;
	}

	public List<RutaDTO> obtenerRutas(){
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
		s.close();
		return rutasDTO;
	}

	public EnvioDTO obtenerEnvioDePedido(int idPedido){
		EnvioDTO envioDTO = new EnvioDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Envio envEnt = (Envio) s.createQuery("SELECT e FROM WHERE e.pedido.idPedido =:id").setParameter("id", idPedido).uniqueResult();
			if(envEnt!=null){
				envioDTO = envEnt.toDTO();
			}
			else{
				envioDTO = null;
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener envio de pedido");
		}
		s.close();
		return envioDTO;
	}

	public SucursalDTO obtenerSucursal(String sucursalOrigen){
		SucursalDTO sucursalDTO = new SucursalDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Sucursal suc = (Sucursal) s.createQuery(" FROM Sucursal s WHERE s.idSucursal=:id").setParameter("id", sucursalOrigen).uniqueResult();
			sucursalDTO = suc.toDTO();
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener obtenerSucursal");
		}
		s.close();
		return sucursalDTO;
	}
	
	public SucursalDTO obtenerSucursal(SucursalDTO sucursalOrigen) {
		SucursalDTO sucursalDTO = new SucursalDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			Sucursal suc = (Sucursal) s.createQuery(" FROM Sucursal s where s.idSucursal=:id").setParameter("id", sucursalOrigen.getIdSucursal()).uniqueResult();
			sucursalDTO= suc.toDTO();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		s.close();
		return sucursalDTO;
	}


	public ViajeDTO obtenerViajeDeEnvio(int id){
		ViajeDTO viajeDTO = new ViajeDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Viaje viajEnt = (Viaje) s.createQuery("FROM Viaje v WHERE v.idEnvio =:id").setParameter("id", id).uniqueResult();
			if(viajEnt!= null){
				viajeDTO = viajEnt.toDTO();
			}
			else{
				viajeDTO = null;
			}
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener viajeDeEnvio x int id");
		}
		s.close();
		return viajeDTO;
	}

	public void borrarViaje(Viaje viaje){
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.delete(viaje);
			s.getTransaction().commit();
			this.closeSession();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ErrorDAO: " + viaje.getClass().getName() + ".borrar");
		}
		s.close();
	}

	public List<VehiculoDTO> obtenerVehiculosListos(){
		List<VehiculoDTO> vehiculosDTO = new ArrayList<VehiculoDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			@SuppressWarnings("unchecked")
			List<Vehiculo> vehiEnt = (List<Vehiculo>) s.createQuery("SELET v FROM Vehiculo v WHERE v.estado = 'En Deposito'").list();
			if(vehiEnt.size()>0){
				for (Vehiculo v: vehiEnt) {
					vehiculosDTO.add(v.toDTO());
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los vehiculos listos");
		}
		s.close();
		return vehiculosDTO;
	}

	public void modificarVehiculo(Vehiculo vehiculo) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.update(vehiculo);
			s.getTransaction().commit();
			this.closeSession();

		} catch (Exception e) {
			s.getTransaction().rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + vehiculo.getClass().getName() + ".modificar");
		}
		s.close();
	}

	public void guardarViaje(Viaje viaje){
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.save(viaje);
			s.getTransaction().commit();
			this.closeSession();
			
		} catch (Exception e) {
			s.getTransaction().rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + viaje.getClass().getName() + ".guardar");
		}
		s.close();
	}

	public List<ViajeDTO> obtenerViajes() {
		Session s = HibernateUtil.getSessionfactory().openSession();
		List<ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		try{
			@SuppressWarnings("unchecked")
			List<Viaje> viajesEnt = (List<Viaje>) s.createQuery("FROM Viaje").list();
			if(viajesEnt.size()>0){
				for (Viaje v: viajesEnt) {
					viajesDTO.add(v.toDTO());
				}
			}
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los viajes");
		}
		s.close();
		return null;
	}

	public ViajeDTO obtenerViajeDeEnvio(ViajeDTO viaje){
		ViajeDTO viajeDTO = new ViajeDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Viaje viajEnt = (Viaje) s.createQuery("FROM Viaje v WHERE v.idEnvio =:id").setParameter("id", viaje.getIdViaje()).uniqueResult();
			if(viajEnt!= null){
				viajeDTO = viajEnt.toDTO();
			}
			else{
				viajeDTO = null;
			}
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener viajeDeEnvio x int id");
		}
		s.close();
		return viajeDTO;
	}

	public VehiculoDTO obtenerVehiculo(VehiculoDTO v){
		VehiculoDTO veh = new VehiculoDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener vehiculo por dto");
		}
		s.close();
		return null;
	}

	public void modificarTrayecto(Trayecto trayecto) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.update(trayecto);
			s.getTransaction().commit();
			this.closeSession();

		} catch (Exception e) {
			s.getTransaction().rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + trayecto.getClass().getName() + ".modificar");
		}
		s.close();
	}

	public boolean buscarDestino(int idDestino) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Sucursal suc = (Sucursal) s.createQuery("FROM Sucursal s WHERE s.idSucursal =:id ").setParameter("id", idDestino).uniqueResult();
			if(suc!= null){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener sucursal x id");
		}
		s.close();
		return false;
	}

	public PedidoDTO obtenerPedido(int idPedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			Pedido pedido = (Pedido) s.createQuery("FROM Pedido p WHERE p.idPedido=:id").setParameter("id", idPedido).uniqueResult();
			pedidoDTO = pedido.toDTO();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		s.close();
		return pedidoDTO;

	}

	public List<EnvioDTO> obtenerEnvios() {
		List<EnvioDTO> enviosDTO = new ArrayList<EnvioDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			List<Envio> envios = s.createQuery("FROM Envio").list();
			List<Pedido> pedidos = s.createQuery("FROM Pedido").list();

			for (Envio e: envios) {
				for(Pedido p :pedidos){

					//if(p.getCliente().getNombre().equals(nombre)&& e.getPedido().getIdPedido()==p.getIdPedido()){
						enviosDTO.add(e.toDTO());
					//}
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		s.close();
		return enviosDTO;

	}
}