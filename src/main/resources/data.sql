DROP TABLE IF EXISTS books, publisher, member;
CREATE TABLE books
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    title     varchar NOT NULL,
    author    varchar NOT NULL,
    price     varchar NOT NULL,
    available bool    NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (id)
);

CREATE TABLE publisher
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    address   varchar NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (id),
    CONSTRAINT publisher_fk FOREIGN KEY (id) REFERENCES public.books (id)
);

CREATE TABLE member
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    address   varchar NOT NULL,
    telephone varchar NOT NULL,
    CONSTRAINT member_pk PRIMARY KEY (id)
);
