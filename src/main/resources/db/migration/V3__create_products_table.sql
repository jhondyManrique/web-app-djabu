CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL UNIQUE,
    unit_price NUMERIC(10, 2) NOT NULL,
    CONSTRAINT price_must_be_positive CHECK (unit_price >= 0)
);
