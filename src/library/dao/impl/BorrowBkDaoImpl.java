package library.dao.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import library.dao.BorrowBkDao;
import library.model.BookInfo;

import java.util.List;

public class BorrowBkDaoImpl extends BaseDao implements BorrowBkDao {

    private BorrowBkDaoImpl(){};
    private static final BorrowBkDao INTANCE = new BorrowBkDaoImpl();
    public static BorrowBkDao getIntance() {
        return INTANCE;
    }

    @Override
    public boolean addBook(BookInfo bookInfo) {
        int row = super.add(bookInfo);
        if (row == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBook(BookInfo bookInfo) {
        String sql = "UPDATE BookInfo SET isReturnBook=?, returnTime=? WHERE borrowTime=?";
        int row = super.update(sql, bookInfo.getIsReturnBook(), bookInfo.getReturnTime(), bookInfo.getBorrowTime());
        if (row == 1) { // 更新成功
            return true;
        }
        return false;
    }

    @Override
    public BookInfo findBkInfoByBorrowTime(String borrowTime) {
        String sql = "SELECT * FROM BorrowBkInfo WHERE BorrowTime=?";
        List<BookInfo> list = super.executeQuery(BookInfo.class, sql, borrowTime);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List quaryBookInfo() {
        List<BookInfo> list = super.executeQuery(BookInfo.class, "SELECT * FROM BorrowBkInfo ");
        if (list != null) {
            return list;
        }
        return null;
    }
}
