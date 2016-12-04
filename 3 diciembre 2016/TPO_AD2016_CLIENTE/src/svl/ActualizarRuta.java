package svl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Delegate.Delegate;
import dto.SucursalDTO;
import dto.TrayectoDTO;


@WebServlet("/ActualizarRuta")
public class ActualizarRuta extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ActualizarRuta() {
    	 super();
    	
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			 
			  		 
	 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarRuta.jsp");
			dispatcher.forward(request, response);
		}
		catch(ServletException e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtengo todos los datos de la pantalla
		String IDDestino = request.getParameter("IDDestino");
		String IDOrigen = request.getParameter("IDOrigen");
		String km = request.getParameter("km");
		String precio = request.getParameter("precio");
		String tiempo = request.getParameter("tiempo");
		 
		//Si alguno de los datos esta vacio
		
		if (IDDestino.length() == 0 || IDOrigen.length() == 0 || km.length() == 0 || precio.length() == 0 || tiempo.length() == 0)
		{
			request.setAttribute("IDDestino", IDDestino);
			request.setAttribute("IDOrigen", IDOrigen);
			request.setAttribute("km", km);
			request.setAttribute("precio", precio);
			request.setAttribute("tiempo", tiempo);
			 
			 
		 
			
		
			request.setAttribute("error", "Se deben ingresar todos los datos solicitados.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarRuta.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//Todos los datos datos se cargaron de forma correcta
		else {
			//Parseo los datos
			int IDDestino2 = Integer.parseInt(IDDestino);
			int IDOrigen2 = Integer.parseInt(IDOrigen);
			int km2 = Integer.parseInt(km);
			float precio2 = Float.parseFloat(precio);
			float tiempo2 = Float.parseFloat(tiempo);
			TrayectoDTO t= new TrayectoDTO();
			 t.setKm(km2);
			 t.setPrecio(precio2);
			 SucursalDTO sucursalDestino=new SucursalDTO();
			 sucursalDestino.setIdSucursal(IDDestino2);			
			 SucursalDTO sucursalOrigen=new SucursalDTO();
			 sucursalOrigen.setIdSucursal(IDOrigen2);
			 t.setSucursalDestino(sucursalDestino);
			 t.setSucursalOrigen(sucursalOrigen);
			 t.setTiempo(tiempo2);
			 t.setIdTrayecto(1);
			 
		     request.getAttribute("trayecto");
		     Delegate.getInstance().actualizarMapaDeRuta(t);
		     
		 
		     RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarRuta.jsp");
				dispatcher.forward(request, response);
		 
			
		}
	} 

}

