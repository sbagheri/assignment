package com.sbaghrei.order.model.dto;

import com.sbaghrei.order.model.order.OrderState;
import lombok.Data;

@Data
public class OrderUpdateStateRequest {
    OrderState state;
}
