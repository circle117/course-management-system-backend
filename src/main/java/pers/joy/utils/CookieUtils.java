package pers.joy.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name==null || cookies==null || cookies.length==0) {
            return null;
        }

        for (Cookie cookie: cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }

    public static void createCookie(HttpServletResponse resp, String key, String value, String domain, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setMaxAge(maxAge);
        resp.addCookie(cookie);
    }
}
