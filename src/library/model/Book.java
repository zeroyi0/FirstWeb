package library.model;


import library.annotation.Table;

import java.util.Objects;

@Table(value = "Book") //value = 可以省略
public class Book implements Comparable<Book> {
    private int bookId;
    private String bookName;
    private String author;//作者

    private int bookNum;
    private int borrowOut;
    private String information;

    public String toString() {
        return bookId + "@_@" + bookName + "@_@" + author +  "@_@" + bookNum + "@_@" + borrowOut + "@_@" + information;
    }

    @Override
   public int compareTo(Book o) {
        //从小到大排序
        if (this.bookId > o.bookId) {
            return 1;
        } else if (this.bookId < o.bookId) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    public Book() {}

    public Book(int bookId, String bookName, String author, int bookNum, int borrowOut, String information) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.bookNum = bookNum;
        this.borrowOut = borrowOut;
        this.information = information;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getBorrowOut() {
        return borrowOut;
    }

    public void setBorrowOut(int borrowOut) {
        this.borrowOut = borrowOut;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
