 <%@ page import="java.util.List" %>
<%@ page import="dto.SucursalDTO" %>
<%@ page import="dto.HabilitadoDTO" %> 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
	<head>
		<meta charset="utf-8">
		<title>Aplicaciones Distribuidas</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="css/styles.css">
		<style>
			.error{
		 	color:red;
		 	display:none
		 }
		</style>
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
					<li>
						<a>Administración de Viajes</a>
						<ul>
							<li><a href="ListarViajes">Listar Viajes</a></li>
						</ul>
					</li>
					<li class="active">
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
					<%-- <%
						List<SucursalDTO> sucursales = (List<SucursalDTO>)request.getAttribute("sucursales");
						List<HabilitadoDTO> habilitados = (List<HabilitadoDTO>)request.getAttribute("habilitados");
					%> --%>
					<h1>Actualizar Ruta</h1>
					<% String error = (String)request.getAttribute("error");
					if(error != null) { %>
					<div class="ui-widget">
						<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
							<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
							<strong>Error:</strong> <%= error %></p>
						</div>
					</div>
					<% } %>

					<form action="ActualizarRuta" method="POST">
						<div class="row">
							<div class="col-md-4">
								<label for="IDDestino">ID Destino</label>
								<input type="number" name="IDDestino" id="IDDestino" class="form-control" required>
								<p id="destinoInexistente" class="error">El id de destino no existe </p>
							</div>
							<div class="col-md-4">
								<label for="IDOrigen">ID Origen</label>
								<input type="number" name="IDOrigen" id="IDOrigen" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="km">Kilometraje</label>
								<input type="number" name="km" id="km" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-4">
								<label for="precio">Precio</label>
								<input type="number" name="precio" id="precio" class="form-control" required>
							</div>
							<div class="col-md-4">
								<label for="tiempo">Tiempo</label>
								<input type="number" name="tiempo" id="tiempo" class="form-control" required>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<input class="btn btn-primary btn-block" type="submit" name="ActualizarRuta" id="ActualizarRuta" value="Actualizar Ruta" />
							</div>
						</div>
					</form>
				</section>
			</main>
		</div>
		<script>
				function validarNombreCliente() {
					var xhr = new XMLHttpRequest();
					var error;
					var desIne = document.getElementById('destinoInexistente');

					// BEGIN AJAX
					xhr.open('POST', '/ValidarDestino', true);

					xhr.onreadystatechange = function() {
						if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 201) {
							var json = JSON.parse(xhr.response);
							error = json['id'];

							if (error) {
								desIne.style.display = 'block';
							} else {
								desIne.style.display = 'none';
							}

						}
					};

					var data = "iddestino=" + document.getElementById('IDDestino').value;
					xhr.send(data);
					// END AJAX

				}
			</script>
	</body>
</html>