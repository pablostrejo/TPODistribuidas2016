<%@ page import="java.util.List" %>
<%@ page import="dto.PedidoDTO" %>
<%@ page import="dto.DireccionDTO" %>
<%@ page import="dto.CargaDTO" %>
<%@ page import="dto.ClienteDTO" %>

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
						<a href="index.jsp">Home</a>
					</li>
					<li>
						<a href="AltaCarga">Alta de Cargas</a>
					</li>
					<li>
						<a>Administraci�n de Pedidos</a>
						<ul>
							<li><a href="AltaCarga">Alta de Pedidos</a></li>
							<li><a href="ListarPedidos">Listado de Pedidos</a></li>
							<li><a href="ControlPedido">Controlar Pedidos</a></li>
						</ul>
					</li>
					<li class="active">
						<a>Administraci�n de Env�os</a>
						<ul>
							<li><a href="ContratarProveedor.jsp">Contrataci�n Proveedor para Env�o al Exterior</a></li>
							<li><a href="GestionDeSegurosYVehiculos">Gesti�n de Seguros y Veh�culos</a></li>
							<li><a href="Despachar">Despachar</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Viajes</a>
						<ul>
							<li><a href="ListarViajes">Listar Viajes</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Rutas</a>
						<ul>
							<li><a href="ActualizarRuta">Actualizar Ruta</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Veh�culos</a>
						<ul>
							<li><a href="ControlarVehiculo">Controlar Veh�culo</a></li>
						</ul>
					</li>
				</ul>
			</nav>
			<main class="col-md-9">
				<section class="col-md-12">
					<h1>Listar Pedidos</h1>
					<%
						List<PedidoDTO> pedidos = (List<PedidoDTO>)request.getAttribute("pedidos");
					%>
					<div class="table-responsive">
						<table id="tabla" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Direcci�n carga</th>
									<th>Direcci�n destino</th>
									<th>Fecha carga</th>
									<th>Fecha m�xima</th>
									<th>Cargas</th>
									<th>Estado</th>
									<th>Precio</th>
									<th>Sucursal Origen</th>
									<th>Sucursal destino</th>
									<th>Cliente</th>
									<th>Transporte directo</th>
									<th>Avioneta particular</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>ID</th>
									<th>Direcci�n carga</th>
									<th>Direcci�n destino</th>
									<th>Fecha carga</th>
									<th>Fecha m�xima</th>
									<th>Cargas</th>
									<th>Estado</th>
									<th>Precio</th>
									<th>Sucursal Origen</th>
									<th>Sucursal destino</th>
									<th>Cliente</th>
									<th>Transporte directo</th>
									<th>Avioneta particular</th>
								</tr>
							</tfoot>
							<tbody>
								<%
									for(PedidoDTO pedido: pedidos) {
								%>
									<tr>
										<td><%= pedido.getIdPedido() %></td>
										<td><%= pedido.getDireccionCarga().getCalle() %>&nbsp;<%= pedido.getDireccionCarga().getNumero() %>&nbsp;<%= pedido.getDireccionCarga().getPiso() %>&nbsp;<%= pedido.getDireccionCarga().getDepartamento() %></td>
										<td><%= pedido.getDireccionDestino().getCalle() %>&nbsp;<%= pedido.getDireccionDestino().getNumero() %>&nbsp;<%= pedido.getDireccionDestino().getPiso() %>&nbsp;<%= pedido.getDireccionDestino().getDepartamento() %></td>
										<td><%= pedido.getFechaCarga() %></td>
										<td><%= pedido.getFechaMaxima() %></td>
										<td><a href="ListarCargas?id=<%= pedido.getIdPedido() %>">Ver</a></td>
										<td><%= pedido.getEstado() %></td>
										<td><%= pedido.getPrecio() %></td>
										<td><%= pedido.getSucursalOrigen() %></td>
										<td><%= pedido.getSucursalDestino() %></td>
										<td><%= pedido.getCliente().getIdCliente() %></td>
										<td><%= pedido.isSolicitaTransporteDirecto() %></td>
										<td><%= pedido.isSolicitaAvionetaParticular() %></td>
									</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</section>
			</main>
		</div>
	</body>
</html>