CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    age SMALLINT NOT NULL,
    phone_number varchar(100),
    username VARCHAR(255) NOT NULL);

CREATE TABLE address (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(255),
    postcode VARCHAR(10) NOT NULL,
    CONSTRAINT fk_addr_user
    Foreign key (user_id) REFERENCES users (id) ON DELETE CASCADE
);

create table USER_CREDENTIALS (
    id SERIAL PRIMARY KEY,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    enabled boolean,
    constraint fk_user_cred_user
    foreign key (email) references users (email)
);