#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: Record 表 Model
 * @Author: ligangwei
 * @Company rainsunset
 * @CreateDate: 2020-03-15 17:58:48
 * @Version : ${version}
 */
@Data
@ApiModel(description = "Record 表")
public class Record {

    @ApiModelProperty(value = "记录Id")
    private Integer recordId;

    @ApiModelProperty(value = "用户Id")
    private String userId;

    @ApiModelProperty(value = "记录值")
    private Integer num;

}



