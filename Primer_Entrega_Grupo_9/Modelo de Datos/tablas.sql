create table Producto
(
nroProducto int,
nombre varchar(50),
tipo varchar(50),
cuit int foreign key references ClienteEmpresa(cuit),
primary key (nroProducto, cuit)
)

create table Paquete
(
nroPaquete int primary key,
peso float,
ancho float,
alto float,
profundidad float,
volumen float,
fragilidad varchar(50),
tratamiento varchar(50),
apilable int,
refrigerable bit,
condiciones varchar(50),
despachado bit,
tipoMercaderia varchar(50)
)

create table Direccion
(
id int identity(1,1) primary key,
calle varchar(50),
numero int,
depto varchar(5),
piso int
)

create table Pedido
(
nroPedido int primary key,
direccionCarga int foreign key references Direccion(id),
fechaCarga date,
horaInicio datetime,
horaFin datetime,
destino int foreign key references Direccion(id),
fechaMax date,
paquete int foreign key references Paquete(nroPaquete),
precio float,
sucDestino int,
transporteDirecto bit,
avionetaPart bit
)

create table Remito
(
nroRemito int identity (1,1) primary key,
nroPedido int foreign key references Pedido(nroPedido)
)

create table Factura
(
nroFactura int identity(1,1)primary key,
nroPedido int foreign key references Pedido(nroPedido),
precio float
)

create table Sucursal
(
idSucursal int primary key,
nombre varchar(50),
direccion int foreign key references Direccion(id)
)

create table Mapa
(
idMapa int primary key,
ruta int foreign key references Ruta(idRuta)
)

create table Ruta
(
idRuta int primary key,
trayecto int foreign key references Trayecto(idTrayecto),
precio float
)

create table Trayecto
(
idTrayecto int primary key,
idOrigen int foreign key references Sucursal(idSucursal),
idDestino int foreign key references Sucursal(idSucursal),
tiempo int,
km int,
precio float
)

create table HabilitadoRetirar
(
id int primary key,
nombre varchar(50),
dni varchar(8),
nroPedido int foreign key references Pedido(nroPedido)
)

create table CuentaCorriente
(
id int primary key,
saldo float,
foreign key (id) references ClienteEmpresa(cuit)
)

create table ClienteEmpresa
(
cuit int primary key,
tipo varchar(50),
detallePoliticas varchar(max),
foreign key (cuit) references Cliente(id)
)

create table ClienteParticular
(
dni int primary key,
apellido varchar(50)
foreign key (dni) references Cliente(id)
)

create table Cliente
(
id int primary key,
nombre varchar(50)
)

create table PlanMantenimiento
(
idPlan int primary key,
diasProximoContro int,
diasDemora date,
politicaVigente int foreign key references PoliticaMantenimiento(idPoliticaMantenimiento)
)


create table PoliticaMantenimiento
(
idPoliticaMantenimiento int primary key
)


