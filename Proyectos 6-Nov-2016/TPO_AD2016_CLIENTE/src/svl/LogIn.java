package svl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CargaDTO;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static String username2;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/LogIn.jsp");
			dispatcher.forward(request, response);
		}
		catch(ServletException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			String type = Delegate.Delegate.getInstance().validarCredenciales(username, password);
			System.out.println(type);
			if(type==null) {
				request.setAttribute("error", "Unknown user, please try again");
				request.getRequestDispatcher("/LogIn.jsp").forward(request, response);
			}
			else if (type.equals("Empleado")) {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				Delegate.Delegate.getInstance().username=username;
//				request.setAttribute("username", username);
				dispatcher.forward(request, response);
			}
			else if(type.equals("Cliente"))
			{
				
				
				
//				request.setAttribute("username", username);
//				System.out.println("Hola");
//				System.out.println(request.getAttribute("username").toString());
				System.out.println("Hola");
				Delegate.Delegate.getInstance().username=username;
				System.out.println(Delegate.Delegate.getInstance().username);
				RequestDispatcher dispatcher = request.getRequestDispatcher("ListarEnvios");
				dispatcher.forward(request, response);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private static String generateSessionId() throws UnsupportedEncodingException {
		
		String uid = new java.rmi.server.UID().toString(); // guaranteed unique
		return URLEncoder.encode(uid,"UTF-8"); // encode any special chars
	}

}
