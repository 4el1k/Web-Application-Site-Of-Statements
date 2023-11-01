package ru.itis.controllers;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.dao.repositories.PostRepository;
import ru.itis.dao.repositories.impl.AccountRepositoryJDBC;
import ru.itis.dao.repositories.impl.PostRepositoryJDBC;
import ru.itis.services.FilesService;
import ru.itis.services.FindAccountService;
import ru.itis.services.SavePostService;
import ru.itis.services.SingUpService;
import ru.itis.services.impl.FindAccountInDataBaseService;
import ru.itis.services.impl.LocalFilesService;
import ru.itis.services.impl.SavePostInDataBaseService;
import ru.itis.services.impl.SignUpDataBaseSaveService;

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
                    "Web-Application-Site-Of-Statements/Site-Of-Statements/src/main/photos/");
            SavePostService savePostService = new SavePostInDataBaseService(
                    postRepository, accountRepository, filesService
            );
            servletContext.setAttribute("accountRepository", accountRepository);
            servletContext.setAttribute("postRepository", postRepository);
            servletContext.setAttribute("signUpService", singUpService);
            servletContext.setAttribute("findAccountService", findAccountService);
            servletContext.setAttribute("filesService", filesService);
            servletContext.setAttribute("savePostService", savePostService);
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
