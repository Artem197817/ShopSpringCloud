package gb.shop.shop.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {


    @AfterReturning(value = "@annotation(LogAs)", returning = "returnedValue")
    public void log(Object returnedValue) {
        System.out.println("Method executed and returned " + returnedValue);
    }
}
