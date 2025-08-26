CREATE TABLE order_details (
    id_detail SERIAL PRIMARY KEY,
    id_order INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_id_order
        FOREIGN KEY(id_order)
        REFERENCES orders(id_order)
        ON DELETE CASCADE
);