package com.sbaghrei.barista.repository;

import com.sbaghrei.barista.model.dto.OrderUpdateStateRequest;
import com.sbaghrei.barista.model.order.Order;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class OrderRepository {

    private final WebClient orderClient;

    public OrderRepository(@Qualifier("OrderClient") WebClient orderClient) {
        this.orderClient = orderClient;
    }

    public List<Order> getOrders() {
        return orderClient.get()
                .uri("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Order.class)
                .collectList()
                .block();
    }

    public Mono<Order> updateState(long id, OrderUpdateStateRequest request) {
        return orderClient.put()
                .uri("/orders/" + id + "/update-state")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(Order.class);
    }

    public Mono<Order> deleteOrder(long id) {
        return orderClient.delete()
                .uri("/orders/" + id)
                .retrieve()
                .bodyToMono(Order.class);
    }
}
