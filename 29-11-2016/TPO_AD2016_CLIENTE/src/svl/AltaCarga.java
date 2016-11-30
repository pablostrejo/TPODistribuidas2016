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
			RequestDispatcher dispatcher = null;
			dispatcher = getServletContext().getRequestDispatcher("/altaCarga.jsp");
			dispatcher.forward(request, response);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/altaCarga.jsp");
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
				Delegate.getInstance().cargas.add(cargaDTO);
//				request.setAttribute("cargas", cargas);
//				Delegate.getInstance().cargas.add(cargaDTO);

				cargas.add(cargaDTO);
				request.setAttribute("itemscargas", cargas);
				//session.setAttribute("itemscargas", cargas); lo habia puesto santi
				
				doGet(request,response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
