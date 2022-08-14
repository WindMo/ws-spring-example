package ws.spring.example.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import ws.spring.example.WsSpringExampleApplicationTests;

/**
 * @author WindShadow
 * @version 2022-08-14.
 * @see AspectJComponent
 */

@Import(AspectJComponent.class)
public class AspectJTests extends WsSpringExampleApplicationTests {

    @Qualifier("defaultSomeService")
    @Autowired
    private SomeService service;

    @Test
    void apectJAopTest() throws Throwable {

        Assertions.assertTrue(AopUtils.isAopProxy(service));
        service.findUsername("123456");
    }
}
