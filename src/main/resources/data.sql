DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    title     varchar NOT NULL,
    author    varchar NOT NULL,
    price     varchar NOT NULL,
    available bool    NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (id)
);