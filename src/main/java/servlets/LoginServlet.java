package servlets;

import entity.Buyer;
import entity.User;
import session.BuyerFacade;
import session.UserFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {
        "/loginForm",
        "/login",
        "/logout",
        "/registrationForm",
        "/registration",
})

public class LoginServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;
    @EJB
    private BuyerFacade buyerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/loginForm":
                request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
                break;

            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if (user == null) {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                if (!password.equals(user.getPassword())) {
                    request.setAttribute("info", "Неправильный логин или пароль!");
                    request.getRequestDispatcher("/loginForm").forward(request, response);
                    break;
                }
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("user", user);
                request.setAttribute("info", "Добро пожаловать! " + user.getLogin() + "!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logout":
                httpSession = request.getSession(false);
                if (httpSession != null) {
                    httpSession.invalidate();
                    request.setAttribute("info", "Вы вышли из системы!");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                if (httpSession == null) {
                    request.setAttribute("info", "Вы не вошли в систему!");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                break;
            case "/registrationForm":
                request.getRequestDispatcher("/registrationForm.jsp").forward(request, response);
                break;
            case "/registration":
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String email = request.getParameter("email");
                String money = request.getParameter("money");
                login = request.getParameter("login");
                password = request.getParameter("password");
                String role = request.getParameter("role");

                Buyer buyer = new Buyer(name, lastname, Integer.parseInt(money), email);
                buyerFacade.create(buyer);
                user = new User(login, password, role, buyer);
                userFacade.create(user);
                httpSession = request.getSession(true);
                httpSession.setAttribute("user", user);
                request.setAttribute("info", "Пользователь " + buyer.getName() + " " + buyer.getLastname() + " успешно зарегестрирован! ");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
