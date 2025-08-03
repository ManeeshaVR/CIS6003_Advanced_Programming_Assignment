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
    }

}
