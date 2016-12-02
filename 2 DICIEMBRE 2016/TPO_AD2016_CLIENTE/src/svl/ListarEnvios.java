package svl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Delegate.Delegate;
import dto.EnvioDTO;


@WebServlet("/ListarEnvios")
public class ListarEnvios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 

    public ListarEnvios() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			System.out.println("BLAAAAAA");
			
			List<EnvioDTO> envios = Delegate.getInstance().obtenerEnvios();
			
			System.out.println(envios.get(0).getEstado());
			request.setAttribute("envios", envios);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/listarEnvios.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String generateSessionId() throws UnsupportedEncodingException {
		
		String uid = new java.rmi.server.UID().toString(); // guaranteed unique
		return URLEncoder.encode(uid,"UTF-8"); // encode any special chars
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Holaaa");
		doGet(request,response);
	}

}
