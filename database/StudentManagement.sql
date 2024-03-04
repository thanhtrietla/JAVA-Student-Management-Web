CREATE DATABASE StudentManagement;
USE StudentManagement;
GO

CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(50),
    grade INT,
    birthday DATE,
    address VARCHAR(100),
    notes VARCHAR(255)
);

CREATE TABLE courses (
    class_id INT PRIMARY KEY,
    name VARCHAR(50),
    lecture VARCHAR(50),
    year INT,
    notes VARCHAR(255)
);

CREATE TABLE enrollments (
    enrollment_id INT IDENTITY(1,1) PRIMARY KEY,
    student_id INT NOT NULL,
    class_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (class_id) REFERENCES courses(class_id)
);


