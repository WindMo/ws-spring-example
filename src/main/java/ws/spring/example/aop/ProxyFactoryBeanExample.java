package ws.spring.example.aop;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@link ProxyFactoryBean}利用{@link FactoryBean}机制将代理对象成为一个bean
 *
 * @author WindShadow
 * @version 2022-07-31.
 */

@Configuration(proxyBeanMethods = false)
public class ProxyFactoryBeanExample {

    @Bean("proxyFactoryBeanSomeService")
    public SomeService someService() {

        return new SomeServiceImpl();
    }

    /**
     * 使用Primary注解将代理对象的优先级提高，使用ProxyFactoryBean的时候，IOC同时存在被代理对象和代理对象，
     * 而使用 AspectJ等其它方式在bean定义阶段声明的aop，被代理对象不加入IOC，存在于IOC的是代理对象
     *
     * @param loggerInterceptor
     * @return
     */
    @Primary
    @Bean("proxyImplInterface")
    public ProxyFactoryBean proxyFactoryBean(@Qualifier("proxyFactoryBeanLoggerInterceptor") @Autowired LoggerInterceptor loggerInterceptor) {

        ProxyFactoryBean bean = new ProxyFactoryBean();
        // 代理目标bean名称
        bean.setTargetName("proxyFactoryBeanSomeService");
        bean.addAdvice(loggerInterceptor);
        return bean;
    }

    /**
     * for test mock bean
     */
    @Bean("proxyFactoryBeanLoggerInterceptor")
    public LoggerInterceptor loggerInterceptor() {

        return new LoggerInterceptor();
    }
}
