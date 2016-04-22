
    create table Users (
        userId varchar(255) not null,
        active bool,
        name varchar(100),
        password varchar(255),
        username varchar(255),
        primary key (userId)
    );
