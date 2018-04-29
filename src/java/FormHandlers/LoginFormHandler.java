/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormHandlers;

import DataBase.DBCon;
import Models.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Marouane
 */
public class LoginFormHandler {

    private DBCon con;
    private HttpServletRequest request;
    private User user;

    private Map<String, String> erreurs = new HashMap<>();

    public LoginFormHandler(HttpServletRequest request) {
        this.request = request;
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        
        try{
            validerChampLogin(login);
        } catch (Exception e) {
            erreurs.put("errLogin", e.getMessage());
        }
        try{
            validerChampPass(pass);
        } catch (Exception e) {
            erreurs.put("errPass", e.getMessage());
        }
        
        con = new DBCon();
        user = con.getUserWhithId(new User(login, pass));
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public void setErreurs(Map<String, String> erreurs) {
        this.erreurs = erreurs;
    }

    public void validerChampLogin(String login) throws Exception {
        if (login.equals("")) {
            throw new Exception("Veuillez entrer votre login");
        }
    }

    public void validerChampPass(String pass) throws Exception {
        if (pass.equals("")) {
            throw new Exception("Merci d'entrer votre password");
        }
    }

    public User validateUserAndReturnIt() {
        if (con.isUserValid(user)) {
            return user;
        }
        return null;
    }

    public String setUserCookieKey() {
        String key = UUID.randomUUID().toString();
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        user = con.getUserWhithId(new User(login, pass));

        con.addCookieKey(user.getId(), key);

        return key;
    }

    public User checkHisCookieKey() {
        String cookieKey = getCookieValue("souv");
        int userid = con.getTheAssociatedUserId(cookieKey);
        if (userid != 0) {
            return con.getUserById(userid);
        } else {
            return null;
        }
    }

    public String getCookieValue(String name) {
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
