package lk.pahana.edu.pahana_edu_billing_system.business.customer.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.mapper.CustomerMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;

import java.io.IOException;

@WebServlet(name = "edit-customer", urlPatterns = "/customer/edit")
public class EditCustomerServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() {
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        CustomerDTO customer = customerService.getCustomerById(id);

        req.setAttribute("customer", customer);
        req.setAttribute("pageTitle", "Edit Customer");
        req.setAttribute("body", "../customer/edit-customer.jsp");

        req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CustomerDTO customerDTO = CustomerMapper.buildCustomerDTOFromRequest(req);

        customerService.updateCustomer(id, customerDTO);

        resp.sendRedirect(req.getContextPath() + "/customer");
    }

}
