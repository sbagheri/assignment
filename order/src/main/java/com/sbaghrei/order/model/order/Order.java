package com.sbaghrei.order.model.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    Long id;
    double price;
    OrderType type;
    MilkType milkType;
    CoffeeSize coffeeSize;
    CoffeeType coffeeType;
    OrderState state;
}
