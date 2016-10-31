package svl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Delegate.Delegate;
import dto.CargaDTO;


@WebServlet("/AltaCarga")
public class AltaCarga extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static List<CargaDTO> cargas = new ArrayList<CargaDTO>();
 
    public AltaCarga() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String sessionid = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("sessionid")) {
						sessionid = cookies[i].getValue();
						break;
					}
				}
			}
			// If the session ID wasn't sent, generate one.
			// Then be sure to send it to the client with the response.
			if (sessionid == null) {
				sessionid = generateSessionId();
				Cookie c = new Cookie("sessionid", sessionid);
				response.addCookie(c);
			}

			// Cart items are associated with the session ID
			List<CargaDTO> items = getItemsFromCart(sessionid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/altaCarga.jsp");
			dispatcher.forward(request, response);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			//Obtengo todos los datos de la pantalla
			String peso = request.getParameter("peso");
			String ancho = request.getParameter("ancho");
			String alto = request.getParameter("alto");
			String profundidad = request.getParameter("profundidad");
			String volumen = request.getParameter("volumen");
			String fragilidad = request.getParameter("fragilidad");
			String tratamiento = request.getParameter("tratamiento");
			String apilable = request.getParameter("apilable");
			String refrigerable = request.getParameter("refrigerable");
			String condiciones = request.getParameter("condiciones");
			String despachado = request.getParameter("despachado");
			String tipoDeMercaderia = request.getParameter("tipoDeMercaderia");
			
			//Si alguno de los datos esta vacio
			if (peso.length() == 0 || ancho.length() == 0 || alto.length() == 0 || profundidad.length() == 0 || volumen.length() == 0 || fragilidad.length() == 0 ||
				tratamiento.length() == 0 || apilable.length() == 0 || condiciones.length() == 0 || tipoDeMercaderia.length() == 0) {
				request.setAttribute("peso", peso);
				request.setAttribute("ancho", ancho);
				request.setAttribute("alto", alto);
				request.setAttribute("profundidad", profundidad);
				request.setAttribute("volumen", volumen);
				request.setAttribute("fragilidad", fragilidad);
				request.setAttribute("tratamiento", tratamiento);
				request.setAttribute("apilable", apilable);
				if (refrigerable == null) {
					request.setAttribute("refrigerable", "=unchecked");
				}
				else {
					request.setAttribute("refrigerable", "=checked");
				}
				request.setAttribute("condiciones", condiciones);
				if (despachado == null) {
					request.setAttribute("despachado", "=unchecked");
				}
				else {
					request.setAttribute("despachado", "=checked");
				}
				request.setAttribute("tipoDeMercaderia", apilable);
				request.setAttribute("error", "Se deben ingresar todos los datos solicitados.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/altaCarga.jsp");
				dispatcher.forward(request, response);
			}
			
			//Todos los datos datos se cargaron de forma correcta
			else {
				//Parseo los datos
				float pesoFloat = Float.parseFloat(peso);
				float anchoFloat = Float.parseFloat(ancho);
				float altoFloat = Float.parseFloat(alto);
				float profundidadFloat = Float.parseFloat(profundidad);
				float volumenFloat = Float.parseFloat(volumen);
				int apilableInt = Integer.parseInt(apilable);
				boolean refrigerableBool = Boolean.parseBoolean(refrigerable);
				boolean despachadoBool = Boolean.parseBoolean(despachado);

				//Genero una CargaDTO
				CargaDTO cargaDTO = new CargaDTO (0,pesoFloat, anchoFloat, altoFloat, profundidadFloat, volumenFloat, fragilidad, tratamiento, apilableInt,
						refrigerableBool, condiciones, despachadoBool, tipoDeMercaderia);
				//Proceso el alta
				
				// ACA EL SERVLET DEBE LLAMAR AL BUSINESS DELEGATE, A UNA FUNCION QUE SE LLAMA altaCarga(cargaDTO)
//				Delegate.getInstance().cargas.add(cargaDTO);
//				request.setAttribute("cargas", cargas);
//				Delegate.getInstance().cargas.add(cargaDTO);

				cargas.add(cargaDTO);
				session.setAttribute("itemscargas", cargas);
				
				// ESTO DE ABAJO DEBERIA ESTAR EN LA PANTALLA QUE APARECE CUANDO PRESIONA "Continuar"
				List<CargaDTO> cargas = (List<CargaDTO>) session.getAttribute("itemscargas");

				doGet(request,response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static String generateSessionId() throws UnsupportedEncodingException {
		
		String uid = new java.rmi.server.UID().toString(); // guaranteed unique
		return URLEncoder.encode(uid,"UTF-8"); // encode any special chars
	}
	
	private static List<CargaDTO> getItemsFromCart(String sessionid) {
		
		return cargas;
	}
}
