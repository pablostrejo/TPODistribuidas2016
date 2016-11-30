package svl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CargaDTO;
import dto.ClienteDTO;
import dto.SucursalDTO;
import dto.ViajeDTO;
import Delegate.Delegate;

/**
 * Servlet implementation class Despachar
 */
@WebServlet("/Despachar")
public class Despachar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Despachar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// List<ViajeDTO> viajes = Delegate.getInstance().despachar();
		try {
			
			System.out.println("Entro al doGet del Despachar");
			
			RequestDispatcher dispatcher = null; 
			
			dispatcher = getServletContext().getRequestDispatcher("/despachar.jsp");
			
			processRequest(request, response);
			
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("Entro al processRequest del Despachar");
		
		List<SucursalDTO> sucursales = Delegate.getInstance().obtenerSucursales();
		request.setAttribute("sucursales", sucursales);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ENVIAR PEDIDOS");
		List<ViajeDTO> viajes = Delegate.getInstance().generarViajes();

		request.setAttribute("viajes", viajes);
		
		doGet(request,response);
	}

}
