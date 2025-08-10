package lk.pahana.edu.pahana_edu_billing_system.business.order.service;

import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO;

public interface OrderService {

    boolean saveOrder(OrderDTO orderDTO);

    OrderDTO getLastOrder();

}
