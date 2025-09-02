package org.example.onlineShopping;

public interface OrderState {
    void ship(Order order);
    void deliver(Order order);
    void cancel(Order order);
}