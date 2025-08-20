package lk.pahana.edu.pahana_edu_billing_system.business.bill.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.InvoiceDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.service.BillService;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.service.impl.BillServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.util.mail.EmailSend;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet(name = "send-invoice-email", urlPatterns = "/bill/sendEmail")
public class EmailInvoiceServlet extends HttpServlet {

    private BillService billService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        billService = new BillServiceImpl();
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String billId = req.getParameter("billId");
        String customerEmail = req.getParameter("customerEmail");

        if (billId == null || customerEmail == null) {
            req.getSession().setAttribute("flash_error", "Customer email not found!");
            resp.sendRedirect(req.getContextPath() + "/bill");
            return;
        }

        InvoiceDTO bill = billService.findBillById(billId);
        CustomerDTO customer = customerService.getCustomerById(bill.getCustomerId());

        if (bill == null || customer == null) {
            req.getSession().setAttribute("flash_error", "Invoice not found!");
            resp.sendRedirect(req.getContextPath() + "/bill");
            return;
        }

        try {
            // Capture invoice.jsp output as HTML string
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(resp) {
                @Override
                public PrintWriter getWriter() {
                    return pw;
                }
            };

            // Forward to JSP with bill + customer data
            req.setAttribute("bill", bill);
            req.setAttribute("customer", customer);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/bill/mail-invoice.jsp");
            rd.include(req, responseWrapper);
            pw.flush();

            String invoiceHtml = sw.toString();

            // Send email
            EmailSend.sendHtmlEmail(customerEmail, "Your Invoice - " + bill.getBillId(), invoiceHtml);

            req.getSession().setAttribute("flash_success", "Invoice sent to email successfully!");
            resp.sendRedirect(req.getContextPath() + "/bill");
        } catch (Exception e) {
            req.getSession().setAttribute("flash_error", "Failed to send invoice email");
            resp.sendRedirect(req.getContextPath() + "/bill");
        }
    }
}
