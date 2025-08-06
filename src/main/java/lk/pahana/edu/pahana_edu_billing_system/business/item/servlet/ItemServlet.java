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

import java.io.IOException;
import java.util.List;

import static lk.pahana.edu.pahana_edu_billing_system.util.validation.Validation.validateItemDTO;

@WebServlet(name = "item", urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    private ItemService itemService;

    @Override
    public void init() {
        itemService = new ItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItemDTO> itemList = itemService.getAllItems();

        req.setAttribute("itemList", itemList);
        req.setAttribute("pageTitle", "Item Management");
        req.setAttribute("body", "../item/view-item.jsp");

        req.getRequestDispatcher("/WEB-INF/views/layout/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDTO itemDTO = ItemMapper.buildItemDTOFromRequest(req);
        String validationError = validateItemDTO(itemDTO);

        if (validationError == null) {
            itemService.saveItem(itemDTO);
            req.getSession().setAttribute("flash_success", "Item created successfully!");
        } else {
            req.getSession().setAttribute("flash_error", validationError);
        }

        resp.sendRedirect(req.getContextPath() + "/item");
    }

}
