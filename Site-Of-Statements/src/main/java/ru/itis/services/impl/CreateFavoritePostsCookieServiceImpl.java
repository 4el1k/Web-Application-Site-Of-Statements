package ru.itis.services.impl;

import ru.itis.dto.AccountFavoritePosts;
import ru.itis.services.CreateFavoritePostsCookieService;
import ru.itis.util.convertor.JsonConvertor;
import ru.itis.util.cookies.CookieTools;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CreateFavoritePostsCookieServiceImpl implements CreateFavoritePostsCookieService {
    @Override
    public Cookie createCookie(AccountFavoritePosts accountFavoritePosts) {
        UUID postId = accountFavoritePosts.postId();
        Cookie[] cookies = accountFavoritePosts.cookies();
        Optional<Cookie> cookieOptional = CookieTools.findByName(cookies, "favoritePosts");
        Cookie cookie;
        if (cookieOptional.isEmpty()){
            cookie = new Cookie("favoritePosts", "{%s}".formatted(postId));
        } else {
            cookie = cookieOptional.get();
            List<String> fromJson = JsonConvertor.fromJson(cookie.getValue());
            for(String id : fromJson){
                if (id.equals(postId.toString())){
                    return cookie;
                }
            }
            fromJson.add(postId.toString());
            String json = JsonConvertor.toJson(fromJson);
            cookie.setValue(json);
        }
        cookie.setMaxAge(60 * 60 * 24 * 30 * 6); // 0.5 years

        return cookie;
    }
}