<%@page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.SucursalDTO" %>
<%@ page import="dto.HabilitadoDTO" %>
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
	<body>
		<%
			List<SucursalDTO> sucursales = (List<SucursalDTO>) request.getAttribute("sucursales");
			List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
			
			
			// AGREGADO PARA MOSTRAR ALGO!
			//if (sucursales == null) sucursales = new ArrayList<SucursalDTO>();
			//if (clientes == null) clientes = new ArrayList<ClienteDTO>();
			
			
		%>
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
					<h1>Alta Pedido</h1>
					<% String error = (String)request.getAttribute("error");
					if(error != null) { %>
					<div class="ui-widget">
						<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
							<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
							<strong>Error:</strong> <%= error %></p>
						</div>
					</div>
					<% } %>
					<form action="AltaPedido" method="POST">
						<div class="row">
							<h2>Dirección de carga</h2>
							<div class="col-md-4">
								<label for="calleCarga">Calle</label>
								<input type="text" name="calleCarga" id="calleCarga" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="numeroCarga">Número</label>
								<input type="number" name="numeroCarga" id="numeroCarga" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="pisoCarga">Piso</label>
								<input type="numeric" name="pisoCarga" id="pisoCarga" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="deptoCarga">Departamento</label>
								<input type="text" name="deptoCarga" id="deptoCarga" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="cpCarga">Código Postal</label>
								<input type="number" name="cpCarga" id="cpCarga" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<h2>Dirección de destino</h2>
							<div class="col-md-4">
								<label for="calleDestino">Calle</label>
								<input type="text" name="calleDestino" id="calleDestino" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="numeroDestino">Número</label>
								<input type="number" name="numeroDestino" id="numeroDestino" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="pisoDestino">Piso</label>
								<input type="numeric" name="pisoDestino" id="pisoDestino" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="deptoDestino">Departamento</label>
								<input type="text" name="deptoDestino" id="deptoDestino" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="cpDestino">Código Postal</label>
								<input type="number" name="cpDestino" id="cpDestino" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="solicitaTransporte">Solicita transporte al exterior</label>
								<select id="solicitaTransporte" name="solicitaTransporte" class="form-control" required>
									<option selected="selected">Seleccione una opcion...</option>
									<option value="opcion">No Solicita</option>
									<option value="opcion">Avion</option>
									<option value="opcion">Avioneta</option>
									<option value="opcion">Barco</option>
								</select>
							</div>
							<div class="col-md-4">
								<label for="fechaCarga">Fecha de carga</label>
								<input type="date" name="fechaCarga" id="fechaCarga" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="horaInicio">Hora de inicio</label>
								<input type="time" name="horaInicio" id="horaInicio" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="horaFin">Hora de fin</label>
								<input type="time" name="horaFin" id="horaFin" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="fechaMaxima">Fecha máxima</label>
								<input type="date" name="fechaMaxima" id="fechaMaxima" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="sucursalOrigen">Sucursal de origen</label>
								<select name="sucursalOrigen" id="sucursalOrigen" class="form-control" required>
									<option selected="selected">Seleccione una opcion...</option>
									<%
									for(SucursalDTO s: sucursales)
									{
									%>
										<option value=<%= s.getIdSucursal() %>><%= s.getIdSucursal() %></option>
									<%
									}
									%>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="sucursalDestino">Sucursal de destino</label>
								<select name="sucursalDestino" id="sucursalDestino" class="form-control" required>
									<option selected="selected">Seleccione una opcion...</option>
									<%
									for(SucursalDTO s: sucursales)
									{
									%>
										<option value=<%= s.getIdSucursal() %>><%= s.getIdSucursal() %></option>
									<%
									}
									%>
								</select>
							</div>
							<div class="col-md-4">
								<label for="solicitaTransporteDirecto">Solicita transporte directo</label>
								<input type="checkbox" name="solicitaTransporteDirecto" id="solicitaTransporteDirecto" required>
							</div>
							<div class="col-md-4">
								<label for="solicitaAvionetaParticular">Solicita avioneta particular</label>
								<input type="checkbox" name="solicitaAvionetaParticular" id="solicitaAvionetaParticular" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="cliente">Cliente</label>
								<select name="cliente" id="cliente" class="form-control" required>
									<option selected="selected">Seleccione una opcion...</option>
									<%
									for(ClienteDTO c: clientes)
									{
									%>
										<option value=<%= c.getIdCliente() %>><%= c.getIdCliente() %></option>
									<%
									}
									%>
								</select>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<input class="btn btn-primary btn-block" type="submit" name="enviar" id="enviar" value="Enviar" />
							</div>
						</div>
					</form>
				</section>
			</main>
		</div>
	</body>
</html>