#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import com.rainsunset.common.bean.ResponseResult;
import ${package}.service.RecordService;
import ${package}.service.request.RecordBatchDelReqDTO;
import ${package}.service.request.RecordDetailReqDTO;
import ${package}.service.request.RecordListReqDTO;
import ${package}.service.request.RecordReqDTO;
import ${package}.service.response.RecordResDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


 /**
  * @Description: Record 表 API
  * @Author: ligangwei
  * @Company rainsunset
  * @CreateDate: 2020-03-15 17:58:48
  * @Version : ${version}
  */
@Api(tags = "Record 表接口")
@RestController
@RequestMapping("/demo/record")
public class RecordController{

    @Autowired
    private RecordService recordService;

    /**
     * 依据条件查找 Record 表 列表
     *
     * @param recordListReqDTO
     * @return
     */
    @ApiOperation("依据条件查找 Record 表 列表")
    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public ResponseResult<List<RecordResDTO>> findList(@RequestBody RecordListReqDTO recordListReqDTO) {
        ResponseResult<List<RecordResDTO>> response = recordService.selectRecordList(recordListReqDTO);
        return response;
    }

    /**
     * 依据Id查询 Record 表 详情
     *
     * @param recordDetailReqDTO
     * @return
     */
    @ApiOperation("依据Id查询 Record 表 详情")
    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public ResponseResult<RecordResDTO> getRecordDetail(@RequestBody RecordDetailReqDTO recordDetailReqDTO) {
        ResponseResult<RecordResDTO> response = recordService.getRecordDetail(recordDetailReqDTO);
        return response;
    }

    /**
     * 新增或更新 Record 表
     *
     * @param recordReqDTO
     * @return
     */
    @ApiOperation("新增或更新 Record 表")
    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public ResponseResult<Integer> addOrUpdate(@RequestBody RecordReqDTO recordReqDTO) {
        ResponseResult<Integer> response = recordService.addOrUpdateRecord(recordReqDTO);
        return response;
    }

    /**
     * 依据Id批量删除 Record 表
     *
     * @param recordBatchDelReqDTO 待删除记录Id数组
     * @return
     */
    @ApiOperation("依据Id批量删除 Record 表")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseResult<Integer> delete(@RequestBody RecordBatchDelReqDTO recordBatchDelReqDTO) {
        ResponseResult<Integer> response = recordService.deleteRecords(recordBatchDelReqDTO);
        return response;
    }

}

