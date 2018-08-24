create table ges_books_test
(
   ISBN varchar(50) not null,
   author varchar(255) not null,
   name_book varchar(255) not null,
   user_take integer
);

create table AUTHORITIES
(
    USERNAME varchar(50) not null,
    AUTHORITY varchar(50) not null
);

create table GES_US_TEST
(
    USER_ID  integer,
    USER_LOG  varchar(50),
    USER_PWD varchar(50)
)