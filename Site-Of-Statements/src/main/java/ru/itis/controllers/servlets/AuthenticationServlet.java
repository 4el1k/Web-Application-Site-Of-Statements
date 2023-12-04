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
import java.util.Date;

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

        SignUpForm signUpForm = new SignUpForm(name, mail, phoneNumber, password);

        boolean authenticationSuccessful = signUpService.singUp(signUpForm);

        if (authenticationSuccessful){
            Account account = findAccountService.find(mail).get();
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("isAuth", true);
            httpSession.setAttribute("accountId", account.getId());

            resp.sendRedirect("http://localhost:8081/Site_Of_Statements_war/profile"); // ToDo: will send by previous URL;
        } else {
            doGet(req, resp);
        }
    }
}
