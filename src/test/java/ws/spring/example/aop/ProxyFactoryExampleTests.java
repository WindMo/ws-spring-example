package ws.spring.example.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * {@link ProxyFactory}代理工厂使用示例，ProxyFactory封装了JDK动态代理和CGLIB代理
 *
 * @author WindShadow
 * @version 2022-07-31.
 * @see ProxyFactory
 */

public class ProxyFactoryExampleTests {

    private final SomeServiceImpl serviceImpl = new SomeServiceImpl();
    private final LoggerInterceptor loggerInterceptor = spy(new LoggerInterceptor());

    /**
     * 通过代理实现接口
     */
    @Test
    void proxyImplInterfaceTest() {

        SomeService someService = ProxyFactory.getProxy(SomeService.class, new ReturnValueAop());
        String username = someService.findUsername("123456");
        Assertions.assertEquals("abcdefg", username);
    }


    /**
     * 接口方法增强
     */
    @Test
    void adviceInterfaceMethodTest() throws Throwable {

        ProxyFactory proxyFactory = new ProxyFactory(serviceImpl);
        proxyFactory.addAdvice(loggerInterceptor);
        SomeService someService = (SomeService) proxyFactory.getProxy();
        // 代理接口
        proxyFactory.setProxyTargetClass(false);
        Assertions.assertFalse(someService instanceof SomeServiceImpl);
        someService.findUsername("123456");
        // 日志增强被调用
        verify(loggerInterceptor, times(1)).invoke(any());
    }

    /**
     * 类方法增强
     */
    @Test
    void adviceClassMethodTest() throws Throwable {

        ProxyFactory proxyFactory = new ProxyFactory(serviceImpl);
        proxyFactory.addAdvice(loggerInterceptor);
        // 代理目标类
        proxyFactory.setProxyTargetClass(true);
        SomeService someService = (SomeService) proxyFactory.getProxy();
        Assertions.assertTrue(someService instanceof SomeServiceImpl);
        someService.findUsername("123456");
        // 日志增强被调用
        verify(loggerInterceptor, times(1)).invoke(any());
    }
}
