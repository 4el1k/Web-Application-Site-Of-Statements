package ru.itis.controllers.servlets;

import ru.itis.models.Account;
import ru.itis.services.FindAccountService;
import ru.itis.util.security.HashFunctions;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/authorisation")
public class AuthorisationServlet extends HttpServlet {
    FindAccountService findAccountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        findAccountService = (FindAccountService) context.getAttribute("findAccountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Optional<Account> account = Optional.empty();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("id")) {
                account = findAccountService.find(UUID.fromString(cookie.getValue()));
            }
        }
        if (account.isPresent()) {
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("isAuth", true);
            httpSession.setAttribute("account", account.get());

            resp.sendRedirect("http://localhost:8081/Site_Of_Statements_war/profile"); // ToDo: will send by previous URL;
        } else {
            req.getRequestDispatcher("/WEB-INF/view/authorisation.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inputMail = req.getParameter("mail");
        String inputPassword = req.getParameter("password");
        if (inputMail == null) {
            doGet(req, resp);
            return;
        }
        if (inputPassword == null) {
            doGet(req, resp);
            return;
        }
        Optional<Account> accountOpt = findAccountService.find(inputMail);
        if (accountOpt.isEmpty()) {
            doGet(req, resp);
            return;
        }
        Account account = accountOpt.get();
        String passwordHash = HashFunctions.getPasswordHashMD5(inputPassword);
        if (passwordHash.equals(account.getPassword())) {
            Cookie cookie = new Cookie("id", account.getId().toString());
            cookie.setMaxAge(60 * 60 * 24 * 30 * 12 * 10); // 10 years
            resp.addCookie(cookie);
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("isAuth", true);
            httpSession.setAttribute("account", account);
            // ToDo: will send by previous URL;
            resp.sendRedirect("http://localhost:8081/Site_Of_Statements_war/profile");
        } else {
            doGet(req, resp);
        }
    }
}
