package ws.spring.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author WindShadow
 * @version 2022-08-03.
 */

@Slf4j
@Aspect
//@Component
public class AspectJComponent {

    @Pointcut("execution(public * ws.spring.example.aop.SomeService.findUsername(String)) && args(userId)")
    public void pointcut(String userId) {

    }

    @Before(value = "pointcut(userId)", argNames = "point,userId")
    public void beforeAdvice(JoinPoint point, String userId) {

        Object[] args = point.getArgs();
        log.info("before 通知，args: {}", Arrays.toString(args));
    }

    @AfterThrowing(value = "pointcut(userId)", throwing = "ex", argNames = "point,userId,ex")
    public void exceptionAdvice(JoinPoint point, String userId, Exception ex) {

        log.error("throwing", ex);
    }

    @Around(value = "pointcut(userId)", argNames = "point,userId")
    public Object around(ProceedingJoinPoint point, String userId) throws Throwable {

        String name = point.getSignature().getName();
        log.info("around - 执行前，{}", name);
        Object returnValue = point.proceed();
        log.info("around - 执行后，{} - return: {}", name, returnValue);
        return returnValue;
    }

    @AfterReturning(pointcut = "pointcut(userId)", returning = "returnValue", argNames = "returnValue,userId")
    public void afterReturning(Object returnValue, String userId) {

        log.info("afterReturning - 返回前 - return，{}", returnValue);
    }
}
