package ru.itis.controllers.servlets;

import ru.itis.dto.AccountFavoritePosts;
import ru.itis.models.Post;
import ru.itis.services.CreateFavoritePostsCookieService;
import ru.itis.services.FindPostService;
import ru.itis.services.FindPostWithPullAccount;
import ru.itis.services.GetPostsService;
import ru.itis.util.cookies.CookieTools;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
    private GetPostsService getPostsService;
    private FindPostWithPullAccount findPostWithPullAccount;
    private CreateFavoritePostsCookieService createFavoritePostsCookieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        getPostsService = (GetPostsService) context.getAttribute("getPostsService");
        findPostWithPullAccount = (FindPostWithPullAccount) context.getAttribute("findPostWithPullAccount");
        createFavoritePostsCookieService =
                (CreateFavoritePostsCookieService) context.getAttribute("createFavoritePostsCookieService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("postId")!=null){
            String uuid = req.getParameter("postId");
            Optional<Post> postOptional = findPostWithPullAccount.findPost(UUID.fromString(uuid));
            req.setAttribute("post", postOptional.get());
            req.getRequestDispatcher("/WEB-INF/view/post.jsp").forward(req, resp);
            return;
        }
        List<Post> postList = getPostsService.getPosts(10);
        req.setAttribute("posts", postList);
        req.getRequestDispatcher("/WEB-INF/view/feed.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getParameter("postId");
        if (postId==null){
            return;
        }
        Cookie[] cookie = req.getCookies();
        AccountFavoritePosts accountFavoritePosts = new AccountFavoritePosts(cookie, UUID.fromString(postId));
        Cookie responseCookie = createFavoritePostsCookieService.createCookie(accountFavoritePosts);
        resp.addCookie(responseCookie);

        resp.sendRedirect("http://localhost:8081/Site_Of_Statements_war/profile");
    }
}
