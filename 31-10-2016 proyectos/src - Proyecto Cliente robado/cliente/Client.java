package cliente;

 

import java.io.File;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Delegate.Delegate;
import dto.FacturaDTO;
import dto.PedidoDTO;
import xml.XmlViaje;


public class Client {
	
	//Main
	public static void main(String[] args){
  
    
		//Metodos sistema calendario 
		
		
		  ScheduledExecutorService ses =Executors.newScheduledThreadPool(1);
		  Runnable actualizarEstadoViaje = new Runnable() {
		            public void run() {
		               actualizarEstadoViaje();
		            }
		        };
        ses.scheduleAtFixedRate(actualizarEstadoViaje, 15, 500000, TimeUnit.SECONDS);
		
		        //
		
		
		 

}
	public static void actualizarEstadoViaje()
	{
		String directorio = "c:\\viajes";
		File f = new File(directorio);		 
 

		 
		if (f.exists()){ 
			File[] ficheros = f.listFiles();
			for (int x=0;x<ficheros.length;x++){
				System.out.println("lenght"+ficheros.length);
			  System.out.println("Encontre el archivo: "+ficheros[x].getName());
		
			   XmlViaje.getInstance().actualizarEstadoViaje(ficheros[x].getName());
			}
		}
		else { 
			File carpeta = new File("c:\\viajes");
			carpeta.mkdirs(); 
		}
		  
     	
	}
	public static void crearFactura(PedidoDTO pd,float precio,Delegate de)
	{
		
		FacturaDTO f=new FacturaDTO();
		f.setPedido(pd);
		f.setPrecio(precio);
		de.altaFactura(f);
		
	}
}