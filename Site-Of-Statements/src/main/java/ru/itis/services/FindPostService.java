package ru.itis.services;

import ru.itis.models.Post;

import java.util.Optional;
import java.util.UUID;

public interface FindPostService {
    Optional<Post> findPost(UUID id);
}
