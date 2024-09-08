--fix this error on this query;
--You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near ' name VARCHAR(100), price DOUBLE )' at line 1
--CREATE TABLE products (
--    id BIGINT PRIMARY KEY SERIAL,
--    name VARCHAR(100),
--    price DOUBLE
--);

DROP TABLE IF EXISTS products;

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    price DOUBLE
);

