package lk.pahana.edu.pahana_edu_billing_system.business.customer.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;

import java.io.IOException;

@WebServlet(name = "delete-customer", urlPatterns = "/customer/delete")
public class DeleteCustomerServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            customerService.deleteCustomer(id);
            req.getSession().setAttribute("flash_success", "Customer deleted successfully!");
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not a valid customer id!");
        }
        resp.sendRedirect(req.getContextPath() + "/customer");
    }
}
