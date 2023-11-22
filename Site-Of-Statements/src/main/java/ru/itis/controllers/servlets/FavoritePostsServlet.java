package ru.itis.controllers.servlets;

import ru.itis.dto.FavoritePostsDto;
import ru.itis.models.Post;
import ru.itis.services.FindFavoritePostsService;

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

@WebServlet("/favoriteposts")
public class FavoritePostsServlet extends HttpServlet {
    private FindFavoritePostsService findFavoritePostsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        findFavoritePostsService = (FindFavoritePostsService) context.getAttribute("findFavoritePostsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FavoritePostsDto favoritePostsDto = new FavoritePostsDto(req.getCookies());
        Optional<List<Post>> favoritePostsOptional = findFavoritePostsService.find(favoritePostsDto);
        if (favoritePostsOptional.isEmpty()){
            resp.sendRedirect("/feed");
            return;
        }
        req.setAttribute("favoritePosts", favoritePostsOptional.get());
        req.getRequestDispatcher("/WEB-INF/view/favoritePosts.jsp").forward(req, resp);
    }
}
