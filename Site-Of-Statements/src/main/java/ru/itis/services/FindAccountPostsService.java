package ru.itis.services;

import ru.itis.models.Post;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FindAccountPostsService {
    List<Post> getPostsByAccountId(UUID id);
}
