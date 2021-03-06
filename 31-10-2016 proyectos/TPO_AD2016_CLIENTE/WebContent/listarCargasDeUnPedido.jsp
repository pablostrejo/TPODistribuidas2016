<%@ page import="java.util.List" %>
<%@ page import="dto.CargaDTO" %>
<%@ page import="dto.PedidoDTO" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>Aplicaciones Distribuidas</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="css/styles.css">
	</head>
	<body class="container-fluid">
		<header class="row">
			
		</header>
		<div class="row">
			<nav class="col-md-3">
				<ul class="nav nav-pills nav-stacked">
					<li>
						<a href="index">Home</a>
					</li>
					<li>
						<a href="AltaCarga">Alta de Cargas</a>
					</li>
					<li class="active">
						<a>Administración de Pedidos</a>
						<ul>
							<li><a href="AltaCarga">Alta de Pedidos</a></li>
							<li><a href="ListarPedidos">Listado de Pedidos</a></li>
							<li><a href="ControlPedido">Controlar Pedidos</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Envíos</a>
						<ul>
							<li><a href="ContratarProveedor.jsp">Contratación Proveedor para Envío al Exterior</a></li>
							<li><a href="GestionDeSegurosYVehiculos">Gestión de Seguros y Vehículos</a></li>
							<li><a href="Despachar">Despachar</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Viajes</a>
						<ul>
							<li><a href="ListarViajes">Listar Viajes</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Rutas</a>
						<ul>
							<li><a href="ActualizarRuta">Actualizar Ruta</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Vehículos</a>
						<ul>
							<li><a href="ControlarVehiculo">Controlar Vehículo</a></li>
						</ul>
					</li>
				</ul>
			</nav>
			<main class="col-md-9">
				<section class="col-md-12">
					<h1>Listar Cargas de un Pedido</h1>
					<%
						PedidoDTO pedido = (PedidoDTO) request.getAttribute("pedido");
						List<CargaDTO> cargas = pedido.getCargas();
					%>
					<div id="pedido">
						<h1>Pedido #<%= pedido.getIdPedido() %></h1>
						<div id="datosPedido">
							<li>
								<strong>Dirección carga</strong>: <%= pedido.getDireccionCarga().getCalle() %>&nbsp;<%= pedido.getDireccionCarga().getNumero() %>&nbsp;<%= pedido.getDireccionCarga().getPiso() %>&nbsp;<%= pedido.getDireccionCarga().getDepartamento() %>
							</li>
							<li>
								<strong>Dirección destino</strong>: <%= pedido.getDireccionDestino().getCalle() %>&nbsp;<%= pedido.getDireccionDestino().getNumero() %>&nbsp;<%= pedido.getDireccionDestino().getPiso() %>&nbsp;<%= pedido.getDireccionDestino().getDepartamento() %>
							</li>
							<li>
								<strong>Fecha carga</strong>: <%= pedido.getFechaCarga() %>
							</li>
							<li>
								<strong>Fecha máxima</strong>: <%= pedido.getFechaMaxima() %>
							</li>
							<li>
								<strong>Precio</strong>: <%= pedido.getPrecio() %>
							</li>
							<li>
								<strong>Sucursal destino</strong>: <%= pedido.getSucursalDestino() %>
							</li>
							<li>
								<strong>Cliente</strong>: <%= pedido.getCliente().getIdCliente() %>
							</li>
						</div>
						<h2>Cargas</h2>
						<div class="table-responsive">
							<table id="tabla" class="table table-striped table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>Peso</th>
										<th>Ancho</th>
										<th>Alto</th>
										<th>Profundidad</th>
										<th>Volumen</th>
										<th>Fragilidad</th>
										<th>Tratamiento</th>
										<th>Apilable</th>
										<th>Refrigerable</th>
										<th>Condiciones</th>
										<th>Despachado</th>
										<th>Tipo de Mercadería</th>	
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>ID</th>
										<th>Peso</th>
										<th>Ancho</th>
										<th>Alto</th>
										<th>Profundidad</th>
										<th>Volumen</th>
										<th>Fragilidad</th>
										<th>Tratamiento</th>
										<th>Apilable</th>
										<th>Refrigerable</th>
										<th>Condiciones</th>
										<th>Despachado</th>
										<th>Tipo de Mercadería</th>	
									</tr>
								</tfoot>
								<tbody>
									<%
										for(CargaDTO carga: cargas) {
									%>
										<tr>
											<td><%= carga.getIdCarga() %></td>
											<td><%= carga.getPeso() %></td>
											<td><%= carga.getAncho() %></td>
											<td><%= carga.getAlto() %></td>
											<td><%= carga.getProfundidad() %></td>
											<td><%= carga.getVolumen() %></td>
											<td><%= carga.getFragilidad() %></td>
											<td><%= carga.getTratamiento() %></td>
											<td><%= carga.getApilable() %></td>
											<td><%= carga.isRefrigerable() %></td>
											<td><%= carga.getCondiciones() %></td>
											<td><%= carga.isDespachado() %></td>
											<td><%= carga.getTipoMercaderia() %></td>
										</tr>
									<%
										}
									%>
								</tbody>
							</table>
						</div>
					</div>
				</section>
			</main>
		</div>
	</body>
</html>
