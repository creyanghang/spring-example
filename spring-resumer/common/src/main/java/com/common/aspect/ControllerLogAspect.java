package com.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.common.cache.SysInfoSession;
import com.common.cache.UsersLoginSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @description AOP
 * @auth yh
 * @date 2022/5/29
 */
@Slf4j
@Aspect
@Order(5)
@Component
public class ControllerLogAspect {
    /**
     * @Before  在切点方法之前执行
     * @After  在切点方法之后执行
     * @AfterReturning 切点方法返回后执行
     * @AfterThrowing 切点方法抛异常执行
     * @Around 属于环绕增强，能控制切点执行前，执行后，，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
     */
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    private ThreadLocal<String> requestMsg = new ThreadLocal<>();

    /**
     * 定义切点，即事件入口
     */
    @Pointcut("execution(public * com.resumer.controller.*Controller.*(..))")
    public void webLog() {
    }

    /**
     * 在切点方法之前执行
     * @author  yh
     * @date  2022/5/29
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        String servletPath = request.getServletPath();

        requestMsg.set("-- uri:"+servletPath+",  params:"+ Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 在上面定义的切面方法返回后执行该方法，可以捕获返回对象或者对返回对象进行增强
     * returning：自定义的变量，表示目标方法的返回值的，自定义变量名必须和通知方法的形参名一样
     *
     * @author  yh
     * @date  2022/5/29
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        String resultString = JSONObject.toJSONString(ret);
        log.info("Result {},  returnMessage:{} ,spendTime :{}", requestMsg.get(), resultString.length() < 500 ? resultString : "resultString length greater 500", (System.currentTimeMillis() - startTime.get()));
        //清理缓存
        startTime.remove();
        requestMsg.remove();
        SysInfoSession.delSysInfo();
        UsersLoginSession.delLoginInfo();
    }

    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *            对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     *  pointcut/value:这两个属性的作用是一样的，它们都属于指定切入点对应的切入表达式。一样既可以是已有的切入点，也可直接定义切入点表达式。当指定了pointcut属性值后，value属性值将会被覆盖。
     *  returning:该属性指定一个形参名，用于表示afterReturning方法中可定义与此同名的形参，该形参可用于访问目标方法的返回值。除此之外，在afterReturning方法中定义该形参（代表目标方法的返回值）时指定的类型，会限制目标方法必须返回指定类型的值或没有返回值。
     * @param joinPoint 切面方法的信息   
     * @param exception 异常
     */
    @AfterThrowing(value = "webLog()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        log.error("Result {} ,spendTime :{}, execption:{}", requestMsg.get(), (System.currentTimeMillis() - startTime.get()), exception.toString());

        //清理缓存
        startTime.remove();
        requestMsg.remove();
        SysInfoSession.delSysInfo();
        UsersLoginSession.delLoginInfo();
    }

}