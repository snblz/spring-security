CREATE DATABASE  spring_security_test;
\connect spring_security_test;


CREATE TABLE users(
 
	username 	varchar(50)
	PRIMARY KEY,
	password	varchar(68)
	NOT NULL,	
	enabled		boolean		NOT NULL
); 

CREATE TABLE authorities(
 
	username 	varchar(50)	NOT NULL,
	authority	varchar(50)	NOT NULL,
	FOREIGN KEY (username) REFERENCES users (username)
); 





INSERT INTO users VALUES 
('John', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', TRUE),
	
			 ('Mary', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', TRUE),
	
			 ('Susan', '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', TRUE);
	


INSERT INTO authorities VALUES  
('John', 'ROLE_EMPLOYEE'),
	
				('Mary', 'ROLE_EMPLOYEE'),
	
				('Mary', 'ROLE_MANAGER'),
				('Susan', 'ROLE_EMPLOYEE'),
				('Susan', 'ROLE_ADMIN');
					

/*  password = fun123 */					