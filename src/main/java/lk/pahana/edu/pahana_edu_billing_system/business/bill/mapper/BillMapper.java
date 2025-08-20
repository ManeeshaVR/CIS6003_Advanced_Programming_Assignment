package lk.pahana.edu.pahana_edu_billing_system.business.bill.mapper;

import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.BillDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.BillItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Bill;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.BillItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillMapper {
    public static BillDTO toDTO(Bill bill) {
        if (bill == null) return null;

        List<BillItemDTO> itemDTOS = new ArrayList<>();
        if (bill.getBillItems() != null) {
            itemDTOS = bill.getBillItems()
                    .stream()
                    .map(BillMapper::toItemDTO)
                    .collect(Collectors.toList());
        }

        return new BillDTO.Builder()
                .setBillId(bill.getBillId())
                .setCustomerId(bill.getCustomerId())
                .setDate(bill.getDate())
                .setTotalAmount(bill.getTotalAmount())
                .setBillItems(itemDTOS)
                .build();
    }

    public static Bill toEntity(BillDTO dto) {
        if (dto == null) return null;

        List<BillItem> items = dto.getBillItems()
                .stream()
                .map(BillMapper::toItemEntity)
                .collect(Collectors.toList());

        return new Bill.Builder()
                .setBillId(dto.getBillId())
                .setCustomerId(dto.getCustomerId())
                .setDate(dto.getDate())
                .setTotalAmount(dto.getTotalAmount())
                .setBillItems(items)
                .build();
    }

    public static BillItemDTO toItemDTO(BillItem item) {
        if (item == null) return null;

        return new BillItemDTO.Builder()
                .itemCode(item.getItemCode())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .build();
    }

    public static BillItem toItemEntity(BillItemDTO dto) {
        if (dto == null) return null;

        return new BillItem.Builder()
                .itemCode(dto.getItemCode())
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .build();
    }

    public static Bill mapToBill(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        List<BillItem> billItems = new ArrayList<>();

        if (!resultSet.next()) {
            return null;
        }

        // Set basic order details from first row
        bill.setBillId(resultSet.getString("bill_id"));
        bill.setDate(resultSet.getTimestamp("bill_date").toLocalDateTime());
        bill.setCustomerId(resultSet.getString("customer_id"));
        bill.setTotalAmount(resultSet.getDouble("total_amount"));

        do {
            BillItem item = new BillItem();
            item.setItemCode(resultSet.getString("item_code"));
            item.setQuantity(resultSet.getInt("quantity"));
            item.setUnitPrice(resultSet.getDouble("unit_price"));
            billItems.add(item);
        } while (resultSet.next());

        bill.setBillItems(billItems);
        return bill;
    }

}
