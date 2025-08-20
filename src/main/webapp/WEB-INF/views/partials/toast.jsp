<%
    String successMsg = (String) session.getAttribute("flash_success");
    String errorMsg = (String) session.getAttribute("flash_error");
    String warningMsg = (String) session.getAttribute("flash_warning");
    session.removeAttribute("flash_success");
    session.removeAttribute("flash_error");
    session.removeAttribute("flash_warning");
%>

<div class="toast toast-top toast-end z-50">
    <div class="w-full max-w-xl">
        <% if (successMsg != null) { %>
        <div class="alert alert-success shadow-lg flex items-center gap-2 px-4 py-3 rounded-md">
            <i class="fa-solid fa-circle-check text-white text-xl"></i>
            <span class="text-white font-medium"><%= successMsg %></span>
        </div>
        <% } %>
        <% if (errorMsg != null) { %>
        <div class="alert alert-error shadow-lg flex items-center gap-2 px-4 py-3 rounded-md text-white">
            <i class="fa-solid fa-circle-xmark text-xl"></i>
            <span class="font-medium"><%= errorMsg %></span>
        </div>
        <% } %>
        <% if (warningMsg != null) { %>
        <div class="alert alert-warning shadow-lg flex items-center gap-2 px-4 py-3 rounded-md text-white">
            <i class="fa-solid fa-triangle-exclamation text-xl"></i>
            <span class="font-medium"><%= warningMsg %></span>
        </div>
        <% } %>
    </div>
</div>
