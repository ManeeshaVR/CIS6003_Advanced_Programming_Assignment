package lk.pahana.edu.pahana_edu_billing_system.persistence.order.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.order.model.Order;

public interface OrderDAO {

    boolean save(Order order);

    Order findLast();

}
