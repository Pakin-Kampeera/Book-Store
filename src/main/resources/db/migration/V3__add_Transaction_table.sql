/* Create table */
CREATE TABLE Transaction
(
    transaction_id int     NOT NULL GENERATED ALWAYS AS IDENTITY,
    member_id      int     NOT NULL,
    book_id        int     NOT NULL,
    ISBN           varchar NOT NULL,
    price          float   NOT NULL,
    quantity       int     NOT NULL,
    CONSTRAINT transaction_pk PRIMARY KEY (transaction_id)
);

/* Inset values */
INSERT INTO Transaction (member_id, book_id, ISBN, price, quantity)
VALUES (1, 1, '0-7696-1930-4', 30.00, 1),
       (1, 2, '0-8048-7360-7', 80.00, 2),
       (1, 3, '0-7066-7258-5', 45.00, 1),
       (2, 1, '0-7696-1930-4', 60.00, 2),
       (2, 3, '0-7066-7258-5', 45.00, 1),
       (2, 4, '0-7974-6269-4', 60.00, 2),
       (3, 1, '0-7696-1930-4', 90.00, 3);

