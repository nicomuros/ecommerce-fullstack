/*
 Para volver a 0: DELETE FROM flyway_schema_history; DROP TABLE users; DROP TABLE products;
 */

CREATE TABLE product(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL ,
    description TEXT NOT NULL ,
    price DECIMAL(10, 2) NOT NULL ,
    available BOOLEAN NOT NULL,
    img_data TEXT
);
