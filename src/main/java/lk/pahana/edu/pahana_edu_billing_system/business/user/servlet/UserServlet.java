package lk.pahana.edu.pahana_edu_billing_system.business.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.user.dto.UserDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.user.service.UserService;
import lk.pahana.edu.pahana_edu_billing_system.business.user.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "user", urlPatterns = "/login")
public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageTitle", "Login to Pahana Edu");
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.getSession().setAttribute("flash_error", "Username and password are required.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserDTO user = userService.findUserByUsernameAndPassword(username, password);

        if (user != null) {
            req.getSession().setAttribute("user", user.getUserId());
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.getSession().setAttribute("flash_error", "Invalid username or password.");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
