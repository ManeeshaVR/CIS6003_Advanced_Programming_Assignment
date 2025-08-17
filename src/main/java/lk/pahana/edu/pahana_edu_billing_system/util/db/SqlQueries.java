package lk.pahana.edu.pahana_edu_billing_system.util.db;

public class SqlQueries {

    public static final class Customer {
        public static final String INSERT =
                "INSERT INTO customer (customer_id, name, address, mobile_number, units_consumed, registration_date, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        public static final String FIND_BY_ID =
                "SELECT * FROM customer WHERE customer_id = ?";

        public static final String FIND_ALL =
                "SELECT * FROM customer";

        public static final String UPDATE =
                "UPDATE customer SET name = ?, address = ?, mobile_number = ?, email = ? WHERE customer_id = ?";

        public static final String DELETE =
                "DELETE FROM customer WHERE customer_id = ?";

        public static final String ADD_UNITS_CONSUMED =
                "UPDATE customer SET units_consumed = units_consumed + ? WHERE customer_id = ?";

        public static final String COUNT =
                "SELECT COUNT(*) FROM customer";

        public static final String FIND_TOP_CUSTOMERS =
                "SELECT * FROM customer ORDER BY units_consumed DESC LIMIT 3";

        public static final String EXISTS_BY_EMAIL =
                "SELECT COUNT(*) FROM customer WHERE email = ?";

        public static final String EXISTS_BY_MOBILE_NUMBER =
                "SELECT COUNT(*) FROM customer WHERE mobile_number = ?";

        public static final String EXISTS_OTHER_BY_EMAIL =
                "SELECT COUNT(*) FROM customer WHERE email = ? AND customer_id <> ?";

        public static final String EXISTS_OTHER_BY_MOBILE_NUMBER =
                "SELECT COUNT(*) FROM customer WHERE mobile_number = ? AND customer_id <> ?";
    }

    public static final class Item {
        public static final String INSERT =
                "INSERT INTO item (item_code, item_name, description, category, unit_price, stock_quantity, publisher, author) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        public static final String FIND_BY_ID =
                "SELECT * FROM item WHERE item_code = ?";

        public static final String FIND_ALL =
                "SELECT * FROM item";

        public static final String UPDATE =
                "UPDATE item SET item_name = ?, description = ?, category = ?, unit_price = ?, stock_quantity = ?, publisher = ?, author = ? WHERE item_code = ?";

        public static final String DELETE =
                "DELETE FROM item WHERE item_code = ?";

        public static final String DEDUCT_QUANTITY =
                "UPDATE item SET stock_quantity = stock_quantity - ? WHERE item_code = ?";

        public static final String COUNT =
                "SELECT COUNT(*) FROM item";

        public static final String FIND_TOP_ITEMS =
                "SELECT i.item_code, " +
                        "       i.item_name, " +
                        "       i.category, " +
                        "       i.description, " +
                        "       i.unit_price, " +
                        "       i.publisher, " +
                        "       i.author, " +
                        "       SUM(bi.quantity) AS stock_quantity " +
                        "FROM item i " +
                        "JOIN bill_item bi ON i.item_code = bi.item_code " +
                        "GROUP BY i.item_code, i.item_name, i.category, i.description, i.unit_price, i.publisher, i.author " +
                        "ORDER BY stock_quantity DESC " +
                        "LIMIT 3";

        public static final String EXISTS_DUPLICATE =
                "SELECT COUNT(*) FROM item WHERE item_name = ? AND author = ? AND publisher = ?";

        public static final String EXISTS_OTHER_DUPLICATE =
                "SELECT COUNT(*) FROM item WHERE item_name = ? AND author = ? AND publisher = ? AND item_code <> ?";
    }

    public static final class Bill {
        public static final String INSERT =
                "INSERT INTO bill (bill_id, bill_date, customer_id, total_amount) VALUES (?, ?, ?, ?)";

        public static final String FIND_LAST =
                "SELECT " +
                        "    b.bill_id, " +
                        "    b.bill_date, " +
                        "    b.customer_id, " +
                        "    b.total_amount, " +
                        "    bi.item_code, " +
                        "    i.item_name, " +
                        "    bi.quantity, " +
                        "    bi.unit_price " +
                        "FROM bill b " +
                        "JOIN bill_item bi ON b.bill_id = bi.bill_id " +
                        "JOIN item i ON bi.item_code = i.item_code " +
                        "WHERE b.bill_id = ( " +
                        "    SELECT bill_id " +
                        "    FROM bill " +
                        "    ORDER BY bill_date DESC" +
                        "    LIMIT 1 " +
                        ")";

        public static final String COUNT =
                "SELECT COUNT(*) FROM bill";

        public static final String FIND_ALL =
                "SELECT * FROM bill ORDER BY bill_date DESC";
    }

    public static final class BillItem {
        public static final String INSERT =
                "INSERT INTO bill_item (bill_id, item_code, quantity, unit_price) VALUES (?, ?, ?, ?)";
    }

    public static final class User {
        public static final String FIND_BY_USERNAME_AND_PASSWORD =
                "SELECT * FROM user WHERE username = ? AND password = ?";
    }

}
