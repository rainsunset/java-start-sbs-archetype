#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.request;

import com.rainsunset.common.bean.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

 /**
  * @Description: 查询Record 表详情请求体
  * @Author: ligangwei
  * @Company rainsunset
  * @CreateDate: 2020-03-15 17:58:48
  * @Version : ${version}
  */
@Data
@ToString(callSuper = true)
public class RecordDetailReqDTO extends BaseRequest {

    @ApiModelProperty(value = "记录Id")
    @NotBlank(message = "记录Id不能为空")
    private Integer recordId;

}



