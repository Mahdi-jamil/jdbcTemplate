DROP TABLE IF EXISTS book;

DROP TABLE IF EXISTS author;

CREATE TABLE author (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    age INT
);

CREATE TABLE book (
    isbn VARCHAR(13) PRIMARY KEY,
    title VARCHAR(255),
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES author(id)
);
