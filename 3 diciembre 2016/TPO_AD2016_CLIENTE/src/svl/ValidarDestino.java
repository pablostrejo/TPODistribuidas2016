package svl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidarDestino
 */
@WebServlet("/ValidarDestino")
public class ValidarDestino extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarDestino() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idDestinoS = request.getParameter("iddestino");
		int idDestino = Integer.parseInt(idDestinoS);
		boolean existe = Delegate.Delegate.getInstance().existeDestino(idDestino);
		
		if(!existe){
			request.setAttribute("error", "El destino no existe");
			
		}
		else{
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/actualizarRuta.jsp");
			dispatcher.forward(request, response);
		}
	}

}
