<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO" %>
<%
    ItemDTO item = (ItemDTO) request.getAttribute("item");
%>

<div class="flex justify-between px-8 py-4">
    <div>
        <a id="backButton" href="<%= request.getContextPath() %>/item" class="btn btn-neutral">
            <i class="fa-solid fa-arrow-left"></i>Back
        </a>
    </div>
</div>

<div class="flex items-center justify-center">
    <div class="w-full max-w-xl px-4 py-8 shadow-lg rounded-lg">
        <h1 class="text-3xl font-bold mb-6 text-center">Edit Item</h1>

        <form action="<%= request.getContextPath() %>/item/edit?code=<%= item.getItemCode() %>" method="post" class="space-y-5">

            <!-- Item Name -->
            <div class="form-control">
                <label class="label" for="itemName">
                    <span class="label-text font-semibold">Item Name</span>
                </label>
                <input id="itemName" name="itemName" value="<%= item.getItemName() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Category -->
            <div class="form-control">
                <label class="label" for="category">
                    <span class="label-text font-semibold">Category</span>
                </label>
                <input id="category" name="category" value="<%= item.getCategory() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Description -->
            <div class="form-control">
                <label class="label" for="description">
                    <span class="label-text font-semibold">Description</span>
                </label>
                <textarea id="description" name="description" class="textarea textarea-bordered w-full"
                          rows="3"><%= item.getDescription() %></textarea>
            </div>

            <!-- Unit Price -->
            <div class="form-control">
                <label class="label" for="unitPrice">
                    <span class="label-text font-semibold">Unit Price</span>
                </label>
                <input id="unitPrice" name="unitPrice" type="number" step="0.01" value="<%= item.getUnitPrice() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Stock Quantity -->
            <div class="form-control">
                <label class="label" for="stockQuantity">
                    <span class="label-text font-semibold">Stock Quantity</span>
                </label>
                <input id="stockQuantity" name="stockQuantity" type="number" value="<%= item.getStockQuantity() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Publisher -->
            <div class="form-control">
                <label class="label" for="publisher">
                    <span class="label-text font-semibold">Publisher</span>
                </label>
                <input id="publisher" name="publisher" value="<%= item.getPublisher() %>"
                       class="input input-bordered w-full"/>
            </div>

            <!-- Author -->
            <div class="form-control">
                <label class="label" for="author">
                    <span class="label-text font-semibold">Author</span>
                </label>
                <input id="author" name="author" value="<%= item.getAuthor() %>"
                       class="input input-bordered w-full"/>
            </div>

            <!-- Submit Button -->
            <div class="form-control mt-6">
                <button type="submit" class="btn btn-primary w-full">Update Item</button>
            </div>
        </form>
    </div>
</div>
