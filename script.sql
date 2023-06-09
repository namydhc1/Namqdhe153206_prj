USE [master]
GO
/****** Object:  Database [Quanlykhohang]    Script Date: 4/18/2023 10:39:08 PM ******/
CREATE DATABASE [Quanlykhohang]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Quanlykhohang', FILENAME = N'E:\SQL\MSSQL15.SQLEXPRESS\MSSQL\DATA\Quanlykhohang.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Quanlykhohang_log', FILENAME = N'E:\SQL\MSSQL15.SQLEXPRESS\MSSQL\DATA\Quanlykhohang_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Quanlykhohang] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Quanlykhohang].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Quanlykhohang] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Quanlykhohang] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Quanlykhohang] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Quanlykhohang] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Quanlykhohang] SET ARITHABORT OFF 
GO
ALTER DATABASE [Quanlykhohang] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Quanlykhohang] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Quanlykhohang] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Quanlykhohang] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Quanlykhohang] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Quanlykhohang] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Quanlykhohang] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Quanlykhohang] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Quanlykhohang] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Quanlykhohang] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Quanlykhohang] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Quanlykhohang] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Quanlykhohang] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Quanlykhohang] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Quanlykhohang] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Quanlykhohang] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Quanlykhohang] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Quanlykhohang] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Quanlykhohang] SET  MULTI_USER 
GO
ALTER DATABASE [Quanlykhohang] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Quanlykhohang] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Quanlykhohang] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Quanlykhohang] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Quanlykhohang] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Quanlykhohang] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [Quanlykhohang] SET QUERY_STORE = OFF
GO
USE [Quanlykhohang]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 4/18/2023 10:39:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[displayname] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 4/18/2023 10:39:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[cid] [int] IDENTITY(1,1) NOT NULL,
	[cname] [nvarchar](150) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[cid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 4/18/2023 10:39:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[oid] [int] IDENTITY(1,1) NOT NULL,
	[profit] [float] NOT NULL,
	[order_date] [date] NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[oid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_detail]    Script Date: 4/18/2023 10:39:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_detail](
	[otid] [int] IDENTITY(1,1) NOT NULL,
	[quantity] [int] NOT NULL,
	[unitprice] [int] NOT NULL,
	[pid] [int] NOT NULL,
	[oid] [int] NOT NULL,
 CONSTRAINT [PK_Order_detail] PRIMARY KEY CLUSTERED 
(
	[otid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 4/18/2023 10:39:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[pid] [int] IDENTITY(1,1) NOT NULL,
	[pname] [nvarchar](150) NOT NULL,
	[dateofWarehousing] [date] NOT NULL,
	[purchaseMoney] [int] NOT NULL,
	[quantityWarehousing] [int] NOT NULL,
	[inventory] [int] NOT NULL,
	[unitprice] [int] NOT NULL,
	[cid] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password], [displayname]) VALUES (N'admin', N'admin', N'Admin')
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([cid], [cname]) VALUES (1, N'food')
INSERT [dbo].[Category] ([cid], [cname]) VALUES (2, N'water')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (1, N'Sting', CAST(N'2023-01-05' AS Date), 400000, 50, 57, 10000, 2)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (2, N'RedBull', CAST(N'2023-01-05' AS Date), 600000, 60, 34, 15000, 2)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (3, N'Xúc xích', CAST(N'2023-04-10' AS Date), 300000, 100, 20, 8000, 1)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (4, N'Trà sữa chân trâu đường đen', CAST(N'2023-04-10' AS Date), 1000000, 80, 70, 25000, 2)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (5, N'Bánh mì dân tổ', CAST(N'2023-04-15' AS Date), 150000, 30, 25, 20000, 1)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (6, N'Bánh bao', CAST(N'2023-04-15' AS Date), 250000, 30, 20, 15000, 1)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (7, N'Coca cola', CAST(N'2023-01-05' AS Date), 600000, 100, 48, 10000, 2)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (10, N'Samurai', CAST(N'2023-01-05' AS Date), 650000, 100, 76, 10000, 2)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (11, N'Khoai tây lốc xoáy', CAST(N'2023-04-15' AS Date), 500000, 50, 13, 23000, 1)
INSERT [dbo].[Product] ([pid], [pname], [dateofWarehousing], [purchaseMoney], [quantityWarehousing], [inventory], [unitprice], [cid]) VALUES (12, N'Khoai lang kén', CAST(N'2023-04-15' AS Date), 500000, 50, 25, 25000, 1)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Order_detail_Order] FOREIGN KEY([oid])
REFERENCES [dbo].[Order] ([oid])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK_Order_detail_Order]
GO
ALTER TABLE [dbo].[Order_detail]  WITH CHECK ADD  CONSTRAINT [FK_Order_detail_Product] FOREIGN KEY([pid])
REFERENCES [dbo].[Product] ([pid])
GO
ALTER TABLE [dbo].[Order_detail] CHECK CONSTRAINT [FK_Order_detail_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([cid])
REFERENCES [dbo].[Category] ([cid])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
USE [master]
GO
ALTER DATABASE [Quanlykhohang] SET  READ_WRITE 
GO
