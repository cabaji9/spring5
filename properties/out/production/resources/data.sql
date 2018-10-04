
insert into user (FIRST_NAME,LAST_NAME,USER_NAME,PASSWORD,ENABLED) values ('uno','dos','usuario','$2a$10$nJEaV6bRxQsGorLFgAtinOfBh4KhQ6KUuxO6NTRPDUkw8tsAt6Odm',1);
insert into user (FIRST_NAME,LAST_NAME,USER_NAME,PASSWORD,ENABLED) values ('tres','cuatro','admin','$2a$10$nJEaV6bRxQsGorLFgAtinOfBh4KhQ6KUuxO6NTRPDUkw8tsAt6Odm',1);


insert into USER_AUTHORITIES(USER_NAME,AUTHORITY) VALUES ('usuario','ROLE_USER');
insert into USER_AUTHORITIES(USER_NAME,AUTHORITY) VALUES ('admin','ROLE_ADMIN');

