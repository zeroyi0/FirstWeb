package library.service;

import library.model.BookInfo;

import java.util.List;

public interface BorrowBkService {

    public boolean addBook(BookInfo bookInfo);

    public boolean updateBook(BookInfo bookInfo);
    // 查询图书
    public List queryBkInfo();
}
