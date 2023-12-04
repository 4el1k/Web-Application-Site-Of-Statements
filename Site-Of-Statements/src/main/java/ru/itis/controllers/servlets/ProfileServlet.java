package ru.itis.controllers.servlets;

import ru.itis.models.Account;
import ru.itis.services.FindAccountService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private FindAccountService findAccountService;
    private Optional<Account> account;

    @Override
    public void init(ServletConfig config) throws ServletException {
        findAccountService = (FindAccountService) config.getServletContext().getAttribute("findAccountService");
    }

    @Override 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(true);
        account = findAccountService.find((UUID) httpSession.getAttribute("accountId"));
        if (account.isEmpty()){
            resp.sendRedirect("http://localhost:8081/Site_Of_Statements_war/authorisation");
        } else {
            req.setAttribute("account", account.get());
            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req,resp);
        }
    }
}
