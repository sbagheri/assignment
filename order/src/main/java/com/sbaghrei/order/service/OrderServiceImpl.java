package com.sbaghrei.order.service;

import com.sbaghrei.order.exception.BadRequestException;
import com.sbaghrei.order.exception.ResourceNotFoundException;
import com.sbaghrei.order.model.dto.OrderUpdateStateRequest;
import com.sbaghrei.order.model.order.Order;
import com.sbaghrei.order.model.order.OrderState;
import com.sbaghrei.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findOrdersByStateNot(OrderState.PICKED_UP);
    }

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            order.setState(OrderState.WAITING);
            log.debug("saving order: {}", order);
            return orderRepository.save(order);
        } else {
            throw new BadRequestException("id must be null in save");
        }
    }

    @Override
    public void cancel(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order not found with the given id"));
        if (isOrderValidForCancellation(order)){
            orderRepository.delete(order);
            log.debug("canceled order: {}", order);
        } else {
            throw new BadRequestException("Order could not be removed due to its state");
        }
    }

    @Override
    public void updateState(long id, OrderUpdateStateRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order not found with the given id"));
        if (request.getState().equals(OrderState.PICKED_UP)){
            orderRepository.delete(order);
            log.debug("order deleted due the picked up state");
        } else {
            order.setState(request.getState());
            log.debug("updating order state: {}", order);
            orderRepository.save(order);
        }
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
        log.debug("order deleted with id: {}", id);
    }

    private boolean isOrderValidForCancellation(Order order) {
        return order.getState().equals(OrderState.WAITING);
    }
}
