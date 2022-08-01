package ws.spring.example.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.SpyBean;
import ws.spring.example.WsSpringExampleApplicationTests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

public class ProxyFactoryBeanExampleTests extends WsSpringExampleApplicationTests {

    @Qualifier("proxyFactoryBeanLoggerInterceptor")
    @SpyBean
    private LoggerInterceptor loggerInterceptor;

    @Qualifier("proxyImplInterface")
    @Autowired
    private SomeService service;

    @Qualifier("proxyFactoryBeanSomeService")
    @Autowired
    private SomeService service2;

    @Test
    void proxyFactoryBeanTest() throws Throwable {

        Assertions.assertTrue(AopUtils.isAopProxy(service));
        Assertions.assertFalse(AopUtils.isAopProxy(service2));
        service.findUsername("123456");
        // 日志增强被调用
        verify(loggerInterceptor, times(1)).invoke(argThat(t -> true));
    }
}
