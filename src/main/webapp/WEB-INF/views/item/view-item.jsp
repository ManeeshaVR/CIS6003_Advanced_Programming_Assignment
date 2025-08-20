<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO" %>

<div class="flex justify-between items-center mb-6">
    <h1 class="text-3xl font-bold">Item Management</h1>
    <label for="add-item" class="btn btn-primary mb-4">+ Add Item</label>
</div>

<div class="card bg-base-200 shadow-md">
    <div class="card-body">
        <div class="flex justify-between items-center mb-4">
            <h2 class="card-title">Items</h2>
            <input id="itemSearch" type="text" placeholder="Search items..." class="input input-bordered w-64"/>
        </div>

        <div class="overflow-x-auto">
            <table class="table table-zebra table-md" id="itemTable">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Unit Price</th>
                    <th>Stock</th>
                    <th>Publisher</th>
                    <th>Author</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("itemList");
                    if (items == null || items.isEmpty()) {
                %>
                <tr>
                    <td colspan="8" class="text-center text-gray-400">No items found</td>
                </tr>
                <%
                } else {
                    for (ItemDTO item : items) {
                %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getCategory() %></td>
                    <td><%= item.getUnitPrice() %></td>
                    <td><%= item.getStockQuantity() %></td>
                    <td><%= item.getPublisher() %></td>
                    <td><%= item.getAuthor() %></td>
                    <td>
                        <div class="flex space-x-2">
                            <a href="<%= request.getContextPath() %>/item/edit?code=<%= item.getItemCode() %>"
                               class="btn btn-sm btn-primary btn-outline"
                            >Edit</a>
                            <form method="post" action="<%= request.getContextPath() %>/item/delete?code=<%= item.getItemCode() %>" onsubmit="return confirmItemDelete(event);">
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

<%@ include file="../item/add-item.jsp" %>

<script>
    function confirmItemDelete(event) {
        const form = event.target;

        Swal.fire({
            title: "Are you sure you want to delete this item?",
            icon: "warning",
            theme: 'dark',
            showCancelButton: true,
            confirmButtonColor: "#0bb82b",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!"
        }).then((result) => {
            if (result.isConfirmed) {
                form.submit();
            }
        });
        return false;
    }

    const itemSearch = document.getElementById('itemSearch');
    const itemTable = document.getElementById('itemTable').getElementsByTagName('tbody')[0];

    itemSearch.addEventListener('input', function() {
        const filter = this.value.toLowerCase();
        const rows = itemTable.getElementsByTagName('tr');

        for (let i = 0; i < rows.length; i++) {
            const cells = rows[i].getElementsByTagName('td');
            let match = false;

            for (let j = 0; j < cells.length - 1; j++) {
                if (cells[j].textContent.toLowerCase().includes(filter)) {
                    match = true;
                    break;
                }
            }

            rows[i].style.display = match ? '' : 'none';
        }
    });
</script>
