/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import FormHandlers.LoginFormHandler;
import Models.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marouane
 */
@WebServlet(name = "Authentification", urlPatterns = {"/auth"})
public class Authentification extends HttpServlet {

    public static String LOGIN_PAGE = "/WEB-INF/loginPage.jsp";
    public static String CLIENT_FORM = "/WEB-INF/formulaireClient.jsp";
    HttpSession session;
    User user;
    LoginFormHandler loginForm;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session = request.getSession();
        loginForm = new LoginFormHandler(request);
        if (session.getAttribute("login") != null && !session.getAttribute("login").equals("")) {
            request.setAttribute("user", session.getAttribute("login"));
            response.sendRedirect("creationClient");
//            request.getRequestDispatcher(CLIENT_FORM).forward(request, response);
        } else if (loginForm.checkHisCookieKey() != null) {
            session.setAttribute("login", loginForm.checkHisCookieKey().getLogin());
            request.setAttribute("user", session.getAttribute("login"));
            request.getRequestDispatcher(CLIENT_FORM).forward(request, response);
        } else {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        loginForm = new LoginFormHandler(request);
        user = loginForm.validateUserAndReturnIt();

        if (loginForm.getErreurs().isEmpty()) {
            if (user != null) {
                // Ila kan had khona baghi n3a9lo 3lih wakha ytfé lPC
                if (request.getParameter("souv") != null) {
                    String generatedKey = loginForm.setUserCookieKey();
                    Cookie cookie = new Cookie("souv", generatedKey + "|" + user.getId());
                    cookie.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(cookie);
                } else {
                    Cookie cookie = new Cookie("souv", "");
                    cookie.setMaxAge(cookie.getMaxAge() - 365 * 24 * 60 * 60);
                    response.addCookie(cookie);
                    System.out.println("Le check mawsal fih tal3ba !!!!!!!!!!!!!!!!!!!");
                }
                session = request.getSession();
                session.setAttribute("login", user.getLogin());
                request.getRequestDispatcher(CLIENT_FORM).forward(request, response);
            } else {
                System.out.println("Matconectétich akhooona !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                request.setAttribute("err", "Login ou mot de pass est incorrecte.");
                request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            }
        } else {
            System.out.println("Matconectétich akhooona 3ndek des erreurs !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            request.setAttribute("errList", loginForm.getErreurs());
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
    }
}
