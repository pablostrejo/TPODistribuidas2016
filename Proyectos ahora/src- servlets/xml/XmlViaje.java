package xml;

import java.io.IOException;
 
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
 
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import Delegate.*;
import dto.PedidoDTO;
import dto.VehiculoDTO;
import dto.ViajeDTO;

//POr las dudas probar con 2 o mas xml si el doc cambia
public class XmlViaje {
	
	 
	private static XmlViaje instancia;
	private Document doc;
	 
	 
	
	public static XmlViaje getInstance()
	{
		if (XmlViaje.instancia == null)
			XmlViaje.instancia = new XmlViaje();
			 
		return XmlViaje.instancia;
	}
	
	private XmlViaje()
	{
		super();
		
	}
	
	public  void crearDocDesdeXML(String archivo)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
		System.out.println("Archivo:"+archivo);
			doc = builder.parse("c:\\viajes\\"+archivo);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
 
	@SuppressWarnings("unused")
	private void crearDocDesdeXMLNuevo(String archivo)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse("c:\\viajes"+archivo);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
 
	public void actualizarEstadoViaje(String archivo) {
		crearDocDesdeXML(archivo);		 
		NodeList lista = doc.getElementsByTagName("Viaje");
		for (int i = 0; i < lista.getLength(); i++)
		{
			Element viaje = (Element)lista.item(i);
			 parseoViaje(viaje);		
		}
 
	 
	}
	private ViajeDTO parseoViaje(Element v) {
		ViajeDTO viaje = new ViajeDTO();
		viaje.setIdViaje(0);
		Element minutos= (Element)v.getElementsByTagName("Llegada").item(0);
		int m=Integer.parseInt(minutos.getElementsByTagName("minutos").item(0).getTextContent());
		
		//CREO QUE NO HACE FALTA QUE BUSQUE EL NUMERO DEPEDIDO DEL VIAJE(ESTA EN EL CU)
		Element vehiculo = (Element)v.getElementsByTagName("Vehiculo").item(0);
		VehiculoDTO vehi=Delegate.getInstance().getVehiculo(Integer.parseInt(vehiculo.getElementsByTagName("IDVehiculo").item(0).getTextContent()));
		if(vehi==null)
			return null;
		 
		viaje.setVehiculo(vehi);
		
		viaje=Delegate.getInstance().obtenerViajePorVehiculo(viaje.getVehiculo());
		if(viaje==null)
			return null;
		//PREGUNTA EL VIAJE LO LEVANTE CON LOS ENVIO QUE TIENE ? y a su vez con los pedidos?
		long milisegundos=60000;
		Date aux= viaje.getFechaLlegada();
		long minutosAux=m*milisegundos;
		Date aux2=new Date(aux.getTime() + minutosAux);
		viaje.setFechaLlegada(aux2);
		for(int i=0;i<viaje.getEnvios().size();i++)
		{
			int m2= viaje.getEnvios().get(i).getPedido().getHoraFin();
		viaje.getEnvios().get(i).getPedido().setHoraFin(m+m2);
		Delegate.getInstance().actualizarPedido(viaje.getEnvios().get(i).getPedido());
		}
		
        Delegate.getInstance().actualiarViaje(viaje);
	 
 
	  //SI no devuelve el viaje hubo un error
		return viaje;
	}
 
}
