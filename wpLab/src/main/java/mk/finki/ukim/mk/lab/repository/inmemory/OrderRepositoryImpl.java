package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.staticdata.DataHolder;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(DataHolder.orders);
    }

    @Override
    public Order save(String pizzaType, String pizzaSize, String clientName, String address) {

        Order order = new Order(pizzaType, pizzaSize, clientName, address, DataHolder.getNextId());
        this.findById(order.getOrderId()).ifPresent(DataHolder.orders::remove);
        DataHolder.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return DataHolder.orders.stream()
                .filter(order -> order.getOrderId()==id)
                .findFirst();
    }
}
