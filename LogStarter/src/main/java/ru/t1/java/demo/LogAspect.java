package ru.t1.java.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final LogProperties logProperties;
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    public LogAspect(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @Around("@annotation(LogAround)")
    public Object logRequestMapping(ProceedingJoinPoint joinPoint) throws Throwable {

        if (logProperties == null || !logProperties.getEnable()) {
            System.out.println("Логирование отключено: http.log.enable = false");
            return joinPoint.proceed();
        }

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logBeforeRequest(joinPoint);

        Object result = joinPoint.proceed();

        logAfterRequest(className, methodName, result);

        return result;
    }

    private void logBeforeRequest(ProceedingJoinPoint joinPoint) {
        logMessage("Запрос к " + joinPoint.getSignature().getName() + " c телом " + Arrays.toString(joinPoint.getArgs()));
    }

    private void logAfterRequest(String className, String methodName, Object result) {
        logMessage(String.format("Ответ от %s %s: %s", className, methodName, result));
    }

    private void logMessage(String message) {
        switch (logProperties.getLevel().toLowerCase()) {
            case "info":
                logger.info("INFO :{}", message);
                break;
            case "debug":
                logger.debug("DEBUG :{}", message);
                break;
            case "warn":
                logger.warn("WARN :{}", message);
                break;
            case "error":
                logger.error("ERROR :{}", message);
                break;
            default:
                logger.info("лог :{}", message);
        }
    }
}