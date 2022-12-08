package com.sbaghrei.order.controller;

import com.sbaghrei.order.model.dto.OrderUpdateStateRequest;
import com.sbaghrei.order.model.order.Order;
import com.sbaghrei.order.model.order.OrderState;
import com.sbaghrei.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        return ResponseEntity.ok().body(orderService.findAll());
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        return ResponseEntity.ok().body(orderService.save(order));
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable(value = "id") long id){
        orderService.cancel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "{id}/update-state")
    public ResponseEntity<Void> updateState(
            @PathVariable(value = "id") long id,
            @RequestBody OrderUpdateStateRequest request){
        orderService.updateState(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(
            @PathVariable(value = "id") long id){
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
