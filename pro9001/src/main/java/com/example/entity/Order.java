package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order.`order`")
public class Order implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "order_name")
    private String orderName;

    @TableField(value = "pay_fee")
    private BigDecimal payFee;

    @TableField(value = "gmt_create")
    private LocalDateTime gmtCreate;

    @TableField(value = "gmt_update")
    private LocalDateTime gmtUpdate;

    @TableField(value = "create_user")
    private String createUser;

    @TableField(value = "update_user")
    private String updateUser;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NAME = "order_name";

    public static final String COL_PAY_FEE = "pay_fee";

    public static final String COL_GMT_CREATE = "gmt_create";

    public static final String COL_GMT_UPDATE = "gmt_update";

    public static final String COL_CREATE_USER = "create_user";

    public static final String COL_UPDATE_USER = "update_user";
}