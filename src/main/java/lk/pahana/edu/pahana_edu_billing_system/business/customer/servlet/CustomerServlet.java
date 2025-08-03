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

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<CustomerDTO> customers = new ArrayList<>();

        customers.add(new CustomerDTO("C001", "Vimukthi", "Colombo", "0771234567", 120, LocalDate.now(), "vinidu@email.com"));
        customers.add(new CustomerDTO("C002", "Kamal", "Galle", "0777654321", 80, LocalDate.now(), "kamal@email.com"));
        customers.add(new CustomerDTO("C003", "Nimal", "Kandy", "0769876543", 95, LocalDate.now(), "nimal@email.com"));

        req.setAttribute("customerList", customers);
        req.setAttribute("pageTitle", "Customer Management");
        req.setAttribute("body", "../customer/view-customer.jsp");

        // Forward to JSP
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp");
        dispatcher.forward(req,resp);
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
