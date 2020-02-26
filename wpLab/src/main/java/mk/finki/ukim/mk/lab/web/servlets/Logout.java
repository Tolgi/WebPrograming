package mk.finki.ukim.mk.lab.web.servlets;


import mk.finki.ukim.mk.lab.model.staticdata.DataHolder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class Logout extends HttpServlet {
    /*
     * We use the service method since it is invoked for all HTTP methods
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        if(null == req.getSession().getAttribute("order")){
            System.out.println("CISTOOOOOOOOOOOOOOOOOO");
        }
        DataHolder.counter = 0L;
        resp.sendRedirect("/");
    }
}
