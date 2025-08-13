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
    }

    public static final class Order {
        public static final String INSERT =
                "INSERT INTO orders (order_id, order_date, customer_id, total_amount) VALUES (?, ?, ?, ?)";

        public static final String FIND_LAST =
                "SELECT \n" +
                        "    o.order_id,\n" +
                        "    o.order_date,\n" +
                        "    o.customer_id,\n" +
                        "    o.total_amount,\n" +
                        "    oi.item_code,\n" +
                        "    oi.quantity,\n" +
                        "    oi.unit_price\n" +
                        "FROM orders o\n" +
                        "JOIN order_item oi ON o.order_id = oi.order_id\n" +
                        "WHERE o.order_id = (\n" +
                        "    SELECT order_id \n" +
                        "    FROM orders \n" +
                        "    ORDER BY order_date DESC, order_id DESC \n" +
                        "    LIMIT 1\n" +
                        ");\n";
    }

    public static final class OrderItem {
        public static final String INSERT =
                "INSERT INTO order_item (order_id, item_code, quantity, unit_price) VALUES (?, ?, ?, ?)";
    }

}
