package com.zxw.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class KnlAccountVo {

    private Long guid;

    private String knlName;

    private BigDecimal knlFee;

    private String userCreate;

    private LocalDate gmtCreate;

    private Set<Account> accountSet;
}
