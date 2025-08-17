package lk.pahana.edu.pahana_edu_billing_system.business.order.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.order.dto.OrderDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.order.mapper.OrderMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.order.service.OrderService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.order.dao.OrderDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.order.dao.impl.OrderDAOImpl;

import java.time.LocalDate;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public boolean saveOrder(OrderDTO orderDTO) {
        orderDTO.setOrderId(UUID.randomUUID().toString());
        orderDTO.setDate(LocalDate.now());
        return orderDAO.save(OrderMapper.toEntity(orderDTO));
    }

    @Override
    public OrderDTO getOrderById(String orderId) {
        return OrderMapper.toDTO(orderDAO.findById(orderId));
    }

    @Override
    public OrderDTO getLastOrder() {
        return OrderMapper.toDTO(orderDAO.findLast());
    }

    @Override
    public int getOrderCount() {
        return orderDAO.getCount();
    }
}
