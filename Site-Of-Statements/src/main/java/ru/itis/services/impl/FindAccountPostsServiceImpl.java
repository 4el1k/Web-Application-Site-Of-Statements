package ru.itis.services.impl;

import ru.itis.dao.repositories.AccountRepository;
import ru.itis.models.Account;
import ru.itis.models.Post;
import ru.itis.services.FindAccountPostsService;
import ru.itis.util.rowmapper.impl.AccountAndPostRowMapper;
import ru.itis.util.rowmapper.impl.AccountRowMapper;
import ru.itis.util.rowmapper.impl.PostRowMapper;

import java.sql.SQLException;
import java.util.*;

public class FindAccountPostsServiceImpl implements FindAccountPostsService {
    private AccountRepository accountRepository;

    public  FindAccountPostsServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Post> getPostsByAccountId(UUID id) {
        try {
            Optional<Account> account =
                    accountRepository.findByIdWithPullPosts(id, new AccountAndPostRowMapper());
            if (account.isPresent()){
                return account.get().getPosts();
            }
            return new ArrayList<>();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
