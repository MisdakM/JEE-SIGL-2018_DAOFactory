/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataBase.DBCon;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marouane
 */
@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Detruire la ssession
        request.getSession().invalidate();

        // Supprimer la klé associé à "se souvenir de moi..." de la base de données
        String cookieVal = getCookieValue("souv", request);
        if (cookieVal != null && !cookieVal.equals("")) {
            int uid = Character.getNumericValue(cookieVal.charAt(cookieVal.length() - 1));
            new DBCon().deleteAllTheSeesionsOfThisUser(uid);
        }

        // Supprimer le cookie
        Cookie cookie = new Cookie("souv", "");
        cookie.setMaxAge(cookie.getMaxAge() - 365 * 24 * 60 * 60);
        response.addCookie(cookie);

        request.getRequestDispatcher("/auth").forward(request, response);
    }

    public String getCookieValue(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
