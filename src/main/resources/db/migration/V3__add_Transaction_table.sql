/* Create table */
CREATE TABLE Transactions
(
    transaction_id SERIAL PRIMARY KEY,
    member_id      integer   NOT NULL,
    book_id        integer   NOT NULL,
    quantity       integer   NOT NULL,
    date           timestamp NOT NULL
);

/* Insert values */
INSERT INTO Transactions (member_id, book_id, quantity, date)
VALUES (1, 1, 1, '2021-03-19 08:07:22.377862'),
       (1, 2, 2, '2021-03-19 08:07:22.377862'),
       (1, 3, 1, '2021-05-25 03:59:33.377862'),
       (2, 1, 2, '2018-11-22 08:07:58.377862'),
       (2, 3, 1, '2020-02-09 12:05:33.377862'),
       (2, 4, 2, '2020-02-09 12:05:33.377862'),
       (3, 1, 3, '2021-03-23 18:00:00.377862');