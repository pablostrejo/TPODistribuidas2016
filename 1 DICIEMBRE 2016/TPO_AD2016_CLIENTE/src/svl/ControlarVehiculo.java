package svl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Delegate.Delegate;
import dto.VehiculoDTO;
import dto.ViajeDTO;

@WebServlet("/ControlarVehiculo")
public class ControlarVehiculo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public ControlarVehiculo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<VehiculoDTO> vehiculos = Delegate.getInstance().obtenerVehiculos(); 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/controlarVehiculo.jsp");
			request.setAttribute("vehiculos", vehiculos);
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       doGet(request, response);
	}
}


	