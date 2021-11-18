/* Drop existing tables */
DROP TABLE IF EXISTS Members, Books, Publishers, Authors, Write, Borrow;

/* Create tables */
CREATE TABLE Members
(
    member_id int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    telephone varchar NOT NULL,
    CONSTRAINT member_pk PRIMARY KEY (member_id)
);

CREATE TABLE Publishers
(
    publisher_id int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    name         varchar NOT NULL,
    street       varchar NOT NULL,
    city         varchar NOT NULL,
    zip          varchar NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (publisher_id)
);

CREATE TABLE Books
(
    book_id      int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    title        varchar NOT NULL,
    price        varchar NOT NULL,
    publisher_id int     NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (book_id),
    CONSTRAINT publisher_fk FOREIGN KEY (publisher_id) REFERENCES Publishers (publisher_id)
);

CREATE TABLE Authors
(
    author_id int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    CONSTRAINT author_pk PRIMARY KEY (author_id)
);

CREATE TABLE Borrow
(
    member_id int NOT NULL,
    book_id   int NOT NULL
);

CREATE TABLE Write
(
    book_id   int NOT NULL,
    author_id int NOT NULL
);

/* Insert values */
INSERT INTO Members (firstname, lastname, telephone)
VALUES ('Steve', 'Jobs', '12345678'),
       ('Nocolus', 'Kate', '93746638'),
       ('Jason', 'Hammer', '63845356');

INSERT INTO Publishers (name, street, city, zip)
values ('Wall Street', '15 Ings Lane', 'DAVENTRY', 'NN11 2JW'),
       ('London House', '27 St Denys Road', 'PREES HIGHER HEATH', 'SY13 5DX');

INSERT INTO Books (title, price, publisher_id)
VALUES ('The jungle book', '30.00', 1),
       ('The martian', '40.00', 2),
       ('Harry Potter', '45.00', 1),
       ('King Kong', '30.00', 2);

INSERT INTO Authors (firstname, lastname)
VALUES ('Tom', 'Hank'),
       ('Jenifer', 'Paul'),
       ('James', 'Cameron'),
       ('Patrick', 'Rothfuss');

INSERT INTO Borrow (member_id, book_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 3),
       (2, 4);

INSERT INTO Write (book_id, author_id)
values (1, 1),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 2),
       (3, 4),
       (4, 1),
       (4, 2),
       (4, 4);