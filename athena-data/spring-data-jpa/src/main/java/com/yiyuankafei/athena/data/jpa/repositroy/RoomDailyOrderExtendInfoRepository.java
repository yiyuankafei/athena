package com.yiyuankafei.athena.data.jpa.repositroy;

import com.yiyuankafei.athena.data.jpa.domain.RoomDailyOrderExtendInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDailyOrderExtendInfoRepository extends JpaRepository<RoomDailyOrderExtendInfo, Long> {
}
