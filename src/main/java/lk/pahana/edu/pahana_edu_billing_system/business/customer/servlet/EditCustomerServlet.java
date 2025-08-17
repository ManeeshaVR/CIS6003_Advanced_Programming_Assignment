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

import static lk.pahana.edu.pahana_edu_billing_system.util.validation.Validation.validateCustomerDTO;

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

        if (id != null && !id.isEmpty()) {
            CustomerDTO customer = customerService.getCustomerById(id);

            if (customer != null) {
                req.setAttribute("customer", customer);
                req.setAttribute("pageTitle", "Edit Customer");
                req.setAttribute("body", "../customer/edit-customer.jsp");

                req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("flash_error", "Couldn't find the customer by: " + id);
                resp.sendRedirect(req.getContextPath() + "/customer");
            }
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not a valid customer id!");
            resp.sendRedirect(req.getContextPath() + "/customer");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            CustomerDTO customerDTO = CustomerMapper.buildCustomerDTOFromRequest(req);
            String validationError = validateCustomerDTO(customerDTO);
            boolean emailExists = customerService.existsCustomerByEmail(customerDTO.getEmail(), id);
            boolean mobileNumberExists = customerService.existsCustomerByMobileNumber(customerDTO.getMobileNumber(), id);

            if (validationError == null) {
                if (!mobileNumberExists) {
                    if (!emailExists) {
                        boolean updateCustomer = customerService.updateCustomer(id, customerDTO);
                        if (updateCustomer) {
                            req.getSession().setAttribute("flash_success", "Customer updated successfully!");
                        } else {
                            req.getSession().setAttribute("flash_error", "Failed to update customer.");
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
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not a valid customer id!");
        }

        resp.sendRedirect(req.getContextPath() + "/customer");
    }

}
