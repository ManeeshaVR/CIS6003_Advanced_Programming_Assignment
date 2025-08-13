package lk.pahana.edu.pahana_edu_billing_system.business.dashboard.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl.ItemServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.OrderService;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.impl.OrderServiceImpl;

import java.io.IOException;

@WebServlet(name = "dashboard", urlPatterns = "/")
public class DashboardServlet extends HttpServlet {

    private CustomerService customerService;
    private ItemService itemService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
        itemService = new ItemServiceImpl();
        orderService = new OrderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerCount = customerService.getCustomerCount();
        int itemCount = itemService.getItemCount();
        int orderCount = orderService.getOrderCount();

        req.setAttribute("customerCount", customerCount);
        req.setAttribute("itemCount", itemCount);
        req.setAttribute("orderCount", orderCount);
        req.setAttribute("pageTitle", "Pahana Edu Billing System");
        req.setAttribute("body", "../dashboard/dashboard.jsp");

        req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
    }
}
