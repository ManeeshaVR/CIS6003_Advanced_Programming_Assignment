<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderItemDTO" %>
<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO" %>

<%
    List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customers");
    List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
    OrderDTO lastOrder = (OrderDTO) request.getAttribute("lastOrder");
    CustomerDTO lastOrderCustomer = (CustomerDTO) request.getAttribute("lastOrderCustomer");
%>

<div class="flex justify-between items-center mb-6">
    <h1 class="text-3xl font-bold">Place Order</h1>
    <div></div>
</div>

<form id="orderForm" action="<%= request.getContextPath() %>/order" method="post"
      class="grid grid-cols-1 lg:grid-cols-3 gap-6">

    <!-- Create New Bill Form -->
    <div class="col-span-2 bg-base-100 p-6 rounded-box shadow-lg">
        <h2 class="text-xl font-bold mb-4 flex items-center gap-2">Create New Bill</h2>

        <!-- Select Customer -->
        <div class="form-control w-full mb-4">
            <label class="label font-semibold">Select Customer *</label>
            <select name="customerId" id="customerId" class="select select-bordered w-full" required>
                <option disabled selected>Choose a customer</option>
                <% for (CustomerDTO c : customers) { %>
                <option value="<%= c.getCustomerId() %>"><%= c.getName() %></option>
                <% } %>
            </select>
        </div>

        <!-- Add Items -->
        <h3 class="font-semibold mt-6 mb-2">Add Items</h3>
        <div class="flex gap-4 items-end">
            <div class="flex-1">
                <label class="label font-semibold">Select Item</label>
                <select id="itemSelect" class="select select-bordered w-full">
                    <option disabled selected value="">Choose an item</option>
                    <% for (ItemDTO i : items) { %>
                    <option value="<%= i.getItemCode() %>" data-name="<%= i.getItemName() %>" data-price="<%= i.getUnitPrice() %>">
                        <%= i.getItemName() %>
                    </option>
                    <% } %>
                </select>
            </div>

            <div class="form-control">
                <label class="label font-semibold">Quantity</label>
                <input type="number" id="itemQuantity" value="1" min="1" class="input input-bordered w-24" />
            </div>

            <button type="button" class="btn btn-secondary btn-outline" onclick="addToBill()">
                <i class="fa-solid fa-plus"></i>
            </button>
        </div>

        <!-- Bill Items Table -->
        <div class="mt-8">
            <h3 class="font-semibold mb-2">Bill Items</h3>
            <div class="overflow-x-auto">
                <table class="table table-zebra w-full text-sm">
                    <thead>
                    <tr>
                        <th>Item</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Total</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody id="bill-items">
                    <tr id="no-items-row">
                        <td colspan="5" class="text-center">No items added to bill</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Bill Summary + Recent Bills -->
    <div class="flex flex-col gap-6">
        <!-- Summary -->
        <div class="bg-base-100 p-6 rounded-box shadow-lg">
            <h3 class="text-lg font-semibold mb-4">Bill Summary</h3>
            <div class="space-y-2 text-sm">
                <div class="flex justify-between">
                    <span>Subtotal:</span>
                    <span>Rs. <span id="subtotal">0.00</span></span>
                </div>
                <div class="flex justify-between font-bold text-lg mt-4">
                    <span>Total:</span>
                    <span>Rs. <span id="total">0.00</span></span>
                </div>
            </div>
            <button type="button" class="btn btn-primary mt-6 w-full" onclick="submitOrder()">
                <i class="fa-solid fa-cart-shopping"></i> Create Bill
            </button>
        </div>

        <!-- Recent Bills -->
        <div class="bg-base-100 p-6 rounded-box shadow-lg">
            <h3 class="text-lg font-semibold mb-2">Recent Bill</h3>

            <% if (lastOrder != null && lastOrder.getOrderItems() != null && !lastOrder.getOrderItems().isEmpty()) { %>
            <p class="mb-2 font-semibold">
                Customer: <%= lastOrderCustomer != null ? lastOrderCustomer.getName() : "Unknown" %><br/>
                Order Date: <%= lastOrder.getDate() %><br/>
                Total: Rs. <%= String.format("%.2f", lastOrder.getTotalAmount()) %>
            </p>

            <table class="table table-zebra w-full text-sm">
                <thead>
                <tr>
                    <th>Item</th>
                    <th>Qty</th>
                    <th>Unit Price</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <% for (OrderItemDTO item : lastOrder.getOrderItems()) { %>
                <tr>
                    <td><%= item.getItemCode() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>Rs. <%= String.format("%.2f", item.getUnitPrice()) %></td>
                    <td>Rs. <%= String.format("%.2f", item.getUnitPrice() * item.getQuantity()) %></td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <% } else { %>
            <p class="text-sm text-gray-400">No bills created yet</p>
            <% } %>
        </div>
    </div>

    <!-- Hidden fields for bill items and total -->
    <div id="hiddenInputsContainer"></div>
    <input type="hidden" name="total" id="totalHidden">

</form>

<script>
    const billItems = [];

    function addToBill() {
        const itemSelect = document.getElementById("itemSelect");
        const qtyInput = document.getElementById("itemQuantity");

        const itemCode = itemSelect.value;
        const itemName = itemSelect.selectedOptions[0]?.dataset.name;
        const unitPrice = parseFloat(itemSelect.selectedOptions[0]?.dataset.price);
        const quantity = parseInt(qtyInput.value);

        if (!itemCode || isNaN(quantity) || quantity <= 0) {
            Swal.fire({
                icon: "warning",
                text: "Please select an item and enter a valid quantity.",
                theme: 'dark'
            });
            return;
        }

        const existing = billItems.find(item => item.code === itemCode);
        console.log('Pushing item:', {
            code: itemCode,
            name: itemName,
            price: unitPrice,
            quantity
        });
        if (existing) {
            existing.quantity += quantity;
        } else {
            billItems.push({ code: itemCode, name: itemName, price: unitPrice, quantity });
        }

        event.preventDefault();
        renderBillTable();
        calculateTotal();
        setTimeout(() => {
            console.log("tbody after render", document.getElementById("bill-items").innerHTML);
        }, 500);
    }

    function removeItem(code) {
        const index = billItems.findIndex(item => item.code === code);
        if (index !== -1) {
            billItems.splice(index, 1);
        }
        renderBillTable();
        calculateTotal();
    }

    function renderBillTable() {
        const tbody = document.getElementById("bill-items");
        tbody.innerHTML = "";

        if (billItems.length === 0) {
            tbody.innerHTML = '<tr id="no-items-row"><td colspan="5" class="text-center">No items added to bill</td></tr>';
            return;
        }

        billItems.forEach(item => {
            const row = document.createElement("tr");

            const tdName = document.createElement("td");
            tdName.textContent = item.name;

            const tdQty = document.createElement("td");
            tdQty.textContent = item.quantity;

            const tdPrice = document.createElement("td");
            tdPrice.textContent = "Rs. " + item.price.toFixed(2);

            const tdTotal = document.createElement("td");
            const total = Number(item.price) * Number(item.quantity);
            tdTotal.textContent = "Rs. " + total.toFixed(2);

            const tdAction = document.createElement("td");
            const btn = document.createElement("button");
            btn.className = "btn btn-sm text-error";
            btn.type = "button";
            btn.onclick = () => removeItem(item.code);
            btn.innerHTML = `<i class="fa-solid fa-trash"></i>`;
            tdAction.appendChild(btn);

            // Append tds to the row
            row.appendChild(tdName);
            row.appendChild(tdQty);
            row.appendChild(tdPrice);
            row.appendChild(tdTotal);
            row.appendChild(tdAction);

            tbody.appendChild(row);
        });
    }

    function calculateTotal() {
        let subtotal = 0;
        billItems.forEach(item => {
            subtotal += item.price * item.quantity;
        });

        const total = subtotal;

        document.getElementById("subtotal").innerText = subtotal.toFixed(2);
        document.getElementById("total").innerText = total.toFixed(2);
    }

    function submitOrder() {
        const customerId = document.getElementById("customerId").value;

        if (!customerId || customerId === "Choose a customer") {
            Swal.fire({
                icon: "error",
                text: "Please select a customer.",
                theme: 'dark'
            });
            return;
        }

        if (billItems.length === 0) {
            Swal.fire({
                icon: "error",
                text: "Please add at least one item to the bill.",
                theme: 'dark'
            });
            return;
        }

        // Clear previous hidden inputs
        const container = document.getElementById("hiddenInputsContainer");
        container.innerHTML = "";

        // Add hidden inputs for each bill item
        billItems.forEach((item, index) => {
            const inputCode = document.createElement("input");
            inputCode.type = "hidden";
            inputCode.name = "items["+ index + "].code";
            inputCode.value = item.code;
            container.appendChild(inputCode);

            const inputQty = document.createElement("input");
            inputQty.type = "hidden";
            inputQty.name = "items["+ index + "].quantity";
            inputQty.value = item.quantity;
            container.appendChild(inputQty);

            const inputPrice = document.createElement("input");
            inputPrice.type = "hidden";
            inputPrice.name = "items["+ index + "].price";
            inputPrice.value = item.price;
            container.appendChild(inputPrice);
        });

        // Set total
        document.getElementById("totalHidden").value = document.getElementById("total").innerText;

        // Submit the form
        document.getElementById("orderForm").submit();
    }
</script>
