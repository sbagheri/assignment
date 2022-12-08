package com.sbaghrei.barista.model.helper;

import com.sbaghrei.barista.model.order.Order;
import com.sbaghrei.barista.model.order.OrderState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderWaitingState implements OrderStateHelper{
    @Override
    public void doBaristaAction(Order order) throws InterruptedException {
        // Sample 1 Seconds delay for waiting state
        log.info("Order: {}, Waiting...", order.getId());
        Thread.sleep(1000);
        log.info("Order: {}, Waiting completed!", order.getId());
        order.setCurrentState(new OrderInPreparationState());
        order.setState(OrderState.IN_PREPARATION);
    }
}
