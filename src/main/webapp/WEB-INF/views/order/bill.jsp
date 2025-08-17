<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderItemDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    OrderDTO order = (OrderDTO) request.getAttribute("order");
    CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<body style="font-family: Arial, sans-serif; width: 800px; margin: auto; padding: 20px; background: #fff; color: #333;">

<div style="text-align: center;">
    <h1 style="color:#1d4ed8; font-size:28px; margin:0;">Pahana Edu Book Shop</h1>
    <p style="font-size:12px; margin:2px 0;">123 Main Street, Colombo, Sri Lanka</p>
    <p style="font-size:12px; margin:2px 0;">Phone: +94 71 360 9606 | Email: pahanaedu.book@gmail.com</p>
</div>

<div style="display:flex; justify-content:space-between; margin-top:20px; margin-bottom:20px;">
    <div style="width:48%;">
        <h2 style="color:#1d4ed8; font-size:16px; margin-bottom:5px;">Invoice</h2>
        <p style="font-size:12px; margin:2px 0;"><strong>Order ID:</strong> <%= order.getOrderId() %></p>
        <p style="font-size:12px; margin:2px 0;"><strong>Date:</strong> <%= order.getDate() %></p>
    </div>
    <div style="width:48%;">
        <h3 style="color:#1d4ed8; font-size:16px; margin-bottom:5px;">Customer</h3>
        <p style="font-size:12px; margin:2px 0;"><strong>Name:</strong> <%= customer.getName() %></p>
        <p style="font-size:12px; margin:2px 0;"><strong>Address:</strong> <%= customer.getAddress() %></p>
        <p style="font-size:12px; margin:2px 0;"><strong>Phone:</strong> <%= customer.getMobileNumber() %></p>
        <p style="font-size:12px; margin:2px 0;"><strong>Email:</strong> <%= customer.getEmail() %></p>
    </div>
</div>

<table style="width:100%; border-collapse:collapse; margin-top:15px;">
    <thead>
    <tr>
        <th style="border:1px solid #ccc; padding:8px; font-size:12px; background-color:#f3f4f6;">Item</th>
        <th style="border:1px solid #ccc; padding:8px; font-size:12px; background-color:#f3f4f6;">Qty</th>
        <th style="border:1px solid #ccc; padding:8px; font-size:12px; background-color:#f3f4f6;">Unit Price</th>
        <th style="border:1px solid #ccc; padding:8px; font-size:12px; background-color:#f3f4f6;">Subtotal</th>
    </tr>
    </thead>
    <tbody>
    <%
        if (order.getOrderItems() != null) {
            for (OrderItemDTO item : order.getOrderItems()) {
                double subtotal = item.getQuantity() * item.getUnitPrice();
    %>
    <tr>
        <td style="border:1px solid #ccc; padding:8px; font-size:12px;"><%= item.getItemName() %></td>
        <td style="border:1px solid #ccc; padding:8px; font-size:12px;"><%= item.getQuantity() %></td>
        <td style="border:1px solid #ccc; padding:8px; font-size:12px;">Rs. <%= String.format("%.2f", item.getUnitPrice()) %></td>
        <td style="border:1px solid #ccc; padding:8px; font-size:12px;">Rs. <%= String.format("%.2f", subtotal) %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<div style="margin-top:20px; text-align:right; font-weight:bold; font-size:16px;">
    Total: Rs. <%= String.format("%.2f", order.getTotalAmount()) %>
</div>

<div style="margin-top:40px; text-align:center; font-size:10px; color:#666; border-top:1px solid #ccc; padding-top:10px;">
    <p>Thank you for shopping with <strong>Pahana Edu Book Shop</strong>!</p>
    <p>This is a computer-generated invoice. No signature required.</p>
</div>

</body>
</html>
