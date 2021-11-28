/* Create table */
CREATE TABLE Transactions
(
    transaction_id SERIAL PRIMARY KEY,
    member_id      integer          NOT NULL,
    book_id        integer          NOT NULL,
    total_price    double precision NOT NULL,
    quantity       integer          NOT NULL,
    date           timestamp        NOT NULL
);

/* Insert values */
INSERT INTO Transactions (member_id, book_id, total_price, quantity, date)
VALUES (1, 1, 30.00, 1, '2021-03-19 08:07:22'),
       (1, 2, 80.00, 2, '2021-03-19 08:07:22'),
       (1, 3, 45.00, 1, '2021-05-25 03:59:33'),
       (2, 1, 60.00, 2, '2018-11-22 08:07:58'),
       (2, 3, 45.00, 1, '2020-02-09 12:05:33'),
       (2, 4, 60.00, 2, '2020-02-09 12:05:33'),
       (3, 1, 90.00, 3, '2021-03-23 18:00:00');