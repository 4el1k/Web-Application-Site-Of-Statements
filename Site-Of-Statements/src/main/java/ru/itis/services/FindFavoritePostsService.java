package ru.itis.services;

import ru.itis.dto.FavoritePostsDto;
import ru.itis.models.Post;

import java.util.List;
import java.util.Optional;

public interface FindFavoritePostsService {
    Optional<List<Post>> find(FavoritePostsDto favoritePostsDto);
}
