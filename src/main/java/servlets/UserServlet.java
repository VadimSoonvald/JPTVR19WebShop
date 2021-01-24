package servlets;

import entity.Buyer;
import entity.History;
import entity.Product;
import entity.User;
import session.BuyerFacade;
import session.HistoryFacade;
import session.ProductFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = {
        "/listProducts",
        "/buyProductForm",
        "/buyProduct",
        "/boughtProducts",
})

public class UserServlet extends HttpServlet {

    @EJB
    private ProductFacade productFacade;
    @EJB
    private BuyerFacade buyerFacade;
    @EJB
    private HistoryFacade historyFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            request.setAttribute("info", "Авторизуйтесь, пожалуйста!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        User authUser = (User) httpSession.getAttribute("user");
        if (authUser == null) {
            request.setAttribute("info", "Авторизуйтесь, пожалуйста!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/listProducts":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/listProducts.jsp").forward(request, response);
                break;

            case "/buyProductForm":
                listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                List<Buyer> listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/buyProduct.jsp").forward(request, response);
                break;

            case "/buyProduct":
                String productId = request.getParameter("productId");
                String buyerId = request.getParameter("buyerId");
                Product product = productFacade.find(Long.parseLong(productId));
                Buyer buyer = buyerFacade.find(Long.parseLong(buyerId));
                if (product.getCount() > 0) {
                    if (buyer.getMoney() - product.getPrice() >= 0) {
                        product.setCount(product.getCount() - 1);
                        productFacade.edit(product);
                        buyer.setMoney(buyer.getMoney() - product.getPrice());
                        buyerFacade.edit(buyer);
                        History history = new History(product, buyer, new GregorianCalendar().getTime());
                        historyFacade.create(history);
                        request.setAttribute("info", "Пицца " + product.getTitle() + " куплена пользователем: " + buyer.getName() + " " + buyer.getLastname() + ".");
                    } else {
                        request.setAttribute("info", "Недостаточно средств на балансе.");
                    }
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/boughtProducts":
                List<History> listHistories = historyFacade.findBoughtProducts();
                request.setAttribute("listHistories", listHistories);
                request.getRequestDispatcher("/showBoughtProducts.jsp")
                        .forward(request, response);
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