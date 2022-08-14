package ws.spring.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class WsSpringExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsSpringExampleApplication.class, args);
    }

}
