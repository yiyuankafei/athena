package com.yiyuankafei.athena.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Peng.Yao
 * @create 2018-09-27 17:06
 */
@Data
@Entity
@Accessors(chain = true)
@Table(name = "t_room_daily_order_extend_info")
public class RoomDailyOrderExtendInfo {

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private RoomDailyOrder roomDailyOrder;

    private Date periodBeginDate;

    private Date periodEndDate;

    /**
     * 合同ID
     */
    private Long contractId;

    /**
     * 账期归类(即账单账期的归类标记, 以前叫做账单类型): 1 - 普通租金账单, 2 - 首付款账单, 3 - 尾期账单
     */
    private Integer orderPeriodMarker;

    /**
     * 是否入客账
     */
    private Boolean isAccountEntry;

    /**
     * 合同账期D
     */
    private Long contractPeriodId;


    /** 账单创建类型:1-人工录入;2-系统自动生成;3-数据迁移, 具体定义看模型文档 */
    private Integer createType;










    /** 账单创建类型枚举值:1-人工录入;2-系统自动生成;3-数据迁移, 具体定义看模型文档 */
    @Getter
    public enum CreateTypeEnum {


        /**
         * 申请开票状态
         */
        CREATETYPE_MANUAL_INPUT(1, "人工录入"),
        CREATETYPE_SYSTEM_GENERATE(2, "系统自动生成"),
        CREATETYPE_MIGRATION(3, "数据迁移"),

        ;


        public static final Map<Integer, CreateTypeEnum> CREATE_TYPE_MAP = new HashMap<>();

        private Integer code;

        private String name;




        static {
            CREATE_TYPE_MAP.put(CREATETYPE_MANUAL_INPUT.getCode(), CREATETYPE_MANUAL_INPUT);
            CREATE_TYPE_MAP.put(CREATETYPE_SYSTEM_GENERATE.getCode(), CREATETYPE_SYSTEM_GENERATE);
            CREATE_TYPE_MAP.put(CREATETYPE_MIGRATION.getCode(), CREATETYPE_MIGRATION);
        }


        CreateTypeEnum(Integer code, String name) {
            this.code = code;
            this.name = name;
        }



        /**
         * 根据枚举值获取对应的枚举值名称
         * @param code
         * @return  对应状态的枚举类
         */
        public static CreateTypeEnum getNameByCode(Integer code){
            return CREATE_TYPE_MAP.get(code);
        }
    }
}