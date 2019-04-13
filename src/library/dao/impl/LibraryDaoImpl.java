package library.dao.impl;

import library.dao.LibraryDao;
import library.model.Book;

import java.util.List;

public class LibraryDaoImpl extends BaseDao implements LibraryDao {

    private LibraryDaoImpl() {}
    private static final LibraryDaoImpl INTANCE = new LibraryDaoImpl();
    public static LibraryDaoImpl getInstance() {
        return INTANCE;
    }

    @Override
    public Book findBookById(int id) {
        List<Book> bookList = executeQuery( Book.class,"SELECT * FROM Book WHERE bookId = ?", id);
        if (bookList == null || bookList.isEmpty()) {
            return null;
        }
        return bookList.get(0);
    }

    @Override
    public List<Book> findAllBook() {
        List<Book> bookList = executeQuery(Book.class,"SELECT * FROM Book");
        if (bookList == null || bookList.isEmpty()) {
            return null;
        }
        return bookList;
    }

    @Override
    public boolean addBook(Book book) {
        int row = super.add(book);
        if (row > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int deleteBook(Book book) {
        try {
            return super.deleteDate(book,"bookId = "+ book.getBookId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateBook(Book book) {
        int row = super.update("UPDATE Book SET bookName=?, author=?, bookNum=?, borrowOut=?, information=? WHERE bookId=?",
                        book.getBookName(),book.getAuthor(),book.getBookNum(),book.getBorrowOut(),book.getInformation(),book.getBookId());
        if (row == -1) {
            return false;
        }
        return true;
    }
}
