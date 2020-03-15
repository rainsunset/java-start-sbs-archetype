#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.mapper;

import ${package}.dal.model.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Record 表 Mapper
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2020-03-15 17:58:48
 * @Version : ${version}
 */
@Repository
public interface RecordMapper {

    /**
     * 依据条件查找 Record 表 列表
     *
     * @param userId
     * @param num
     * @return
     */
    public List<Record> selectRecordList(
        @Param("userId")String userId, @Param("num")Integer num
    );

    /**
     * 依据Id查找 Record 表 详情
     *
     * @param recordId
     * @return
     */
    public Record getRecordDetail(@Param("recordId")Integer recordId);

    /**
     * 全量插入 Record 表
     *
     * @param record
     */
    public Integer fullInsertRecord(Record record);

    /**
     * 依据主键更新 Record 表
     *
     * @param record
     */
    public Integer updateRecord(Record record);

    /**
     * 依据主键删除 Record 表 记录
     *
     * @param recordIdArray
     * @param updatedBy
     */
    public Integer deleteRecords(@Param("recordIdArray") Integer[] recordIdArray,
                                            @Param("updatedBy") String updatedBy);
}
