package ru.itis.services.impl;

import ru.itis.dto.FavoritePostsDto;
import ru.itis.models.Post;
import ru.itis.services.FindFavoritePostsService;
import ru.itis.services.FindPostWithPullAccount;
import ru.itis.util.convertor.JsonConvertor;
import ru.itis.util.cookies.CookieTools;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindFavoritePostsServiceImpl implements FindFavoritePostsService {
    private FindPostWithPullAccount findPostService;

    public FindFavoritePostsServiceImpl(FindPostWithPullAccount findPostService) {
        this.findPostService = findPostService;
    }

    @Override
    public Optional<List<Post>> find(FavoritePostsDto favoritePostsDto) {
        Cookie[] cookies = favoritePostsDto.cookies();
        Optional<Cookie> favoritePostsOptional = CookieTools.findByName(cookies, "favoritePosts");
        if (favoritePostsOptional.isEmpty()) {
            return Optional.empty();
        }
        List<String> posts = JsonConvertor.fromJson(favoritePostsOptional.get().getValue());
        List<Post> result = new ArrayList<>();
        Optional<Post> post;
        for (int i = 0; i < posts.size(); i++) {
            post = findPostService.findPost(UUID.fromString(posts.get(i)));
            if (post.isPresent()){
                result.add(post.get());
            }
        }

        if(result.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
