package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    List<Order> getAllOrders();
    Order save(String pizzaType, String pizzaSize, String clientName, String address);
    Optional<Order> findById(Long id);

}
