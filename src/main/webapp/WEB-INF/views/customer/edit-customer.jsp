<%@ page import="lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO" %>
<%
    CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
%>

<div class="flex justify-between px-8 py-4">
    <div>
        <a id="backButton" href="<%= request.getContextPath() %>/customer" class="btn btn-neutral">
            <i class="fa-solid fa-arrow-left"></i>Back
        </a>
    </div>
</div>

<div class="flex items-center justify-center">
    <div class="w-full max-w-xl px-4 py-8 shadow-lg rounded-lg">
        <h1 class="text-3xl font-bold mb-6 text-center">Edit Customer</h1>

        <form action="<%= request.getContextPath() %>/customer/edit?id=<%= customer.getCustomerId() %>" method="post" class="space-y-5">

            <!-- Name -->
            <div class="form-control">
                <label class="label" for="name">
                    <span class="label-text font-semibold">Name</span>
                </label>
                <input id="name" name="name" value="<%= customer.getName() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Address -->
            <div class="form-control">
                <label class="label" for="address">
                    <span class="label-text font-semibold">Address</span>
                </label>
                <input id="address" name="address" value="<%= customer.getAddress() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Mobile Number -->
            <div class="form-control">
                <label class="label" for="mobileNumber">
                    <span class="label-text font-semibold">Mobile Number</span>
                </label>
                <input id="mobileNumber" name="mobileNumber" value="<%= customer.getMobileNumber() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Email -->
            <div class="form-control">
                <label class="label" for="email">
                    <span class="label-text font-semibold">Email</span>
                </label>
                <input id="email" name="email" value="<%= customer.getEmail() %>" required
                       class="input input-bordered w-full"/>
            </div>

            <!-- Submit Button -->
            <div class="form-control mt-6">
                <button type="submit" class="btn btn-primary w-full">Update Customer</button>
            </div>
        </form>
    </div>
</div>
