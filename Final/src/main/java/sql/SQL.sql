--Creacion tabla usuario
--Credenciales para acceder como Cliente: nombreUsuario: ari, password: 3
--Credenciales para acceder como Empleado: nombreUsuario: ari, password: 2
--Credenciales para acceder como Admin: nombreUsuario: admin, password: 12345

CREATE TABLE IF NOT EXISTS usuario (id INTEGER auto_increment NOT NULL PRIMARY KEY, 
                    nombreUsuario VARCHAR(50) NULL, 
                    contrasena VARCHAR(50) NULL,
                    tipoUsuario ENUM('CLIENTE', 'EMPLEADO', 'ADMIN'), 
                    dni VARCHAR(50) NULL,
                    nombre VARCHAR(50) NULL,
                    apellido VARCHAR(50) NULL,
                    sexo VARCHAR(15) NULL,
                    telefono LONG NULL,
                    email VARCHAR(50) NULL);

INSERT INTO usuario(nombre, contrasena) VALUES ('ari', '2');
INSERT INTO usuario(nombreUsuario, contrasena, tipoUsuario, dni, nombre, apellido, sexo, telefono, email) 
VALUES ('ari', '5', 'CLIENTE', 'null', 'null', 'null', 'null', null, 'null');

--Creacion tabla mensaje
CREATE TABLE IF NOT EXISTS mensaje (id INTEGER auto_increment NOT NULL PRIMARY KEY,
                    idAutor INTEGER NULL,
                    nombreAutor VARCHAR(50),
                    asunto VARCHAR(50),
                    tipoUsuario ENUM('CLIENTE', 'EMPLEADO', 'ADMIN') NULL,
                    esUrgente BOOLEAN NULL,
                    esLeido BOOLEAN NULL,
                    idDestinatario NULL,
                    texto VARCHAR(255) NULL,
                    FOREIGN KEY (idAutor) REFERENCES usuario(id), 
                    FOREIGN KEY (idDestinatario) REFERENCES usuario(id));

INSERT INTO mensaje(idAutor, nombreAutor, asunto, tipoUsuario, esUrgente, esLeido, idDestinatario, texto) 
VALUES (3, 'ari', 'Respuesta', 'EMPLEADO', false, false, 2, 'Buenos días, jjjkkllk');

--Creacion tabla servicio
CREATE TABLE IF NOT EXISTS servicio(id INTEGER auto_increment NOT NULL PRIMARY KEY, 
                    tipoServicio ENUM('CEREMONIA', 'GASTRONOMIA', 'MUSICA', 'FOTOGRAFIA', 'VIDEO', 'TRANSPORTE'), 
                    nombreServicio VARCHAR(50), 
                    precio DOUBLE);

INSERT INTO servicio(tipoServicio, nombreServicio, precio) VALUES ('VIDEO', 'hjhjkj', 43553.0);

--Creacion tabla personal
CREATE TABLE IF NOT EXISTS personal(id INTEGER auto_increment PRIMARY KEY NOT NULL, 
                    dni VARCHAR(50) NULL, 
                    nombre VARCHAR(255) NULL, 
                    fechaAlta TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
                    fechaBaja TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
                    sueldo DOUBLE NULL, idServicio INTEGER, 
                    FOREIGN KEY (idServicio) REFERENCES servicio (id) );

INSERT INTO personal(dni, nombre, fechaAlta, fechaBaja, sueldo, idServicio) VALUES 
('TYYU8778', 'Ariel', '2022-06-02 00:00:00.0', '2022-06-30 00:00:00.0', 55454.0, 8);

--Creacion tabla venta
CREATE TABLE IF NOT EXISTS venta(id INTEGER auto_increment PRIMARY KEY NOT NULL, 
                    codigoConjunto VARCHAR(50) NULL,
                    fechaVenta TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL, 
                    valorTotalVenta DOUBLE NULL, 
                    nombreUsuario VARCHAR(50) NULL,
                    nombreServicio VARCHAR(50) NULL,
                    idUsuario INTEGER,
                    idServicio INTEGER,
                    FOREIGN KEY (idUsuario) REFERENCES usuario(id),
                    FOREIGN KEY (idServicio) REFERENCES servicio (id) );

INSERT INTO venta(codigoConjunto, fechaVenta, valorTotalVenta, idUsuario, nombreUsuario, nombreServicio, idServicio) VALUES 
('TYYU8778', 'Ariel', '2022-06-02 00:00:00.0', '2022-06-30 00:00:00.0', 55454.0, 8);

