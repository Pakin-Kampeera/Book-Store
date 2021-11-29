/* Drop existing tables */
DROP TABLE IF EXISTS Members, Books, Publishers, Authors, Write, Buy;

/* Create tables */
CREATE TABLE Members
(
    member_id SERIAL PRIMARY KEY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    telephone varchar NOT NULL
);

CREATE TABLE Publishers
(
    publisher_id SERIAL PRIMARY KEY,
    name         varchar NOT NULL,
    street       varchar NOT NULL,
    city         varchar NOT NULL,
    zip          varchar NOT NULL
);

CREATE TABLE Books
(
    book_id      SERIAL PRIMARY KEY,
    title        varchar          NOT NULL,
    price        double precision NOT NULL,
    publisher_id integer          NOT NULL REFERENCES Publishers (publisher_id)
);

CREATE TABLE Authors
(
    author_id SERIAL PRIMARY KEY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL
);

CREATE TABLE Write
(
    book_id   integer NOT NULL references Books (book_id),
    author_id integer NOT NULL references Authors (author_id)
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
VALUES ('The jungle book', 30.00, 1),
       ('The martian', 40.00, 2),
       ('Harry Potter', 45.00, 1),
       ('King Kong', 30.00, 2);

INSERT INTO Authors (firstname, lastname)
VALUES ('Tom', 'Hank'),
       ('Jenifer', 'Paul'),
       ('James', 'Cameron'),
       ('Patrick', 'Rothfuss');

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