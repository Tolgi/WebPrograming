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


@WebServlet(name = "confirmationInfo", urlPatterns = "/ConfirmationInfo.do")
public class ConfirmationInfo extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public ConfirmationInfo(SpringTemplateEngine springTemplateEngine, OrderService orderService){
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        String clientName = req.getParameter("clientName");
        String clientAddress = req.getParameter("clientAddress");
        Order newOrder = (Order) session.getAttribute("order");

        Order finalOrder = orderService.placeOrder(newOrder.getPizzaType(), newOrder.getPizzaSize(), clientName, clientAddress);

        webContext.setVariable("pizzaSize", newOrder.getPizzaSize());
        webContext.setVariable("pizzaType", newOrder.getPizzaType());
        webContext.setVariable("clientName", clientName);
        webContext.setVariable("clientAddress", clientAddress);
        webContext.setVariable("ipaddress", req.getRemoteHost());
        webContext.setVariable("browser", req.getHeader("User-Agent"));
        webContext.setVariable("orderId", finalOrder.getOrderId());

        session.setAttribute("order", newOrder);
        this.springTemplateEngine.process("confirmationInfo.html", webContext, resp.getWriter());

    }
}
