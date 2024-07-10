CREATE DATABASE bcn;

CREATE TABLE `cliente` (
  `idCliente` int NOT NULL,
  `nombre_Completo` varchar(100) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `compra` (
  `idcompra` int NOT NULL,
  `fecha_compra` datetime DEFAULT NULL,
  `monto_gastado` decimal(10,2) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `id_TarjetaC` int DEFAULT NULL,
  PRIMARY KEY (`idcompra`),
  KEY `id_TarjetaC_idx` (`id_TarjetaC`),
  CONSTRAINT `id_TarjetaC` FOREIGN KEY (`id_TarjetaC`) REFERENCES `tarjeta` (`id_TarjetaC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `facilitador` (
  `idfacilitador` int NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idfacilitador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `tarjeta` (
  `id_TarjetaC` int NOT NULL,
  `numero_Tarjeta` varchar(50) DEFAULT NULL,
  `fecha_Expiracion` date DEFAULT NULL,
  `idTarjeta` int DEFAULT NULL,
  `idfacilitador` int DEFAULT NULL,
  `idCliente` int DEFAULT NULL,
  PRIMARY KEY (`id_TarjetaC`),
  UNIQUE KEY `numero_Tarjeta_UNIQUE` (`numero_Tarjeta`),
  KEY `idTarjeta_idx` (`idTarjeta`),
  KEY `idfacilitador_idx` (`idfacilitador`),
  KEY `idCliente_idx` (`idCliente`),
  CONSTRAINT `idCliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `idfacilitador` FOREIGN KEY (`idfacilitador`) REFERENCES `facilitador` (`idfacilitador`),
  CONSTRAINT `idTarjeta` FOREIGN KEY (`idTarjeta`) REFERENCES `tipo_tarjeta` (`idTarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `tipo_tarjeta` (
  `idTarjeta` int NOT NULL,
  `tipo_Tarjeta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTarjeta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


