INSERT INTO authors (id, full_name)
VALUES
       ( 1, 'Horstmann Cornell' ),
       ( 2, 'Aka Akasaka');

INSERT INTO books (id, title)
VALUES
       (1, 'Java' ),
       (2, 'Love is war' );

INSERT INTO author_book (id_author, id_book)
VALUES
       ( 1, 1 ),
       ( 2, 2);


INSERT INTO volumes (id, book_id, isbn, volume_name, volume_number)
VALUES
       ( 1, 1, 'java', '', 1 ),
       ( 2, 2, 'liw1', '1', 1),
       ( 3, 2, 'liw2', '2', 2);

INSERT INTO customers (id, full_name)
VALUES
       ( 1, 'me' ),
       ( 2, 'some other guy (or gal)');

INSERT INTO contact_info (id, customer_id, email, phone)
values
       ( 1, 1, 'main@mail.com', '+12 345 67 89' ),
       ( 2, 1, 'secondary@mail.com', ''),
       ( 3, 2, 'other@mail.com', '+98 765 43 21');

INSERT INTO categories (id, name)
VALUES
       ( 1, 'programming' ),
       ( 2, 'java'),
       ( 3, 'romance'),
       ( 4, 'commedy');

INSERT INTO book_category (id_book, id_category)
VALUES
       ( 1, 1 ),
       ( 1, 2 ),
       ( 2, 3 ),
       ( 2, 4);