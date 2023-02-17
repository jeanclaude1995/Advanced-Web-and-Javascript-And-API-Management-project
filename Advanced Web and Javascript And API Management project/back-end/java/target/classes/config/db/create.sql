create table users
(
    id       serial      not null primary key,
    username varchar(30) not null,
    email    varchar(50) not null,
    password varchar(255) not null
);

create table contacts
(
    user_id   int         not null primary key,
    firstname varchar(30) not null,
    lastname  varchar(30) not null,
    birthdate date        not null,
    gender    varchar(1)  not null
);

create table addresses
(
    user_id int         not null primary key,
    country varchar(30) not null,
    area    varchar(20) not null,
    city    varchar(20) not null,
    street  varchar(40) not null,
    number  varchar(20) not null
);

create table movies
(
    id           serial      not null primary key,
    eid          bigint      not null,
    source       varchar(2)  not null,
    title        varchar(50) not null,
    image        varchar(255),
    release_date date        not null
);

create table user_activities
(
    id          bigserial                not null primary key,
    activity_ts timestamp with time zone not null,
    user_id     int                      not null,
    movie_id    int                      not null
);

ALTER TABLE users
    ADD CONSTRAINT unique_email UNIQUE (email);
ALTER TABLE users
    ADD CONSTRAINT unique_username UNIQUE (username);

ALTER TABLE contacts
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE addresses
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE movies
    ADD CONSTRAINT unique_external_movie UNIQUE (eid, source);

ALTER TABLE user_activities
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_activities
    ADD CONSTRAINT fk_movie_id
        FOREIGN KEY (movie_id) REFERENCES users (id);