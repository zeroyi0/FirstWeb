package library.service.impl;

import library.dao.BorrowBkDao;
import library.dao.impl.BorrowBkDaoImpl;
import library.model.BookInfo;
import library.service.BorrowBkService;

import java.util.List;

public class BorrowBkServiceImpl implements BorrowBkService {

    private BorrowBkServiceImpl(){};
    private static final BorrowBkService INTANCE = new BorrowBkServiceImpl();
    public static BorrowBkService getIntance() {
        return INTANCE;
    }
    BorrowBkDao borrowBkDao = BorrowBkDaoImpl.getIntance();

    @Override
    public boolean addBook(BookInfo bookInfo) {
        return borrowBkDao.addBook(bookInfo);
    }

    @Override
    public boolean updateBook(BookInfo bookInfo) {
        return borrowBkDao.updateBook(bookInfo);
    }

    @Override
    public BookInfo findBkInfoByBorrowTime(String borrowTime) {
        return borrowBkDao.findBkInfoByBorrowTime(borrowTime);
    }

    @Override
    public List queryBkInfo() {
        return borrowBkDao.quaryBookInfo();
    }
}
