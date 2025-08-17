<div class="drawer-side">
    <label for="my-drawer-2" aria-label="close sidebar" class="drawer-overlay"></label>
    <ul class="menu bg-base-200 text-base-content min-h-full w-64 gap-2 p-4">
        <li>
            <a href="<%= request.getContextPath() %>/" class="flex items-center p-3 rounded-lg transition-all
            <%= request.getRequestURI().equals(request.getContextPath() + "/") ? "text-primary" : "" %>
                focus:text-primary active:text-primary">
                <i class="fa-solid fa-house mr-3 text-xl"></i>
                Dashboard
            </a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/customer" class="flex items-center p-3 rounded-lg transition-all
            <%= request.getRequestURI().contains("/customer") ? "text-primary" : "" %>
                focus:text-primary active:text-primary">
                <i class="fa-solid fa-users mr-3 text-lg"></i>
                Customers
            </a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/item" class="flex items-center p-3 rounded-lg transition-all
            <%= request.getRequestURI().startsWith("/item") ? "text-primary" : "" %>
                focus:text-primary active:text-primary">
                <i class="fa-solid fa-boxes-stacked mr-3 text-lg"></i>
                Items
            </a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/bill/create" class="flex items-center p-3 rounded-lg transition-all
            <%= request.getRequestURI().equals(request.getContextPath() + "/bill/create") ? "text-primary" : "" %>
                focus:text-primary active:text-primary">
                <i class="fa-solid fa-cart-plus mr-3 text-lg"></i>
                Create Bill
            </a>
        </li>
        <li>
            <a href="<%= request.getContextPath() %>/bill" class="flex items-center p-3 rounded-lg transition-all
            <%= request.getRequestURI().equals(request.getContextPath() + "/bill") ? "text-primary" : "" %>
                focus:text-primary active:text-primary">
                <i class="fa-solid fa-cart-shopping mr-3 text-lg"></i>
                Bills Overview
            </a>
        </li>
    </ul>
</div>
