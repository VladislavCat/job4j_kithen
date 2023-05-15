package ru.job4j.kitchen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.model.Order;
import ru.job4j.kitchen.repository.OrderDataRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDataRepository odr;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaTemplate<Object, Order> kafkaTemplate;

    @KafkaListener(topics = "cooked_order")
    public void sendOrder(Order order) {
        odr.save(order);
        order = setStatus(order);
        kafkaTemplate.send("cooked_order", order);
    }

    public Order setStatus(Order order) {
        double d = Math.random() * 10;
        if (d <= 6) {
            order.setStatusOrder("Заказ готов");
        } else if (d > 6) {
            order.setStatusOrder("На кухне возникли проблемы");
        }
        return order;
    }
 }
