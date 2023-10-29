package ru.itis.controllers.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ru.itis.dto.SignUpForm;
import ru.itis.models.Account;
import ru.itis.services.FindAccountService;
import ru.itis.services.SingUpService;
import ru.itis.util.security.HashFunctions;

import java.io.IOException;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
    private SingUpService signUpService;
    private FindAccountService findAccountService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        signUpService = (SingUpService) servletContext.getAttribute("signUpService");
        findAccountService = (FindAccountService) servletContext.getAttribute("findAccountService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/authentication.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String passwordReply = req.getParameter("passwordReplay");
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phoneNumber");
        String mail = req.getParameter("mail");
        if (password == null) {
            doGet(req, resp);
            return;
        }
        if (passwordReply == null) {
            doGet(req, resp);
            return;
        }
        if (name == null) {
            doGet(req, resp);
            return;
        }
        if (phoneNumber == null) {
            doGet(req, resp);
            return;
        }
        if (mail == null) {
            doGet(req, resp);
            return;
        }
        if (!password.equals(passwordReply)) {
            doGet(req, resp);
            return;
        }
        String passwordHash = HashFunctions.getPasswordHashMD5(req.getParameter("password"));
        SignUpForm signUpForm = new SignUpForm(name, mail, phoneNumber, passwordHash);

        boolean authenticationSuccessful = signUpService.singUp(signUpForm);

        if (authenticationSuccessful){
            Account account = findAccountService.find(mail).get();
            Cookie cookie = new Cookie("id", account.getId().toString());
            cookie.setMaxAge(60 * 60 * 24 * 30 * 12 * 10); // 10 years
            resp.addCookie(cookie);
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("isAuth", true);
            httpSession.setAttribute("account", account);

            resp.sendRedirect("/profile"); // ToDo: will send by previous URL;
        } else {
            doGet(req, resp);
        }
    }
}
