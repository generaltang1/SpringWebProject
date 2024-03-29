CREATE DATABASE taegumall;

USE taengumall;

CREATE TABLE accounts (
	id INTEGER AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL UNIQUE,
    email VARCHAR(32) NOT NULL UNIQUE,
    address VARCHAR(64) NOT NULL,
    admin BOOL NOT NULL DEFAULT FALSE,
    created DATETIME NOT NULL DEFAULT(NOW()),
    CONSTRAINT PRIMARY KEY(id)
);

INSERT INTO accounts VALUES(DEFAULT, "Admin", "admin", "admin", "", TRUE, DEFAULT);

CREATE TABLE products (
	id INTEGER AUTO_INCREMENT,
    price INTEGER NOT NULL,
    title VARCHAR(32) NOT NULL,
    detail TEXT,
    stock INTEGER NOT NULL,
    owner INTEGER NOT NULL,
    created DATETIME NOT NULL DEFAULT(NOW()),
    CONSTRAINT PRIMARY KEY(id),
    CONSTRAINT FOREIGN KEY(owner) REFERENCES accounts(id)
);

CREATE TABLE carts (
	id INTEGER AUTO_INCREMENT,
    account_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    count INTEGER NOT NULL,
    created DATETIME NOT NULL DEFAULT(NOW()),
    CONSTRAINT PRIMARY KEY(id),
    CONSTRAINT FOREIGN KEY(account_id) REFERENCES accounts(id),
    CONSTRAINT FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE records (
	id INTEGER AUTO_INCREMENT,
    account_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
	state INTEGER NOT NULL,
    count INTEGER NOT NULL,
    created DATETIME NOT NULL DEFAULT(NOW()),
    CONSTRAINT PRIMARY KEY(id),
    CONSTRAINT FOREIGN KEY(account_id) REFERENCES accounts(id),
    CONSTRAINT FOREIGN KEY(product_id) REFERENCES products(id)
);