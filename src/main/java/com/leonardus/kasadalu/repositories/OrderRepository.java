package com.leonardus.kasadalu.repositories;

import com.leonardus.kasadalu.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
