package svl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CargaDTO;
import dto.ClienteDTO;
import dto.DireccionDTO;
import dto.PedidoDTO;
import dto.SucursalDTO;
import Delegate.Delegate;
@WebServlet("/AltaPedido")
public class AltaPedido extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
   
    public AltaPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/altaPedido.jsp");
			
			List<SucursalDTO> sucursales = Delegate.getInstance().obtenerSucursales();
			request.setAttribute("sucursales", sucursales);
			
			List<ClienteDTO> clientes = Delegate.getInstance().obtenerClientes();
			request.setAttribute("clientes", clientes);
			
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			//Obtengo todos los datos de la pantalla
			String calleCarga = request.getParameter("calleCarga");
			String numeroCarga = request.getParameter("numeroCarga");
			String pisoCarga = request.getParameter("pisoCarga");
			String deptoCarga = request.getParameter("deptoCarga");
			String cpCarga = request.getParameter("cpCarga");
			String calleDestino = request.getParameter("calleDestino");
			String numeroDestino = request.getParameter("numeroDestino");
			String pisoDestino = request.getParameter("pisoDestino");
			String deptoDestino = request.getParameter("deptoDestino");
			String cpDestino = request.getParameter("cpDestino");
			String fechaCarga = request.getParameter("fechaCarga");
			String horaInicio = request.getParameter("horaInicio");
			String horaFin = request.getParameter("horaFin");
			String fechaMaxima = request.getParameter("fechaMaxima");
			String sucursalOrigen = request.getParameter("sucursalOrigen");
			String sucursalDestino = request.getParameter("sucursalDestino");
			String solicitaTransporteDirecto = request.getParameter("solicitaTransporteDirecto");
			String solicitaAvionetaParticular = request.getParameter("solicitaAvionetaParticular");
			String cliente = request.getParameter("cliente");
			
			//Si alguno de los datos esta vacio
			if (calleCarga.length() == 0 || numeroCarga.length() == 0 || pisoCarga.length() == 0 || deptoCarga.length() == 0 || cpCarga.length() == 0 ||
				calleDestino.length() == 0 || numeroDestino.length() == 0 || pisoDestino.length() == 0 || deptoDestino.length() == 0 ||
				cpDestino.length() == 0 || fechaCarga.length() == 0 || horaInicio.length() == 0 || horaFin.length() == 0 || fechaMaxima.length() == 0 ||
				sucursalOrigen.length() == 0 || sucursalDestino.length() == 0  || cliente.length() == 0) {
				request.setAttribute("calleCarga", calleCarga);
				request.setAttribute("numeroCarga", numeroCarga);
				request.setAttribute("pisoCarga", pisoCarga);
				request.setAttribute("deptoCarga", deptoCarga);
				request.setAttribute("cpCarga", cpCarga);
				request.setAttribute("calleDestino", calleDestino);
				request.setAttribute("numeroDestino", numeroDestino);
				request.setAttribute("pisoDestino", pisoDestino);
				request.setAttribute("deptoDestino", deptoDestino);
				request.setAttribute("cpDestino", cpDestino);
				request.setAttribute("fechaCarga", fechaCarga);
				request.setAttribute("horaInicio", horaInicio);
				request.setAttribute("horaFin", horaFin);
				request.setAttribute("fechaMaxima", fechaMaxima);
				request.setAttribute("sucursalOrigen", sucursalOrigen);
				request.setAttribute("sucursalDestino", sucursalDestino);
				if (solicitaTransporteDirecto == null) {
					request.setAttribute("solicitaTransporteDirecto", "=unchecked");
				}
				else {
					request.setAttribute("solicitaTransporteDirecto", "=checked");
				}
				if (solicitaAvionetaParticular == null) {
					request.setAttribute("solicitaAvionetaParticular", "=unchecked");
				}
				else {
					request.setAttribute("solicitaAvionetaParticular", "=checked");
				}
				request.setAttribute("cliente", cliente);
				request.setAttribute("error", "Se deben ingresar todos los datos solicitados.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/altaPedido.jsp");
				dispatcher.forward(request, response);
			}
			
			//Todos los datos datos se cargaron de forma correcta
			else {
				//Parseo los datos
				int numeroCargaInt = Integer.parseInt(numeroCarga);
				int pisoCargaInt = Integer.parseInt(pisoCarga);
				DireccionDTO direccionCargaDTO = new DireccionDTO(0, calleCarga, numeroCargaInt, pisoCargaInt, deptoCarga, cpCarga);
				int numeroDestinoInt = Integer.parseInt(numeroDestino);
				int pisoDestinoInt = Integer.parseInt(pisoDestino);
				DireccionDTO direccionDestinoDTO = new DireccionDTO(0, calleDestino, numeroDestinoInt, pisoDestinoInt, deptoDestino, cpDestino);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				Date fechaCargaDate = sdf.parse(fechaCarga);
				int horaInicioInt = Integer.valueOf(horaInicio.substring(0,1));
				int horaFinInt = Integer.valueOf(horaFin.substring(0,1));;
				Date fechaMaximaDate = sdf.parse(fechaMaxima);
				boolean solicitaTransporteDirectoBool = Boolean.parseBoolean(solicitaTransporteDirecto);
				boolean solicitaAvionetaParticularBool = Boolean.parseBoolean(solicitaAvionetaParticular);
				ClienteDTO clienteDTO = Delegate.getInstance().obtenerClientePorID(Integer.parseInt(cliente));
				List<CargaDTO> cargasDTO = Delegate.getInstance().cargas;
				System.out.println(cargasDTO.get(0).getPeso());
				int cant=cargasDTO.size();
				for(int i=cant/2;i<cant;i++)
				{
					cargasDTO.remove(cant/2);
					
				}
				
				//Genero un PedidoDTO
				PedidoDTO pedidoDTO = new PedidoDTO(0, direccionCargaDTO, direccionDestinoDTO, fechaCargaDate, horaInicioInt, horaFinInt, fechaMaximaDate, 
													cargasDTO, 0, sucursalDestino, sucursalOrigen, solicitaTransporteDirectoBool, 
													solicitaAvionetaParticularBool, clienteDTO);
				//Proceso el alta
				Delegate.getInstance().altaPedido(pedidoDTO);
				Delegate.getInstance().cargas=new ArrayList<CargaDTO>();
			 			 
	            Delegate.getInstance().despachar();
			}
	        
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
