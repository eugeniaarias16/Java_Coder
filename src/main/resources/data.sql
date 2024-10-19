
INSERT INTO CLIENT (id, name, lastname, dni, birth)
VALUES
(5, 'Camila', 'Sosa', 889900112, '1992-12-30'),
(6, 'Gonzalo', 'Martinez', 778800991, '1985-07-19'),
(7, 'Lucia', 'Diaz', 665544332, '1993-01-15'),
(8, 'Julian', 'Moreno', 223311445, '1989-10-25'),
(9, 'Valentina', 'Ortiz', 998877665, '1994-04-17'),
(10, 'Santiago', 'Molina', 113322445, '1987-09-03');


INSERT INTO ADDRESS (id, street, number, floor, apartment, cp, province, city)
VALUES
(5, 'Rivadavia', 1020, 4, 'F', 5500, 'Mendoza', 'Mendoza Capital'),
(6, 'Mitre', 765, 2, 'A', 3200, 'Santa Fe', 'Rosario'),
(7, 'Belgrano', 1500, 1, 'B', 8300, 'Neuquén', 'Neuquén Capital'),
(8, 'San Juan', 456, 6, 'G', 2000, 'Santa Fe', 'Santa Fe Capital'),
(9, 'Avenida Libertador', 205, 12, 'D', 1160, 'Buenos Aires', 'Recoleta'),
(10, 'Alsina', 987, 8, 'E', 3000, 'Santa Fe', 'Santo Tomé');


INSERT INTO PRODUCT (id, description, stock, price, colors, sizes)
VALUES
('P007', 'Remera Básica', 100, 35000, 'BLANCO-NEGRO-GRIS', 'S-M-L-XL'),
('P008', 'Camisa Formal', 40, 95000, 'BLANCO-NEGRO', 'M-L-XL'),
('P009', 'Zapatillas Urbanas', 20, 110000, 'ROJO-AZUL-BLANCO', '40-41-42'),
('P010', 'Pantalón Cargo', 30, 92000, 'VERDE-MARRON', 'M-L-XL');


INSERT INTO SALE (sale_id, fecha, product_id, amount, total)
VALUES
(53, '2024-10-08', 'P007', 5, 175000),
(54, '2024-10-09', 'P008', 2, 190000),
(55, '2024-10-10', 'P009', 3, 330000),
(56, '2024-10-11', 'P010', 1, 92000),
(57, '2024-10-12', 'P001', 2, 159998),
(58, '2024-10-13', 'P003', 4, 155600);
