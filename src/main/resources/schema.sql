DROP TABLE CLIENT IF EXISTS;
CREATE TABLE CLIENT (
    id INT,
    name VARCHAR(150) NOT NULL,
    lastname VARCHAR(150) NOT NULL,
    dni INT NOT NULL,
    birth DATE NOT NULL
);

DROP TABLE ADDRESS IF EXISTS;
CREATE TABLE ADDRESS (
    id INT,
    street VARCHAR(150) NOT NULL,
    number INT NOT NULL,
    floor int,
    apartment VARCHAR(50),
    cp VARCHAR(20) NOT NULL,
    province VARCHAR(150) NOT NULL,
    city VARCHAR(150) NOT NULL
);

DROP TABLE PRODUCT IF EXISTS;
CREATE TABLE PRODUCT (
    id VARCHAR(80) NOT NULL,
    description VARCHAR(150) NOT NULL,
    stock INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    colors VARCHAR(150) NOT NULL,
    sizes VARCHAR(150)
);

DROP TABLE SALE IF EXISTS;
CREATE TABLE SALE (
    sale_id INT NOT NULL AUTO_INCREMENT,
    fecha DATE NOT NULL,
    product_id VARCHAR(80) NOT NULL,
    amount INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL
);

DROP TABLE IF EXISTS ALUMNO;