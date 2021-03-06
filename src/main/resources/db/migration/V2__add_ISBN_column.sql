/* Add ISBN Column */
ALTER TABLE Books
    ADD ISBN varchar;

/* Insert values */
UPDATE Books
SET ISBN = '0-7696-1930-4'
WHERE book_id = 1;

UPDATE Books
SET ISBN = '0-8048-7360-7'
WHERE book_id = 2;

UPDATE Books
SET ISBN = '0-7066-7258-5'
WHERE book_id = 3;

UPDATE Books
SET ISBN = '0-7974-6269-4'
WHERE book_id = 4;

/* Alter column */
ALTER TABLE Books
    ALTER COLUMN ISBN SET NOT NULL;