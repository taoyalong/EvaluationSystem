USE [master]
GO
/****** Object:  Database [EvaluationSystem]    Script Date: 06/08/2020 11:38:57 ******/
CREATE DATABASE [EvaluationSystem] ON  PRIMARY 
( NAME = N'EvaluationSystem', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\EvaluationSystem.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'EvaluationSystem_log', FILENAME = N'D:\Program Files\Microsoft SQL Server\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\EvaluationSystem_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [EvaluationSystem] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [EvaluationSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [EvaluationSystem] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [EvaluationSystem] SET ANSI_NULLS OFF
GO
ALTER DATABASE [EvaluationSystem] SET ANSI_PADDING OFF
GO
ALTER DATABASE [EvaluationSystem] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [EvaluationSystem] SET ARITHABORT OFF
GO
ALTER DATABASE [EvaluationSystem] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [EvaluationSystem] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [EvaluationSystem] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [EvaluationSystem] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [EvaluationSystem] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [EvaluationSystem] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [EvaluationSystem] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [EvaluationSystem] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [EvaluationSystem] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [EvaluationSystem] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [EvaluationSystem] SET  DISABLE_BROKER
GO
ALTER DATABASE [EvaluationSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [EvaluationSystem] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [EvaluationSystem] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [EvaluationSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [EvaluationSystem] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [EvaluationSystem] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [EvaluationSystem] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [EvaluationSystem] SET  READ_WRITE
GO
ALTER DATABASE [EvaluationSystem] SET RECOVERY SIMPLE
GO
ALTER DATABASE [EvaluationSystem] SET  MULTI_USER
GO
ALTER DATABASE [EvaluationSystem] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [EvaluationSystem] SET DB_CHAINING OFF
GO
USE [EvaluationSystem]
GO
/****** Object:  Table [dbo].[sysdtslog90]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sysdtslog90](
	[id] [int] NOT NULL,
	[event] [nvarchar](128) NOT NULL,
	[computer] [nvarchar](128) NOT NULL,
	[operator] [nvarchar](128) NOT NULL,
	[source] [nvarchar](1024) NOT NULL,
	[sourceid] [uniqueidentifier] NOT NULL,
	[executionid] [uniqueidentifier] NOT NULL,
	[starttime] [datetime] NOT NULL,
	[endtime] [datetime] NOT NULL,
	[datacode] [int] NOT NULL,
	[databytes] [image] NULL,
	[message] [nvarchar](2048) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[metadata_valve]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[metadata_valve](
	[vid] [varchar](30) NOT NULL,
	[vtype] [varchar](30) NULL,
	[vpressure] [varchar](30) NULL,
	[vflow] [varchar](30) NULL,
	[vtemperature] [varchar](30) NULL,
	[vcaliber] [varchar](30) NULL,
	[vlength] [varchar](30) NULL,
	[vthickness] [varchar](30) NULL,
 CONSTRAINT [PK_metadata_valve] PRIMARY KEY CLUSTERED 
(
	[vid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[metadata_pipeline]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[metadata_pipeline](
	[pid] [varchar](30) NOT NULL,
	[ptype] [varchar](30) NULL,
	[ppressure] [varchar](30) NULL,
	[pflow] [varchar](30) NULL,
	[ptemperature] [varchar](30) NULL,
	[pcaliber] [varchar](30) NULL,
	[plength] [varchar](30) NULL,
	[pthickness] [varchar](30) NULL,
 CONSTRAINT [PK_metadata_pipeline] PRIMARY KEY CLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[metadata_elbow]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[metadata_elbow](
	[eid] [varchar](30) NOT NULL,
	[etype] [varchar](30) NULL,
	[epressure] [varchar](30) NULL,
	[eflow] [varchar](30) NULL,
	[etemperature] [varchar](30) NULL,
	[ecaliber] [varchar](30) NULL,
	[ethickness] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[eid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[evaluate_valve]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[evaluate_valve](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[vid] [varchar](30) NULL,
	[vname] [varchar](30) NULL,
	[vtime] [datetime] NULL,
	[vspeed] [varchar](30) NULL,
	[vcaliber] [varchar](30) NULL,
	[vlength] [varchar](30) NULL,
	[vexternaltemperature] [varchar](30) NULL,
	[vambienttemperature] [varchar](30) NULL,
	[vheatloss] [varchar](30) NULL,
	[vresult] [varchar](30) NULL,
 CONSTRAINT [PK_evaluate_valve] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[evaluate_pipeline]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[evaluate_pipeline](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[pid] [varchar](30) NULL,
	[pname] [varchar](30) NULL,
	[ptime] [datetime] NULL,
	[pspeed] [varchar](30) NULL,
	[pcaliber] [varchar](30) NULL,
	[plength] [varchar](30) NULL,
	[pexternal_temperature] [varchar](30) NULL,
	[pambient_temperature] [varchar](30) NULL,
	[pheat_loss] [varchar](30) NULL,
	[presult] [varchar](30) NULL,
 CONSTRAINT [PK_evaluate_pipeline] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[evaluate_elbow]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[evaluate_elbow](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[e_id] [varchar](30) NULL,
	[e_name] [varchar](30) NULL,
	[e_time] [datetime] NULL,
	[e_speed] [varchar](30) NULL,
	[e_caliber] [varchar](30) NULL,
	[e_length] [varchar](30) NULL,
	[e_external_temperature] [varchar](30) NULL,
	[e_ambient_temperature] [varchar](30) NULL,
	[e_heat_loss] [varchar](30) NULL,
	[e_result] [varchar](255) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BookCard]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookCard](
	[cid] [nchar](10) NOT NULL,
	[name] [nchar](10) NULL,
	[sex] [nchar](10) NULL,
	[cardDate] [nchar](10) NULL,
	[deposit] [nchar](10) NULL,
 CONSTRAINT [PK_BookCard] PRIMARY KEY CLUSTERED 
(
	[cid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[_user]    Script Date: 06/08/2020 11:38:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[_user](
	[id] [int] IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
	[name] [varchar](30) NULL,
	[password] [varchar](30) NULL,
	[age] [varchar](30) NULL,
	[gender] [varchar](30) NULL,
	[position] [varchar](30) NULL,
	[phone] [varchar](30) NULL,
	[role] [varchar](30) NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
