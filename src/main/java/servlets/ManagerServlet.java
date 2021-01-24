package servlets;

import entity.Buyer;
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
import java.util.List;

@WebServlet(name = "ManagerServlet", urlPatterns = {
        "/addProduct",
        "/createProduct",
        "/editProductForm",
        "/editProduct",
        "/editBuyerForm",
        "/editBuyer",
        "/listBuyers",
})

public class ManagerServlet extends HttpServlet {

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
            request.setAttribute("info", "У вас нет доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }
        User authUser = (User) httpSession.getAttribute("user");
        if (authUser == null) {
            request.setAttribute("info", "У вас нет доступа!");
            request.getRequestDispatcher("/loginForm").forward(request, response);
            return;
        }

        String path = request.getServletPath();
        switch (path) {
            case "/addProduct":
                request.getRequestDispatcher("/newProduct.jsp").forward(request, response);
                break;
            case "/createProduct":
                String title = request.getParameter("title");
                String size = request.getParameter("size");
                String price = request.getParameter("price");
                String count = request.getParameter("count");

                Product product = new Product(title, size, Integer.parseInt(price), Integer.parseInt(count));
                productFacade.create(product);
                request.setAttribute("info", "Пицца " + product.getTitle() + " успешно добавлена!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/editProductForm":
                List<Product> listProducts = productFacade.findAll();
                request.setAttribute("listProducts", listProducts);
                request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
                break;
            case "/editProduct":
                String productId = request.getParameter("productId");
                title = request.getParameter("title");
                size = request.getParameter("size");
                price = request.getParameter("price");
                count = request.getParameter("count");

                product = productFacade.find(Long.parseLong(productId));
                product.setTitle(title);
                product.setSize(size);
                product.setPrice(Integer.parseInt(price));
                product.setCount(Integer.parseInt(count));
                productFacade.edit(product);
                request.setAttribute("productId", productId);
                request.setAttribute("product", product);
                request.setAttribute("info", "Пицца " + product.getTitle() + " успешно изменена.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;

            case "/editBuyerForm":
                List<Buyer> listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/editBuyer.jsp").forward(request, response);
                break;

            case "/editBuyer":
                String buyerId = request.getParameter("buyerId");
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String money = request.getParameter("money");
                String email = request.getParameter("email");

                Buyer buyer = buyerFacade.find(Long.parseLong(buyerId));
                buyer.setName(name);
                buyer.setLastname(lastname);
                buyer.setMoney(Integer.parseInt(money));
                buyer.setEmail(email);
                buyerFacade.edit(buyer);
                request.setAttribute("buyerId", buyerId);
                request.setAttribute("buyer", buyer);
                request.setAttribute("info", "Пользователь" + buyer.getName() + " " + buyer.getLastname() + " был успешно изменён.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;

            case "/listBuyers":
                listBuyers = buyerFacade.findAll();
                request.setAttribute("listBuyers", listBuyers);
                request.getRequestDispatcher("/listBuyers.jsp").forward(request, response);
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