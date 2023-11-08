package ru.itis.controllers.servlets;

import ru.itis.models.Post;
import ru.itis.services.FindPostService;
import ru.itis.services.FindPostWithPullAccount;
import ru.itis.services.GetPostsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
    private FindPostService findPostService;
    private GetPostsService getPostsService;
    private FindPostWithPullAccount findPostWithPullAccount;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        findPostService = (FindPostService) context.getAttribute("findPostService");
        getPostsService = (GetPostsService) context.getAttribute("getPostsService");
        findPostWithPullAccount = (FindPostWithPullAccount) context.getAttribute("findPostWithPullAccount");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("postId")!=null){
            doHead(req,resp);
            System.out.println(123);
            return;
        }
        List<Post> postList = getPostsService.getPosts(10);
        req.setAttribute("posts", postList);
        req.getRequestDispatcher("/WEB-INF/view/feed.jsp").forward(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("postId");
        Optional<Post> postOptional = findPostWithPullAccount.findPost(UUID.fromString(uuid));
        req.setAttribute("post", postOptional.get());
        req.getRequestDispatcher("/WEB-INF/view/post.jsp").forward(req, resp);
    }
}
