create table IF NOT EXISTS ges_books_test
(
   ISBN varchar(50) not null,
   author varchar(255) not null,
   name_book varchar(255) not null,
   user_take varchar(50)
);

create table IF NOT EXISTS authorities
(
    USERNAME varchar(50) not null,
    AUTHORITY varchar(50) not null
);

create table IF NOT EXISTS ges_us_test
(
    USER_LOG  varchar(50),
    USER_PWD varchar(50)
)