package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "pizzaOrder", urlPatterns = "/pizzaOrder.do")
public class PizzaOrder extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public PizzaOrder(SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        String pizzaSize = req.getParameter("pizza_size");
        Order newOrder = (Order) session.getAttribute("order");
        newOrder.setPizzaSize(pizzaSize);
        webContext.setVariable("pizzaSize", pizzaSize);
        webContext.setVariable("pizzaType", newOrder.getPizzaType());
        webContext.setVariable("order", newOrder);

        session.setAttribute("order", newOrder);
        this.springTemplateEngine.process("deliveryInfo.html", webContext, resp.getWriter());

    }
}
