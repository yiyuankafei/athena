package com.yiyuankafei.athena.data.jpa.repositroy;

import com.yiyuankafei.athena.data.jpa.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
