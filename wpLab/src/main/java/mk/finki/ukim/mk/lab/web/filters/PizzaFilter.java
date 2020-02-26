//package mk.finki.ukim.mk.lab.web.filters;
//
//import mk.finki.ukim.mk.lab.model.Order;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter
//public class PizzaFilter implements Filter{
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//
//
//        Order order = (Order) httpRequest.getSession().getAttribute("order");
//        //String pizzaType = order.getPizzaType();
//        String path = httpRequest.getServletPath();
//
//        if (!"/home".equals(path) && (order == null || (order.getPizzaType() == null))) {
//            httpResp.sendRedirect("/home");
//        }
//        else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
