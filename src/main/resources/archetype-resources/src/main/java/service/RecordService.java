#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import com.rainsunset.common.bean.ResponseResult;
import ${package}.service.request.RecordBatchDelReqDTO;
import ${package}.service.request.RecordDetailReqDTO;
import ${package}.service.request.RecordListReqDTO;
import ${package}.service.request.RecordReqDTO;
import ${package}.service.response.RecordResDTO;

import java.util.List;

 /**
  * @Description: Record 表 接口
  * @Author: ligangwei
  * @Company rainsunset
  * @CreateDate: 2020-03-15 17:58:48
  * @Version : ${version}
  */
public interface RecordService {

    /**
     * 依据条件查找 Record 表 列表
     * @param recordListReqDTO
     * @return
     */
    public ResponseResult<List<RecordResDTO>> selectRecordList(RecordListReqDTO recordListReqDTO);

    /**
     * 依据Id查找 Record 表 详情
     * @param recordDetailReqDTO
     * @return
     */
    public ResponseResult<RecordResDTO> getRecordDetail(RecordDetailReqDTO recordDetailReqDTO);

    /**
     * 新增或更新 Record 表
     * @param recordReqDTO
     */
    public ResponseResult<Integer> addOrUpdateRecord(RecordReqDTO recordReqDTO);

    /**
     * 依据Id批量删除 Record 表 记录
     * @param recordBatchDelReqDTO 待删除记录Id数组
     */
    public ResponseResult<Integer> deleteRecords(RecordBatchDelReqDTO recordBatchDelReqDTO);
}
