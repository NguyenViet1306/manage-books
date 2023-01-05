package com.bezkoder.springjwt.service.imlp;

import com.bezkoder.springjwt.models.Books;
import com.bezkoder.springjwt.models.Orders;
import com.bezkoder.springjwt.repository.OrderRepository;
import com.bezkoder.springjwt.service.IOrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Orders save(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
