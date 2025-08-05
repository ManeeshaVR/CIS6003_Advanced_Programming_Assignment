<%
    String successMsg = (String) session.getAttribute("flash_success");
    String errorMsg = (String) session.getAttribute("flash_error");
    String warningMsg = (String) session.getAttribute("flash_warning");
    session.removeAttribute("flash_success");
    session.removeAttribute("flash_error");
    session.removeAttribute("flash_warning");
%>

<div class="toast">
    <div class="w-full max-w-xl">
        <% if (successMsg != null) { %>
        <div class="alert alert-success alert-soft shadow-lg mb-4">
            <span><%= successMsg %></span>
        </div>
        <% } %>
        <% if (errorMsg != null) { %>
        <div class="alert alert-error alert-soft shadow-lg mb-4">
            <span><%= errorMsg %></span>
        </div>
        <% } %>
        <% if (warningMsg != null) { %>
        <div class="alert alert-warning alert-soft shadow-lg mb-4">
            <span><%= warningMsg %></span>
        </div>
        <% } %>
    </div>
</div>
