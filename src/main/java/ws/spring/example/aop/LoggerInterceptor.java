package ws.spring.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

@Slf4j
public class LoggerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Object returnObj = invocation.proceed();
        log.info("Call - {} - arguments: {}, return: {}",methodName, Arrays.toString(arguments), returnObj);
        return returnObj;
    }
}
