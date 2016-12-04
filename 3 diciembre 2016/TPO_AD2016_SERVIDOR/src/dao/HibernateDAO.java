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
	
	public static HibernateDAO getInstancia(){
		if (instancia == null){
			sessionFactory = HibernateUtil.getSessionfactory();
			instancia = new HibernateDAO();
		}
		return instancia;
	}

	public void guardarPedido(Pedido pedido){
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.save(pedido);
			s.getTransaction().commit();
			
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
	
	@SuppressWarnings("unchecked")
	public List<Pedido> obtenerPedidosEnDeposito(){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			pedidos = (List<Pedido>) s.createQuery("FROM Pedido p WHERE p.estado =:estado").setParameter("estado", "En Deposito").list();

		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los pedidos");
		}
		s.close();
		return pedidos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pedido> obtenerPedidosEnDeposito(int sucursal){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			pedidos = (List<Pedido>) s.createQuery("FROM Pedido p WHERE p.estado =:estado "
					+ " AND sucursalOrigen =:idSucursal").setParameter("estado", "En Deposito").setParameter("idSucursal", String.valueOf(sucursal)).list();

		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los pedidos");
		}
		s.close();
		return pedidos;
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

	public Envio obtenerEnvioDePedido(int idPedido){
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Envio envEnt = (Envio) s.createQuery("FROM Envio e WHERE e.pedido.idPedido =:id").setParameter("id", idPedido).uniqueResult();
			if(envEnt!=null){
				return envEnt;
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener envio de pedido");
		}
		s.close();
		return null;
	}

	public SucursalDTO obtenerSucursal(int sucursalOrigen){
		SucursalDTO sucursalDTO = new SucursalDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			Sucursal suc = (Sucursal) s.createQuery("FROM Sucursal s WHERE s.idSucursal=:id").setParameter("id", sucursalOrigen).uniqueResult();
			sucursalDTO = suc.toDTO();
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener obtenerSucursal");
		}
		s.close();
		return sucursalDTO;
	}
	
	public Sucursal obtenerSucursalEntity(int sucursalOrigen){
		Sucursal sucursal = new Sucursal();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			sucursal = (Sucursal) s.createQuery("FROM Sucursal s WHERE s.idSucursal=:id").setParameter("id", sucursalOrigen).uniqueResult();
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener obtenerSucursal");
		}
		s.close();
		return sucursal;
	}
	
	public SucursalDTO obtenerSucursal(SucursalDTO sucursalOrigen) {
		SucursalDTO sucursalDTO = new SucursalDTO();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			Sucursal suc = (Sucursal) s.createQuery("FROM Sucursal s where s.idSucursal=:id").setParameter("id", sucursalOrigen.getIdSucursal()).uniqueResult();
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
			s.close();
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("ErrorDAO: " + viaje.getClass().getName() + ".borrar");
		}
		s.close();
	}

	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerVehiculosListos(){
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			vehiculos = (List<Vehiculo>) s.createQuery("FROM Vehiculo v WHERE v.estado = :estado "
					+ "ORDER BY v.volumen ASC").setParameter("estado", "En Deposito").list();
			System.out.println("LLEGO ACÄ ");
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los vehiculos listos");
		}
		s.close();
		return vehiculos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehiculo> obtenerVehiculosDeSucursal(int sucursal){
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try{
			vehiculos = (List<Vehiculo>) s.createQuery("FROM Vehiculo v WHERE v.estado = :estado AND idSucursal =:idSucursal"
					+ "ORDER BY v.volumen ASC").setParameter("estado", "En Deposito").setParameter("idSucursal", sucursal).list();
			System.out.println("LLEGO ACÄ ");
		}
		catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener los vehiculos listos");
		}
		s.close();
		return vehiculos;
	}

	public void modificarVehiculo(Vehiculo vehiculo) {
		Session s = null;
		Transaction t = null;
		try {
			s = HibernateUtil.getSessionfactory().openSession();
			t = s.beginTransaction();

			s.update(vehiculo);
			t.commit();
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + vehiculo.getClass().getName() + ".modificar");
		} finally {
			if (s != null)
				s.close();
		}
	}

	public void guardarViaje(Viaje viaje){
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.save(viaje);
			s.getTransaction().commit();
			s.close();			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al guardar el viaje " + viaje.getClass().getName() + ": " + e.getMessage());
			s.getTransaction().rollback();
		}
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
		return viajesDTO;
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
			Vehiculo vehiEnt = (Vehiculo) s.createQuery("FROM Vehiculo v WHERE v.idVehiculo =:id").setParameter("id", v.getIdVehiculo()).uniqueResult();
			if(vehiEnt != null){
				veh = vehiEnt.toDTO();
			}
		}catch(Exception e){
			System.out.println(e);
			System.out.println("Error al obtener vehiculo por dto");
		}
		s.close();
		return veh;
	}

	public void modificarTrayecto(Trayecto trayecto) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.update(trayecto);
			s.getTransaction().commit();
			s.close();

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

	@SuppressWarnings("unchecked")
	public List<EnvioDTO> obtenerEnvios() {
		List<EnvioDTO> enviosDTO = new ArrayList<EnvioDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			List<Envio> envios = s.createQuery("FROM Envio").list();

			for (Envio e: envios) {
						enviosDTO.add(e.toDTO());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		s.close();
		return enviosDTO;

	}

	public List<VehiculoDTO> obtenerVehiculos() {
		List<VehiculoDTO> vehiculosDTO = new ArrayList<VehiculoDTO>();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			@SuppressWarnings("unchecked")
			List<Vehiculo> vehiculosEnt = s.createQuery("FROM Vehiculo").list();

			for (Vehiculo v: vehiculosEnt) {

						vehiculosDTO.add(v.toDTO());
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		s.close();
		return vehiculosDTO;
	}

	public PlanDeMantenimiento obtenerPlanDeMantenimiento(int i) {
		PlanDeMantenimiento plan = new PlanDeMantenimiento();
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			plan = (PlanDeMantenimiento) s.createQuery("FROM PlanDeMantenimiento p WHERE p.idPlanDeMantenimiento =:id").setParameter("id", i).uniqueResult();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		s.close();
		return plan;
	}

	public void altaVehiculo(Vehiculo vehi) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		try {
			s.beginTransaction();
			s.save(vehi);
			s.getTransaction().commit();
			
		} catch (Exception e) {
			s.getTransaction().rollback();
			System.out.println(e);
			System.out.println("ErrorDAO: " + vehi.getClass().getName() + ".guardar");
		}
		s.close();
		
	}

	public Vehiculo obtenerVehiculo(int idVehiculo) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		Vehiculo vehiEnt = new Vehiculo();
		try{
			vehiEnt = (Vehiculo) s.createQuery("FROM Vehiculo v WHERE v.idVehiculo =:id").setParameter("id", idVehiculo).uniqueResult();
		}catch(Exception e){
				System.out.println(e);
				System.out.println("Error al obtener vehiculo por entity");
		}
		s.close();
		return vehiEnt;
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> obtenerPedidosPorCliente(int idCliente) {
		Session s = HibernateUtil.getSessionfactory().openSession();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try{
			pedidos = (List<Pedido>) s.createQuery("FROM Pedido p WHERE p.idCliente =:id").setParameter("id", idCliente).list();
		}catch(Exception e){
				System.out.println(e);
				System.out.println("Error al obtener vehiculo por entity");
		}
		s.close();
		return pedidos;
	}
}