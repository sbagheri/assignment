package com.sbaghrei.barista.model.helper;

import com.sbaghrei.barista.model.order.Order;
import com.sbaghrei.barista.model.order.OrderState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderInPreparationState implements OrderStateHelper{
    @Override
    public void doBaristaAction(Order order) throws InterruptedException {
        log.info("Order: {}, In preparation state...", order.getId());
        // Sample 2 Seconds delay for waiting state as a fictive task
        Thread.sleep(2000);
        log.info("Order: {}, Preparation done!", order.getId());
        order.setCurrentState(new OrderFinishedState());
        order.setState(OrderState.FINISHED);
    }
}
