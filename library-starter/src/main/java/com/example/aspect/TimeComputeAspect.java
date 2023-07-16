package com.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 陈磊
 * @version 2.0
 * @date 2023/6/11 23:54
 */
@Aspect
@Component
@Slf4j
public class TimeComputeAspect {
    /**
     * execution表达式
     * 第一个*号表示修饰符是任意的返回类型
     * 第二个*号表示是任意的类
     * 第三个参数列表表示是任意的参数类型
     */

    @Pointcut(value = "execution(public * com.example..*.*(..)) && !execution(public * com.example.service.UserServiceImpl.*(..))")
    public void pointCut() {

    }

//    @Before(value = "pointCut()")
//    public void before(JoinPoint joinPoint) {
//        log.info("当前处理类: "+joinPoint.getTarget().getClass().getName());
//    }

    @Around(value = "pointCut()")
    public Object afterReturn(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        } finally {
            log.info("\nclass: ("+joinPoint.getTarget().getClass().getName()+")method: ("+methodName+")执行耗时: "+(System.currentTimeMillis() - start) +"ms");
        }
    }
}
