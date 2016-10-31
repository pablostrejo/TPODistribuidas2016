package svl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.VehiculoDTO;

import Delegate.Delegate;

@WebServlet("/ControlarVehiculoPorID")
public class ControlarVehiculoPorID extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public ControlarVehiculoPorID() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idVehicuoString = request.getParameter("id");
			if (idVehicuoString != null && idVehicuoString != "") {
				
				int idVehiculo = Integer.parseInt(request.getParameter("id"));
				VehiculoDTO vehiculo = Delegate.getInstance().obtenerVehiculo(idVehiculo);
				
				if(Delegate.getInstance().ControlarVehiculo(idVehiculo)) {
					request.setAttribute("info", "En mantenimiento");
				}
				else {
					request.setAttribute("info", "No es necesario enviarlo al taller todavia.");
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/controlarVehiculoPorID.jsp");
				request.setAttribute("vehiculo", vehiculo);
				
				dispatcher.forward(request, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}