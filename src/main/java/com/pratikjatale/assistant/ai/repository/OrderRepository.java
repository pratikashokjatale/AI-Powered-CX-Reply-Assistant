package com.pratikjatale.assistant.ai.repository;

import com.pratikjatale.assistant.ai.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumberIgnoreCase(String orderNumber);
    List<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId);
}
