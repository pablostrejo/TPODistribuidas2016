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
						<a>Administraci�n de Pedidos</a>
						<ul>
							<li><a href="altaCarga.jsp">Alta de Pedidos</a></li>
							<li><a href="listarPedidos.jsp">Listado de Pedidos</a></li>
							<li><a href="controlPedido">Controlar Pedidos</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Env�os</a>
						<ul>
							<li><a href="contratarProveedor.jsp">Contrataci�n Proveedor para Env�o al Exterior</a></li>
							<li><a href="gestionDeSegurosYVehiculos">Gesti�n de Seguros y Veh�culos</a></li>
							<li><a href="despachar.jsp">Despachar</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Viajes</a>
						<ul>
							<li><a href="listarViajes.jsp">Listar Viajes</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Rutas</a>
						<ul>
							<li><a href="actualizarRuta.jsp">Actualizar Ruta</a></li>
						</ul>
					</li>
					<li>
						<a>Administraci�n de Veh�culos</a>
						<ul>
							<li><a href="controlarVehiculo.jsp">Controlar Veh�culo</a></li>
						</ul>
					</li>
				</ul>
			</nav>
			<main class="col-md-9">
				<section class="col-md-12">
					<h1>Der gro�e Verteiler</h1>
					<p>La empresa de distribuci�n y env�os <strong>Der gro�e Verteiler</strong>  tienen gran reputaci�n y fama gracias a su esmerada atenci�n y responsabilidad en el tratamiento de las cargas que env�an sus clientes, es por esto que para mantener y superar sus est�ndares de calidad y facilitar la administraci�n de la empresa decidi� la implementaci�n de un sistema inform�tico que le permita llevar un mayor control sobre sus operaciones, tanto en lo que respecta a atenci�n al cliente y control de las cargas como a sus �reas operativas.</p>
					<p>Cuenta con m�s de 50 sucursales en todo el pa�s (la misma no opera fuera del pa�s directamente sino a trav�s de carriers internacionales) y existen por lo menos una sucursal en cada una de las provincia (por lo general en la capital de la misma o muy cerca de ellas) y en alguna provincias con alto tr�fico de env�os puede haber hasta 4 (por ejemplo Buenos Aires y Mendoza).</p>
					<p>El circuito general de un paquete es el siguiente:</p>
					<ul>
						<li>El cliente gestiona el env�o en recepci�n.</li>
						<li>Recepci�n lo registra y lo ingresa en un dep�sito intermedio hasta que sea incluido en alg�n viaje.</li>
						<li>El �rea de planificaci�n de viajes cuando el sistema de indica que tiene un env�o intersucursal conformado controla y genera la documentaci�n y los seguros pertinentes.</li>
						<li>La sucursal de destino recibe el env�o intersucursal y lo verifica y lo ingresa en un dep�sito intermedio hasta que sea retirado de la sucursal o bien despachado hasta el domicilio indicado por el cliente al realizar el pedido de env�o.</li>
						<li>El cobro del env�o puede ser en el origen o en una cuenta corriente seg�n el tipo de cliente y/o lo solicitado al gestionarlo.</li>
					</ul>
				</section>
			</main>
		</div>
		<footer class="row">
			TPO - Distribuidas Grupo 9 &reg; 2C 2016
		</footer>
	</body>
</html>