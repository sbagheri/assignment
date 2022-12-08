package com.sbaghrei.order.repository;

import com.sbaghrei.order.model.order.Order;
import com.sbaghrei.order.model.order.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByStateNot(OrderState state);
}
