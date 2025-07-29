<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" data-theme="night">

<head>
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Pahana Edu" %></title>
    <%@ include file="../partials/header.jsp" %>
</head>

<body class="min-h-screen bg-base-100 text-base-content">
<%@ include file="../partials/navbar.jsp" %>

<div class="drawer lg:drawer-open">
    <input id="my-drawer-2" type="checkbox" class="drawer-toggle"/>
    <div class="drawer-content">
        <main class="flex-1 p-8 overflow-auto">
            <jsp:include page='<%= (String) request.getAttribute("body") %>' />
        </main>
    </div>
    <%@ include file="../partials/sidebar.jsp" %>
</div>
</body>
</html>
