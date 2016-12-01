package svl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Delegate.Delegate;
import dto.PedidoDTO;

@WebServlet("/ListarCargas")
public class ListarCargasDeUnPedido extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public ListarCargasDeUnPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idPedidoString = request.getParameter("id");
			if (idPedidoString != null && idPedidoString != "") {
				
				int idPedido = Integer.parseInt(request.getParameter("id"));
				PedidoDTO pedido = Delegate.getInstance().obtenerPedido(idPedido);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/listarCargasDeUnPedido.jsp");
				request.setAttribute("pedido", pedido);
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
