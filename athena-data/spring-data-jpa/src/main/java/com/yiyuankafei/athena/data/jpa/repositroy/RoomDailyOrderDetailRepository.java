package com.yiyuankafei.athena.data.jpa.repositroy;

import com.yiyuankafei.athena.data.jpa.domain.RoomDailyOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDailyOrderDetailRepository extends JpaRepository<RoomDailyOrderDetail, Long> {
}
