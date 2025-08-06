<!-- Add Item Modal -->
<input type="checkbox" id="add-item" class="modal-toggle" />
<div class="modal">
    <div class="modal-box w-11/12 max-w-lg">
        <h3 class="font-bold text-lg mb-4">Add New Item</h3>
        <form action="<%= request.getContextPath() %>/item" method="post" class="space-y-4">

            <!-- Item Name -->
            <div class="form-control flex flex-col">
                <label class="label" for="itemName">Item Name</label>
                <input id="itemName" name="itemName" type="text" placeholder="Introduction to Java" class="input input-bordered" required />
            </div>

            <!-- Category -->
            <div class="form-control flex flex-col">
                <label class="label" for="category">Category</label>
                <input id="category" name="category" type="text" placeholder="Book / Stationery" class="input input-bordered" required />
            </div>

            <!-- Description -->
            <div class="form-control flex flex-col">
                <label class="label" for="description">Description</label>
                <textarea id="description" name="description" placeholder="Enter item description..." class="textarea textarea-bordered" required></textarea>
            </div>

            <!-- Unit Price -->
            <div class="form-control flex flex-col">
                <label class="label" for="unitPrice">Unit Price</label>
                <input id="unitPrice" name="unitPrice" type="number" step="0.01" placeholder="500.00" class="input input-bordered" required />
            </div>

            <!-- Stock Quantity -->
            <div class="form-control flex flex-col">
                <label class="label" for="stockQuantity">Stock Quantity</label>
                <input id="stockQuantity" name="stockQuantity" type="number" placeholder="10" class="input input-bordered" required />
            </div>

            <!-- Publisher -->
            <div class="form-control flex flex-col">
                <label class="label" for="publisher">Publisher</label>
                <input id="publisher" name="publisher" type="text" placeholder="Pearson" class="input input-bordered" required />
            </div>

            <!-- Author -->
            <div class="form-control flex flex-col">
                <label class="label" for="author">Author</label>
                <input id="author" name="author" type="text" placeholder="John Doe" class="input input-bordered" required />
            </div>

            <!-- Modal Actions -->
            <div class="modal-action">
                <label for="add-item" class="btn">Cancel</label>
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>
