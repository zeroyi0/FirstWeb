package library.servlet;

import library.model.Book;
import library.model.BookInfo;
import library.model.User;
import library.service.BorrowBkService;
import library.service.LibraryService;
import library.service.impl.BorrowBkServiceImpl;
import library.service.impl.LibraryServiceImpl;
import library.util.Result;
import library.util.ServletUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LibraryServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        String configParam = config.getInitParameter("paramName");
    }

    private LibraryService libraryService = LibraryServiceImpl.getInstance();
    private BorrowBkService borrowBkService = BorrowBkServiceImpl.getIntance();
    Book book = null;
    BookInfo bookInfo = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletUtil.encode(req, resp);

        String url = req.getServletPath();
        switch (url) {
            case "/library":
                showAllBook(req,resp);
                break;
            case "/libraryAddBook":
                addBook(req, resp);
                break;
            case "/libraryDelete":
                deleteBook(req,resp);
                break;
            case "/libraryChange":
                changeBook(req,resp);
                break;
            case "/libraryBorrow":
                borrowBook(req,resp);
                break;
            case "/returnBk":
                returnBook(req,resp);
                break;

            case "/borrowBkInfo":
                showAllBorrowBook(req,resp);
            break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long bookId = parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        int bookNum = parseInt(req.getParameter("bookNum"));
        String information = req.getParameter("information");
        PrintWriter out = resp.getWriter(); //抛异常

        book = new Book(bookId, bookName, author, bookNum, 0, information);
        boolean addResult = libraryService.addBook(book);
        if (addResult) {
            out.println(Result.OK("添加成功！"));
            return;
        }
        out.print(Result.Fail("添加失败！"));
        return;
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int uid = parseInt(req.getParameter("id"));
        PrintWriter out = resp.getWriter();

        boolean delectResult = libraryService.delectBook(uid);

        if (delectResult) {
            out.print(Result.OK("删除成功！"));
            return;
        }
        out.print(Result.Fail("删除失败！"));
        return;
    }

    private void changeBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long bookId = parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        int bookNum = parseInt(req.getParameter("bookNum"));
        int borrowOut = parseInt(req.getParameter("borrowOut"));
        String information = req.getParameter("information");
        PrintWriter out = resp.getWriter(); //抛异常

        book = new Book(bookId, bookName, author, bookNum, borrowOut, information);
        boolean updataResult = libraryService.update(book);
        if (updataResult) {
            out.print(Result.OK("修改信息成功"));
            return;
        }
        out.print(Result.Fail("修改信息失败"));
        return;
    }
    // 借书
    private void borrowBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        boolean borrowResult = libraryService.borrowBookById(id);
        if (borrowResult) { // 借书成功
            bookInfo = borrowBookInfo(id, req);
            // 校验借书结果
            boolean bkInfo = borrowBkService.addBook(bookInfo);
            if (bkInfo) {
                out.print(Result.OK("借书成功！"));
                return;
            }
        }
        out.print(Result.Fail("借书失败！"));
        return;
    }
    // 保存借书信息
    private BookInfo borrowBookInfo(int id, HttpServletRequest req) {
        book = libraryService.findBookById(id);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        // 填入参数
        String borrowTime = df.format(date);
        User user = (User)req.getSession().getAttribute("ses_userInfo");
        Long userId = user.getId();
        String userName = user.getUserName();
        Long bookId = book.getBookId();
        String bookName = book.getBookName();
        String isReturnBook = "否";
        String returnTime = null;
        return new BookInfo(borrowTime, userId, userName, bookId, bookName, isReturnBook, returnTime);
    }
    // 还书
    private void returnBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("id"));
        PrintWriter out = resp.getWriter();

        boolean returnResult = libraryService.returnBook(id);;
        if (returnResult) {
//            String borrowTime = req.getParameter("borrowTime");
//            book = libraryService.findBookById(id);
//            BookInfo bookInfo = borrowBkService.findBkInfoByBorrowTime(borrowTime);
//            // 校验还书结果
//            bookInfo.setIsReturnBook("是");
//            Date date = new Date();
//            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String returnTime = sdf.format(date);
//            bookInfo.setReturnTime(returnTime);
//            boolean bkInfo = borrowBkService.updateBook(bookInfo);
//            if (bkInfo) {
                out.print(Result.OK("还书成功！"));
                return;
//            }
        }
        out.print(Result.Fail("还书失败！"));
        return;
    }
    // 全部图书信息
    private void showAllBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = libraryService.showAllBook();
        req.setAttribute("req_books", books);

        req.getRequestDispatcher("./library.jsp").forward(req, resp);
    }
    // 借阅图书信息
    private void showAllBorrowBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookInfo> bookInfo = borrowBkService.queryBkInfo();
        req.getSession().setAttribute("ses_borrowBkInfo", bookInfo);
        req.getRequestDispatcher("./borrowBkInfo.jsp").forward(req, resp);
    }

}
