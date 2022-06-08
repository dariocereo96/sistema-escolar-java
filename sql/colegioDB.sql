/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.14 : Database - colegiodb
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/colegiodb /*!40100 DEFAULT CHARACTER SET utf8 */;

USE colegiodb;

/*Table structure for table `alumnos` */

DROP TABLE IF EXISTS alumnos;

CREATE TABLE `alumnos` (
  `id_alumno` int(11) NOT NULL AUTO_INCREMENT,
  `id_representante` int(11) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apell_paterno` varchar(50) NOT NULL,
  `apell_materno` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `provincia` varchar(30) DEFAULT NULL,
  `canton` varchar(30) DEFAULT NULL,
  `parroquia` varchar(30) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_alumno`),
  UNIQUE KEY `unique_cedula` (`cedula`),
  KEY `fk_alumno_repr` (`id_representante`),
  CONSTRAINT `fk_alumno_repr` FOREIGN KEY (`id_representante`) REFERENCES `representantes` (`id_representante`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `autoridades` */

DROP TABLE IF EXISTS `autoridades`;

CREATE TABLE `autoridades` (
  `id_autoridad` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `apell_paterno` varchar(50) NOT NULL,
  `apell_materno` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `provincia` varchar(30) DEFAULT NULL,
  `canton` varchar(30) DEFAULT NULL,
  `parroquia` varchar(30) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `estado_civil` varchar(12) DEFAULT NULL,
  `profesion` varchar(50) NOT NULL,
  `cargo` varchar(50) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_autoridad`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `cursos` */

DROP TABLE IF EXISTS `cursos`;

CREATE TABLE `cursos` (
  `id_curso` int(11) NOT NULL AUTO_INCREMENT,
  `id_nivel` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `paralelo` varchar(2) NOT NULL,
  `jornada` varchar(10) NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_curso`),
  KEY `fk_curso_nivel` (`id_nivel`),
  CONSTRAINT `fk_curso_nivel` FOREIGN KEY (`id_nivel`) REFERENCES `niveles` (`id_nivel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `distribucion` */

DROP TABLE IF EXISTS `distribucion`;

CREATE TABLE `distribucion` (
  `id_periodo` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  `id_profesor` int(11) NOT NULL,
  `id_materia` int(11) NOT NULL,
  KEY `fk_distr_cuso` (`id_curso`),
  KEY `fk_distr_periodo` (`id_periodo`),
  KEY `fk_distr_curso` (`id_profesor`),
  KEY `fk_distr_mat` (`id_materia`),
  CONSTRAINT `fk_distr_curso` FOREIGN KEY (`id_profesor`) REFERENCES `docentes` (`id_docente`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_distr_cuso` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_distr_mat` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_distr_periodo` FOREIGN KEY (`id_periodo`) REFERENCES `periodos` (`id_periodo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `docentes` */

DROP TABLE IF EXISTS `docentes`;

CREATE TABLE `docentes` (
  `id_docente` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `apell_paterno` varchar(50) NOT NULL,
  `apell_materno` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varbinary(10) DEFAULT NULL,
  `correo` varbinary(50) DEFAULT NULL,
  `provincia` varchar(30) DEFAULT NULL,
  `canton` varchar(30) DEFAULT NULL,
  `parroquia` varchar(30) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `estado_civil` varchar(12) DEFAULT NULL,
  `titulo` varchar(50) NOT NULL,
  `tipo_contrato` varchar(20) NOT NULL,
  `anterior_institucion` varchar(50) DEFAULT NULL,
  `fecha_ingreso` date NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_docente`),
  UNIQUE KEY `unique_cedula` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `facturacion` */

DROP TABLE IF EXISTS `facturacion`;

CREATE TABLE `facturacion` (
  `id_factura` int(11) NOT NULL AUTO_INCREMENT,
  `id_pago` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `fecha_emision` date NOT NULL,
  `subtotal` double NOT NULL,
  `descuento` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `fk_fact_pagos` (`id_pago`),
  CONSTRAINT `fk_fact_pagos` FOREIGN KEY (`id_pago`) REFERENCES `pagos` (`id_pago`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `materias` */

DROP TABLE IF EXISTS `materias`;

CREATE TABLE `materias` (
  `id_materia` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `area` varchar(50) NOT NULL,
  `horas_semanales` int(11) NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_materia`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `matriculas` */

DROP TABLE IF EXISTS `matriculas`;

CREATE TABLE `matriculas` (
  `id_matricula` int(11) NOT NULL AUTO_INCREMENT,
  `id_alumno` int(11) NOT NULL,
  `id_periodo` int(11) NOT NULL,
  `id_curso` int(11) NOT NULL,
  `id_nivel` int(11) NOT NULL,
  `id_pension` int(11) NOT NULL,
  `fecha_matricula` date NOT NULL,
  PRIMARY KEY (`id_matricula`),
  KEY `fk_matr_alumno` (`id_alumno`),
  KEY `fk_matr_periodo` (`id_periodo`),
  KEY `fk_matr_nivel` (`id_nivel`),
  KEY `fk_matr_curso` (`id_curso`),
  KEY `fk_matr_pension` (`id_pension`),
  KEY `fk_matr_nivel_ant` (`id_nivel_anterior`),
  CONSTRAINT `fk_matr_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id_alumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_matr_curso` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`),
  CONSTRAINT `fk_matr_nivel` FOREIGN KEY (`id_nivel`) REFERENCES `niveles` (`id_nivel`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_matr_pension` FOREIGN KEY (`id_pension`) REFERENCES `pensiones` (`id_pension`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_matr_periodo` FOREIGN KEY (`id_periodo`) REFERENCES `periodos` (`id_periodo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `meses` */

DROP TABLE IF EXISTS `meses`;

CREATE TABLE `meses` (
  `id_mes` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id_mes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `niveles` */

DROP TABLE IF EXISTS `niveles`;

CREATE TABLE `niveles` (
  `id_nivel` int(11) NOT NULL AUTO_INCREMENT,
  `orden` int(11) NOT NULL,
  `grado` varchar(30) NOT NULL,
  `especialidad` varchar(30) DEFAULT NULL,
  `estado` varchar(30) NOT NULL,
  PRIMARY KEY (`id_nivel`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Table structure for table `notas` */

DROP TABLE IF EXISTS `notas`;

CREATE TABLE `notas` (
  `id_notas` int(11) NOT NULL AUTO_INCREMENT,
  `id_materia` int(11) NOT NULL,
  `id_matricula` int(11) NOT NULL,
  `id_periodo` int(11) NOT NULL,
  `id_quimestre` int(11) NOT NULL,
  `notaP1` double DEFAULT NULL,
  `notaP2` double DEFAULT NULL,
  `notaP3` double DEFAULT NULL,
  `promedio` double DEFAULT NULL,
  PRIMARY KEY (`id_notas`),
  KEY `fk_notas_matr` (`id_materia`),
  KEY `fk_notas_matricula` (`id_matricula`),
  KEY `fk_notas_periodo` (`id_periodo`),
  KEY `fk_notas_quimestre` (`id_quimestre`),
  CONSTRAINT `fk_notas_materia` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notas_matricula` FOREIGN KEY (`id_matricula`) REFERENCES `matriculas` (`id_matricula`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notas_periodo` FOREIGN KEY (`id_periodo`) REFERENCES `periodos` (`id_periodo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notas_quimestre` FOREIGN KEY (`id_quimestre`) REFERENCES `quimestres` (`id_quimestre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pagos` */

DROP TABLE IF EXISTS `pagos`;

CREATE TABLE `pagos` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_matricula` int(11) NOT NULL,
  `id_pension` int(11) NOT NULL,
  `id_periodo` int(11) NOT NULL,
  `id_mes` int(11) NOT NULL,
  `valor` double NOT NULL,
  `estado` varchar(11) NOT NULL,
  `fecha_pago` date DEFAULT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `fk_pago_matricula` (`id_matricula`),
  KEY `fk_pago_periodo` (`id_periodo`),
  KEY `fk_pago_pension` (`id_pension`),
  CONSTRAINT `fk_pago_matricula` FOREIGN KEY (`id_matricula`) REFERENCES `matriculas` (`id_matricula`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pago_pension` FOREIGN KEY (`id_pension`) REFERENCES `pensiones` (`id_pension`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pago_periodo` FOREIGN KEY (`id_periodo`) REFERENCES `periodos` (`id_periodo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pensiones` */

DROP TABLE IF EXISTS `pensiones`;

CREATE TABLE `pensiones` (
  `id_pension` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `valor_matricula` double NOT NULL,
  `valor_mensual` double NOT NULL,
  `cantidad_pagos` int(11) NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pension`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `pensum` */

DROP TABLE IF EXISTS `pensum`;

CREATE TABLE `pensum` (
  `id_materia` int(11) NOT NULL,
  `id_nivel` int(11) NOT NULL,
  PRIMARY KEY (`id_materia`,`id_nivel`),
  KEY `fk_pensum_mat` (`id_materia`),
  KEY `fk_pensum_nivel` (`id_nivel`),
  CONSTRAINT `fk_pensum_mat` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pensum_nivel` FOREIGN KEY (`id_nivel`) REFERENCES `niveles` (`id_nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `periodos` */

DROP TABLE IF EXISTS `periodos`;

CREATE TABLE `periodos` (
  `id_periodo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_periodo`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Table structure for table `quimestres` */

DROP TABLE IF EXISTS `quimestres`;

CREATE TABLE `quimestres` (
  `id_quimestre` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  PRIMARY KEY (`id_quimestre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `representantes` */

DROP TABLE IF EXISTS `representantes`;

CREATE TABLE `representantes` (
  `id_representante` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `apell_paterno` varchar(50) NOT NULL,
  `apell_materno` varchar(50) NOT NULL,
  `cedula` varchar(10) NOT NULL,
  `genero` varchar(10) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `provincia` varchar(30) DEFAULT NULL,
  `canton` varchar(30) DEFAULT NULL,
  `parroquia` varchar(30) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `ocupacion` varchar(50) DEFAULT NULL,
  `lugar_trabajo` varchar(50) DEFAULT NULL,
  `telefono_trabajo` varchar(10) DEFAULT NULL,
  `estado_civil` varchar(12) DEFAULT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_representante`),
  UNIQUE KEY `unique_cedula` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_autoridad` int(11) NOT NULL DEFAULT '0',
  `usuario` varchar(30) NOT NULL,
  `contrasena` varchar(15) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/* Procedure structure for procedure `activarPeriodo` */

/*!50003 DROP PROCEDURE IF EXISTS  `activarPeriodo` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `activarPeriodo`(IN `id_periodo` int)
BEGIN	UPDATE periodos p SET p.estado='ACTIVO' WHERE p.id_periodo=id_periodo;	UPDATE periodos p SET p.estado='INACTIVO' WHERE p.id_periodo!=id_periodo;END */$$
DELIMITER ;

/* Procedure structure for procedure `buscarUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `buscarUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarUsuario`(IN `username` VARCHAR(30), IN `usercontrasena` VARCHAR(30))
BEGIN	SELECT u.id_usuario, 	u.id_autoridad, 	u.usuario, 	u.contrasena, 	upper(concat(a.nombres," ",a.apell_paterno," ",a.apell_materno)) as NOMBRES,	upper(u.tipo), 	upper(u.estado) 	FROM usuarios u join autoridades a on u.id_autoridad=a.id_autoridad	WHERE u.usuario=username AND u.contrasena=usercontrasena;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirAlumno` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirAlumno` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirAlumno`(	IN `id_alumno` INT)
BEGIN	SELECT a.id_alumno AS 'ID',	upper(a.nombres) AS 'NOMBRES',	UPPER(a.apell_paterno) AS 'APELLIDO_PATERNO',	UPPER(a.apell_materno) AS 'APELLIDO_MATERNO',	a.cedula AS 'CEDULA',	UPPER(a.genero) AS 'GENERO',	a.fecha_nacimiento AS 'FECHA_NACIMIENTO',	a.telefono AS 'TELEFONO',	a.correo AS 'CORREO',	upper(a.provincia) AS 'PROVINCIA',	UPPER(a.canton) AS 'CANTON',	upper(a.parroquia) AS 'PARROQUIA',	UPPER(a.direccion) AS 'DIRECCION',	a.estado AS 'ESTADO',	r.id_representante AS 'ID_REPRESENTANTE',	upper(r.nombres) as 'NOMBRES_REPRESENTANTE',	upper(concat(r.apell_paterno,' ',r.apell_materno)) AS 'APELLIDOS_REPRESENTANTE'		FROM  alumnos a join representantes r on a.id_representante=r.id_representante	WHERE a.id_alumno=id_alumno;End */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirAutoridad` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirAutoridad` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirAutoridad`(IN `id_autoridad` INT
)
BEGIN	SELECT  `d`.`id_autoridad` AS `ID`,  UCASE(`d`.`nombres`) AS `NOMBRES`,  UCASE(d.apell_paterno) AS 'APELLIDO_PATERNO',  UCASE(d.apell_materno) AS 'APELLIDO_MATERNO',  `d`.`cedula`     AS `CEDULA`,  UCASE(`d`.`genero`) AS `GENERO`,  d.fecha_nacimiento AS `FECHA_NACIMIENTO`,  d.telefono AS 'TELEFONO',  d.correo AS 'CORREO',  UCASE(d.provincia) AS 'PROVINCIA',  UCASE(d.canton) AS 'CANTON',  UCASE(d.parroquia) AS 'PARROQUIA',  UCASE(d.direccion) AS 'DIRECCION',  UCASE(d.estado_civil) AS 'ESTADO_CIVIL',  UCASE(`d`.`profesion`) AS 'PROFESION',  UCASE(`d`.`cargo`) AS 'CARGO',  d.fecha_ingreso AS 'FECHA_INGRESO',  UCASE(d.estado) AS 'ESTADO'	FROM  autoridades d	WHERE d.id_autoridad=id_autoridad;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirCurso` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirCurso` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirCurso`(IN `id_curso` INT
)
BEGIN	SELECT 		c.id_curso as 'ID', 		c.id_nivel as 'ID_NIVEL', 		c.capacidad AS 'CAPACIDAD', 		upper(c.paralelo) AS 'PARALELO', 		upper(c.jornada) AS 'JORNADA', 		upper(c.estado) AS 'ESTADO'	FROM cursos c join niveles n on c.id_nivel=n.id_nivel	where c.id_curso=id_curso;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirDocente` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirDocente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirDocente`(
	IN `id_docente` int
)
BEGIN
	select
  `d`.`id_docente` AS `ID`,
  ucase(`d`.`nombres`) AS `NOMBRES`,
  UCASE(d.apell_paterno) as 'APELLIDO_PATERNO',
  UCASE(d.apell_materno) as 'APELLIDO_MATERNO',
  `d`.`cedula`     AS `CEDULA`,
  ucase(`d`.`genero`) AS `GENERO`,
  d.fecha_nacimiento AS `FECHA_NACIMIENTO`,
  d.telefono as 'TELEFONO',
  d.correo as 'CORREO',
  ucase(d.provincia) as 'PROVINCIA',
  ucase(d.canton) as 'CANTON',
  ucase(d.parroquia) as 'PARROQUIA',
  ucase(d.direccion) as 'DIRECCION',
  ucase(d.estado_civil) as 'ESTADO_CIVIL',
  ucase(`d`.`titulo`) AS 'TITULO',
  ucase(`d`.`tipo_contrato`) AS 'TIPO_CONTRATO',
  ucase(d.anterior_institucion) as 'ANTERIOR_INSTITUCION',
  d.fecha_ingreso as 'FECHA_INGRESO',
  ucase(d.estado) as 'ESTADO'
	FROM  docentes d
	WHERE d.id_docente=id_docente;
END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirMateria` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirMateria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirMateria`(	IN `id_materia` INT)
BEGINSELECT id_materia as ID, upper(nombre) as NOMBRE, upper(area) AS AREA, horas_semanales AS HORAS_SEMANALES,upper(estado) as ESTADOFROM materias mwhere m.id_materia=id_materia;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirMatricula` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirMatricula` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirMatricula`(	IN `id_alumno` INT)
BEGIN		select 		m.id_matricula as ID,		m.id_alumno as 'ID_ALUMNO',		m.id_periodo as 'ID_PERIODO',		m.id_curso as 'ID_CURSO',		m.id_nivel as 'ID_NIVEL',		m.id_pension as 'ID_PENSION',		m.fecha_matricula as 'FECHA_MATRICULA'	from matriculas m 	where m.id_alumno=id_alumno;		END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirNivel` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirNivel` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirNivel`(IN `id_nivel` int)
BEGIN		select		n.id_nivel AS ID,  		upper(n.orden) as ORDEN,  		upper(n.grado) AS GRADO,  		upper(n.especialidad) AS ESPECIALIDAD,  		upper(n.estado) as ESTADO		FROM niveles n 		where n.id_nivel=id_nivel;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirNotaCurso` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirNotaCurso` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirNotaCurso`(	IN `id_curso` INT,	IN `id_materia` INT,	IN `id_quimestre` INT,	IN `id_periodo` INT)
BEGIN	select	 n.id_notas as ID_NOTA,	 n.id_materia as ID_MATERIA,	 n.id_matricula as ID_MATRICULA,	 n.id_periodo as ID_PERIODO,	 n.id_quimestre as ID_QUIMESTRE,	 	 upper(concat(alum.apell_paterno,' ',alum.apell_materno,' ',alum.nombres)) as 'NOMBRES',	 upper(q.nombre) as 'QUIMESTRE',	 n.notaP1 as 'PRIMER_PARCIAL',	 n.notaP2 as 'SEGUNDO_PARCIAL',	 n.notaP3 as 'TERCER_PARCIAL',	 n.promedio as 'PROMEDIO',	 p.descripcion as 'PERIODO',	 mat.nombre as 'MATERIA'	 	from notas n join matriculas m on n.id_matricula=m.id_matricula	join cursos c on m.id_curso=c.id_curso	join alumnos alum on m.id_alumno=alum.id_alumno	join materias mat on n.id_materia = mat.id_materia	join quimestres q on n.id_quimestre=q.id_quimestre	join periodos p on n.id_periodo=p.id_periodo		where c.id_curso=id_curso and mat.id_materia=id_materia and q.id_quimestre=id_quimestre and p.id_periodo=id_periodo;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirPagoIndividual` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirPagoIndividual` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirPagoIndividual`(	IN `id_pago` INT)
BEGIN	SELECT id_pago as ID, 	id_matricula as ID_MATRICULA, 	id_pension AS ID_PENSION, 	id_periodo AS ID_PERIODO, 	concat('PENSION MES DE',' ',m.nombre) as 'CONCEPTO',	valor AS VALOR, 	estado AS ESTADO,	p.fecha_pago as FECHA_PAGOFROM pagos p join meses m on p.id_mes=m.id_meswhere p.id_pago=id_pago;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirPagos` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirPagos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirPagos`(	IN `id_matricula` INT)
BEGIN		select 		p.id_pago as 'ID',	concat('PENSION MES DE',' ',m.nombre) as 'CONCEPTO',	p.valor as 'VALOR',	upper(per.descripcion) as 'PERIODO',	upper(p.estado) as 'ESTADO',	p.fecha_pago as 'FECHA_PAGO'		from pagos p join meses m on p.id_mes=m.id_mes join periodos per on p.id_periodo=per.id_periodo	where p.id_matricula=id_matricula;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirPension` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirPension` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirPension`(	IN `id_pension` INT)
BEGIN	SELECT 		upper(p.id_pension) as ID,		upper(p.descripcion) as DESCRIPCION, 		upper(p.valor_matricula) as VALOR_MATRICULA, 		upper(p.valor_mensual) as VALOR_MENSUAL, 		upper(p.cantidad_pagos) as CANTIDAD_PAGOS,		upper(p.estado) as ESTADO		FROM pensiones p		where p.id_pension=id_pension;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirPensum` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirPensum` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirPensum`(	IN `id_nivel` INT)
BEGIN	select 		m.id_materia as 'ID_MATERIA',		upper(m.nombre) as 'NOMBRE',		upper(m.area) as 'AREA',		m.horas_semanales as 'HORAS_SEMALES',		upper(m.estado) as 'ESTADO'	from niveles n join pensum p on n.id_nivel=p.id_nivel join materias m on p.id_materia=m.id_materia	where n.id_nivel=id_nivel;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirPeriodo` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirPeriodo` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirPeriodo`(IN `id_periodo` int)
BEGIN	select id_periodo as 'ID',	upper(descripcion) as 'DESCRIPCION',	fecha_inicio as 'FECHA_INICIO',	fecha_fin AS 'FECHA_FIN',	upper(estado) AS 'ESTADO' 	from periodos p 	where p.id_periodo=id_periodo; END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirRepresentante` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirRepresentante` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirRepresentante`(	IN `id_representante` int)
BEGIN	SELECT 	r.id_representante AS 'ID',	upper(r.nombres) AS 'NOMBRES',	UPPER(r.apell_paterno) AS 'APELLIDO_PATERNO',	UPPER(r.apell_materno) AS 'APELLIDO_MATERNO',	concat(ucase(`r`.`apell_paterno`),' ',ucase(`r`.`apell_materno`)) AS `APELLIDOS`,	r.cedula AS 'CEDULA',	UPPER(r.genero) AS 'GENERO',	r.fecha_nacimiento AS 'FECHA_NACIMIENTO',	r.telefono AS 'TELEFONO',	r.correo AS 'CORREO',	upper(r.provincia) AS 'PROVINCIA',	UPPER(r.canton) AS 'CANTON',	upper(r.parroquia) AS 'PARROQUIA',	UPPER(r.direccion) AS 'DIRECCION',	upper(r.ocupacion) as 'OCUPACION',	upper(r.lugar_trabajo) as 'LUGAR_TRABAJO',	upper(r.telefono_trabajo) as 'TELEFONO_TRABAJO',	upper(r.estado_civil) as 'ESTADO_CIVIL',	upper(r.estado) AS 'ESTADO'	from representantes r	WHERE r.id_representante=id_representante;END */$$
DELIMITER ;

/* Procedure structure for procedure `conseguirUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `conseguirUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `conseguirUsuario`(IN `id_usuario` INT)
BEGIN		SELECT u.id_usuario, 	u.id_autoridad, 	u.usuario, 	u.contrasena, 	upper(u.tipo), 	upper(u.estado)	FROM usuarios u		where u.id_usuario=id_usuario;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarAlumno` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarAlumno` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarAlumno`(	IN `id_alumno` INT)
BEGIN	UPDATE alumnos a SET a.estado='INACTIVO' WHERE a.id_alumno=id_alumno;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarAutoridad` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarAutoridad` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarAutoridad`(
	IN `id_autoridad` INT
)
BEGIN
	UPDATE autoridades a SET a.estado='INACTIVO' WHERE a.id_autoridad=id_autoridad;
END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarCurso` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarCurso` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarCurso`(	IN `id_curso` INT)
BEGIN	UPDATE cursos c SET c.estado='INACTIVO' WHERE c.id_curso=id_curso;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarDocente` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarDocente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarDocente`(	IN `id_docente` int)
BEGIN	UPDATE docentes d SET d.estado='INACTIVO' WHERE d.id_docente=id_docente;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarMateria` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarMateria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarMateria`(	IN `id_materia` INT)
BEGIN	UPDATE materias m	SET		estado='INACTIVO'	WHERE m.id_materia=id_materia;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarNivel` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarNivel` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarNivel`(	IN `id_nivel` int)
BEGIN	UPDATE niveles n SET n.estado='INACTIVO' WHERE n.id_nivel=id_nivel;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarPensum` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarPensum` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarPensum`(IN `id_materia` INT, IN `id_nivel` INT)
BEGIN	DELETE FROM pensum WHERE pensum.id_materia=id_materia and pensum.id_nivel=id_nivel;END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarRepresentante` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarRepresentante` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarRepresentante`(	IN `id_representante` int)
BEGIN	UPDATE representantes r SET r.estado='INACTIVO' WHERE r.id_representante=id_representante;    END */$$
DELIMITER ;

/* Procedure structure for procedure `eliminarUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `eliminarUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarUsuario`(IN `id_user` INT)
BEGIN	DELETE FROM usuarios  WHERE id_usuario=id_user;END */$$
DELIMITER ;

/* Procedure structure for procedure `generarTablaPagos` */

/*!50003 DROP PROCEDURE IF EXISTS  `generarTablaPagos` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `generarTablaPagos`(	IN `id_matricula` INT,	IN `id_periodo` INT,	IN `id_pension` INT)
BEGIN	declare cont int;	declare npagos int;	declare idmes int;	declare vm double;		select p.cantidad_pagos into npagos from pensiones p  where p.id_pension=id_pension;	select p.valor_mensual into vm from pensiones p  where p.id_pension=id_pension;	select extract(month from per.fecha_inicio) into idmes from periodos per where per.id_periodo=id_periodo;	set cont=1;		while cont<=npagos do			if(idmes>12) then				set idmes=1;			end if;						insert into pagos values(null,id_matricula,id_pension,id_periodo,idmes,vm,'NO PAGADO');			set cont=cont+1;			set idmes=idmes+1;	end while;	END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarAlumno` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarAlumno` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarAlumno`(	IN `id_representante` int,	IN `nombres` varchar(50),	IN `apell_paterno` varchar(50),	IN `apell_materno` varchar(50),	IN `cedula` varchar(10),	IN `genero` varchar(10),	IN `fecha_nacimiento` VARCHAR(20),	IN `telefono` VARCHAR(10),	IN `correo` VARCHAR(50),	IN `provincia` VARCHAR(30),	IN `canton` VARCHAR(30),	IN `parroquia` varchar(30),	IN `direccion` VARCHAR(50),	IN `estado` VARCHAR(10))
begin	INSERT INTO alumnos(id_representante,nombres,apell_paterno,apell_materno,cedula,genero,fecha_nacimiento,telefono,correo,provincia,canton,parroquia,direccion,estado)	VALUES(id_representante,nombres,apell_paterno,apell_materno,cedula,genero,fecha_nacimiento,telefono,correo,provincia,canton,parroquia,direccion,estado);end */$$
DELIMITER ;

/* Procedure structure for procedure `insertarAutoridad` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarAutoridad` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarAutoridad`(IN `nombres` VARCHAR(50), IN `apell_paterno` VARCHAR(50), IN `apell_materno` VARCHAR(50), IN `cedula` VARCHAR(10), IN `genero` VARCHAR(10), IN `fecha_nacimiento` VARCHAR(20), IN `telefono` VARCHAR(10), IN `correo` VARCHAR(50), IN `provincia` VARCHAR(30), IN `canton` VARCHAR(30), IN `parroquia` VARCHAR(30), IN `direccion` VARCHAR(50), IN `estado_civil` VARCHAR(12), IN `profesion` VARCHAR(50), IN `cargo` VARCHAR(20), IN `fecha_ingreso` VARCHAR(20), IN `estado` VARCHAR(10)
)
BEGIN	INSERT INTO `colegiodb`.`autoridades`            (`nombres`,             `apell_paterno`,             `apell_materno`,             `cedula`,             `genero`,             `fecha_nacimiento`,             `telefono`,             `correo`,             `provincia`,             `canton`,             `parroquia`,             `direccion`,             `estado_civil`,             `profesion`,             `cargo`,             `fecha_ingreso`,             `estado`)VALUES ( nombres,        apell_paterno,        apell_materno,        cedula,        genero,        fecha_nacimiento,        telefono,        correo,        provincia,        canton,        parroquia,        direccion,        estado_civil,        profesion,        cargo,        fecha_ingreso,        estado);END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarCurso` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarCurso` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarCurso`(IN `id_nivel` INT, IN `capacidad` INT, IN `paralelo` VARCHAR(2), IN `jornada` VARCHAR(10), IN `estado` VARCHAR(10)
)
BEGIN	insert into cursos (id_nivel,capacidad,paralelo,jornada,estado) values(id_nivel,capacidad,paralelo,jornada,estado);END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarDocente` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarDocente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarDocente`(	IN `nombres` VARCHAR(50),	IN `apell_paterno` VARCHAR(50),	IN `apell_materno` VARCHAR(50),	IN `cedula` VARCHAR(10),	IN `genero` VARCHAR(10),	IN `fecha_nacimiento` VARCHAR(20),	IN `telefono` VARCHAR(10),	IN `correo` VARCHAR(50),	IN `provincia` VARCHAR(30),	IN `canton` VARCHAR(30),	IN `parroquia` VARCHAR(30),	IN `direccion` VARCHAR(50),	IN `estado_civil` VARCHAR(12),	IN `titulo` varchar(50),	IN `tipo_contrato` varchar(20),	IN `anterior_institucion` varchar(50),	IN `fecha_ingreso` varchar(20),	IN `estado` VARCHAR(10))
BEGIN	INSERT INTO `colegiodb`.`docentes`            (`nombres`,             `apell_paterno`,             `apell_materno`,             `cedula`,             `genero`,             `fecha_nacimiento`,             `telefono`,             `correo`,             `provincia`,             `canton`,             `parroquia`,             `direccion`,             `estado_civil`,             `titulo`,             `tipo_contrato`,             `anterior_institucion`,             `fecha_ingreso`,             `estado`)VALUES ( nombres,        apell_paterno,        apell_materno,        cedula,        genero,        fecha_nacimiento,        telefono,        correo,        provincia,        canton,        parroquia,        direccion,        estado_civil,        titulo,        tipo_contrato,        anterior_institucion,        fecha_ingreso,        estado);END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarMateria` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarMateria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarMateria`(	IN `nombre` VARCHAR(50),	IN `area` VARCHAR(50),	IN `horas_semanales` INT,	IN `estado` VARCHAR(10))
BEGIN	INSERT INTO materias	(nombre, area, horas_semanales,estado)	VALUES (nombre,area,horas_semanales,estado);END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarMatricula` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarMatricula` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarMatricula`(	IN `id_alumno` INT,	IN `id_periodo` INT,	IN `id_curso` INT,	IN `id_nivel` INT,	IN `id_pension` INT
)
BEGIN	declare matr int;		INSERT INTO matriculas	(id_alumno, id_periodo, id_curso, id_nivel,id_pension, fecha_matricula)	VALUES (id_alumno, id_periodo, id_curso, id_nivel,id_pension, CURRENT_DATE);		select last_insert_id() into matr;		call generarTablaPagos(matr,id_periodo,id_pension);		END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarNivel` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarNivel` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarNivel`(IN `orden` INT, IN `grado` varchar(30), IN `especialidad` varchar(30), IN `estado` VARCHAR(10))
begin	INSERT INTO niveles            (orden,             grado,             especialidad,				 estado)	VALUES (orden,             grado,             especialidad,				 estado);    END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarNota` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarNota` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarNota`(	IN `id_materia` INT,	IN `id_matricula` INT,	IN `id_periodo` INT,	IN `id_quimestre` INT,	IN `notaP1` INT,	IN `notaP2` INT,	IN `notaP3` INT)
BEGIN	INSERT INTO notas	(id_notas, id_materia, id_matricula, id_periodo, id_quimestre, notaP1, notaP2, notaP3)	VALUES (NULL, id_materia, id_matricula, id_periodo, id_quimestre, notaP1, notaP2, notaP3);END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarPension` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarPension` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarPension`(	IN `descripcion` VARCHAR(50),	IN `valor_matricula` DOUBLE,	IN `valor_mensual` DOUBLE,	IN `cantidad_pagos` INT,	IN `estado` VARCHAR(10))
BEGIN	INSERT INTO pensiones	(id_pension, descripcion, valor_matricula, valor_mensual, cantidad_pagos,estado)	VALUES (id_pension, descripcion, valor_matricula, valor_mensual, cantidad_pagos,estado);END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarPensum` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarPensum` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarPensum`(IN `id_nivel` INT, IN `id_materia` INT)
BEGIN
	INSERT INTO pensum
	(id_nivel, id_materia)
	VALUES (id_nivel, id_materia);
END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarPeriodo` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarPeriodo` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarPeriodo`(	IN `descripcion` varchar(50),	IN `fecha_inicio` varchar(20),	IN `fecha_fin` varchar(20),	IN `estado` VARCHAR(10))
BEGIN	insert into periodos(descripcion,fecha_inicio,fecha_fin,estado) values(descripcion,fecha_inicio,fecha_fin,estado);    END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarRepresentante` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarRepresentante` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarRepresentante`(	IN `nombres` VARCHAR(50),	IN `apell_paterno` VARCHAR(50),	IN `apell_materno` VARCHAR(50),	IN `cedula` VARCHAR(10),	IN `genero` VARCHAR(10),	IN `fecha_nacimiento` VARCHAR(20),	IN `telefono` VARCHAR(10),	IN `correo` VARCHAR(50),	IN `provincia` VARCHAR(30),	IN `canton` VARCHAR(30),	IN `parroquia` VARCHAR(30),	IN `direccion` VARCHAR(50),	IN `ocupacion` varchar(50),	IN `lugar_trabajo` varchar(50),	IN `telefono_trabajo` varchar(10),	IN `estado_civil` varchar(12),	IN `estado` VARCHAR(10))
BEGIN	INSERT INTO representantes(nombres,apell_paterno,apell_materno,cedula,genero,fecha_nacimiento,telefono,correo,provincia,canton,parroquia,direccion,ocupacion,lugar_trabajo,telefono_trabajo,estado_civil,estado)	VALUES(nombres,apell_paterno,apell_materno,cedula,genero,fecha_nacimiento,telefono,correo,provincia,canton,parroquia,direccion,ocupacion,lugar_trabajo,telefono_trabajo,estado_civil,estado);	END */$$
DELIMITER ;

/* Procedure structure for procedure `insertarUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertarUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertarUsuario`(IN `id_autoridad` INT, IN `usuario` VARCHAR(30), IN `contrasena` VARCHAR(20), IN `tipo` VARCHAR(30), IN `estado` VARCHAR(10))
BEGIN	INSERT INTO usuarios	(id_autoridad, usuario, contrasena, tipo, estado)	VALUES (id_autoridad, usuario, contraseña, tipo, estado);END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarAlumno` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarAlumno` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarAlumno`(id_alumno int,id_representante INT,nombres VARCHAR(50),apell_paterno VARCHAR(50),apell_materno VARCHAR(50),
cedula VARCHAR(10),genero VARCHAR(10),fecha_nacimiento VARCHAR(20), telefono VARCHAR(10),correo VARCHAR(50),provincia VARCHAR(30),canton VARCHAR(30),parroquia VARCHAR(30),
direccion VARCHAR(50),estado varchar(10)
)
BEGIN
	UPDATE alumnos a
	SET
		a.id_representante=id_representante,
		a.nombres=nombres,
		a.apell_paterno=apell_paterno,
		a.apell_materno=apell_materno,
		a.cedula=cedula,
		a.genero=genero,
		a.fecha_nacimiento=fecha_nacimiento,
		a.telefono=telefono,
		a.correo=correo,
		a.provincia=provincia,
		a.canton=canton,
		a.parroquia=parroquia,
		a.direccion=direccion,
		a.estado=estado
	WHERE a.id_alumno=id_alumno;
END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarAutoridad` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarAutoridad` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarAutoridad`(IN `id_docente` INT, IN `nombres` VARCHAR(50), IN `apell_paterno` VARCHAR(50), IN `apell_materno` VARCHAR(50), IN `cedula` VARCHAR(10), IN `genero` VARCHAR(10), IN `fecha_nacimiento` VARCHAR(20), IN `telefono` VARCHAR(10), IN `correo` VARCHAR(50), IN `provincia` VARCHAR(30), IN `canton` VARCHAR(30), IN `parroquia` VARCHAR(30), IN `direccion` VARCHAR(50), IN `estado_civil` VARCHAR(12), IN `profesion` VARCHAR(50), IN `cargo` VARCHAR(20), IN `fecha_ingreso` VARCHAR(20), IN `estado` VARCHAR(10))
BEGIN	UPDATE `colegiodb`.`autoridades` d	SET 	  d.`nombres` = nombres,	  d.`apell_paterno` = apell_paterno,	  d.`apell_materno` = apell_materno,	  d.`cedula` = cedula,	  d.`genero` = genero,	  d.`fecha_nacimiento` = fecha_nacimiento,	  d.`telefono` = telefono,	  d.`correo` = correo,	  d.`provincia` = provincia,	  d.`canton` = canton,	  d.`parroquia` = parroquia,	  d.`direccion` = direccion,	  d.`estado_civil` = estado_civil,	  d.`profesion` = profesion,	  d.`cargo` = cargo,	  d.`fecha_ingreso` = fecha_ingreso,	  d.estado=estado	  	WHERE d.`id_autoridad` = id_autoridad;    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarCurso` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarCurso` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarCurso`(IN `id_curso` VARCHAR(50), IN `id_nivel` INT, IN `capacidad` INT, IN `paralelo` VARCHAR(2), IN `jornada` VARCHAR(10), IN `estado` varchar(10)
    )
BEGIN	UPDATE `colegiodb`.`cursos` c	SET 	  c.`id_nivel` = id_nivel,	  c.`capacidad` = capacidad,	  c.`paralelo` = paralelo,	  c.`jornada` = jornada,	  c.estado=estado	WHERE c.`id_curso` = id_curso;    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarDocente` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarDocente` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarDocente`(id_docente INT,nombres VARCHAR(50),apell_paterno VARCHAR(50),apell_materno VARCHAR(50),
cedula VARCHAR(10),genero VARCHAR(10),fecha_nacimiento VARCHAR(20), telefono VARCHAR(10),correo VARCHAR(50),provincia VARCHAR(30),canton VARCHAR(30),parroquia VARCHAR(30),
direccion VARCHAR(50),estado_civil VARCHAR(12),profesion VARCHAR(50),cargo VARCHAR(20),fecha_ingreso VARCHAR(20),estado VARCHAR(10))
BEGIN
	UPDATE `colegiodb`.`docentes` d
	SET 
	  d.`nombres` = nombres,
	  d.`apell_paterno` = apell_paterno,
	  d.`apell_materno` = apell_materno,
	  d.`cedula` = cedula,
	  d.`genero` = genero,
	  d.`fecha_nacimiento` = fecha_nacimiento,
	  d.`telefono` = telefono,
	  d.`correo` = correo,
	  d.`provincia` = provincia,
	  d.`canton` = canton,
	  d.`parroquia` = parroquia,
	  d.`direccion` = direccion,
	  d.`estado_civil` = estado_civil,
	  d.`profesion` = profesion,
	  d.`cargo` = cargo,
	  d.`fecha_ingreso` = fecha_ingreso,
	  d.estado=estado
	  
	WHERE d.`id_docente` = id_docente;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarMateria` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarMateria` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarMateria`(IN `id_materia` INT, IN `nombre` VARCHAR(50), IN `area` VARCHAR(50), IN `horas_semanales` INT, IN `estado` VARCHAR(50))
BEGINUPDATE materias m	SET		m.nombre=nombre,		m.area=area,		m.horas_semanales=horas_semanales,		m.estado=estado	WHERE m.id_materia=id_materia;END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarMatricula` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarMatricula` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarMatricula`(	IN `id_matricula` INT,	IN `id_periodo` INT,	IN `id_curso` INT,	IN `id_nivel` INT,	IN `id_pension` INT
	
    )
BEGIN	UPDATE `colegiodb`.`matriculas` mSET  `id_periodo` = id_periodo,  `id_curso` = id_curso,  `id_nivel` = id_nivel,  `id_pension` = id_pension,  `fecha_matricula` =  CURRENT_DATEWHERE m.id_matricula= id_matricula;	    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarNivel` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarNivel` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarNivel`(IN `id_nivel` int, IN `orden` INT, IN `grado` varchar(30), IN `especialidad` varchar(30), IN `estado` varchar(10))
BEGIN	UPDATE `colegiodb`.`niveles` n	SET 	  n.`orden` = orden,	  n.`grado` = grado,	  n.`especialidad` = especialidad,	  n.estado=estado	WHERE n.`id_nivel` = id_nivel;    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarPeriodo` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarPeriodo` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarPeriodo`(id_periodo int,descripcion VARCHAR(50),fecha_inicio VARCHAR(20),fecha_fin VARCHAR(20),estado varchar(10))
BEGIN
	UPDATE `colegiodb`.`periodos` p
	SET 
	p.`descripcion` = descripcion,
	p.`fecha_inicio` = fecha_inicio,
	p.`fecha_fin` = fecha_fin,
	p.estado=estado
	WHERE p.`id_periodo` = id_periodo;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarRepresentante` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarRepresentante` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarRepresentante`(id_representante int,nombres VARCHAR(50),apell_paterno VARCHAR(50),apell_materno VARCHAR(50),
cedula VARCHAR(10),genero VARCHAR(10),fecha_nacimiento VARCHAR(20), telefono VARCHAR(10),correo VARCHAR(50),provincia VARCHAR(30),canton VARCHAR(30),parroquia VARCHAR(30),
direccion VARCHAR(50),ocupacion VARCHAR(50),lugar_trabajo VARCHAR(50),telefono_trabajo VARCHAR(10),estado_civil VARCHAR(12),estado varchar(10))
BEGIN
	UPDATE `colegiodb`.`representantes` r
	SET 
	`nombres` = nombres,
	  r.`apell_paterno` = apell_paterno,
	  r.`apell_materno` = apell_materno,
	  r.`cedula` = cedula,
	  r.`genero` = genero,
	  r.`fecha_nacimiento` = fecha_nacimiento,
	  r.`telefono` = telefono,
	  r.`correo` = correo,
	  r.`provincia` = provincia,
	  r.`canton` = canton,
	  r.`parroquia` = parroquia,
	  r.`direccion` = direccion,
	  r.`ocupacion` = ocupacion,
	  r.`lugar_trabajo` = lugar_trabajo,
	  r.`telefono_trabajo` = telefono_trabajo,
	  r.`estado_civil` = estado_civil,
	  r.estado=estado
	WHERE r.`id_representante` = id_representante;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `modificarUsuario` */

/*!50003 DROP PROCEDURE IF EXISTS  `modificarUsuario` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `modificarUsuario`(IN `id_usuario` INT, IN `id_autoridad` INT, IN `usuario` VARCHAR(30), IN `contrasena` VARCHAR(20), IN `tipo` VARCHAR(20), IN `estado` VARCHAR(10))
BEGIN	UPDATE usuarios u	SET		u.id_autoridad=id_autoridad,		u.usuario=usuario,		u.contrasena=contrasena,		u.tipo=tipo,		u.estado=estado	WHERE u.id_usuario=id_usuario;END */$$
DELIMITER ;

/* Procedure structure for procedure `registrarPago` */

/*!50003 DROP PROCEDURE IF EXISTS  `registrarPago` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `registrarPago`(	IN `id_pago` INT)
BEGIN	update pagos p set p.estado='PAGADO',p.fecha_pago=CURRENT_DATE where p.id_pago=id_pago;END */$$
DELIMITER ;

/*Table structure for table `mostraralumnos` */

DROP TABLE IF EXISTS `mostraralumnos`;

/*!50001 DROP VIEW IF EXISTS `mostraralumnos` */;
/*!50001 DROP TABLE IF EXISTS `mostraralumnos` */;

/*!50001 CREATE TABLE  `mostraralumnos`(
 `ID` int(11) ,
 `NOMBRES` varchar(50) ,
 `APELLIDO_PATERNO` varchar(50) ,
 `APELLIDO_MATERNO` varchar(50) ,
 `APELLIDOS` varchar(101) ,
 `CEDULA` varchar(10) ,
 `GENERO` varchar(10) ,
 `FECHA_NACIMIENTO` date ,
 `EDAD` int(5) ,
 `TELEFONO` varchar(10) ,
 `CORREO` varchar(50) ,
 `PROVINCIA` varchar(30) ,
 `CANTON` varchar(30) ,
 `PARROQUIA` varchar(30) ,
 `DIRECCION` varchar(50) ,
 `ESTADO` varchar(10) ,
 `ID_REPRESENTANTE` int(11) ,
 `NOMBRES_REPRESENTANTE` varchar(50) ,
 `APELLIDOS_REPRESENTANTE` varchar(101) 
)*/;

/*Table structure for table `mostrarautoridades` */

DROP TABLE IF EXISTS `mostrarautoridades`;

/*!50001 DROP VIEW IF EXISTS `mostrarautoridades` */;
/*!50001 DROP TABLE IF EXISTS `mostrarautoridades` */;

/*!50001 CREATE TABLE  `mostrarautoridades`(
 `ID` int(11) ,
 `NOMBRES` varchar(50) ,
 `APELLIDO_PATERNO` varchar(50) ,
 `APELLIDO_MATERNO` varchar(50) ,
 `APELLIDOS` varchar(101) ,
 `CEDULA` varchar(10) ,
 `GENERO` varchar(10) ,
 `FECHA_NACIMIENTO` date ,
 `TELEFONO` varchar(10) ,
 `CORREO` varchar(50) ,
 `PROVINCIA` varchar(30) ,
 `CANTON` varchar(30) ,
 `PARROQUIA` varchar(30) ,
 `DIRECCION` varchar(50) ,
 `ESTADO_CIVIL` varchar(12) ,
 `PROFESION` varchar(50) ,
 `CARGO` varchar(50) ,
 `FECHA_INGRESO` date ,
 `ESTADO` varchar(10) 
)*/;

/*Table structure for table `mostrarcursos` */

DROP TABLE IF EXISTS `mostrarcursos`;

/*!50001 DROP VIEW IF EXISTS `mostrarcursos` */;
/*!50001 DROP TABLE IF EXISTS `mostrarcursos` */;

/*!50001 CREATE TABLE  `mostrarcursos`(
 `ID` int(11) ,
 `ID_NIVEL` int(11) ,
 `DESCRIPCION` varchar(91) ,
 `PARALELO` varchar(2) ,
 `JORNADA` varchar(10) ,
 `CAPACIDAD` int(11) 
)*/;

/*Table structure for table `mostrardocentes` */

DROP TABLE IF EXISTS `mostrardocentes`;

/*!50001 DROP VIEW IF EXISTS `mostrardocentes` */;
/*!50001 DROP TABLE IF EXISTS `mostrardocentes` */;

/*!50001 CREATE TABLE  `mostrardocentes`(
 `ID` int(11) ,
 `NOMBRES` varchar(50) ,
 `APELLIDO_PATERNO` varchar(50) ,
 `APELLIDO_MATERNO` varchar(50) ,
 `APELLIDOS` varchar(101) ,
 `CEDULA` varchar(10) ,
 `GENERO` varchar(10) ,
 `FECHA_NACIMIENTO` date ,
 `TELEFONO` varbinary(10) ,
 `CORREO` varbinary(50) ,
 `PROVINCIA` varchar(30) ,
 `CANTON` varchar(30) ,
 `PARROQUIA` varchar(30) ,
 `DIRECCION` varchar(50) ,
 `ESTADO_CIVIL` varchar(12) ,
 `TITULO` varchar(50) ,
 `TIPO_CONTRATO` varchar(20) ,
 `ANTERIOR_INSTITUCION` varchar(50) ,
 `FECHA_INGRESO` date ,
 `ESTADO` varchar(10) 
)*/;

/*Table structure for table `mostrarmaterias` */

DROP TABLE IF EXISTS `mostrarmaterias`;

/*!50001 DROP VIEW IF EXISTS `mostrarmaterias` */;
/*!50001 DROP TABLE IF EXISTS `mostrarmaterias` */;

/*!50001 CREATE TABLE  `mostrarmaterias`(
 `ID` int(11) ,
 `NOMBRE` varchar(50) ,
 `AREA` varchar(50) ,
 `HORAS_SEMANALES` int(11) ,
 `ESTADO` varchar(10) 
)*/;

/*Table structure for table `mostrarniveles` */

DROP TABLE IF EXISTS `mostrarniveles`;

/*!50001 DROP VIEW IF EXISTS `mostrarniveles` */;
/*!50001 DROP TABLE IF EXISTS `mostrarniveles` */;

/*!50001 CREATE TABLE  `mostrarniveles`(
 `ID` int(11) ,
 `DESCRIPCION` varchar(77) ,
 `ORDEN` varchar(11) ,
 `GRADO` varchar(30) ,
 `ESPECIALIDAD` varchar(30) ,
 `ESTADO` varchar(30) 
)*/;

/*Table structure for table `mostrarpensiones` */

DROP TABLE IF EXISTS `mostrarpensiones`;

/*!50001 DROP VIEW IF EXISTS `mostrarpensiones` */;
/*!50001 DROP TABLE IF EXISTS `mostrarpensiones` */;

/*!50001 CREATE TABLE  `mostrarpensiones`(
 `ID` int(11) ,
 `DESCRIPCION` varchar(50) ,
 `VALOR_MATRICULA` varchar(22) ,
 `VALOR_MENSUAL` varchar(22) ,
 `CANTIDAD_PAGOS` varchar(11) ,
 `ESTADO` varchar(10) 
)*/;

/*Table structure for table `mostrarperiodos` */

DROP TABLE IF EXISTS `mostrarperiodos`;

/*!50001 DROP VIEW IF EXISTS `mostrarperiodos` */;
/*!50001 DROP TABLE IF EXISTS `mostrarperiodos` */;

/*!50001 CREATE TABLE  `mostrarperiodos`(
 `ID` int(11) ,
 `DESCRIPCION` varchar(50) ,
 `FECHA_INICIO` date ,
 `FECHA_FIN` date ,
 `ESTADO` varchar(10) 
)*/;

/*Table structure for table `mostrarrepresentantes` */

DROP TABLE IF EXISTS `mostrarrepresentantes`;

/*!50001 DROP VIEW IF EXISTS `mostrarrepresentantes` */;
/*!50001 DROP TABLE IF EXISTS `mostrarrepresentantes` */;

/*!50001 CREATE TABLE  `mostrarrepresentantes`(
 `ID` int(11) ,
 `NOMBRES` varchar(50) ,
 `APELLIDO_PATERNO` varchar(50) ,
 `APELLIDO_MATERNO` varchar(50) ,
 `APELLIDOS` varchar(101) ,
 `CEDULA` varchar(10) ,
 `GENERO` varchar(10) ,
 `FECHA_NACIMIENTO` date ,
 `TELEFONO` varchar(10) ,
 `CORREO` varchar(50) ,
 `PROVINCIA` varchar(30) ,
 `CANTON` varchar(30) ,
 `PARROQUIA` varchar(30) ,
 `DIRECCION` varchar(50) ,
 `OCUPACION` varchar(50) ,
 `LUGAR_TRABAJO` varchar(50) ,
 `TELEFONO_TRABAJO` varchar(10) ,
 `ESTADO_CIVIL` varchar(12) ,
 `ESTADO` varchar(10) 
)*/;

/*Table structure for table `mostrarusuarios` */

DROP TABLE IF EXISTS `mostrarusuarios`;

/*!50001 DROP VIEW IF EXISTS `mostrarusuarios` */;
/*!50001 DROP TABLE IF EXISTS `mostrarusuarios` */;

/*!50001 CREATE TABLE  `mostrarusuarios`(
 `id_usuario` int(11) ,
 `id_autoridad` int(11) ,
 `usuario` varchar(30) ,
 `contrasena` varchar(15) ,
 `NOMBRES` varchar(152) ,
 `upper(u.tipo)` varchar(20) ,
 `upper(u.estado)` varchar(10) 
)*/;

/*View structure for view mostraralumnos */

/*!50001 DROP TABLE IF EXISTS `mostraralumnos` */;
/*!50001 DROP VIEW IF EXISTS `mostraralumnos` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostraralumnos` AS select `a`.`id_alumno` AS `ID`,upper(`a`.`nombres`) AS `NOMBRES`,upper(`a`.`apell_paterno`) AS `APELLIDO_PATERNO`,upper(`a`.`apell_materno`) AS `APELLIDO_MATERNO`,concat(upper(`a`.`apell_paterno`),' ',upper(`a`.`apell_materno`)) AS `APELLIDOS`,`a`.`cedula` AS `CEDULA`,upper(`a`.`genero`) AS `GENERO`,`a`.`fecha_nacimiento` AS `FECHA_NACIMIENTO`,(extract(year from curdate()) - extract(year from `a`.`fecha_nacimiento`)) AS `EDAD`,`a`.`telefono` AS `TELEFONO`,`a`.`correo` AS `CORREO`,upper(`a`.`provincia`) AS `PROVINCIA`,upper(`a`.`canton`) AS `CANTON`,upper(`a`.`parroquia`) AS `PARROQUIA`,upper(`a`.`direccion`) AS `DIRECCION`,`a`.`estado` AS `ESTADO`,`r`.`id_representante` AS `ID_REPRESENTANTE`,upper(`r`.`nombres`) AS `NOMBRES_REPRESENTANTE`,upper(concat(`r`.`apell_paterno`,' ',`r`.`apell_materno`)) AS `APELLIDOS_REPRESENTANTE` from (`alumnos` `a` join `representantes` `r` on((`a`.`id_representante` = `r`.`id_representante`))) where (`a`.`estado` = 'ACTIVO') order by concat(upper(`a`.`apell_paterno`),' ',upper(`a`.`apell_materno`)) limit 100 */;

/*View structure for view mostrarautoridades */

/*!50001 DROP TABLE IF EXISTS `mostrarautoridades` */;
/*!50001 DROP VIEW IF EXISTS `mostrarautoridades` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarautoridades` AS select `d`.`id_autoridad` AS `ID`,upper(`d`.`nombres`) AS `NOMBRES`,upper(`d`.`apell_paterno`) AS `APELLIDO_PATERNO`,upper(`d`.`apell_materno`) AS `APELLIDO_MATERNO`,concat(upper(`d`.`apell_paterno`),' ',upper(`d`.`apell_materno`)) AS `APELLIDOS`,`d`.`cedula` AS `CEDULA`,upper(`d`.`genero`) AS `GENERO`,`d`.`fecha_nacimiento` AS `FECHA_NACIMIENTO`,`d`.`telefono` AS `TELEFONO`,`d`.`correo` AS `CORREO`,upper(`d`.`provincia`) AS `PROVINCIA`,upper(`d`.`canton`) AS `CANTON`,upper(`d`.`parroquia`) AS `PARROQUIA`,upper(`d`.`direccion`) AS `DIRECCION`,upper(`d`.`estado_civil`) AS `ESTADO_CIVIL`,upper(`d`.`profesion`) AS `PROFESION`,upper(`d`.`cargo`) AS `CARGO`,`d`.`fecha_ingreso` AS `FECHA_INGRESO`,upper(`d`.`estado`) AS `ESTADO` from `autoridades` `d` where (`d`.`estado` = 'ACTIVO') order by concat(upper(`d`.`apell_paterno`),' ',upper(`d`.`apell_materno`)) limit 100 */;

/*View structure for view mostrarcursos */

/*!50001 DROP TABLE IF EXISTS `mostrarcursos` */;
/*!50001 DROP VIEW IF EXISTS `mostrarcursos` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarcursos` AS select `c`.`id_curso` AS `ID`,`n`.`id_nivel` AS `ID_NIVEL`,concat(`n`.`orden`,' AÑO ',`n`.`grado`,' ',`n`.`especialidad`,' ',`c`.`jornada`,' ',`c`.`paralelo`) AS `DESCRIPCION`,upper(`c`.`paralelo`) AS `PARALELO`,upper(`c`.`jornada`) AS `JORNADA`,`c`.`capacidad` AS `CAPACIDAD` from (`cursos` `c` join `niveles` `n` on((`c`.`id_nivel` = `n`.`id_nivel`))) where (`c`.`estado` = 'ACTIVO') limit 100 */;

/*View structure for view mostrardocentes */

/*!50001 DROP TABLE IF EXISTS `mostrardocentes` */;
/*!50001 DROP VIEW IF EXISTS `mostrardocentes` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrardocentes` AS select `d`.`id_docente` AS `ID`,upper(`d`.`nombres`) AS `NOMBRES`,upper(`d`.`apell_paterno`) AS `APELLIDO_PATERNO`,upper(`d`.`apell_materno`) AS `APELLIDO_MATERNO`,concat(upper(`d`.`apell_paterno`),' ',upper(`d`.`apell_materno`)) AS `APELLIDOS`,`d`.`cedula` AS `CEDULA`,upper(`d`.`genero`) AS `GENERO`,`d`.`fecha_nacimiento` AS `FECHA_NACIMIENTO`,`d`.`telefono` AS `TELEFONO`,`d`.`correo` AS `CORREO`,upper(`d`.`provincia`) AS `PROVINCIA`,upper(`d`.`canton`) AS `CANTON`,upper(`d`.`parroquia`) AS `PARROQUIA`,upper(`d`.`direccion`) AS `DIRECCION`,upper(`d`.`estado_civil`) AS `ESTADO_CIVIL`,upper(`d`.`titulo`) AS `TITULO`,upper(`d`.`tipo_contrato`) AS `TIPO_CONTRATO`,upper(`d`.`anterior_institucion`) AS `ANTERIOR_INSTITUCION`,`d`.`fecha_ingreso` AS `FECHA_INGRESO`,upper(`d`.`estado`) AS `ESTADO` from `docentes` `d` where (`d`.`estado` = 'ACTIVO') order by concat(upper(`d`.`apell_paterno`),' ',upper(`d`.`apell_materno`)) limit 100 */;

/*View structure for view mostrarmaterias */

/*!50001 DROP TABLE IF EXISTS `mostrarmaterias` */;
/*!50001 DROP VIEW IF EXISTS `mostrarmaterias` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarmaterias` AS select `m`.`id_materia` AS `ID`,upper(`m`.`nombre`) AS `NOMBRE`,upper(`m`.`area`) AS `AREA`,`m`.`horas_semanales` AS `HORAS_SEMANALES`,upper(`m`.`estado`) AS `ESTADO` from `materias` `m` where (`m`.`estado` = 'ACTIVO') */;

/*View structure for view mostrarniveles */

/*!50001 DROP TABLE IF EXISTS `mostrarniveles` */;
/*!50001 DROP VIEW IF EXISTS `mostrarniveles` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarniveles` AS select `n`.`id_nivel` AS `ID`,upper(concat(`n`.`orden`,' AÑO ',`n`.`grado`,' ',`n`.`especialidad`)) AS `DESCRIPCION`,upper(`n`.`orden`) AS `ORDEN`,upper(`n`.`grado`) AS `GRADO`,upper(`n`.`especialidad`) AS `ESPECIALIDAD`,upper(`n`.`estado`) AS `ESTADO` from `niveles` `n` where (`n`.`estado` = 'ACTIVO') limit 100 */;

/*View structure for view mostrarpensiones */

/*!50001 DROP TABLE IF EXISTS `mostrarpensiones` */;
/*!50001 DROP VIEW IF EXISTS `mostrarpensiones` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarpensiones` AS select `p`.`id_pension` AS `ID`,upper(`p`.`descripcion`) AS `DESCRIPCION`,upper(`p`.`valor_matricula`) AS `VALOR_MATRICULA`,upper(`p`.`valor_mensual`) AS `VALOR_MENSUAL`,upper(`p`.`cantidad_pagos`) AS `CANTIDAD_PAGOS`,upper(`p`.`estado`) AS `ESTADO` from `pensiones` `p` where (`p`.`estado` = 'ACTIVO') */;

/*View structure for view mostrarperiodos */

/*!50001 DROP TABLE IF EXISTS `mostrarperiodos` */;
/*!50001 DROP VIEW IF EXISTS `mostrarperiodos` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarperiodos` AS select `p`.`id_periodo` AS `ID`,upper(`p`.`descripcion`) AS `DESCRIPCION`,`p`.`fecha_inicio` AS `FECHA_INICIO`,`p`.`fecha_fin` AS `FECHA_FIN`,upper(`p`.`estado`) AS `ESTADO` from `periodos` `p` order by `p`.`fecha_inicio` desc limit 100 */;

/*View structure for view mostrarrepresentantes */

/*!50001 DROP TABLE IF EXISTS `mostrarrepresentantes` */;
/*!50001 DROP VIEW IF EXISTS `mostrarrepresentantes` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarrepresentantes` AS select `r`.`id_representante` AS `ID`,upper(`r`.`nombres`) AS `NOMBRES`,upper(`r`.`apell_paterno`) AS `APELLIDO_PATERNO`,upper(`r`.`apell_materno`) AS `APELLIDO_MATERNO`,concat(upper(`r`.`apell_paterno`),' ',upper(`r`.`apell_materno`)) AS `APELLIDOS`,`r`.`cedula` AS `CEDULA`,upper(`r`.`genero`) AS `GENERO`,`r`.`fecha_nacimiento` AS `FECHA_NACIMIENTO`,`r`.`telefono` AS `TELEFONO`,`r`.`correo` AS `CORREO`,upper(`r`.`provincia`) AS `PROVINCIA`,upper(`r`.`canton`) AS `CANTON`,upper(`r`.`parroquia`) AS `PARROQUIA`,upper(`r`.`direccion`) AS `DIRECCION`,upper(`r`.`ocupacion`) AS `OCUPACION`,upper(`r`.`lugar_trabajo`) AS `LUGAR_TRABAJO`,upper(`r`.`telefono_trabajo`) AS `TELEFONO_TRABAJO`,upper(`r`.`estado_civil`) AS `ESTADO_CIVIL`,upper(`r`.`estado`) AS `ESTADO` from `representantes` `r` where (`r`.`estado` = 'ACTIVO') order by concat(upper(`r`.`apell_paterno`),' ',upper(`r`.`apell_materno`)) limit 100 */;

/*View structure for view mostrarusuarios */

/*!50001 DROP TABLE IF EXISTS `mostrarusuarios` */;
/*!50001 DROP VIEW IF EXISTS `mostrarusuarios` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `mostrarusuarios` AS select `u`.`id_usuario` AS `id_usuario`,`u`.`id_autoridad` AS `id_autoridad`,`u`.`usuario` AS `usuario`,`u`.`contrasena` AS `contrasena`,upper(concat(`a`.`nombres`,' ',`a`.`apell_paterno`,' ',`a`.`apell_materno`)) AS `NOMBRES`,upper(`u`.`tipo`) AS `upper(u.tipo)`,upper(`u`.`estado`) AS `upper(u.estado)` from (`usuarios` `u` join `autoridades` `a` on((`u`.`id_autoridad` = `a`.`id_autoridad`))) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
