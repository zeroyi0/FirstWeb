package library.service;

import library.model.Book;

import java.util.List;

public interface LibraryService {

    public Book findBookById(int id);
    // 借书
    public boolean borrowBookById(int id);

    public boolean returnBook(int id);

    public List<Book> showAllBook();

    public boolean addBook(Book book);

    public boolean delectBook(int id);

    public boolean update(Book book);
}
