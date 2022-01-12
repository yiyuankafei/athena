package com.yiyuankafei.athena.data.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author Geeson
 * @Date 2018-9-17
 * @Description: 订单基础类
 */
@Data
@Entity
@EqualsAndHashCode
@Table(name = "t_order")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Accessors(chain = true)
@DiscriminatorColumn(name = "order_type")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String ORDER_TYPE_LONG_PRE_ORDER = "LongPreOrder";
    public static final String ORDER_TYPE_ROOM_DAILY_ORDER = "RoomDailyOrder";
    public static final String ORDER_TYPE_BATCH_ORDER = "BatchOrder";

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


    /**
     * 业务线
     */
    private String bizLine;

    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 接待单ID
     */
    private Long receptionId;
    /**
     * 联系人名称
     */
    private String contactName;
    /**
     * 联系人电话
     */
    private String contactPhone;
    /**
     * 订单总额
     */
    private Long amount;

    /**
     * 最晚支付时间(应收截止日期----支付晚于此时间刚为逾期)
     */
    private Date latestPayTime;

    /**
     * 可收款日期(应收开始日期,租金账单为账单周期开始时间前7天，能源账单、其他账单、退租账单为出账单当天）
     */
    private Date receivableTime;

    /**
     * 实际支付完成时间
     */
    private Date finishPayTime;

    /**
     * 备注
     */
    private String remark;

    protected void afterPaymentOver(){
        throw new UnsupportedOperationException();
    }

    public boolean canCancelPay(){
        throw new UnsupportedOperationException();
    }


    public enum PaymentResultCode{
        /**
         * 子类型
         */
        SUCCESS(0),REPEAT(1),OVERAMOUNT(2),NOTCANPAY(3),NOTFOUND(4);

        public int code;
        PaymentResultCode(int code){
            this.code=code;
        }
    }

}
