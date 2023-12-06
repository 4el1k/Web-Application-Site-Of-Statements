package ru.itis.dto;

import javax.servlet.http.Cookie;

public record FavoritePostsDto(Cookie[] cookies) {
}
