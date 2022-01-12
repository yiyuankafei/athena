package com.yiyuankafei.athena.data.jpa.repositroy;

import com.yiyuankafei.athena.data.jpa.domain.RoomDailyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDailyOrderRepository extends JpaRepository<RoomDailyOrder, Long> {
}
