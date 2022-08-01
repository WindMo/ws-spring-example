package ws.spring.example.aop;

/**
 * @author WindShadow
 * @version 2022-07-31.
 */

public interface SomeService {

    @ReturnValue("abcdefg")
    String findUsername(String userId);
}
