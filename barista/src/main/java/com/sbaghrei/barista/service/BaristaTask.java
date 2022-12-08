package com.sbaghrei.barista.service;

import com.sbaghrei.barista.model.dto.OrderUpdateStateRequest;
import com.sbaghrei.barista.model.order.Order;
import com.sbaghrei.barista.model.order.OrderState;
import com.sbaghrei.barista.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class BaristaTask {

    private final OrderRepository orderRepository;

    public BaristaTask(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 5 seconds delay after finishing a task
    @Scheduled(fixedDelay = 5 * 1000)
    public void doBarista() throws InterruptedException {
        List<Order> orders = orderRepository.getOrders();
        log.debug("There are {} orders to be processed", orders.size());
        for (Order order: orders){
            order.getCurrentState().doBaristaAction(order);
            if (order.getState().equals(OrderState.PICKED_UP)){
                Mono<Order> res = orderRepository.deleteOrder(order.getId());
                res.doOnSuccess(x -> log.debug("Order: {}, deleted after pick up", order.getId())).subscribe();
            } else {
                Mono<Order> res = orderRepository.updateState(
                        order.getId(),
                        new OrderUpdateStateRequest(order.getState()));
                res.doOnSuccess(x -> log.debug("Order: {}, state updated with state: {}", order.getId(), order.getState())).subscribe();
            }
        }
    }
}
