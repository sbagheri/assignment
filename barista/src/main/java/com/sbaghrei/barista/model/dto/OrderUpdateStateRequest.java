package com.sbaghrei.barista.model.dto;

import com.sbaghrei.barista.model.order.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateStateRequest {
    OrderState state;
}
