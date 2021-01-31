package com.zxw.consu.controller;

import com.zxw.consu.config.CrmSetVal;
import com.zxw.consu.controller.ConvertController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ConvertRespVo {

    private String enableStatus;

    @CrmSetVal(beanClass = ConvertController.class,method = "getVal",param = "enableStatus",targetField = "enableStatusName")
    private String enableStatusName;
}
