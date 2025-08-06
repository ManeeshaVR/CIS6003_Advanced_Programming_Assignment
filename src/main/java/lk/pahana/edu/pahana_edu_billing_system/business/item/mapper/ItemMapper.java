package lk.pahana.edu.pahana_edu_billing_system.business.item.mapper;

import jakarta.servlet.http.HttpServletRequest;
import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.item.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        if (item == null) return null;

        return new ItemDTO.Builder()
                .setItemCode(item.getItemCode())
                .setItemName(item.getItemName())
                .setCategory(item.getCategory())
                .setDescription(item.getDescription())
                .setUnitPrice(item.getUnitPrice())
                .setStockQuantity(item.getStockQuantity())
                .setPublisher(item.getPublisher())
                .setAuthor(item.getAuthor())
                .build();
    }

    public static Item toEntity(ItemDTO dto) {
        if (dto == null) return null;

        return new Item.Builder()
                .setItemCode(dto.getItemCode())
                .setItemName(dto.getItemName())
                .setCategory(dto.getCategory())
                .setDescription(dto.getDescription())
                .setUnitPrice(dto.getUnitPrice())
                .setStockQuantity(dto.getStockQuantity())
                .setPublisher(dto.getPublisher())
                .setAuthor(dto.getAuthor())
                .build();
    }

    public static ItemDTO buildItemDTOFromRequest(HttpServletRequest req) {
        return new ItemDTO.Builder()
                .setItemCode(req.getParameter("itemCode"))
                .setItemName(req.getParameter("itemName"))
                .setCategory(req.getParameter("category"))
                .setDescription(req.getParameter("description"))
                .setUnitPrice(Double.parseDouble(req.getParameter("unitPrice")))
                .setStockQuantity(Integer.parseInt(req.getParameter("stockQuantity")))
                .setPublisher(req.getParameter("publisher"))
                .setAuthor(req.getParameter("author"))
                .build();
    }

    public static Item mapToItem(ResultSet rs) throws SQLException {
        return new Item(
                rs.getString("item_code"),
                rs.getString("item_name"),
                rs.getString("category"),
                rs.getString("description"),
                rs.getDouble("unit_price"),
                rs.getInt("stock_quantity"),
                rs.getString("publisher"),
                rs.getString("author")
        );
    }
}
