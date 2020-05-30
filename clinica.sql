-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-08-2019 a las 17:07:21
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clinica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

CREATE TABLE `citas` (
  `Numero_Documento` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `Tipo_Consulta` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Fecha_Cita` date NOT NULL,
  `Medico` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Valor_Pagar` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `citas`
--

INSERT INTO `citas` (`Numero_Documento`, `Tipo_Consulta`, `Fecha_Cita`, `Medico`, `Valor_Pagar`) VALUES
('1093798043', 'Especialista', '2019-02-17', 'Lina Rojas', 100000),
('1094723495', 'General', '2019-02-15', 'Andres Paez', 150000),
('10049284738', 'Prioritaria', '2019-02-16', 'Nestor Camacho', 10000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `Nombres` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Tipo_Documento` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `Numero_Documento` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `Fecha_Nacimiento` date NOT NULL,
  `Pais_Origen` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `Sexo` varchar(25) COLLATE utf8_spanish_ci NOT NULL,
  `Grupo_Sanguineo` varchar(5) COLLATE utf8_spanish_ci NOT NULL,
  `RH` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Nombres`, `Apellidos`, `Tipo_Documento`, `Numero_Documento`, `Fecha_Nacimiento`, `Pais_Origen`, `Sexo`, `Grupo_Sanguineo`, `RH`) VALUES
('Nelson Eduardo', 'Amaya Calderon', 'Cedula de Ciudadania', '1093798043', '2019-02-14', 'Colombia', 'Masculino', 'AB', 'Positivo'),
('Adriana Paola', 'Rojas Calderon', 'Cedula de Ciudadania', '1094723495', '2019-02-13', 'Chile', 'Femenino', 'O', 'Negativo'),
('Jaider Alberto', 'Amaya Calderon', 'Tarjeta de Identidad', '10049284738', '2019-02-15', 'Colombia', 'Masculino', 'B', 'Positivo'),
('Paulo', 'Calvo', 'Cedula de Ciudadania', '1039462364', '2008-04-11', 'Colombia', 'Masculino', 'AB', 'Positivo');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
