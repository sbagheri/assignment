package com.sbaghrei.barista.model.helper;

import com.sbaghrei.barista.model.order.OrderState;

public class OrderStateFactory {

    public static OrderStateHelper getState(OrderState state){
        return switch (state) {
            case IN_PREPARATION -> new OrderInPreparationState();
            case FINISHED -> new OrderFinishedState();
            case PICKED_UP -> new OrderPickedUpState();
            default -> new OrderWaitingState();
        };
    }
}
