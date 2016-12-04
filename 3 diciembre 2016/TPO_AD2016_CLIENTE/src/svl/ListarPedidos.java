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
import dto.PedidoDTO;
 
@WebServlet("/ListarPedidos")
public class ListarPedidos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ListarPedidos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<PedidoDTO> pedidos = Delegate.getInstance().obtenerPedidos();
			RequestDispatcher dispatcher = null; 
			
			dispatcher = getServletContext().getRequestDispatcher("/listarPedidos.jsp");
			
			request.setAttribute("pedidos", pedidos);
			
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
