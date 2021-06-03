package com.example.work.loging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
   private Logger logger= LoggerFactory.getLogger(LoggingAspect.class);

//    @Pointcut("@annotation(Loggable)")//can add pointCut with annotation to method
//    public void executeLogging(){
//
//    }

    @Before("@annotation(com.example.work.loging.Loggable)")//can add pointCut to advice annotations
    public void beforeMethodCall(JoinPoint joinPoint){
        logger.info("Method "+joinPoint.getSignature().getName()+" was called");
    }
    @AfterReturning(pointcut = "execution(public * com.example.work.controller.ProductController.*(..))", returning = "result")//one more pointCut adding example
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info( "возвращенное значение: " + result.toString());
    }

}
