CREATE DATABASE studentdb;
SHOW DATABASES;
USE studentdb;
CREATE TABLE users( username varchar(50) ,pass_word varchar(50));
INSERT INTO users VALUES('ADMIN','1234');
CREATE TABLE students(
    id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    gender VARCHAR(10),
    email VARCHAR(100),
    phone VARCHAR(15),
    year_of_study INT,
    attendance VARCHAR(20)
);

