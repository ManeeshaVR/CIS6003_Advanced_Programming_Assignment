<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="flex justify-between items-center mb-6">
    <h1 class="text-3xl font-bold">Welcome Back, Admin!</h1>
</div>

<!-- Stat Cards -->
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 mb-6">
    <div class="bg-neutral rounded-box py-4 px-6">
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
    <div class="bg-neutral rounded-box py-4 px-6">
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
    <div class="bg-neutral rounded-box py-4 px-6">
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

<div class="grid grid-cols-1 lg:grid-cols-3 gap-6 mb-6">

    <!-- Top Customers Card -->
    <div class="card bg-base-100 w-96 shadow-sm">
        <figure class="px-10 pt-10">
            <img
                    src="<%= request.getContextPath() %>/images/customers.svg"
                    alt="Top Customers"
                    class="rounded-xl" />
        </figure>
        <div class="card-body items-center text-center">
            <h2 class="card-title flex items-center gap-2">
                <i class="fa-solid fa-users text-blue-500"></i>
                Top Customers
            </h2>
            <div class="w-full mt-2">
                <ul class="space-y-1 text-left">
                    <li class="flex items-center gap-2">
                        <i class="fa-solid fa-user text-gray-600"></i>
                        <span>John Doe</span>
                        <span class="ml-auto badge badge-primary">5</span>
                    </li>
                    <li class="flex items-center gap-2">
                        <i class="fa-solid fa-user text-gray-600"></i>
                        <span>Jane Smith</span>
                        <span class="ml-auto badge badge-primary">3</span>
                    </li>
                </ul>
            </div>
            <div class="card-actions mt-4">
                <button class="btn btn-primary">Buy Now</button>
            </div>
        </div>
    </div>

    <!-- Best Selling Items Card -->
    <div class="card bg-base-100 w-96 shadow-sm">
        <figure class="px-10 pt-10">
            <img
                    src="<%= request.getContextPath() %>/images/items.svg"
                    alt="Best Selling Items"
                    class="rounded-xl" />
        </figure>
        <div class="card-body items-center text-center">
            <h2 class="card-title flex items-center gap-2">
                <i class="fa-solid fa-boxes-stacked text-purple-500"></i>
                Best Selling Items
            </h2>
            <div class="w-full mt-2">
                <ul class="space-y-1 text-left">
                    <li class="flex items-center gap-2">
                        <i class="fa-solid fa-box text-gray-600"></i>
                        <span>Laptop</span>
                        <span class="ml-auto badge badge-primary">10</span>
                    </li>
                    <li class="flex items-center gap-2">
                        <i class="fa-solid fa-box text-gray-600"></i>
                        <span>Smartphone</span>
                        <span class="ml-auto badge badge-primary">8</span>
                    </li>
                </ul>
            </div>
            <div class="card-actions mt-4">
                <button class="btn btn-primary">Buy Now</button>
            </div>
        </div>
    </div>

</div>
