<!-- Add Customer Modal -->
<input type="checkbox" id="add-customer" class="modal-toggle" />
<div class="modal">
    <div class="modal-box w-11/12 max-w-lg">
        <h3 class="font-bold text-lg mb-4">Add New Customer</h3>
        <form action="/customer/add" method="post" class="space-y-4">
            <!-- Customer ID -->
            <div class="form-control flex flex-col">
                <label class="label" for="id">Customer ID</label>
                <input id="id" name="id" type="text" placeholder="C001" class="input input-bordered" required />
            </div>

            <!-- Name -->
            <div class="form-control flex flex-col">
                <label class="label" for="name">Name</label>
                <input id="name" name="name" type="text" placeholder="John Doe" class="input input-bordered" required />
            </div>

            <!-- Address -->
            <div class="form-control flex flex-col">
                <label class="label" for="address">Address</label>
                <input id="address" name="address" type="text" placeholder="123 Main St" class="input input-bordered"
                       required />
            </div>

            <!-- Mobile Number -->
            <div class="form-control flex flex-col">
                <label class="label" for="mobileNumber">Mobile Number</label>
                <input id="mobileNumber" name="mobileNumber" type="tel" placeholder="0771234567"
                       class="input input-bordered" required />
            </div>

            <!-- Email -->
            <div class="form-control flex flex-col">
                <label class="label" for="email">Email</label>
                <input id="email" name="email" type="email" placeholder="example@email.com" class="input input-bordered"
                       required />
            </div>

            <!-- Modal Actions -->
            <div class="modal-action">
                <label for="add-customer" class="btn">Cancel</label>
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>
