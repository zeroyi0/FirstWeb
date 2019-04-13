package library.dao;

import library.model.Book;

import java.util.List;
import java.util.Set;

public interface LibraryDao {
    //根据id查找图书
    public Book findBookById(int id);
    //查找全部图书
    public List<Book> findAllBook();
    //添加图书
    public boolean addBook(Book book);
    //删除图书
    public int deleteBook(Book book);
    //更新图书信息
    public boolean updateBook(Book book);
}
