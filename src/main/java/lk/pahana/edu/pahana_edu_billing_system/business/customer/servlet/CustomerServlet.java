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
import jakarta.servlet.http.HttpServletResponseWrapper;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.mapper.CustomerMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.util.mail.EmailSend;

import static lk.pahana.edu.pahana_edu_billing_system.util.validation.Validation.validateCustomerDTO;

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
            String validationError = validateCustomerDTO(customerDTO);

            boolean emailExists = customerService.existsCustomerByEmail(customerDTO.getEmail(), null);
            boolean mobileNumberExists = customerService.existsCustomerByMobileNumber(customerDTO.getMobileNumber(), null);

            if (validationError == null) {
                if (!mobileNumberExists) {
                    if (!emailExists) {
                        boolean savedCustomer = customerService.saveCustomer(customerDTO);

                        if (savedCustomer) {
                            // Welcome email
                            try {
                                // Capture welcome.jsp output as HTML string
                                StringWriter sw = new StringWriter();
                                PrintWriter pw = new PrintWriter(sw);
                                HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(resp) {
                                    @Override
                                    public PrintWriter getWriter() {
                                        return pw;
                                    }
                                };

                                // Forward to welcome.jsp with customer data
                                req.setAttribute("customer", customerDTO);
                                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/customer/mail-welcome.jsp");
                                rd.include(req, responseWrapper);
                                pw.flush();

                                String welcomeHtml = sw.toString();

                                // Send email
                                EmailSend.sendHtmlEmail(customerDTO.getEmail(), "Welcome to Pahana Edu!", welcomeHtml);

                                req.getSession().setAttribute("flash_success", "Customer created and welcome email sent successfully!");
                            } catch (Exception e) {
                                e.printStackTrace();
                                req.getSession().setAttribute("flash_error",
                                        "Customer created successfully, but failed to send welcome email.");
                            }
                            //
                        } else {
                            req.getSession().setAttribute("flash_error", "Failed to create customer.");
                        }
                    } else {
                        req.getSession().setAttribute("flash_error", "Email already exists.");
                    }
                } else {
                    req.getSession().setAttribute("flash_error", "Mobile Number already exists.");
                }
            } else {
                req.getSession().setAttribute("flash_error", validationError);
            }

            resp.sendRedirect(req.getContextPath() + "/customer");
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("flash_error", "Unexpected error occurred.");
            resp.sendRedirect(req.getContextPath() + "/customer");
        }
    }
}
