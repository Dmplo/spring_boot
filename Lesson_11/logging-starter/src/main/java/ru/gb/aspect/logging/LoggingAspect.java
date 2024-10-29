package ru.gb.aspect.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProperties properties;

    @Pointcut("@annotation(ru.gb.aspect.logging.Logging)") // method
    public void loggingMethodsPointcut() {
    }

    @Pointcut("@within(ru.gb.aspect.logging.Logging)") // class
    public void loggingTypePointcut() {
    }

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
        try {
            if (properties.printArgs) {
                Object[] args = pjp.getArgs();
                if (args.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Method#").append(methodName).append(" has ").append(args.length).append(" args:").append("\n");
                    for (int i = 0; i < args.length; i++) {
                        sb.append("Arg#").append(i+1).append(" -> ").append(args[i]).append("\n");
                    }
                    log.atLevel(properties.getLevel()).log(sb.toString());
                } else {
                    log.atLevel(properties.getLevel()).log("Method#{} has no args", methodName);
                }

            }
            return pjp.proceed();
        } finally {
            log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
        }
    }

}
