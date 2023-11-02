package ru.itis.services.impl;

import ru.itis.models.Account;
import ru.itis.models.Post;
import ru.itis.services.FindAccountService;
import ru.itis.services.FindPostService;
import ru.itis.services.FindPostWithPullAccount;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

public class FindPostWithPullAccountFromDataBaseService implements FindPostWithPullAccount {
    FindPostService findPostService;
    FindAccountService findAccountService;
    public FindPostWithPullAccountFromDataBaseService(
            FindPostService findPostService, FindAccountService findAccountService){
        this.findPostService = findPostService;
        this.findAccountService = findAccountService;
    }

    @Override
    public Optional<Post> findPost(UUID id) {
        Optional<Post> postOptional = findPostService.findPost(id);
        if (postOptional.isEmpty()){
            throw new NoSuchElementException("No such post");
        }
        Post post = postOptional.get();
        Optional<Account> accountOptional = findAccountService.find(post.getAccount().getId());
        if (accountOptional.isEmpty()){
            throw new NoSuchElementException("No such account");
        }
        post.setAccount(accountOptional.get());

        return Optional.of(post);
    }
}
