package com.zxw.demo.mapper;

import com.zxw.demo.entity.KnlTestVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class KnlTestMapperTest {

    @Autowired
    private KnlTestMapper knlTestMapper;


    @Test
    void findByGuid() {
        List<KnlTestVo> list = knlTestMapper.findByParentId(1L);
        System.out.println("list = " + list);
    }
}