package ru.itis.controllers.servlets;

import ru.itis.dto.SavePostForm;
import ru.itis.models.Account;
import ru.itis.services.FilesService;
import ru.itis.services.SavePostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

// ToDo: jsp form, ota, service, post method
// in future will be implementation another methods of HttpServlet. For example i want to do post edit features.
@WebServlet("/posting")
@MultipartConfig
public class PostingServlet extends HttpServlet {
    private FilesService filesService;
    private SavePostService savePostService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        filesService = (FilesService) context.getAttribute("filesService");
        savePostService = (SavePostService) context.getAttribute("savePostService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/fileUpload.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        if (title==null){
            doGet(req,resp);
            return;
        }
        if (description==null){
            doGet(req,resp);
            return;
        }
        if (price<0){
            doGet(req,resp);
            return;
        }
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        Part part = req.getPart("file");

        String fileName = account.getId().toString() + "#" + UUID.randomUUID() + ".jpeg"; // part of business logic :(
        SavePostForm savePostForm = SavePostForm.builder()
                .pathsOfPhotos(Collections.singletonList(fileName))
                .title(title)
                .accountId(account.getId())
                .description(description)
                .price(price)
                .build();
        boolean isSaved = savePostService.save(savePostForm, part.getInputStream());
        if (!isSaved){
            // ToDo
            resp.sendRedirect("/kriinge");
        } else {
            // ToDo
            resp.sendRedirect("/SUIIII");
        }
    }
}
