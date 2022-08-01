package ws.spring.example.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ws.spring.example.WsSpringExampleApplicationTests;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

public class BeanNameAutoProxyCreatorExampleTests extends WsSpringExampleApplicationTests {

    @Qualifier("beanNameAutoProxyCreatorInterceptor")
    @SpyBean
    private LoggerInterceptor loggerInterceptor;

    @Qualifier("beanNameAutoProxyCreatorSomeService")
    @Autowired
    private SomeService service;

    @Test
    void proxyFactoryBeanTest() throws Throwable {

        Assertions.assertTrue(AopUtils.isAopProxy(service));
        service.findUsername("123456");
        // 日志增强被调用
        verify(loggerInterceptor, times(1)).invoke(argThat(t -> true));
    }
}
