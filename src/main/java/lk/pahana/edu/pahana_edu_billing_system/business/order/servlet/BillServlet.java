package lk.pahana.edu.pahana_edu_billing_system.business.order.servlet;

import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.model.Customer;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl.ItemServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.order.model.Order;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.OrderService;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.impl.OrderServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.util.JspRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@WebServlet(name =  "bill", urlPatterns = "/bill")
public class BillServlet extends HttpServlet {
    private CustomerService customerService;
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
        orderService = new OrderServiceImpl();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String orderId = request.getParameter("orderId");
            OrderDTO order = orderService.getOrderById(orderId);
            CustomerDTO customer = customerService.getCustomerById(order.getCustomerId());

            request.setAttribute("order", order);
            request.setAttribute("customer", customer);

            boolean download = "true".equalsIgnoreCase(request.getParameter("download"));

            if (!download) {
                // HTML preview only
                request.setAttribute("order", order);
                request.setAttribute("customer", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/order/bill.jsp");
                dispatcher.forward(request, response);
            } else {
                // Generate PDF
                request.setAttribute("order", order);
                request.setAttribute("customer", customer);

                String html = JspRenderer.renderJspToString("/WEB-INF/views/order/bill.jsp", request, response);

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.withHtmlContent(html, null);
                builder.toStream(os);
                builder.useDefaultPageSize(297f, 210f, PdfRendererBuilder.PageSizeUnits.MM);
                builder.run();

                byte[] pdfData = os.toByteArray();
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=bill.pdf");
                response.getOutputStream().write(pdfData);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
