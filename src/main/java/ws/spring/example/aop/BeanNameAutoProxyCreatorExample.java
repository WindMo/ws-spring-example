package ws.spring.example.aop;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

@Configuration(proxyBeanMethods = false)
public class BeanNameAutoProxyCreatorExample {

    @Bean("beanNameAutoProxyCreatorSomeService")
    public SomeService someService() {

        return new SomeServiceImpl();
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {

        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        // 指定被代理的bean
        creator.setBeanNames("beanNameAutoProxyCreatorSomeService");
        creator.setInterceptorNames("beanNameAutoProxyCreatorInterceptor");
        return creator;
    }

    @Bean("beanNameAutoProxyCreatorInterceptor")
    public LoggerInterceptor loggerInterceptor() {

        return new LoggerInterceptor();
    }
}
