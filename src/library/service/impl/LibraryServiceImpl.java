package library.service.impl;

import library.dao.LibraryDao;
import library.dao.impl.LibraryDaoImpl;
import library.model.Book;
import library.model.status.LibraryStatus;
import library.service.LibraryService;

import java.util.List;
import java.util.Set;

public class LibraryServiceImpl implements LibraryService {

    private LibraryServiceImpl() {}
    public static final LibraryServiceImpl INTANCE = new LibraryServiceImpl();
    public static LibraryServiceImpl getInstance() {
        return INTANCE;
    }

    LibraryDao libraryDao = LibraryDaoImpl.getInstance();

    Set<Book> set = null;

    /**
     *  通过id查找书
     */
    @Override
    public boolean borrowBookById(int id) {
        Book book = findBookById(id);
        if (book == null) {
            return false;
        }
        book.setBookNum(book.getBookNum() - 1);
        book.setBorrowOut(book.getBorrowOut() + 1);
        if (update(book) == true) {
            return true;
        }
        return false;
    }
    // 找书
    public Book findBookById(int id) {
        return libraryDao.findBookById(id);
    }

    @Override
    public boolean returnBook(int id) {
        Book book = libraryDao.findBookById(id);
        if (book == null) {
            return false;
        }
        book.setBookNum(book.getBookNum() + 1);
        book.setBorrowOut(book.getBorrowOut() - 1);
        if (update(book) == true) {
            return true;
        }
        return false;
    }

    /**
     *
     */
    public boolean update(Book book) {
        return libraryDao.updateBook(book);
    }

    @Override
    public List<Book> showAllBook() {
        List<Book> listBook = libraryDao.findAllBook();
        return listBook;
    }

    @Override
    public boolean addBook(Book book) {
        boolean addResult = libraryDao.addBook(book);
        //添加成功，让成员个数保存
        return  addResult;
    }

    @Override
    public boolean delectBook(int id) {
        Book book = libraryDao.findBookById(id);
        if (book == null) {
            return false;
        }
        int row = libraryDao.deleteBook(book);
        if (row > 0) {
            return true;
        }
        return false;
    }

    /*public boolean delectBook(int id) {
        Set<Book> set = libraryDao.findAllBook();
        Book book = libraryDao.findBookById(id);
        set.remove(book); //set.size() 少了一位
        int temp = 1;
        int delectId = id;
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
            book = iterator.next();
            if (temp >= delectId) {
                book.setBookId(id++);
                set.add(book);
            }
            temp++;
        }
        libraryDao.saveAllBook(set);
        return true;
    }*/

}
