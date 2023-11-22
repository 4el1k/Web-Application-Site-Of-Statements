package ru.itis.util.cookies;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class CookieTools {
    public static Optional<Cookie> findByName(Cookie[] cookies, String name){
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(name)){
                return Optional.of(cookie);
            }
        }
        return Optional.empty();
    }
}
