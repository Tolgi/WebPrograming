package mk.finki.ukim.mk.lab.web.servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "showPizza", urlPatterns = "/home")
public class ShowPizza extends HttpServlet {

    private final PizzaService pizzaService;
    private final SpringTemplateEngine springTemplateEngine;

    public ShowPizza(PizzaService pizzaService, SpringTemplateEngine springTemplateEngine){
        this.pizzaService = pizzaService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        List<Pizza> pizzas = pizzaService.listPizzas();
        webContext.setVariable("listPizzas", pizzas);
        resp.setContentType("text/html; charset=UTF-8");
        this.springTemplateEngine.process("listPizzas.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        WebContext webContext = new WebContext(req, resp, req.getServletContext());


        String pizzaType = req.getParameter("pizzaName");
        Order newOrder = new Order();
        newOrder.setPizzaType(pizzaType);
        session.setAttribute("order", newOrder);

        String errorM = " ";
        if(pizzaType == null){
            Pizza pizza = new Pizza();
            String name = req.getParameter("newPizzaName");
            String dec = req.getParameter("newDesc");
           // pizzaService.save(name, dec);
            errorM = "Ve molime izberete tip na pizza!!!";
            webContext.setVariable("errorM", errorM);

            List<Pizza> pizzas = pizzaService.listPizzas();
            webContext.setVariable("listPizzas", pizzas);
            this.springTemplateEngine.process("listPizzas.html", webContext, resp.getWriter());

        }else{
            resp.sendRedirect("/selectPizza.do");
        }


      //  webContext.setVariable("pizzaType", pizzaType);
        //this.springTemplateEngine.process("selectPizzaSize.html", webContext, resp.getWriter());
    }
}
