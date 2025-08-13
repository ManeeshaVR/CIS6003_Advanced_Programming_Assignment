package lk.pahana.edu.pahana_edu_billing_system.business.order.mapper;

import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.order.model.Order;
import lk.pahana.edu.pahana_edu_billing_system.business.order.model.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        if (order == null) return null;

        List<OrderItemDTO> itemDTOs = order.getOrderItems()
                .stream()
                .map(OrderMapper::toItemDTO)
                .collect(Collectors.toList());

        return new OrderDTO.Builder()
                .setOrderId(order.getOrderId())
                .setCustomerId(order.getCustomerId())
                .setDate(order.getDate())
                .setTotalAmount(order.getTotalAmount())
                .setOrderItems(itemDTOs)
                .build();
    }

    public static Order toEntity(OrderDTO dto) {
        if (dto == null) return null;

        List<OrderItem> items = dto.getOrderItems()
                .stream()
                .map(OrderMapper::toItemEntity)
                .collect(Collectors.toList());

        return new Order.Builder()
                .setOrderId(dto.getOrderId())
                .setCustomerId(dto.getCustomerId())
                .setDate(dto.getDate())
                .setTotalAmount(dto.getTotalAmount())
                .setOrderItems(items)
                .build();
    }

    public static OrderItemDTO toItemDTO(OrderItem item) {
        if (item == null) return null;

        return new OrderItemDTO.Builder()
                .itemCode(item.getItemCode())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .build();
    }

    public static OrderItem toItemEntity(OrderItemDTO dto) {
        if (dto == null) return null;

        return new OrderItem.Builder()
                .itemCode(dto.getItemCode())
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .build();
    }

    public static Order mapToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();

        if (!resultSet.next()) {
            return null; // no rows
        }

        // Set basic order details from first row
        order.setOrderId(resultSet.getString("order_id"));
        order.setDate(resultSet.getDate("order_date").toLocalDate());
        order.setCustomerId(resultSet.getString("customer_id"));
        order.setTotalAmount(resultSet.getDouble("total_amount"));

        do {
            OrderItem item = new OrderItem();
            item.setItemCode(resultSet.getString("item_code"));
            item.setQuantity(resultSet.getInt("quantity"));
            item.setUnitPrice(resultSet.getDouble("unit_price"));
            orderItems.add(item);
        } while (resultSet.next());

        order.setOrderItems(orderItems);
        return order;
    }

}
