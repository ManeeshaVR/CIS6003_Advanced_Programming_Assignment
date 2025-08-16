<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderItemDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<CustomerDTO> topCustomers = (List<CustomerDTO>) request.getAttribute("topCustomers");
    List<ItemDTO> topItems = (List<ItemDTO>) request.getAttribute("topItems");
    OrderDTO lastOrder = (OrderDTO) request.getAttribute("lastOrder");
    CustomerDTO lastOrderCustomer = (CustomerDTO) request.getAttribute("lastOrderCustomer");
%>

<div class="flex justify-between items-center mb-6">
    <h1 class="text-3xl font-bold">Welcome Back, Admin!</h1>
</div>

<!-- Stat Cards -->
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 mb-6">
    <div class="bg-neutral rounded-box py-4 px-6 transition transform duration-300 hover:shadow-2xl hover:border-[#3b82f6] hover:scale-104">
        <div class="flex justify-between items-center">
            <div>
                <div class="text-3xl font-bold text-blue-500 mt-2 mb-2"><%= request.getAttribute("customerCount") %></div>
                <span>Total Customers</span>
            </div>
            <div class="ml-auto">
                <div class="w-16 h-16 rounded-lg flex items-center justify-center">
                    <i class="fa-solid fa-users text-blue-400 text-3xl"></i>
                </div>
            </div>
        </div>
    </div>
    <div class="bg-neutral rounded-box py-4 px-6 transition transform duration-300 hover:shadow-2xl hover:border-[#C27AFF] hover:scale-104">
        <div class="flex justify-between items-center">
            <div>
                <div class="text-3xl font-bold text-purple-500 mt-2 mb-2"><%= request.getAttribute("itemCount") %></div>
                <span>Total Items</span>
            </div>
            <div class="ml-auto">
                <div class="w-16 h-16 rounded-lg flex items-center justify-center">
                    <i class="fa-solid fa-boxes-stacked text-purple-400 text-3xl"></i>
                </div>
            </div>
        </div>
    </div>
    <div class="bg-neutral rounded-box py-4 px-6 transition transform duration-300 hover:shadow-2xl hover:border-[#FB64B6] hover:scale-104">
        <div class="flex justify-between items-center">
            <div>
                <div class="text-3xl font-bold text-pink-500 mt-2 mb-2"><%= request.getAttribute("orderCount") %></div>
                <span>Total Orders</span>
            </div>
            <div class="ml-auto">
                <div class="w-16 h-16 rounded-lg flex items-center justify-center">
                    <i class="fa-solid fa-cart-shopping text-pink-400 text-3xl"></i>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="grid grid-cols-1 lg:grid-cols-3 gap-6 mt-6">
    <!-- Recent Order -->
    <div class="lg:col-span-2">
        <div class="card card-side bg-base-100 shadow-lg rounded-xl border border-gray-700
            transition transform duration-300 hover:shadow-2xl hover:border-[#e5c810] hover:scale-104">
        <figure class="p-6 flex items-center w-1/2 justify-center">
                <img
                        src="<%= request.getContextPath() %>/images/orders.svg?v=<%= System.currentTimeMillis() %>"
                        alt="Recent Order"
                        class="rounded-xl w-84 h-84 object-contain" />
            </figure>
            <div class="card-body w-1/2">
                <h2 class="card-title flex items-center gap-2">
                    <i class="fa-solid fa-receipt" style="color: #e5c810"></i>
                    Recent Order
                </h2>

                <!-- Customer Info -->
                <div class="mt-2 space-y-1 text-sm">
                    <p>
                        <i class="fa-solid fa-user text-gray-600 mr-2"></i>
                        <span class="font-semibold">Customer:</span>
                        <%= lastOrderCustomer != null ? lastOrderCustomer.getName() : "N/A" %>
                    </p>
                    <p>
                        <i class="fa-solid fa-calendar-day text-gray-600 mr-2"></i>
                        <span class="font-semibold">Date:</span>
                        <%= lastOrder != null ? lastOrder.getDate() : "N/A" %>
                    </p>
                </div>

                <!-- Items List -->
                <div class="w-full mt-3">
                    <ul class="space-y-2 text-left">
                        <%
                            if (lastOrder != null && lastOrder.getOrderItems() != null) {
                                for (OrderItemDTO item : lastOrder.getOrderItems()) {
                        %>
                        <li class="flex items-center gap-2 border-b pb-1">
                            <i class="fa-solid fa-box text-gray-600"></i>
                            <span><%= item.getItemName() %></span>
                            <span class="ml-auto text-sm">Qty: <%= item.getQuantity() %></span>
                            <span class="ml-4 badge badge-outline">Rs. <%= String.format("%.2f", item.getUnitPrice() * item.getQuantity()) %></span>
                        </li>
                        <%
                                }
                            }
                        %>
                    </ul>
                </div>
                <div class="flex justify-between items-center mt-4 border-t pt-2">
                    <span class="font-bold">Total:</span>
                    <span class="text-lg font-bold" style="color: #e5c810">
                        Rs. <%= lastOrder != null ? lastOrder.getTotalAmount() : "0" %>
                    </span>
                </div>
                <div class="card-actions justify-end mt-4">
                    <a href="<%= request.getContextPath() %>/order" class="btn btn-outline <%= request.getRequestURI().contains("/order") ? "text-primary" : "" %>">View</a>
                </div>
            </div>
        </div>
    </div>

    <div class="flex flex-col gap-6">
        <!-- Top Customers -->
        <div class="card card-side bg-base-100 shadow-lg rounded-xl border border-gray-700
            transition transform duration-300 hover:shadow-2xl hover:border-[#e5c810] hover:scale-105">
        <figure class="p-4">
                <img
                        src="<%= request.getContextPath() %>/images/customers.svg?v=<%= System.currentTimeMillis() %>"
                        alt="Top Customers"
                        class="rounded-xl w-28 h-28 object-contain" />
            </figure>
            <div class="card-body">
                <h2 class="card-title flex items-center gap-2">
                    <i class="fa-solid fa-crown" style="color: #e5c810"></i>
                    Top Customers
                </h2>
                <ul class="space-y-1 mt-2">
                    <%
                        if (topCustomers != null) {
                            for (CustomerDTO c : topCustomers) {
                    %>
                    <li class="flex items-center gap-2">
                        <i class="fa-solid fa-user text-gray-600"></i>
                        <span><%= c.getName() %></span>
                        <span class="ml-auto badge badge-outline" style="border-color:#e5c810; color:#e5c810;"><%= c.getUnitsConsumed() %></span>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
                <div class="card-actions justify-end mt-2">
                    <a href="<%= request.getContextPath() %>/customer" class="btn btn-outline btn-sm <%= request.getRequestURI().contains("/customer") ? "text-primary" : "" %>">View</a>
                </div>
            </div>
        </div>

        <!-- Best Selling Items -->
        <div class="card card-side bg-base-100 shadow-lg rounded-xl border border-gray-700
            transition transform duration-300 hover:shadow-2xl hover:border-[#e5c810] hover:scale-105">
        <figure class="p-4">
                <img
                        src="<%= request.getContextPath() %>/images/items.svg?v=<%= System.currentTimeMillis() %>"
                        alt="Best Selling Items"
                        class="rounded-xl w-28 h-28 object-contain" />
            </figure>
            <div class="card-body">
                <h2 class="card-title flex items-center gap-2">
                    <i class="fa-solid fa-arrow-trend-up" style="color: #e5c810"></i>
                    Best Selling Items
                </h2>
                <ul class="space-y-1 mt-2">
                    <%
                        if (topItems != null) {
                            for (ItemDTO i : topItems) {
                    %>
                    <li class="flex items-center gap-2">
                        <i class="fa-solid fa-box text-gray-600"></i>
                        <span><%= i.getItemName() %></span>
                        <span class="ml-auto badge badge-outline" style="border-color:#e5c810; color:#e5c810;"><%= i.getStockQuantity() %></span>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
                <div class="card-actions justify-end mt-2">
                    <a href="<%= request.getContextPath() %>/item" class="btn btn-outline btn-sm <%= request.getRequestURI().contains("/item") ? "text-primary" : "" %>">View</a>
                </div>
            </div>
        </div>
    </div>
</div>

