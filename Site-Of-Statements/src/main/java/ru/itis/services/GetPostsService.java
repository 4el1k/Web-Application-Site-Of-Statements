package ru.itis.services;

import ru.itis.models.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetPostsService {
    /**
     * Will not load account in posts, only id of account
     * */
    List<Post> getPosts(int amount);
}
