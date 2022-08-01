package ws.spring.example.aop;

import java.lang.annotation.*;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReturnValue {

    /** 作用在方法上代表预期返回值，模拟方法注入 */
    String value();
}
