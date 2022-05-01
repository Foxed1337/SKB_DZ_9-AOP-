package ru.zenkov.skb_dz_9.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServerTimeMaxCallsAspect {
    @Value("${max_calls_to_server_time_api}")
    private int MAX_CALLS;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int callsCounter;

    @Around("execution(* ru.zenkov.skb_dz_9.controllers.TimeController.getServerTime())")
    public Object serverTimeAccessControl(ProceedingJoinPoint pjp) {

        if (callsCounter < MAX_CALLS) {
            try {
                logger.info("Count Of \"getServerTime\" calls equals " + ++callsCounter);
                return pjp.proceed();
            } catch (Throwable e) {
                logger.error(e.getMessage());
            }
        }

        logger.info("The Count Of \"getServerTime\" calls has reached the maximum value. Max calls count is {}", MAX_CALLS);

        return null;
    }
}
