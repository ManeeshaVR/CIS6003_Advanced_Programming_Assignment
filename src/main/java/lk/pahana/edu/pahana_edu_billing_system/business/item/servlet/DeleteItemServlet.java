package lk.pahana.edu.pahana_edu_billing_system.business.item.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl.ItemServiceImpl;

import java.io.IOException;

@WebServlet(name = "delete-item", urlPatterns = "/item/delete")
public class DeleteItemServlet extends HttpServlet {

    private ItemService itemService;

    @Override
    public void init() {
        itemService = new ItemServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        if (code != null && !code.isEmpty()) {
            itemService.deleteItem(code);
            req.getSession().setAttribute("flash_success", "Item deleted successfully!");
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not a valid item code!");
        }
        resp.sendRedirect(req.getContextPath() + "/item");
    }
}
