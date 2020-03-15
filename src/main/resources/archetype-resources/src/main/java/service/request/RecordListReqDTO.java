#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.request;

import com.rainsunset.common.bean.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

 /**
  * @Description: 查询Record 表列表请求体
  * @Author: ligangwei
  * @Company rainsunset
  * @CreateDate: 2020-03-15 17:58:48
  * @Version : ${version}
  */
@Data
@ToString(callSuper = true)
public class RecordListReqDTO extends BaseRequest {

    @ApiModelProperty(value = "记录Id")
    private Integer recordId;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "记录值")
    private Integer num;

}



