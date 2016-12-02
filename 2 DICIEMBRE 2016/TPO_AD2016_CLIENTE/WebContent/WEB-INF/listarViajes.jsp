<%@ page import="dto.ViajeDTO" %>
<%@ page import="dto.EnvioDTO" %>
<%@ page import="dto.SucursalDTO" %>
<%@ page import="dto.VehiculoDTO" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<title>Aplicaciones Distribuidas Grupo 9 2C 2016</title>
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
					<li>
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
					<li class="active">
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
					<h1>Listar Viajes</h1>
					<%
						List<ViajeDTO> viajes = (List<ViajeDTO>)request.getAttribute("viajes");
					%>
					<div class="table-responsive">
						<table id="tabla" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>ID</th>
									<th>Fecha llegada</th>
									<th>Origen</th>
									<th>Destino</th>
									<th>Vehículo</th>
									<th>Finalizado</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th>ID</th>
									<th>Fecha llegada</th>
									<th>Origen</th>
									<th>Destino</th>
									<th>Vehículo</th>
									<th>Finalizado</th>
								</tr>
							</tfoot>
							<tbody>
								<%
									for(ViajeDTO viaje: viajes) {
								%>
									<tr>
										<td><%= viaje.getIdViaje() %></td>
										<td><%= viaje.getFechaLlegada() %></td>
										<td><%= viaje.getSucursalOrigen().getNombre() %></td>
										<td><%= viaje.getSucursalDestino().getNombre() %></td>
										<td><%= viaje.getVehiculo().getIdVehiculo() %></td>
										<td><%= viaje.isFinalizado() %></td>
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