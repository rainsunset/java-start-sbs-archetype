#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * Company
 * Copyright (C) 2004-2018 All Rights Reserved.
 */
package ${package}.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ligw
 * @version ${symbol_dollar}Id apiController.java, v 0.1 2018-07-09 17:03 ligw Exp ${symbol_dollar}${symbol_dollar}
 */
@Api(tags = "框架测试")
@RestController
@RequestMapping("/test")
public class apiController {

    @ApiOperation(value = "框架测试")
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String Hello(){
        return "hellow Spring Cloud";
    }

}
