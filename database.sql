CREATE DATABASE employee_db;

USE employee_db;

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100),
    salary DOUBLE
);

INSERT INTO employees VALUES
(1, 'Ravi', 'IT', 45000),
(2, 'Priya', 'HR', 40000),
(3, 'Kiran', 'Finance', 50000);

SELECT * FROM employees;
