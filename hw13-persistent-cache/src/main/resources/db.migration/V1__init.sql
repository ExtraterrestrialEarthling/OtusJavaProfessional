CREATE TABLE IF NOT EXISTS BOOKS
(
    id bigserial PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    last_access_time TIMESTAMP
    );




INSERT INTO BOOKS (title, author) VALUES
                                      ('The Catcher in the Rye', 'J.D. Salinger'),
                                      ('To Kill a Mockingbird', 'Harper Lee'),
                                      ('1984', 'George Orwell'),
                                      ('The Great Gatsby', 'F. Scott Fitzgerald'),
                                      ('Pride and Prejudice', 'Jane Austen'),
                                      ('The Hobbit', 'J.R.R. Tolkien'),
                                      ('One Hundred Years of Solitude', 'Gabriel Garcia Marquez'),
                                      ('The Lord of the Rings', 'J.R.R. Tolkien'),
                                      ('The Chronicles of Narnia', 'C.S. Lewis'),
                                      ('Brave New World', 'Aldous Huxley');