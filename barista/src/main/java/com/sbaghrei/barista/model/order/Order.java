package com.sbaghrei.barista.model.order;

import com.sbaghrei.barista.model.helper.OrderStateFactory;
import com.sbaghrei.barista.model.helper.OrderStateHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Long id;
    double price;
    OrderType type;
    MilkType milkType;
    CoffeeSize coffeeSize;
    CoffeeType coffeeType;
    OrderState state;

    OrderStateHelper currentState;

    public OrderStateHelper getCurrentState(){
        if (currentState == null){
            currentState = OrderStateFactory.getState(state);
        }
        return currentState;
    }
}
