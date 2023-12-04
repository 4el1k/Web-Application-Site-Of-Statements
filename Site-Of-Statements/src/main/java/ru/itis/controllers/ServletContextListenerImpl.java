package ru.itis.controllers;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.dao.repositories.PostRepository;
import ru.itis.dao.repositories.impl.AccountRepositoryJDBC;
import ru.itis.dao.repositories.impl.PostRepositoryJDBC;
import ru.itis.services.*;
import ru.itis.services.impl.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestrovka";
    private static Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            AccountRepository accountRepository = new AccountRepositoryJDBC(connection);
            PostRepository postRepository = new PostRepositoryJDBC(connection);
            SingUpService singUpService = new SignUpDataBaseSaveService(accountRepository);
            FindAccountService findAccountService = new FindAccountInDataBaseService(accountRepository);
            FilesService filesService = new LocalFilesService("/home/bebra/repositories-ITIS/" +
                    "Web-Application-Site-Of-Statements/Site-Of-Statements/src/main/webapp/photos/");
            SavePostService savePostService = new SavePostInDataBaseService(
                    postRepository, accountRepository, filesService
            );
            FindPostService findPostService = new FindPostInDataBaseService(postRepository);
            FindPostWithPullAccount findPostWithPullAccount = new FindPostWithPullAccountFromDataBaseService(
                    findPostService, findAccountService
            );
            GetPostsService getPostsService = new GetPostsFromDataBaseService(postRepository);
            CreateFavoritePostsCookieService createFavoritePostsCookieService =
                    new CreateFavoritePostsCookieServiceImpl();
            FindFavoritePostsService findFavoritePostsService = new FindFavoritePostsServiceImpl(
                    findPostWithPullAccount);
            FindAccountPostsServiceImpl findAccountPostsService = new FindAccountPostsServiceImpl(accountRepository);

            servletContext.setAttribute("accountRepository", accountRepository);
            servletContext.setAttribute("postRepository", postRepository);
            servletContext.setAttribute("signUpService", singUpService);
            servletContext.setAttribute("findAccountService", findAccountService);
            servletContext.setAttribute("filesService", filesService);
            servletContext.setAttribute("savePostService", savePostService);
            servletContext.setAttribute("findPostService", findPostService);
            servletContext.setAttribute("findPostWithPullAccount", findPostWithPullAccount);
            servletContext.setAttribute("getPostsService", getPostsService);
            servletContext.setAttribute("createFavoritePostsCookieService", createFavoritePostsCookieService);
            servletContext.setAttribute("findFavoritePostsService", findFavoritePostsService);
            servletContext.setAttribute("findAccountPostsService", findAccountPostsService);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
