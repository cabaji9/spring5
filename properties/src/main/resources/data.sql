
insert into user (FIRST_NAME,LAST_NAME,USER_NAME,PASSWORD,ENABLED) values ('uno','dos','usuario','$2a$10$cq6zqGVcpH731Vt22i9Z/erHLMG5pEFg2Ie68obEWXPXLCLlXjg9a',1);
insert into user (FIRST_NAME,LAST_NAME,USER_NAME,PASSWORD,ENABLED) values ('tres','cuatro','admin','$2a$10$cq6zqGVcpH731Vt22i9Z/erHLMG5pEFg2Ie68obEWXPXLCLlXjg9a',1);


insert into user_authorities(USER_NAME,AUTHORITY) VALUES ('usuario','ROLE_USER');
insert into user_authorities(USER_NAME,AUTHORITY) VALUES ('admin','ROLE_ADMIN');

