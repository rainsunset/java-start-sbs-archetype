#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.aop;

import com.rainsunset.common.bean.BaseRequest;
import com.rainsunset.common.bean.GlobalErrorInfoException;
import com.rainsunset.common.bean.ResponseResult;
import com.rainsunset.common.bean.RestResultGenerator;
import ${package}.constant.Constants;
import ${package}.constant.GlobalErrorInfoEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 *
 * @version : 1.0
 * @description: 日志、异常统一处理
 * @author: ligangwei
 * @company rainsunset
 * @date: 2020 -02-15
 */
@Aspect
@Component
public class ServiceAspect {
    /**
     * Local validator factory bean
     */
    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    /**
     * ERROR_MSG
     */
    private static final String ERROR_MSG = "call [{}] [{}] [{}ms] [{}] [{}] RESPONSE:Result{} Cause:{}";

    /**
     * Log
     */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Service access response result.
     *
     * @param joinPoint the join point
     * @return the response result
     * @author : ligangwei / 2020-02-15
     */
    @Around("execution(public * ${package}.service.impl.*.*(..))")
    public ResponseResult serviceAccess(ProceedingJoinPoint joinPoint) {
        // 计时开始
        StopWatch clock = new StopWatch();
        clock.start();

        ResponseResult response = null;
        String clazzName = joinPoint.getTarget().getClass().getSimpleName(); //获得类名
        //获得方法名
        String methodName = joinPoint.getSignature().getName();
        //获得参数列表
        Object[] args = joinPoint.getArgs();

        if (args != null) {
            //初始化日志ID
            initMDC(args);
            Object argObject = args[0];
            log.info("call [{}] [{}] PARAMETER:[{}]", clazzName, methodName, argObject);
            try {
                //参数校验
                validate(argObject);
                //业务执行
                response = (ResponseResult) joinPoint.proceed();
                // 计时结束
                clock.stop();
                log.info("call [{}] [{}] [{}ms] [{}] [{}] RESPONSE:Result{}", clazzName, methodName,
                        clock.getTotalTimeMillis(), "Success", response.getErrorCode(), response);
            } catch (GlobalErrorInfoException e) {
                response = RestResultGenerator.genResult(e);
                clock.stop();
                log.info(ERROR_MSG, clazzName, methodName, clock.getTotalTimeMillis(), "Success",
                        response.getErrorCode(), response, e.getCause());
            } catch (Throwable throwable) {
                response = new ResponseResult();
                response = RestResultGenerator.genResult(GlobalErrorInfoEnum.DEMOEC_500000);
                clock.stop();
                log.error(ERROR_MSG, clazzName, methodName, clock.getTotalTimeMillis(), "Failure",
                        response.getErrorCode(), response, throwable.getCause());
            }
        }
        return response;
    }

    /**
     * 参数校验
     *
     * @param <T>    the type parameter
     * @param object 校验对象
     * @param groups the groups
     * @throws GlobalErrorInfoException
     * @author : ligangwei / 2020-02-15
     */
    private <T> void validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> constraintViolations = localValidatorFactoryBean.validate(
                object, groups);
        if (constraintViolations != null && !constraintViolations.isEmpty()) {
            ConstraintViolation c = constraintViolations.iterator().next();
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.DEMOEC_415000);
        }
    }

    /**
     * 初始化日志ID
     * @param args 入参
     */
    private String initMDC(Object[] args) {
        if (args.length > 0) {
            Object argObject = args[0];
            if (argObject instanceof BaseRequest) {
                String traceLogId = ((BaseRequest) argObject).getTraceLogId();
                if (StringUtils.isEmpty(traceLogId)) {
                    Integer random = (int)(100 * Math.random());
                    StringBuilder stringBuilder = new StringBuilder("spec_");
                    stringBuilder.append(System.currentTimeMillis()).append("_").append(random);
                    traceLogId = stringBuilder.toString();
                    ((BaseRequest) argObject).setTraceLogId(traceLogId);
                }
                MDC.put(Constants.TRACE_LOG_ID, traceLogId);
                return traceLogId;
            }
        }
        return null;
    }

    /**
     * 获取耗时
     *
     * @param startTime 开始时间
     * @return 耗时(单位毫秒) long
     * @author : ligangwei / 2020-02-15
     */
    private long getTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

}