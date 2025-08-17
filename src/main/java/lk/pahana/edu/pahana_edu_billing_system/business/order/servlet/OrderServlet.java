package lk.pahana.edu.pahana_edu_billing_system.business.order.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl.CustomerServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl.ItemServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.OrderService;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.impl.OrderServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

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
        req.setAttribute("customers", customerService.getAllCustomers());
        req.setAttribute("items", itemService.getAllItems());
        req.setAttribute("recentOrder", orderService.getLastOrder());
        req.setAttribute("recentCustomer", customerService.getCustomerById(orderService.getLastOrder().getCustomerId()));

        String lastOrderId = req.getParameter("lastOrderId");
        String showInvoiceParam = req.getParameter("showInvoice");

        if (lastOrderId != null && "true".equalsIgnoreCase(showInvoiceParam)) {
            OrderDTO lastOrder = orderService.getOrderById(lastOrderId);
            req.setAttribute("showInvoice", true);
            req.setAttribute("lastOrder", lastOrder);
            req.setAttribute("lastOrderCustomer", customerService.getCustomerById(lastOrder.getCustomerId()));
        }

        req.setAttribute("pageTitle", "Place Order");
        req.setAttribute("body", "../order/view-order.jsp");

        req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");
        String total = req.getParameter("total");

        List<OrderItemDTO> orderItems = new ArrayList<>();
        Map<String, Integer> itemQuantity = new HashMap<>();
        int totalQuantity = 0;

        int index = 0;
        while (true) {
            String code = req.getParameter("items[" + index + "].code");
            String quantityStr = req.getParameter("items[" + index + "].quantity");
            String priceStr = req.getParameter("items[" + index + "].price");

            if (code == null || quantityStr == null || priceStr == null) break;

            try {
                int quantity = Integer.parseInt(quantityStr);
                double price = Double.parseDouble(priceStr);

                OrderItemDTO item = new OrderItemDTO.Builder()
                        .itemCode(code)
                        .quantity(quantity)
                        .unitPrice(price)
                        .build();

                itemQuantity.put(code, quantity);
                totalQuantity += quantity;
                orderItems.add(item);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            index++;
        }

        if (customerId != null && total != null && !orderItems.isEmpty()){
            OrderDTO orderDTO = new OrderDTO.Builder()
                    .setCustomerId(customerId)
                    .setOrderItems(orderItems)
                    .setTotalAmount(Double.valueOf(total))
                    .build();

            boolean isOrderSaved = orderService.saveOrder(orderDTO);
            if (isOrderSaved) {
                req.getSession().setAttribute("flash_success", "Order Placed successfully!");
                itemService.deductItemQuantity(itemQuantity);
                customerService.addUnitsConsumed(customerId, totalQuantity);

                // Redirect with lastOrderId to show invoice
                resp.sendRedirect(req.getContextPath() + "/order?lastOrderId=" + orderDTO.getOrderId() + "&showInvoice=true");
                return;
            } else {
                req.getSession().setAttribute("flash_error", "Failed to place the order");
            }
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not valid data!");
        }

        resp.sendRedirect(req.getContextPath() + "/order");
    }
}
