-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 12-02-2021 a las 07:38:52
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `facturas_beitech`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `email` varchar(191) DEFAULT NULL,
  `name` varchar(191) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`customer_id`, `email`, `name`) VALUES
(1, 'pabloneruda@email.com', 'Pablo Neruda'),
(2, 'jgaitan@email.com', 'Jorge Eliezer Gaitán'),
(3, 'wcordero@email.com', 'Wilson Cordero');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customer_product`
--

CREATE TABLE `customer_product` (
  `customer_product_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `customer_product`
--

INSERT INTO `customer_product` (`customer_product_id`, `customer_id`, `product_id`) VALUES
(1, 3, 6),
(2, 3, 7),
(3, 3, 4),
(4, 2, 2),
(5, 2, 1),
(6, 1, 5),
(7, 1, 1),
(8, 3, 3),
(9, 3, 9),
(10, 3, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `delivery_address` varchar(191) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `total` double DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`order_id`, `delivery_address`, `creation_date`, `total`, `customer_id`) VALUES
(1, 'calle 72', '2021-02-12', 100, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order_detail`
--

CREATE TABLE `order_detail` (
  `order_detail_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `order_detail`
--

INSERT INTO `order_detail` (`order_detail_id`, `quantity`, `price`, `order_id`, `product_id`) VALUES
(1, 2, 50, 1, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL,
  `product_description` varchar(191) DEFAULT NULL,
  `name` varchar(191) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`product_id`, `product_description`, `name`, `price`) VALUES
(1, 'TECLADO INALÁMBRICO', 'TECLADO HP', 30.25),
(2, 'TECLADO LENOVO ALAMBRICO', 'TECLADO LENOVO', 42.7),
(3, 'MONITOR LCD 14\"', 'MONITOR LCD 14\"', 120.5),
(4, 'PARLANTES MI SONIDO', 'PARLANTES MI SONIDO', 15.3),
(5, 'FLEX LENOVO', 'FLEX LENOVO', 10.88),
(6, 'BOLÍGRAFO ROJO', 'BOLÍGRAFO ROJO FABERCASTELL', 1),
(7, 'LUPA 3X', 'LUPA 3X', 5.99),
(8, 'AURICULARES JBL', 'AURICULARES JBL', 17.85),
(9, 'CABLE USB', 'CABLE USB ORIGINAL', 20.8),
(10, 'QUE MÁS DA', 'LO QUE SEA', 5.8),
(11, 'OTRO VALOR', 'OTRO VALOR A COMPRAR Y MODIFICAR', 3.5),
(12, 'NADA QUE HACER CON ESTE', 'VENTILADOR 10\" 6 ASPAS ', 96.3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`),
  ADD UNIQUE KEY `INDEX_CUSTOMER_EMAIL` (`email`);

--
-- Indices de la tabla `customer_product`
--
ALTER TABLE `customer_product`
  ADD PRIMARY KEY (`customer_product_id`),
  ADD KEY `FK8l04qpcphqsemdfr89i05piww` (`customer_id`),
  ADD KEY `FKamw6m4xtfpjuhf4we62chd94d` (`product_id`);

--
-- Indices de la tabla `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`);

--
-- Indices de la tabla `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`order_detail_id`),
  ADD KEY `FKrws2q0si6oyd6il8gqe2aennc` (`order_id`),
  ADD KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `customer_product`
--
ALTER TABLE `customer_product`
  MODIFY `customer_product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `order_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `customer_product`
--
ALTER TABLE `customer_product`
  ADD CONSTRAINT `FK8l04qpcphqsemdfr89i05piww` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `FKamw6m4xtfpjuhf4we62chd94d` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Filtros para la tabla `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Filtros para la tabla `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  ADD CONSTRAINT `FKrws2q0si6oyd6il8gqe2aennc` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
