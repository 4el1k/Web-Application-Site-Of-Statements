package ru.itis.services.impl;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.dao.repositories.PostRepository;
import ru.itis.dto.SavePostForm;
import ru.itis.models.Account;
import ru.itis.models.Post;
import ru.itis.models.states.PostStatus;
import ru.itis.services.FilesService;
import ru.itis.services.SavePostService;
import ru.itis.util.rowmapper.impl.AccountRowMapper;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;

public class SavePostInDataBaseService implements SavePostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final FilesService filesService;
    public SavePostInDataBaseService(PostRepository postRepository, AccountRepository accountRepository,
                                     FilesService filesService) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
        this.filesService = filesService;

    }

    @Override
    public boolean save(SavePostForm form, InputStream fileInputStream) {
        Optional<Account> account = Optional.empty();
        try {
            account = accountRepository.findById(form.getAccountId(), new AccountRowMapper());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (account.isEmpty()){
            return false;
        }

        filesService.upload(form.getPathsOfPhotos().get(0), fileInputStream);

        Post post = Post.builder()
                .account(account.get())
                .description(form.getDescription())
                .title(form.getTitle())
                .price(form.getPrice())
                .status(PostStatus.PUBLISHED)
                .pathsOfPhotos(form.getPathsOfPhotos())
                .build();

        try {
            postRepository.save(post);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
