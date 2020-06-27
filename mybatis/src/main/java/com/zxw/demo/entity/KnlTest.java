package com.zxw.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class KnlTest implements Serializable {
    private Long guid;

    private String knlName;

    private BigDecimal knlFee;

    private String userCreate;

    private LocalDate gmtCreate;

    private static final long serialVersionUID = 1L;
}