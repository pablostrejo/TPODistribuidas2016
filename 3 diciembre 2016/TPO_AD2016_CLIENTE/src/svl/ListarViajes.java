package svl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ViajeDTO;

import Delegate.Delegate;

@WebServlet("/ListarViajes")
public class ListarViajes extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public ListarViajes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<ViajeDTO> viajes = Delegate.getInstance().obtenerViajes();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/listarViajes.jsp");
			request.setAttribute("viajes", viajes);
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