/* Drop existing tables */
DROP TABLE IF EXISTS Members, Books, Publishers, Authors, Write, Borrow;

/* Create tables */
CREATE TABLE Members
(
    MemberID   int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    FirstName  varchar NOT NULL,
    LastName   varchar NOT NULL,
    Telephone  varchar NOT NULL,
    BorrowDate date    NOT NULL,
    ReturnDate date    NOT NULL,
    CONSTRAINT member_pk PRIMARY KEY (MemberID)
);

CREATE TABLE Books
(
    BookID int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    Title  varchar NOT NULL,
    Price  varchar NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (BookID)
);

CREATE TABLE Publishers
(
    PublisherID int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    Name        varchar NOT NULL,
    Street      varchar NOT NULL,
    City        varchar NOT NULL,
    Zip         varchar NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (PublisherID)
);

CREATE TABLE Authors
(
    AuthorID  int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    FirstName varchar NOT NULL,
    LastName  varchar NOT NULL,
    CONSTRAINT author_pk PRIMARY KEY (AuthorID)
);

CREATE TABLE Borrow
(
    MemberID int NOT NULL,
    BookID   int NOT NULL
);

CREATE TABLE Write
(
    BookID   int NOT NULL,
    AuthorID int NOT NULL
);

/* Insert values */
INSERT INTO Members (firstname, lastname, telephone, borrowDate, returnDate)
VALUES ('Steve', 'Jobs', '12345678', '2021-10-13', '2021-10-18'),
       ('Nocolus', 'Kate', '93746638', '2021-10-17', '2021-10-22');

INSERT INTO Books (title, price)
VALUES ('The jungle book', '30.00'),
       ('The martian', '40.00');

INSERT INTO Publishers (name, street, city, zip)
values ('Wall Street', '15 Ings Lane', 'DAVENTRY', 'NN11 2JW'),
       ('London House', '27 St Denys Road', 'PREES HIGHER HEATH', 'SY13 5DX');

INSERT INTO Authors (firstname, lastname)
VALUES ('Tom', 'Hank'),
       ('Jenifer', 'Paul'),
       ('James', 'Cameron');

INSERT INTO Borrow (MemberID, BookID)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO Write (BookID, AuthorID)
values (1, 1),
       (1, 3),
       (2, 1),
       (2, 2);