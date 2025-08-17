package lk.pahana.edu.pahana_edu_billing_system.business.item.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.item.mapper.ItemMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.ItemService;
import lk.pahana.edu.pahana_edu_billing_system.business.item.service.impl.ItemServiceImpl;
import lk.pahana.edu.pahana_edu_billing_system.util.validation.Validation;

import java.io.IOException;

@WebServlet(name = "edit-item", urlPatterns = "/item/edit")
public class EditItemServlet extends HttpServlet {

    private ItemService itemService;

    @Override
    public void init() {
        itemService = new ItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        if (code != null && !code.isEmpty()) {
            ItemDTO item = itemService.getItemByCode(code);

            if (item != null) {
                req.setAttribute("item", item);
                req.setAttribute("pageTitle", "Edit Item");
                req.setAttribute("body", "../item/edit-item.jsp");

                req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("flash_error", "Couldn't find the item by code: " + code);
                resp.sendRedirect(req.getContextPath() + "/item");
            }
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not a valid item code!");
            resp.sendRedirect(req.getContextPath() + "/item");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");

        if (code != null && !code.isEmpty()) {
            ItemDTO itemDTO = ItemMapper.buildItemDTOFromRequest(req);
            String validationError = Validation.validateItemDTO(itemDTO);
            boolean existsDuplicate = itemService.existsItemDuplicate(itemDTO.getItemName(), itemDTO.getPublisher(), itemDTO.getAuthor(), code);

            if (validationError == null) {
                if (!existsDuplicate) {
                    boolean updateItem = itemService.updateItem(code, itemDTO);
                    if (updateItem) {
                        req.getSession().setAttribute("flash_success", "Item updated successfully!");
                    } else {
                        req.getSession().setAttribute("flash_error", "Failed to update item.");
                    }
                } else {
                    req.getSession().setAttribute("flash_error", "Item with the same name, publisher, and author already exists.");
                }
            } else {
                req.getSession().setAttribute("flash_error", validationError);
            }
        } else {
            req.getSession().setAttribute("flash_error", "Missing or not a valid item code!");
        }

        resp.sendRedirect(req.getContextPath() + "/item");
    }
}
