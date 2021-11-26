/* Create table */
CREATE TABLE Transactions
(
    transaction_id int              NOT NULL GENERATED ALWAYS AS IDENTITY,
    member_id      int              NOT NULL,
    book_id        int              NOT NULL,
    total_price    double precision NOT NULL,
    quantity       int              NOT NULL,
    date           timestamp        NOT NULL,
    CONSTRAINT transaction_pk PRIMARY KEY (transaction_id)
);

/* Inset values */
INSERT INTO Transactions (member_id, book_id, total_price, quantity, date)
VALUES (1, 1, 30.00, 1, '2021-03-19 08:07:22'),
       (1, 2, 80.00, 2, '2020-08-01 16:55:33'),
       (1, 3, 45.00, 1, '2021-05-25 03:59:33'),
       (2, 1, 60.00, 2, '2018-11-22 08:07:58'),
       (2, 3, 45.00, 1, '2020-02-09 12:05:33'),
       (2, 4, 60.00, 2, '2021-11-15 15:50:24'),
       (3, 1, 90.00, 3, '2021-03-23 18:00:00');

