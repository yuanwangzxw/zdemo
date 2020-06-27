package com.zxw.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxw.mybatis.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    int updateBatch(List<Account> list);

    int batchInsert(@Param("list") List<Account> list);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    //@Param对应传参
    @Select("select * from account limit #{pageNo} , #{pageSize} ")
    List<Account> page(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);
    //对象中的属性对应传参
    @Select({"select * from account where id=#{id}",})
    Account obtain(AccountQuery accountQuery);

    @Accessors
    @AllArgsConstructor
    @Data
    class AccountQuery{
        private  Integer id;
    }

    @ResultType(String.class)
    @Select("select id from account")
    List<String> idList();
}