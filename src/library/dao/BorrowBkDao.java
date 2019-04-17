package library.dao;

import library.model.BookInfo;

import java.util.List;

public interface BorrowBkDao {

    public boolean addBook(BookInfo bookInfo);

    public boolean updateBook(BookInfo bookInfo);

    public BookInfo findBkInfoByBorrowTime(String borrowTime);

    public List quaryBookInfo();
}
