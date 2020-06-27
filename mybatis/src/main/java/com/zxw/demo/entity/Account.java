package com.zxw.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
    * account
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    /**
    * id
    */
    private Integer id;

    /**
    * name
    */
    private String name;

    /**
    * money
    */
    private BigDecimal money;

    private Long kId;

    private static final long serialVersionUID = 1L;
}