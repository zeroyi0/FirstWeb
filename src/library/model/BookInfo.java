package library.model;

import library.annotation.Table;
import library.annotation.TableField;

// 所有借出图书的信息
@Table("BorrowBkInfo")
public class BookInfo {
    @TableField("borrowTime")
    private String borrowTime;

    private int userId;

    private int bookId;

    private String bookName;
    // 是否归还图书
    private String isReturnBook;

    private String returnTime;

    public BookInfo(String borrowTime, int userId, int bookId, String bookName, String isReturnBook, String returnTime) {
        this.borrowTime = borrowTime;
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.isReturnBook = isReturnBook;
        this.returnTime = returnTime;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String isReturnBook() {
        return isReturnBook;
    }

    public void setReturnBook(String returnBook) {
        isReturnBook = returnBook;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
