package com.sbaghrei.barista.model.helper;

import com.sbaghrei.barista.model.order.Order;
import com.sbaghrei.barista.model.order.OrderState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderFinishedState implements OrderStateHelper{
    @Override
    public void doBaristaAction(Order order) throws InterruptedException {
        log.info("Order: {}, Finished state...", order.getId());
        // Sample 100ms delay for waiting state as a fictive task
        Thread.sleep(100);
        log.info("Order: {}, Finishing done!", order.getId());
        order.setCurrentState(new OrderPickedUpState());
        order.setState(OrderState.PICKED_UP);
    }
}
