package com.zxw.demo.mapper;

import com.zxw.demo.entity.KnlAccountVo;
import com.zxw.demo.entity.KnlTest;
import com.zxw.demo.entity.KnlTestVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface KnlTestMapper {
    /**
     * delete by primary key
     *
     * @param guid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long guid);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(KnlTest record);

    int insertOrUpdate(KnlTest record);

    int insertOrUpdateSelective(KnlTest record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(KnlTest record);

    /**
     * select by primary key
     *
     * @param guid primary key
     * @return object by primary key
     */
    KnlTest selectByPrimaryKey(Long guid);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(KnlTest record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(KnlTest record);

    int updateBatch(List<KnlTest> list);

    int batchInsert(@Param("list") List<KnlTest> list);

    @Results({
            @Result(column = "guid",property = "guid"),
            @Result(column = "guid",property = "knlTestSet",many = @Many(select = "findByParentId"))
    })
    @Select("select * from knl_test where parent_id=#{xxx}")
    List<KnlTestVo> findByParentId(Long parentId);


    /**
     * 通过column字段，调用其它mapper的接口，所以这写了两个相同的result映射
     */
    @Results({
            @Result(column = "guid",property = "guid"),
            @Result(column = "guid",property = "accountSet"
                    ,many = @Many(select = "com.zxw.demo.mapper.AccountMapper.findByKid",fetchType = FetchType.LAZY))
    })
    @Select("select * from knl_test where guid=${guid}")
    List<KnlAccountVo> findByGuid(@Param("guid") Long guid);

}