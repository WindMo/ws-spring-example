package ws.spring.example.aop;

import org.springframework.stereotype.Component;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

@Component("defaultSomeService")
public class SomeServiceImpl implements SomeService {

    @Override
    public String findUsername(String userId) {
        return userId + " - username - SomeServiceImpl";
    }
}
