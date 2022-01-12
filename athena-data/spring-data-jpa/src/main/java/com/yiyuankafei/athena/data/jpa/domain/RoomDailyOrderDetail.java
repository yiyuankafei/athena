package com.yiyuankafei.athena.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Peng.Yao
 * @create 2018-09-27 16:48
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "t_room_daily_order_detail")
public class RoomDailyOrderDetail {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "pid"
    )
    protected Long pid;
    protected String createBy;
    protected Date createTime;
    protected String modifyBy;
    protected Date modifyTime;


    private Integer feeType;

    private String feeName;
    private Date beginDate;
    private Date endDate;
    private Long price;
    private String unitName;

    //modify by yupf on 20190214
    private BigDecimal beginCount;
    private BigDecimal endCount;
    private BigDecimal personCounter;
    private BigDecimal totalCounter;
    private Integer sharePersonCount;

    private Long amount;

    private Integer relatedParty;

    private Long migrationBillDetailId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private RoomDailyOrder roomDailyOrder;

    /**
     * 房间市政账单编号
     */
    //private Long roomMunicipalCycleId;
}
