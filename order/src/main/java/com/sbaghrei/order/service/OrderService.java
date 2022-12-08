package com.sbaghrei.order.service;

import com.sbaghrei.order.model.dto.OrderUpdateStateRequest;
import com.sbaghrei.order.model.order.Order;
import com.sbaghrei.order.model.order.OrderState;

import java.util.List;

public interface OrderService {

    List<Order> findAll();
    Order save(Order order);
    void cancel(long id);
    void updateState(long id, OrderUpdateStateRequest request);
    void delete(long id);
}
