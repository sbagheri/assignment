package com.sbaghrei.barista.model.helper;

import com.sbaghrei.barista.model.order.Order;
import com.sbaghrei.barista.model.order.OrderState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderPickedUpState implements OrderStateHelper{
    @Override
    public void doBaristaAction(Order order) throws InterruptedException {
        log.info("Order: {}, Nothing to do in picked up state", order.getId());
    }
}
