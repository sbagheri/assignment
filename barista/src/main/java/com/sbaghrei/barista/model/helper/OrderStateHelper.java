package com.sbaghrei.barista.model.helper;

import com.sbaghrei.barista.model.order.Order;

public interface OrderStateHelper {
    void doBaristaAction(Order order) throws InterruptedException;
}
