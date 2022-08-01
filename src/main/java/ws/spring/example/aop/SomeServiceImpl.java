package ws.spring.example.aop;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

public class SomeServiceImpl implements SomeService {

    @Override
    public String findUsername(String userId) {
        return userId + " - username - SomeServiceImpl";
    }
}
