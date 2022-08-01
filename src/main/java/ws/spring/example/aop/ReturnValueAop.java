package ws.spring.example.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

public class ReturnValueAop implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        ReturnValue returnValue = method.getAnnotation(ReturnValue.class);
        Assert.state(returnValue != null, "Not found ReturnValue");
        return returnValue.value();
    }
}
