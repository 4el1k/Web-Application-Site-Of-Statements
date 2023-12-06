package ru.itis.services;

import ru.itis.dto.AccountFavoritePosts;

import javax.servlet.http.Cookie;

public interface CreateFavoritePostsCookieService {
    Cookie createCookie(AccountFavoritePosts accountFavoritePosts);
}
