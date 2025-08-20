package lk.pahana.edu.pahana_edu_billing_system.business.bill.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.InvoiceDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.service.BillService;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.service.impl.BillServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;

import java.io.IOException;

@WebServlet(name = "generate-bill", urlPatterns = "/bill/generate")
public class GenerateBillServlet extends HttpServlet {

    private BillService billService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        billService = new BillServiceImpl();
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billId = req.getParameter("id");
        if (billId == null) {
            resp.sendRedirect(req.getContextPath() + "/bill/create");
            return;
        }

        InvoiceDTO billDTO = billService.findBillById(billId);
        if (billDTO == null) {
            resp.sendRedirect(req.getContextPath() + "/bill/create");
            return;
        }

        CustomerDTO customer = customerService.getCustomerById(billDTO.getCustomerId());

        req.setAttribute("bill", billDTO);
        req.setAttribute("customer", customer);
        req.setAttribute("pageTitle", "Bill-" + billDTO.getBillId());
        req.getRequestDispatcher("/WEB-INF/views/bill/invoice.jsp").forward(req, resp);
    }
}
