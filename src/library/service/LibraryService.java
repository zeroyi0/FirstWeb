package library.service;

import library.model.Book;

import java.util.List;

public interface LibraryService {

    public Book findBookById(int id);

    public List<Book> showAllBook();

    public boolean addBook(Book book);

    public boolean delectBook(int id);

    public boolean update(Book book);
}
