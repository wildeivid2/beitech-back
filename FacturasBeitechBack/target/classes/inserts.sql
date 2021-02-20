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

--
-- Volcado de datos para la tabla `customer`
--

INSERT INTO `customer` (`customer_id`, `email`, `name`) VALUES
(1, 'pabloneruda@email.com', 'Pablo Neruda'),
(2, 'jgaitan@email.com', 'Jorge Eliezer Gaitán'),
(3, 'wcordero@email.com', 'Wilson Cordero');

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

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`order_id`, `delivery_address`, `creation_date`, `total`, `customer_id`) VALUES
(1, 'calle 72', '2021-02-12', 100, 3);

--
-- Volcado de datos para la tabla `order_detail`
--

INSERT INTO `order_detail` (`order_detail_id`, `quantity`, `price`, `order_id`, `product_id`) VALUES
(1, 2, 50, 1, 8);

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
