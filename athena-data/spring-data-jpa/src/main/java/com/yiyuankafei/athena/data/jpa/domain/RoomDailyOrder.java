package com.yiyuankafei.athena.data.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.*;

/**
 * @author Peng.Yao
 * @create 2018-09-27 16:37
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("RoomDailyOrder")
public class RoomDailyOrder extends Order {


    private static final Logger logger = LoggerFactory.getLogger(RoomDailyOrder.class);

    @Enumerated(EnumType.STRING)
    private SubType subType;

    @OneToOne(cascade = {CascadeType.PERSIST}, mappedBy = "roomDailyOrder", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private RoomDailyOrderExtendInfo extendInfo;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    @EqualsAndHashCode.Exclude
    private Set<RoomDailyOrderDetail> roomDailyOrderDetails;

    @Enumerated(EnumType.STRING)
    private Status status;


    public enum SubType {
        /**
         * 子类型
         */
        RentalOrder("租金账单"), EnergyOrder("能源账单"), CheckOutOrder("退租结算账单"), OtherOrder("其它账单");

        public String desc;

        SubType(String desc) {
            this.desc = desc;
        }

        public static final Map<String, SubType> SUB_TYPE_MAP = new HashMap<>();


        static {
            SUB_TYPE_MAP.put(RentalOrder.name(), RentalOrder);
            SUB_TYPE_MAP.put(EnergyOrder.name(), EnergyOrder);
            SUB_TYPE_MAP.put(CheckOutOrder.name(), CheckOutOrder);
            SUB_TYPE_MAP.put(OtherOrder.name(), OtherOrder);
        }
    }

    public enum Status {
        PAY("待付款"), PAD("已付款"), CAL("已取消"), FAL("已置坏账"), DEL("已删除");
        public String desc;

        Status(String desc) {
            this.desc = desc;
        }

        public static Status[] DefaultValidStatusOrder = new Status[]{PAY, PAD};
    }

    public RoomDailyOrder() {

    }


    public boolean isEffective() {
        if (status == null) {
            return false;
        }
        if (status.equals(Status.PAY) || status.equals(Status.PAD)) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean canCancel() {
        if (Status.PAY.equals(status)) {
            return true;
        } else {
            return false;
        }
    }



    public boolean isRentalOrder() {
        return SubType.RentalOrder == subType;
    }

}