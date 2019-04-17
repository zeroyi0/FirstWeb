package library.model;

import library.annotation.Table;
import library.annotation.TableField;

// 所有借出图书的信息
@Table("BorrowBkInfo")
public class BookInfo {
    @TableField("borrowTime")
    private String borrowTime;

    private Long userId;

    private String userName;

    private Long bookId;

    private String bookName;
    // 是否归还图书
    private String isReturnBook;

    private String returnTime;

    public BookInfo(){}
    public BookInfo(String borrowTime, Long userId, String userName, Long bookId, String bookName, String isReturnBook, String returnTime) {
        this.borrowTime = borrowTime;
        this.userId = userId;
        this.userName = userName;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsReturnBook() {
        return isReturnBook;
    }

    public void setIsReturnBook(String isReturnBook) {
        this.isReturnBook = isReturnBook;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
