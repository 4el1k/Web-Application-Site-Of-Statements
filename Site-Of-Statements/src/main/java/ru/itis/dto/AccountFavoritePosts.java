package ru.itis.dto;

import javax.servlet.http.Cookie;
import java.util.UUID;

public record AccountFavoritePosts(Cookie[] cookies, UUID postId) {
}
