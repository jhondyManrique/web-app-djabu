CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    identification VARCHAR(25) NOT NULL UNIQUE,
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100),
    phone VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT password_min_length CHECK (length(password) >= 8)
);