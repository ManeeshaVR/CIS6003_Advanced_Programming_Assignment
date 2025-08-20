<div class="navbar bg-neutral text-neutral-content px-6">
    <div class="flex-1">
        <label for="my-drawer-2" class="btn btn-primary drawer-button lg:hidden m-4">☰</label>
        <a class="text-xl font-bold"><i class="fa-solid fa-book-open mr-2 text-lg text-primary"></i>Pahana Edu</a>
    </div>
    <div class="flex-none gap-4">
        <i class="fa-solid fa-circle-user mr-2 text-lg"></i>
        <span class="font-semibold mr-2">Admin</span>
        <a href="<%= request.getContextPath() %>/logout" class="btn btn-sm btn-error">Logout</a>
    </div>
</div>
