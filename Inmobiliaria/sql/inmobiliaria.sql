DROP DATABASE IF EXISTS inmobiliaria_db;

CREATE DATABASE inmobiliaria_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_spanish_ci;

USE inmobiliaria_db;

CREATE TABLE viviendas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_operacion ENUM('COMPRA', 'ALQUILER') NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    ciudad VARCHAR(80) NOT NULL,
    habitaciones INT NOT NULL,
    metros DECIMAL(7,2) NOT NULL,
    precio DECIMAL(12,2) NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO viviendas
(tipo_operacion, direccion, ciudad, habitaciones, metros, precio, disponible)
VALUES
('COMPRA', 'Calle Mayor 15, 2ºA', 'Madrid', 3, 92.50, 315000.00, TRUE),
('COMPRA', 'Avenida de la Constitución 42', 'Alcalá de Henares', 4, 118.00, 289000.00, TRUE),
('COMPRA', 'Calle Real 8, Bajo B', 'Getafe', 2, 74.25, 198000.00, TRUE),
('COMPRA', 'Paseo del Prado 21, 4ºC', 'Madrid', 3, 101.00, 465000.00, FALSE),
('COMPRA', 'Calle Luna 6, Ático', 'Leganés', 2, 68.00, 175000.00, TRUE),

('ALQUILER', 'Calle Sol 12, 1ºD', 'Madrid', 1, 45.00, 850.00, TRUE),
('ALQUILER', 'Avenida Europa 25, 3ºB', 'Pozuelo de Alarcón', 2, 72.00, 1250.00, TRUE),
('ALQUILER', 'Calle Jardines 3, 2ºA', 'Móstoles', 3, 88.00, 950.00, FALSE),
('ALQUILER', 'Plaza España 10, 5ºC', 'Madrid', 2, 64.50, 1100.00, TRUE),
('ALQUILER', 'Calle Olivo 19, Bajo A', 'Fuenlabrada', 1, 39.00, 690.00, TRUE);
