package ru.itis.controllers.servlets;

import ru.itis.models.Post;
import ru.itis.services.FindAccountPostsService;
import ru.itis.services.impl.FindAccountPostsServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/userposts")
public class AccountPostsServlet extends HttpServlet {
    private FindAccountPostsService findAccountPostsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        findAccountPostsService =
                (FindAccountPostsService) config.getServletContext().getAttribute("findAccountPostsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(true);
        List<Post> posts = findAccountPostsService.getPostsByAccountId((UUID) httpSession.getAttribute("accountId"));
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/view/accountPosts.jsp").forward(req, resp);
    }
}
