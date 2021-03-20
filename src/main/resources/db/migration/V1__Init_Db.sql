CREATE SEQUENCE author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE authors
(
    id        int8 NOT NULL DEFAULT nextval('author_id_seq'),
    full_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE books
(
    id int8 NOT NULL DEFAULT nextval('book_id_seq'),
    title VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE author_book
(
    id_author int8 NOT NULL,
    id_book int8 NOT NULL,
    PRIMARY KEY (id_author, id_book),
    FOREIGN KEY (id_book) REFERENCES books,
    FOREIGN KEY (id_author) REFERENCES authors
);

CREATE SEQUENCE volume_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE volumes
(
    id int8 NOT NULL DEFAULT nextval('volume_id_seq'),
    book_id int8 NOT NULL,
    isbn VARCHAR(50) NOT NULL,
    volume_name VARCHAR(255),
    volume_number int8,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books
);

CREATE SEQUENCE customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE customers
(
    id int8 NOT NULL DEFAULT nextval('customer_id_seq'),
    full_name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE SEQUENCE contact_info_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE contact_info
(
    id int8 NOT NULL DEFAULT nextval('contact_info_id_seq'),
    customer_id int8 NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers
);

CREATE SEQUENCE category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 2147483647
    CACHE 1;

CREATE TABLE categories
(
    id int8 NOT NULL DEFAULT nextval('category_id_seq'),
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE book_category
(
    id_book int8 NOT NULL,
    id_category int8 NOT NULL,
    PRIMARY KEY (id_book, id_category),
    FOREIGN KEY (id_book) REFERENCES books,
    FOREIGN KEY (id_category) REFERENCES categories
)