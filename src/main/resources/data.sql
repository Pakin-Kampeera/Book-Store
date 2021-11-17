/* Drop exist table */
DROP TABLE IF EXISTS Members, Books, Publishers, Author;

/* Create tales */
CREATE TABLE Members
(
    id         int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname  varchar NOT NULL,
    lastname   varchar NOT NULL,
    telephone  varchar NOT NULL,
    borrowDate date    NOT NULL,
    returnDate date    NOT NULL,
    CONSTRAINT member_pk PRIMARY KEY (id)
);

CREATE TABLE Books
(
    id     int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    title  varchar NOT NULL,
    price  varchar NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (id),
    CONSTRAINT member_fk FOREIGN KEY (id) REFERENCES Members (id)
);

CREATE TABLE Publishers
(
    id     int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    name   varchar NOT NULL,
    street varchar NOT NULL,
    city   varchar NOT NULL,
    zip    varchar NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (id),
    CONSTRAINT book_fk FOREIGN KEY (id) REFERENCES Books (id)
);

CREATE TABLE Authors
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    CONSTRAINT author_pk PRIMARY KEY (id),
    CONSTRAINT book_fk FOREIGN KEY (id) REFERENCES Books (id)
);

/* Insert data */
INSERT INTO Members (firstname, lastname, telephone, borrowDate, returnDate)
VALUES ('Steve', 'Jobs', '12345678', '2021-10-13', '2021-10-18');

INSERT INTO Members (firstname, lastname, telephone, borrowDate, returnDate)
VALUES ('Nocolus', 'Kate', '93746638', '2021-10-17', '2021-10-22');

INSERT INTO Books (title, price)
VALUES ('The jungle book', '30.00');

INSERT INTO Books (title, price)
VALUES ('The martian', '40.00');

INSERT INTO Publishers (name, street, city, zip)
values ('Wall Street', '15 Ings Lane', 'DAVENTRY', 'NN11 2JW');

INSERT INTO Publishers (name, street, city, zip)
values ('London House', '27 St Denys Road', 'PREES HIGHER HEATH', 'SY13 5DX');

INSERT INTO Authors (firstname, lastname)
VALUES ('Tom', 'Hank');

INSERT INTO Authors (firstname, lastname)
VALUES ('Jenifer', 'Pual');