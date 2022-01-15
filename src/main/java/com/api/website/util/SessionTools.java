package com.api.website.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SessionTools {

    public static void storeRequestSession(String attribute, String info, HttpServletRequest request) {
        // Get session info
        List<String> sessionInfo = (List<String>) request.getSession().getAttribute(attribute);
        // check if session stored already... if not, store session
        if (sessionInfo == null) {
            sessionInfo = new ArrayList<>();
            request.getSession().setAttribute(attribute, sessionInfo);
        }
        sessionInfo.add(info);
        request.getSession().setAttribute(attribute, sessionInfo);
    }

    public static List<String> getSession(String attribute, HttpSession session) {
        List<String> info = (List<String>) session.getAttribute(attribute);
        return session == null ? new ArrayList<String>() : info != null ? info : new ArrayList<>();
    }
}
