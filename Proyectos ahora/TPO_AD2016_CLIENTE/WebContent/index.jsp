<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
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
					<li class="active">
						<a href="index">Home</a>
					</li>
					<li>
						<a href="altaCarga.jsp">Alta de Cargas</a>
					</li>
					<li>
						<a>Administración de Pedidos</a>
						<ul>
							<li><a href="altaCarga.jsp">Alta de Pedidos</a></li>
							<li><a href="listarPedidos.jsp">Listado de Pedidos</a></li>
							<li><a href="controlPedido">Controlar Pedidos</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Envíos</a>
						<ul>
							<li><a href="contratarProveedor.jsp">Contratación Proveedor para Envío al Exterior</a></li>
							<li><a href="gestionDeSegurosYVehiculos">Gestión de Seguros y Vehículos</a></li>
							<li><a href="despachar.jsp">Despachar</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Viajes</a>
						<ul>
							<li><a href="listarViajes.jsp">Listar Viajes</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Rutas</a>
						<ul>
							<li><a href="actualizarRuta.jsp">Actualizar Ruta</a></li>
						</ul>
					</li>
					<li>
						<a>Administración de Vehículos</a>
						<ul>
							<li><a href="controlarVehiculo.jsp">Controlar Vehículo</a></li>
						</ul>
					</li>
				</ul>
			</nav>
			<main class="col-md-9">
				<section class="col-md-12">
					<h1>Der große Verteiler</h1>
					<p>La empresa de distribución y envíos <strong>Der große Verteiler</strong>  tienen gran reputación y fama gracias a su esmerada atención y responsabilidad en el tratamiento de las cargas que envían sus clientes, es por esto que para mantener y superar sus estándares de calidad y facilitar la administración de la empresa decidió la implementación de un sistema informático que le permita llevar un mayor control sobre sus operaciones, tanto en lo que respecta a atención al cliente y control de las cargas como a sus áreas operativas.</p>
					<p>Cuenta con más de 50 sucursales en todo el país (la misma no opera fuera del país directamente sino a través de carriers internacionales) y existen por lo menos una sucursal en cada una de las provincia (por lo general en la capital de la misma o muy cerca de ellas) y en alguna provincias con alto tráfico de envíos puede haber hasta 4 (por ejemplo Buenos Aires y Mendoza).</p>
					<p>El circuito general de un paquete es el siguiente:</p>
					<ul>
						<li>El cliente gestiona el envío en recepción.</li>
						<li>Recepción lo registra y lo ingresa en un depósito intermedio hasta que sea incluido en algún viaje.</li>
						<li>El área de planificación de viajes cuando el sistema de indica que tiene un envío intersucursal conformado controla y genera la documentación y los seguros pertinentes.</li>
						<li>La sucursal de destino recibe el envío intersucursal y lo verifica y lo ingresa en un depósito intermedio hasta que sea retirado de la sucursal o bien despachado hasta el domicilio indicado por el cliente al realizar el pedido de envío.</li>
						<li>El cobro del envío puede ser en el origen o en una cuenta corriente según el tipo de cliente y/o lo solicitado al gestionarlo.</li>
					</ul>
				</section>
			</main>
		</div>
		<footer class="row">
			TPO - Distribuidas Grupo 9 &reg; 2C 2016
		</footer>
	</body>
</html>