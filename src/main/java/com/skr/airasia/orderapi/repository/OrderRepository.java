package com.skr.airasia.orderapi.repository;

import com.skr.airasia.orderapi.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Order details crud
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderDto, Long> {
}
