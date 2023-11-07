package ru.itis.controllers.servlets;

import ru.itis.models.Account;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private HttpSession httpSession;
    private Account account;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        httpSession = req.getSession(true);
        account = (Account) httpSession.getAttribute("account");
        if (account==null){
            resp.sendRedirect("http://localhost:8081/Site_Of_Statements_war/authorisation");
        } else {
            req.setAttribute("account", account);
            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req,resp);
        }
    }
}
