CREATE TABLE detalle_ventas (
    id_detalle SERIAL PRIMARY KEY,
    id_venta INTEGER NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    cantidad INTEGER NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_venta
        FOREIGN KEY(id_venta)
        REFERENCES ventas(id_venta)
        ON DELETE CASCADE
);