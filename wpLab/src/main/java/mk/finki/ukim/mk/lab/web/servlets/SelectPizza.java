package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name ="selectPizza", urlPatterns = "/selectPizza.do")
public class SelectPizza extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public SelectPizza(SpringTemplateEngine springTemplateEngine){
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Order order = (Order) session.getAttribute("order");
        String pizzaType = order.getPizzaType();



        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("pizzaType", pizzaType);



        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());

    }

    /*  @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        String pizzaType = req.getParameter("pizzaName");
        Order newOrder = new Order();
        newOrder.setPizzaType(pizzaType);
        webContext.setVariable("pizzaType", pizzaType);

        session.setAttribute("order", newOrder);
        this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());

    }*/
}
