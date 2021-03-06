USE [AD20162C]
GO
/****** Object:  Table [dbo].[Cargas]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cargas](
	[idCarga] [int] IDENTITY(1,1) NOT NULL,
	[peso] [float] NULL,
	[ancho] [float] NULL,
	[alto] [float] NULL,
	[profundidad] [float] NULL,
	[volumen] [float] NULL,
	[fragilidad] [varchar](50) NULL,
	[tratamiento] [varchar](50) NULL,
	[apilable] [int] NULL,
	[refrigerable] [bit] NULL,
	[condiciones] [varchar](50) NULL,
	[despachado] [bit] NULL,
	[tipoMercaderia] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idCarga] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Clientes]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Clientes](
	[idCliente] [int] NOT NULL,
	[nombre] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Direcciones]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Direcciones](
	[idDireccion] [int] IDENTITY(1,1) NOT NULL,
	[calle] [varchar](50) NULL,
	[numero] [int] NULL,
	[piso] [int] NULL,
	[departamento] [varchar](50) NULL,
	[CP] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idDireccion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Empresas]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Empresas](
	[idCliente] [int] NOT NULL,
	[CUIT] [int] NULL,
	[tipo] [varchar](50) NULL,
	[detallePoliticas] [varchar](50) NULL,
	[saldoCuentaCorriente] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Envios]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Envios](
	[idEnvio] [int] IDENTITY(1,1) NOT NULL,
	[fechaSalida] [date] NULL,
	[fechaLlegada] [date] NULL,
	[cumpleCondicionesCarga] [bit] NULL,
	[estado] [varchar](50) NULL,
	[prioridad] [int] NULL,
	[idPedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idEnvio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Facturas]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Facturas](
	[idFactura] [int] IDENTITY(1,1) NOT NULL,
	[precio] [float] NULL,
	[idPedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idFactura] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Habilitados]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Habilitados](
	[dniHabilitado] [varchar](50) NOT NULL,
	[nombre] [varchar](50) NULL,
	[idCliente] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[dniHabilitado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Particulares]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Particulares](
	[idCliente] [int] NOT NULL,
	[DNI] [int] NULL,
	[apellido] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idCliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Pedidos]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pedidos](
	[idPedido] [int] IDENTITY(1,1) NOT NULL,
	[fechaCarga] [date] NULL,
	[horaInicio] [date] NULL,
	[horaFin] [date] NULL,
	[fechaMaxima] [date] NULL,
	[precio] [float] NULL,
	[solicitaTransporteDirecto] [bit] NULL,
	[solicitaAvionetaParticular] [bit] NULL,
	[idDireccionDestino] [int] NULL,
	[idCliente] [int] NULL,
	[idDireccionCarga] [int] NULL,
	[idSucursal] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idPedido] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PlanesDeMantenimiento]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlanesDeMantenimiento](
	[idPlanDeMantenimiento] [int] IDENTITY(1,1) NOT NULL,
	[diasProxControl] [int] NULL,
	[diasDemora] [int] NULL,
	[kmProxControl] [int] NULL,
	[idVehiculo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idPlanDeMantenimiento] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PreciosVehiculos]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PreciosVehiculos](
	[idPrecioVehiculo] [int] IDENTITY(1,1) NOT NULL,
	[tipoVehiculo] [varchar](50) NULL,
	[precio] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[idPrecioVehiculo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Productos]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Productos](
	[idProducto] [int] NOT NULL,
	[nombre] [varchar](50) NULL,
	[tipo] [varchar](50) NULL,
	[idCliente] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idProducto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proveedores]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedores](
	[idProveedor] [int] IDENTITY(1,1) NOT NULL,
	[compania] [varchar](50) NULL,
	[tipoMercaderia] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Remitos]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Remitos](
	[idRemito] [int] IDENTITY(1,1) NOT NULL,
	[idPedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idRemito] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Rutas]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rutas](
	[idRuta] [int] IDENTITY(1,1) NOT NULL,
	[precio] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[idRuta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Seguros]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Seguros](
	[idProveedor] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Sucursales]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Sucursales](
	[idSucursal] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NULL,
	[idDireccion] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idSucursal] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Transportes]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Transportes](
	[idProveedor] [int] NOT NULL,
	[tipoTransporte] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Trayectos]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Trayectos](
	[idTrayecto] [int] IDENTITY(1,1) NOT NULL,
	[tiempo] [date] NULL,
	[km] [int] NULL,
	[precio] [float] NULL,
	[idSucursalOrigen] [int] NULL,
	[idSucursalDestino] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idTrayecto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Vehiculos]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Vehiculos](
	[idVehiculo] [int] IDENTITY(1,1) NOT NULL,
	[tipo] [varchar](50) NULL,
	[volumen] [float] NULL,
	[peso] [float] NULL,
	[ancho] [float] NULL,
	[alto] [float] NULL,
	[profundidad] [float] NULL,
	[tara] [float] NULL,
	[kilometraje] [int] NULL,
	[estado] [varchar](50) NULL,
	[especificacion] [varchar](50) NULL,
	[fechaUltimaControl] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[idVehiculo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Viajes]    Script Date: 16/10/2016 17:26:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Viajes](
	[idViaje] [int] IDENTITY(1,1) NOT NULL,
	[fechaLlegada] [date] NULL,
	[finalizado] [bit] NULL,
	[idVehiculo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idViaje] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Cargas]  WITH CHECK ADD  CONSTRAINT [FK77E0FAA515529A5] FOREIGN KEY([idCarga])
REFERENCES [dbo].[Pedidos] ([idPedido])
GO
ALTER TABLE [dbo].[Cargas] CHECK CONSTRAINT [FK77E0FAA515529A5]
GO
ALTER TABLE [dbo].[Empresas]  WITH CHECK ADD  CONSTRAINT [FK4B4D352A2961B92C] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Clientes] ([idCliente])
GO
ALTER TABLE [dbo].[Empresas] CHECK CONSTRAINT [FK4B4D352A2961B92C]
GO
ALTER TABLE [dbo].[Envios]  WITH CHECK ADD  CONSTRAINT [FK7C03B8201DF7B3D4] FOREIGN KEY([idEnvio])
REFERENCES [dbo].[Viajes] ([idViaje])
GO
ALTER TABLE [dbo].[Envios] CHECK CONSTRAINT [FK7C03B8201DF7B3D4]
GO
ALTER TABLE [dbo].[Envios]  WITH CHECK ADD  CONSTRAINT [FK7C03B820A8C8FA52] FOREIGN KEY([idPedido])
REFERENCES [dbo].[Pedidos] ([idPedido])
GO
ALTER TABLE [dbo].[Envios] CHECK CONSTRAINT [FK7C03B820A8C8FA52]
GO
ALTER TABLE [dbo].[Facturas]  WITH CHECK ADD  CONSTRAINT [FK22580C1BA8C8FA52] FOREIGN KEY([idPedido])
REFERENCES [dbo].[Pedidos] ([idPedido])
GO
ALTER TABLE [dbo].[Facturas] CHECK CONSTRAINT [FK22580C1BA8C8FA52]
GO
ALTER TABLE [dbo].[Habilitados]  WITH CHECK ADD  CONSTRAINT [FKD892253E6752B7C1] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Particulares] ([idCliente])
GO
ALTER TABLE [dbo].[Habilitados] CHECK CONSTRAINT [FKD892253E6752B7C1]
GO
ALTER TABLE [dbo].[Particulares]  WITH CHECK ADD  CONSTRAINT [FKA80D22632961B92C] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Clientes] ([idCliente])
GO
ALTER TABLE [dbo].[Particulares] CHECK CONSTRAINT [FKA80D22632961B92C]
GO
ALTER TABLE [dbo].[Pedidos]  WITH CHECK ADD  CONSTRAINT [FK39FE69AE2961B92C] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Clientes] ([idCliente])
GO
ALTER TABLE [dbo].[Pedidos] CHECK CONSTRAINT [FK39FE69AE2961B92C]
GO
ALTER TABLE [dbo].[Pedidos]  WITH CHECK ADD  CONSTRAINT [FK39FE69AE55127D16] FOREIGN KEY([idDireccionDestino])
REFERENCES [dbo].[Direcciones] ([idDireccion])
GO
ALTER TABLE [dbo].[Pedidos] CHECK CONSTRAINT [FK39FE69AE55127D16]
GO
ALTER TABLE [dbo].[Pedidos]  WITH CHECK ADD  CONSTRAINT [FK39FE69AE7691E57C] FOREIGN KEY([idDireccionCarga])
REFERENCES [dbo].[Direcciones] ([idDireccion])
GO
ALTER TABLE [dbo].[Pedidos] CHECK CONSTRAINT [FK39FE69AE7691E57C]
GO
ALTER TABLE [dbo].[Pedidos]  WITH CHECK ADD  CONSTRAINT [FK39FE69AEA47796C8] FOREIGN KEY([idSucursal])
REFERENCES [dbo].[Sucursales] ([idSucursal])
GO
ALTER TABLE [dbo].[Pedidos] CHECK CONSTRAINT [FK39FE69AEA47796C8]
GO
ALTER TABLE [dbo].[PlanesDeMantenimiento]  WITH CHECK ADD  CONSTRAINT [FK1610E68E86A7EC92] FOREIGN KEY([idVehiculo])
REFERENCES [dbo].[Vehiculos] ([idVehiculo])
GO
ALTER TABLE [dbo].[PlanesDeMantenimiento] CHECK CONSTRAINT [FK1610E68E86A7EC92]
GO
ALTER TABLE [dbo].[Productos]  WITH CHECK ADD  CONSTRAINT [FK38C07AF3954B73BB] FOREIGN KEY([idCliente])
REFERENCES [dbo].[Empresas] ([idCliente])
GO
ALTER TABLE [dbo].[Productos] CHECK CONSTRAINT [FK38C07AF3954B73BB]
GO
ALTER TABLE [dbo].[Remitos]  WITH CHECK ADD  CONSTRAINT [FKA449EFC9A8C8FA52] FOREIGN KEY([idPedido])
REFERENCES [dbo].[Pedidos] ([idPedido])
GO
ALTER TABLE [dbo].[Remitos] CHECK CONSTRAINT [FKA449EFC9A8C8FA52]
GO
ALTER TABLE [dbo].[Seguros]  WITH CHECK ADD  CONSTRAINT [FKD8E10AF6F91DD4F4] FOREIGN KEY([idProveedor])
REFERENCES [dbo].[Proveedores] ([idProveedor])
GO
ALTER TABLE [dbo].[Seguros] CHECK CONSTRAINT [FKD8E10AF6F91DD4F4]
GO
ALTER TABLE [dbo].[Sucursales]  WITH CHECK ADD  CONSTRAINT [FK196E974EC804D998] FOREIGN KEY([idDireccion])
REFERENCES [dbo].[Direcciones] ([idDireccion])
GO
ALTER TABLE [dbo].[Sucursales] CHECK CONSTRAINT [FK196E974EC804D998]
GO
ALTER TABLE [dbo].[Transportes]  WITH CHECK ADD  CONSTRAINT [FKFD4B8577F91DD4F4] FOREIGN KEY([idProveedor])
REFERENCES [dbo].[Proveedores] ([idProveedor])
GO
ALTER TABLE [dbo].[Transportes] CHECK CONSTRAINT [FKFD4B8577F91DD4F4]
GO
ALTER TABLE [dbo].[Trayectos]  WITH CHECK ADD  CONSTRAINT [FKC82F4664EBAEC407] FOREIGN KEY([idTrayecto])
REFERENCES [dbo].[Rutas] ([idRuta])
GO
ALTER TABLE [dbo].[Trayectos] CHECK CONSTRAINT [FKC82F4664EBAEC407]
GO
ALTER TABLE [dbo].[Trayectos]  WITH CHECK ADD  CONSTRAINT [FKC82F4664F2B32532] FOREIGN KEY([idSucursalOrigen])
REFERENCES [dbo].[Sucursales] ([idSucursal])
GO
ALTER TABLE [dbo].[Trayectos] CHECK CONSTRAINT [FKC82F4664F2B32532]
GO
ALTER TABLE [dbo].[Trayectos]  WITH CHECK ADD  CONSTRAINT [FKC82F4664F4AD1F3A] FOREIGN KEY([idSucursalDestino])
REFERENCES [dbo].[Sucursales] ([idSucursal])
GO
ALTER TABLE [dbo].[Trayectos] CHECK CONSTRAINT [FKC82F4664F4AD1F3A]
GO
ALTER TABLE [dbo].[Viajes]  WITH CHECK ADD  CONSTRAINT [FK98B61BEA64697BBB] FOREIGN KEY([idViaje])
REFERENCES [dbo].[Sucursales] ([idSucursal])
GO
ALTER TABLE [dbo].[Viajes] CHECK CONSTRAINT [FK98B61BEA64697BBB]
GO
ALTER TABLE [dbo].[Viajes]  WITH CHECK ADD  CONSTRAINT [FK98B61BEA86A7EC92] FOREIGN KEY([idVehiculo])
REFERENCES [dbo].[Vehiculos] ([idVehiculo])
GO
ALTER TABLE [dbo].[Viajes] CHECK CONSTRAINT [FK98B61BEA86A7EC92]
GO
