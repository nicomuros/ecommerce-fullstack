/*
 Para volver a 0: DELETE FROM flyway_schema_history; DROP TABLE users; DROP TABLE products;
 */

CREATE TABLE product (
     id INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description TEXT NOT NULL,
     price INT NOT NULL,
     available TINYINT(1) NOT NULL,
     img_data LONGTEXT
);
