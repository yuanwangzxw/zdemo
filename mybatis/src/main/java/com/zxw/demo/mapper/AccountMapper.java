package com.zxw.demo.mapper;

import com.zxw.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Account record);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Account record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Account selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int batchInsert(@Param("list") List<Account> list);

    @Select("select * from account where k_id=#{kId} ")
    List<Account> findByKid(@Param("kId") Long kId);
}