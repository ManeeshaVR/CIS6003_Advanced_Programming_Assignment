package lk.pahana.edu.pahana_edu_billing_system.business.customer.servlet;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.mapper.CustomerMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;

@WebServlet(name = "customer", urlPatterns = {"/customer"})
public class CustomerServlet extends HttpServlet {
    CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

        req.setAttribute("customerList", customerDTOList);
        req.setAttribute("pageTitle", "Customer Management");
        req.setAttribute("body", "../customer/view-customer.jsp");

        req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerDTO customerDTO = CustomerMapper.buildCustomerDTOFromRequest(req);
            customerService.saveCustomer(customerDTO);

            resp.sendRedirect(req.getContextPath() + "/customer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
