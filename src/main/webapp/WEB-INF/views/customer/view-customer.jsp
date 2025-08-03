<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO" %>

<div class="flex justify-between items-center mb-6">
    <h1 class="text-3xl font-bold">Customer Management</h1>
    <label for="add-customer" class="btn btn-primary mb-4">+ Add Customer</label>
</div>

<div class="card bg-base-200 shadow-md">
    <div class="card-body">
        <div class="flex justify-between items-center mb-4">
            <h2 class="card-title">Customers</h2>
            <input type="text" placeholder="Search customers..." class="input input-bordered w-64"/>
        </div>

        <div class="overflow-x-auto">
            <table class="table table-zebra table-md">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Mobile</th>
                    <th>Units</th>
                    <th>Email</th>
                    <th>Registered</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customerList");
                    if (customers == null || customers.isEmpty()) {
                %>
                <tr>
                    <td colspan="9" class="text-center text-gray-400">No customers found</td>
                </tr>
                <%
                } else {
                    for (CustomerDTO customer : customers) {
                %>
                <tr>
                    <td><%= customer.getCustomerId() %></td>
                    <td><%= customer.getName() %></td>
                    <td><%= customer.getAddress() %></td>
                    <td><%= customer.getMobileNumber() %></td>
                    <td><%= customer.getUnitsConsumed() %></td>
                    <td><%= customer.getEmail() %></td>
                    <td><%= customer.getRegistrationDate() %></td>
                    <td>
                        <div class="flex space-x-2">
                            <a href="<%= request.getContextPath() %>/customer/edit?id=<%= customer.getCustomerId() %>"
                               class="btn btn-sm btn-primary btn-outline">Edit</a>
                            <form method="post" action="<%= request.getContextPath() %>/customer/delete?id=<%= customer.getCustomerId() %>" onsubmit="return confirm('Are you sure you want to delete this customer?');">
                                <button type="submit" class="btn btn-sm btn-secondary btn-outline">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../customer/add-customer.jsp" %>
