package lk.pahana.edu.pahana_edu_billing_system.business.bill.mapper;

import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.InvoiceDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.dto.InvoiceItemDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Invoice;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.InvoiceItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceMapper {

    public static InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) return null;

        List<InvoiceItemDTO> itemDTOs = invoice.getBillItems()
                .stream()
                .map(InvoiceMapper::toItemDTO)
                .collect(Collectors.toList());

        return new InvoiceDTO.Builder()
                .setBillId(invoice.getBillId())
                .setCustomerId(invoice.getCustomerId())
                .setDate(invoice.getDate())
                .setTotalAmount(invoice.getTotalAmount())
                .setBillItems(itemDTOs)
                .build();
    }

    public static Invoice toEntity(InvoiceDTO dto) {
        if (dto == null) return null;

        List<InvoiceItem> items = dto.getBillItems()
                .stream()
                .map(InvoiceMapper::toItemEntity)
                .collect(Collectors.toList());

        return new Invoice.Builder()
                .setBillId(dto.getBillId())
                .setCustomerId(dto.getCustomerId())
                .setDate(dto.getDate())
                .setTotalAmount(dto.getTotalAmount())
                .setBillItems(items)
                .build();
    }

    public static InvoiceItemDTO toItemDTO(InvoiceItem item) {
        if (item == null) return null;

        return new InvoiceItemDTO.Builder()
                .itemCode(item.getItemCode())
                .itemName(item.getItemName())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .build();
    }

    public static InvoiceItem toItemEntity(InvoiceItemDTO dto) {
        if (dto == null) return null;

        return new InvoiceItem.Builder()
                .itemCode(dto.getItemCode())
                .itemName(dto.getItemName())
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .build();
    }

    public static Invoice mapToInvoice(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        List<InvoiceItem> items = new ArrayList<>();

        if (!resultSet.next()) {
            return null;
        }

        // Set basic invoice details from first row
        invoice.setBillId(resultSet.getString("bill_id"));
        invoice.setDate(resultSet.getTimestamp("bill_date").toLocalDateTime());
        invoice.setCustomerId(resultSet.getString("customer_id"));
        invoice.setTotalAmount(resultSet.getDouble("total_amount"));

        do {
            InvoiceItem item = new InvoiceItem();
            item.setItemCode(resultSet.getString("item_code"));
            item.setItemName(resultSet.getString("item_name"));
            item.setQuantity(resultSet.getInt("quantity"));
            item.setUnitPrice(resultSet.getDouble("unit_price"));
            items.add(item);
        } while (resultSet.next());

        invoice.setBillItems(items);
        return invoice;
    }
}
