/* Drop exist table */
DROP TABLE IF EXISTS books, publisher, member;

/* Create tales */
CREATE TABLE public.books
(
    id    int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    title varchar NOT NULL,
    price varchar NOT NULL,
    CONSTRAINT books_pk PRIMARY KEY (id)
);

CREATE TABLE public.publisher
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    address   varchar NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (id),
    CONSTRAINT publisher_fk FOREIGN KEY (id) REFERENCES public.books (id)
);

CREATE TABLE public.member
(
    id         int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname  varchar NOT NULL,
    lastname   varchar NOT NULL,
    telephone  varchar NOT NULL,
    borrowDate date    NOT NULL,
    returnDate date    NOT NULL
        CONSTRAINT member_pk PRIMARY KEY (id)
);

CREATE TABLE public.author
(
    id        int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    CONSTRAINT author_pk PRIMARY KEY (id)
);
