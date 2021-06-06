CREATE TABLE users (
    id Number Not Null primary key,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    age number NOT NULL,
    phone_number varchar2(100),
    username VARCHAR(255) NOT NULL);

CREATE TABLE address (
    id Number Not Null primary key,
    user_id Number NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    postcode VARCHAR(10) NOT NULL,
    Foreign key user_id REFERENCES users (id) );

create table USER_CREDENTIALS(
    id number not null primary key,
    email varchar2(255) not null unique,
    password varchar2(255) not null,
    enabled boolean,
    foreign key email references users (email)
);