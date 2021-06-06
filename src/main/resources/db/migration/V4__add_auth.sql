create table USER_AUTHORITY (
    id SERIAL PRIMARY KEY,
    authority varchar(255) NOT NULL,
    user_id INT NOT NULL,
    constraint fk_user_auth_user
    foreign key (user_id) references users (id)
);