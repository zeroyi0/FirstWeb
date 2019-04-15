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
        int row = super.update(sql, bookInfo.isReturnBook(),bookInfo.getReturnTime(),bookInfo.getBorrowTime());
        if (row == 1) { // 更新成功
            return true;
        }
        return false;
    }

    @Override
    public List quaryBookInfo() {
        String sql = "SELECT * FROM BookInfo";
        List list = super.executeQuery(BookInfo.class, sql);
        if (list != null) {
            return list;
        }
        return null;
    }
}
