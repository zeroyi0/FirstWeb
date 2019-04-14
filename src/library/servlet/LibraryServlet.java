package library.servlet;

import library.model.Book;
import library.service.LibraryService;
import library.service.impl.LibraryServiceImpl;
import library.util.Result;
import library.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LibraryServlet extends HttpServlet {

    private LibraryService libraryService = LibraryServiceImpl.getInstance();

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
            case "/libraryReturnBk":
            returnBook(req,resp);
            break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int bookId = parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        int bookNum = parseInt(req.getParameter("bookNum"));
        String information = req.getParameter("information");
        PrintWriter out = resp.getWriter(); //抛异常

        Book book = new Book(bookId, bookName, author, bookNum, 0, information);
        boolean addResult = libraryService.addBook(book);
        if (addResult) {
            out.println(Result.OK("添加成功！"));
            return;
        }
        out.print(Result.Fail("添加失败！"));
        return;
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void changeBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");
        int bookNum = parseInt(req.getParameter("bookNum"));
        int borrowOut = parseInt(req.getParameter("borrowOut"));
        String information = req.getParameter("information");
        PrintWriter out = resp.getWriter(); //抛异常

        Book book = new Book(bookId, bookName, author, bookNum, borrowOut, information);
        boolean updataResult = libraryService.update(book);
        if (updataResult) {
            out.print(Result.OK("修改信息成功"));
            return;
        }
        out.print(Result.Fail("修改信息失败"));
        return;
    }
    private void borrowBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        boolean borrowResult = libraryService.borrowBookById(id);
        if (borrowResult) {
            out.print(Result.OK("借书成功！"));
            return;
        }
        out.print(Result.Fail("借书失败！"));
        return;
    }


    private void returnBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = parseInt(req.getParameter("id"));
        PrintWriter out = resp.getWriter();

        boolean returnResult = libraryService.returnBook(id);;
        if (returnResult) {
            out.print(Result.OK("还书成功！"));
            return;
        }
        out.print(Result.Fail("还书失败！"));
        return;
    }


    private void showAllBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = libraryService.showAllBook();
        req.setAttribute("req_books", books);

        req.getRequestDispatcher("./library.jsp").forward(req, resp);
    }

    }
